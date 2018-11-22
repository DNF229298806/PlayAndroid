package example.com.playandroid.util;

import com.blankj.utilcode.util.SPUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.lang.reflect.Field;
import java.util.Set;

import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.base.BaseFragment;
import io.reactivex.functions.Consumer;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/26 21:17
 */
public class DogUtil {


    /**
     * 使用反射来遍历这个entity中所有的成员变量，并循环调用putvalue方法 对成员变量进行保存
     * @param obj  需要保存到SP里的对象
     * @param sp   SP对象
     */
    public static void saveToSpByReflect(Object obj,SPUtils sp) {
        try {
            //2.获得某个类的全部属性
            Field[] fields = obj.getClass().getDeclaredFields();
            //3.取消权限的访问控制 为了能够访问私有属性  不然会抛出IllegalAccessException异常
            //循环写入
            for (Field fd : fields) {
                fd.setAccessible(true);
                putValue(fd.getName(),fd.get(obj),sp);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

   /* public static void getFromSpByReflect(Object obj,SPUtils sp) {

    }*/

    /**
     * 判断传入的value值进行保存
     * @param key       SP的KEY值
     * @param value     SP的VALUE值
     * @param sp        SP对象
     */
    public static void putValue(String key, Object value,SPUtils sp) {
        if (value == null) return;
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
