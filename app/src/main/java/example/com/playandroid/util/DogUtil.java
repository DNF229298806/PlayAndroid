package example.com.playandroid.util;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.base.BaseFragment;
import io.reactivex.functions.Consumer;

/**
 * @author Richard_Y_Wang
 * @des 2018/9/26 21:17
 */
public class DogUtil {
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
