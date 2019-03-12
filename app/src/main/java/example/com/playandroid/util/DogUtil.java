package example.com.playandroid.util;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import com.blankj.utilcode.util.SPUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yalantis.ucrop.UCrop;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.base.BaseFragment;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;
import static example.com.playandroid.constant.Constant.REQUEST_CODE_CHOOSE;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/26 21:17
 */
public class DogUtil {


    /**
     * 使用反射来遍历这个entity中所有的成员变量，并循环调用putvalue方法 对成员变量进行保存
     *
     * @param obj 需要保存到SP里的对象
     * @param sp  SP对象
     */
    public static void saveToSpByReflect(Object obj, SPUtils sp) {
        try {
            //2.获得某个类的全部属性
            Field[] fields = obj.getClass().getDeclaredFields();
            //3.取消权限的访问控制 为了能够访问私有属性  不然会抛出IllegalAccessException异常
            //循环写入
            for (Field fd : fields) {
                fd.setAccessible(true);
                putValue(fd.getName(), fd.get(obj), sp);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

   /* public static void getFromSpByReflect(Object obj,SPUtils sp) {

    }*/

    /**
     * 判断传入的value值进行保存
     *
     * @param key   SP的KEY值
     * @param value SP的VALUE值
     * @param sp    SP对象
     */
    public static void putValue(String key, Object value, SPUtils sp) {
        if (value == null)
            return;
        if (value instanceof String) {
            sp.put(key, (String) value);
        } else if (value instanceof Boolean) {
            sp.put(key, (Boolean) value);
        } else if (value instanceof Float) {
            sp.put(key, (Float) value);
        } else if (value instanceof Integer) {
            sp.put(key, (Integer) value);
        } else if (value instanceof Long) {
            sp.put(key, (Long) value);
        } else if (value instanceof Set) {
            sp.put(key, (Set<String>) value);
        }
    }

    /*private void getValue(String key, Class<?> clazz, SharedPreferences share) {
        if (clazz == String.class) {
            return (E) share.getString(key, "");
        } else if (clazz == int.class || clazz == Integer.class) {
            Integer result = share.getInt(key, 0);
            return (E) result;
        } else if (clazz == long.class || clazz == Long.class) {
            Long result = share.getLong(key, 0);
            return (E) result;
        } else if (clazz == boolean.class || clazz == Boolean.class) {
            Boolean result = share.getBoolean(key, false);
            return (E) result;
        } else if (clazz == float.class || clazz == Float.class) {
            Float result = share.getFloat(key, 0);
            return (E) result;
        } else if (clazz == Set.class) {
            Set<String> result = share.getStringSet(key, null);
            return (E) result;
        }
        return null;
    }*/

    public static void checkPermission(BaseActivity activity, Consumer<Boolean> onNext, String... permissions) {
        activity.getModel().addDisposable(
                new RxPermissions(activity)
                        .request(permissions)
                        .subscribe(onNext, Throwable::printStackTrace));
    }

    public static void checkPermissionOneByOne(BaseActivity activity, Consumer<Permission> onNext, String... permissions) {
        activity.getModel().addDisposable(
                new RxPermissions(activity)
                        .requestEach(permissions)
                        .subscribe(onNext, Throwable::printStackTrace));
    }

    public static void checkPermission(BaseFragment fragment, Consumer<Boolean> onNext, String... permissions) {
        fragment.getModel().addDisposable(
                new RxPermissions(fragment)
                        .request(permissions)
                        .subscribe(onNext, Throwable::printStackTrace));
    }

    public static void checkPermissionOneByOne(BaseFragment fragment, Consumer<Permission> onNext, String... permissions) {
        fragment.getModel().addDisposable(
                new RxPermissions(fragment)
                        .requestEach(permissions)
                        .subscribe(onNext, Throwable::printStackTrace));
    }

    public static void choosePicture(BaseFragment fragment, boolean isNeedCamera) {
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (isNeedCamera) {
            permissions.add(Manifest.permission.CAMERA);
        }
        String[] arr_permissions = new String[permissions.size()];
        checkPermission(fragment, aBoolean -> {
            if (aBoolean) {
                startMatisse(fragment);
            }
        }, permissions.toArray(arr_permissions));
    }

    public static void choosePicture(BaseActivity activity, boolean isNeedCamera) {
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (isNeedCamera) {
            permissions.add(Manifest.permission.CAMERA);
        }
        String[] arr_permissions = new String[permissions.size()];
        checkPermission(activity, aBoolean -> {
            if (aBoolean) {
                startMatisse(activity);
            }
        }, permissions.toArray(arr_permissions));
    }

    private static void startMatisse(BaseFragment fragment) {
        Matisse.from(fragment)
                //照片视频全部显示
                .choose(MimeType.ofImage())
                //有序选择图片
                .countable(true)
                .maxSelectable(1)
                .gridExpectedSize(300)
                .capture(true)
                .captureStrategy(new CaptureStrategy(true, "example.com.playandroid.fileprovider"))
                //图像选择和预览活动所需的方向
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                //缩放比例
                .thumbnailScale(0.85f)
                //主题 暗色主题
                .theme(R.style.Matisse_Zhihu)
                //加载方式 原来的GlideEngine 在v4以后要改成Glide4Engine
                .imageEngine(new Glide4Engine())
                .forResult(REQUEST_CODE_CHOOSE);
    }

    private static void startMatisse(BaseActivity activity) {
        Matisse.from(activity)
                //照片视频全部显示
                .choose(MimeType.ofImage())
                //有序选择图片
                .countable(true)
                .maxSelectable(1)
                .gridExpectedSize(300)
                .capture(true)
                .captureStrategy(new CaptureStrategy(true, "example.com.playandroid.fileprovider"))
                //图像选择和预览活动所需的方向
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                //缩放比例
                .thumbnailScale(0.85f)
                //主题 暗色主题
                .theme(R.style.Matisse_Zhihu)
                //加载方式
                .imageEngine(new Glide4Engine())
                .forResult(REQUEST_CODE_CHOOSE);
    }

    public interface CropPictureCallBack {
        void cropSuccess();
        void cropFailed();
    }

    public interface ZHIHUCallBack{
        void success();
    }

    public static void choosePictureCallBack(int requestCode, int resultCode, Intent data,ZHIHUCallBack zhihu,CropPictureCallBack callBack) {
        if (resultCode == RESULT_OK) {
            if (requestCode == UCrop.REQUEST_CROP) {
                callBack.cropSuccess();
                Timber.d("走了裁剪的回调 而且裁剪成功");
                return;
            }
            if (requestCode == UCrop.RESULT_ERROR) {
                Timber.d("头像裁剪失败");
                callBack.cropFailed();
                return;
            }
            if (requestCode == REQUEST_CODE_CHOOSE) {
                zhihu.success();
                Timber.d("走了知乎的回调");
            }
        }
    }

    public static void choosePictureCallBack(int requestCode, int resultCode, Intent data, ZHIHUCallBack zhihu) {
        if (resultCode == RESULT_OK&&requestCode == REQUEST_CODE_CHOOSE) {
            zhihu.success();
            Timber.d("走了知乎的回调");
        }
    }


   /*
   If multiple permissions at the same time, the result is combined :

rxPermissions
    .request(Manifest.permission.CAMERA,
             Manifest.permission.READ_PHONE_STATE)
    .subscribe(granted -> {
        if (granted) {
           // All requested permissions are granted
        } else {
           // At least one permission is denied
        }
    });
You can also observe a detailed result with requestEach or ensureEach :

rxPermissions
    .requestEach(Manifest.permission.CAMERA,
             Manifest.permission.READ_PHONE_STATE)
    .subscribe(permission -> { // will emit 2 Permission objects
        if (permission.granted) {
           // `permission.name` is granted !
        } else if (permission.shouldShowRequestPermissionRationale) {
           // Denied permission without ask never again
        } else {
           // Denied permission with ask never again
           // Need to go to the settings
        }
    });




    */
}
