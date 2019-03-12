package example.com.playandroid.content.main;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yalantis.ucrop.UCrop;
import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import example.com.playandroid.App;
import example.com.playandroid.R;
import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.content.home.HomeFragment;
import example.com.playandroid.content.navigation.NavigationFragment;
import example.com.playandroid.content.project.ProjectFragment;
import example.com.playandroid.content.system.SystemFragment;
import example.com.playandroid.databinding.ActivityMainBinding;
import example.com.playandroid.util.DogUtil;
import example.com.playandroid.util.StatusBarUtil;
import timber.log.Timber;

import static example.com.playandroid.constant.Constant.FragmentType.HOME;
import static example.com.playandroid.constant.Constant.FragmentType.PROJECT;
import static example.com.playandroid.constant.Constant.FragmentType.SYSTEM;

@Route(path = Constant.ActivityPath.MainActivity)
public class MainActivity extends BaseActivity<MainModel, ActivityMainBinding>/*extends BaseActivity<MainModel,ActivityMainBinding>*/ {
    private HomeFragment mHomeFragment;
    private ProjectFragment mProjectFragment;
    private SystemFragment mSystemFragment;
    private NavigationFragment mNavigationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        long l = System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        long l1 = System.currentTimeMillis();
        Timber.i("super=%s", (l1 - l));
        //设置model 标题栏 还有底部的导航
        setModel(new MainModel(this));
        setSupportActionBar(getBinding().toolbar);
        StatusBarUtil.setStatusBarColor(this, R.color.colorPrimary);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            //解决NavigationView这边点击按钮以后无法变色的问题
            ColorStateList cs = getBaseContext().getResources().getColorStateList(R.color.nav_menu_text_color, null);
            getBinding().navigationView.setItemTextColor(cs);
            getBinding().navigationView.setItemIconTintList(cs);
        }
        initFragment(mHomeFragment, HOME);
        long l2 = System.currentTimeMillis();
        Timber.i("Main=%s", (l2 - l1));
      /*  DogUtil.checkPermissionOneByOne(this, (permission -> {
            if (permission.granted) {
                ToastUtils.showShort("请求全部成功");
            } else if (permission.shouldShowRequestPermissionRationale) {
                ToastUtils.showShort("拒绝许可 不再询问 ");
            }else{
                ToastUtils.showShort("进入设置？？？？？？？？");
            }
        }), Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)*/
        ;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    protected void initFragment(Fragment fragment, int type) {
        //记录当前的fragment
        Fragment checkedFragment;

        //1.开启事务,fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //2.第一种方式(add) 初始化fragment并添加到事务中,如果null就new一个
        if (fragment == null) {
            if (type == HOME) {
                mHomeFragment = new HomeFragment();
                checkedFragment = mHomeFragment;
            } else if (type == PROJECT) {
                mProjectFragment = new ProjectFragment();
                checkedFragment = mProjectFragment;
            } else if (type == SYSTEM) {
                mSystemFragment = new SystemFragment();
                checkedFragment = mSystemFragment;
            } else {
                mNavigationFragment = new NavigationFragment();
                checkedFragment = mNavigationFragment;
            }
            //若当前选中碎片未添加到事务中 则创建碎片对象并添加到事务中
            transaction.add(R.id.frame_layout, checkedFragment);
        } else {
            //若当前选中碎片已经添加到事务中,则将当前碎片赋值给checkedFragment
            checkedFragment = fragment;
        }

        //3.隐藏所有fragment
        hideFragment(transaction);

        //4.显示需要的fragment
        transaction.show(checkedFragment);

        //5.提交事务
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mProjectFragment != null) {
            transaction.hide(mProjectFragment);
        }
        if (mSystemFragment != null) {
            transaction.hide(mSystemFragment);
        }
        if (mNavigationFragment != null) {
            transaction.hide(mNavigationFragment);
        }
    }

    public HomeFragment getHomeFragment() {
        return mHomeFragment;
    }

    public ProjectFragment getProjectFragment() {
        return mProjectFragment;
    }

    public SystemFragment getSystemFragment() {
        return mSystemFragment;
    }

    public NavigationFragment getNavigationFragment() {
        return mNavigationFragment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        DogUtil.choosePictureCallBack(requestCode, resultCode, data, () -> {
            List<Uri> uris = Matisse.obtainResult(data);
            Timber.d("uris:" + uris);
            UCrop.Options options = new UCrop.Options();
            options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
            options.setCompressionQuality(100);
            /*“缩放”，“旋转”，“裁剪”*/
            //options.setAllowedGestures(UCropActivity.ALL,UCropActivity.NONE,UCropActivity.NONE);
            //设置是否展示矩形裁剪框
            options.setShowCropFrame(false);
            //设置圆形切片
            options.setCircleDimmedLayer(true);
            //Toolbar颜色
            options.setToolbarColor(App.getResColor(R.color.colorPrimary));
            //状态栏颜色
            options.setStatusBarColor(App.getResColor(R.color.colorPrimaryDark));
            //设置竖线的数量
            options.setCropGridColumnCount(0);
            //设置横线的数量
            options.setCropGridRowCount(0);
            //options.setShowCropFrame(true);
            UCrop ucrop = UCrop.of(uris.get(0), Uri.fromFile(new File(getCacheDir(), "SampleCropImage.jpeg")));
            ucrop.withOptions(options);
            ucrop.start(this);
        }, new DogUtil.CropPictureCallBack() {
            @Override
            public void cropSuccess() {
                  //裁剪以后的效果
                Uri resultUri = UCrop.getOutput(data);

                CircleImageView imHead = getBinding().navigationView.getHeaderView(0).findViewById(R.id.iv_head);
              /*  Glide.with(MainActivity.this)
                        .load(resultUri)
                        //Glide中的图片缓存key的生成是通过一个散列算法来实现的，所以很难手动去删除指定的图片缓存
                        //Glide的图片缓存都有对应的唯一标识符，如果是相同的，就不加载调用缓存
                        //不过改变标识符很困难，所以Glide提供signature()方法，来附加一个数据到缓存key中如果链接是文件，就用StringSignature，
                        //比如.signature(nre StringSignature(yourVersionMetadata)).
                        //如果链接是多媒体，就用MediaStoreSignature，
                        //比如.signature(new MediaStoreSignature(mimeType, dateModified, orientation)).
                        //.signature(new StringSignature(UUID.randomUUID().toString()))
                        .into((CircleImageView) imHead);*/
                runOnUiThread(() -> imHead.setImageBitmap(BitmapFactory.decodeFile(resultUri.getPath())));
            }

            @Override
            public void cropFailed() {

            }
        });
    }
}