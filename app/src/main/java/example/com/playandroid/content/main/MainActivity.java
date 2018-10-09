package example.com.playandroid.content.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.content.home.HomeFragment;
import example.com.playandroid.content.navigation.NavigationFragment;
import example.com.playandroid.content.project.ProjectFragment;
import example.com.playandroid.content.system.SystemFragment;
import example.com.playandroid.databinding.ActivityMainBinding;
import example.com.playandroid.util.BottomNavigationViewHelper;

import static example.com.playandroid.util.Constant.FragmentType.HOME;
import static example.com.playandroid.util.Constant.FragmentType.PROJECT;
import static example.com.playandroid.util.Constant.FragmentType.SYSTEM;

public class MainActivity extends BaseActivity<MainModel,ActivityMainBinding>/*extends BaseActivity<MainModel,ActivityMainBinding>*/{
    private HomeFragment mHomeFragment;
    private ProjectFragment mProjectFragment;
    private SystemFragment mSystemFragment;
    private NavigationFragment mNavigationFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置model 标题栏 还有底部的导航
        setModel(new MainModel(this));
        setSupportActionBar(getBinding().toolbar);
        BottomNavigationViewHelper.disableShiftMode(getBinding().bnv);
        /*mModel = new MainModel(this,getResources());
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setVm(mModel);
        setSupportActionBar(mBinding.toolbar);*/
        //FragmentManager fm = getSupportFragmentManager();
       /* Fragment fragment = fm.findFragmentById(R.id.frame_layout);
        if (fragment == null) {
            fragment = new HomeFragment();
            fm.beginTransaction().add(R.id.frame_layout, fragment).commit();
        }*/
       initFragment(mHomeFragment,HOME);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    protected void initFragment(Fragment fragment,int type) {
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
        /*mBinding.bnv.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.it_home:
                    ToastUtils.showLong("home");
                    return true;
                case R.id.it_project:
                    ToastUtils.showLong("it_project");
                    return true;
                case R.id.it_system:
                    ToastUtils.showLong("it_system");
                    return true;
                case R.id.it_nav:
                    ToastUtils.showLong("it_nav");
                    return true;
                default:
                    break;
            }
            return false;
        });*/
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
        if (mNavigationFragment!=null) {
            transaction.hide(mNavigationFragment);
        }
    }


/*Banner banner = mBinding.banner;
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        List<Integer> list = new ArrayList();
        list.add(R.mipmap.timg);
        list.add(R.mipmap.timg1);
        list.add(R.mipmap.timg2);
        list.add(R.mipmap.timg3);
        List<String> list1 = new ArrayList<>();
        list1.add("111111");
        list1.add("2222222");
        list1.add("3333333333");
        list1.add("4444444");

        List<String> list2 = new ArrayList<>();
        list2.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537979001696&di=2a5d0e45641b0008b13a810160937583&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3D108cdf6f5766d0167e4c962ca21bf831%2F95eef01f3a292df5c9a9f0b7b9315c6034a8732d.jpg");
        list2.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537979001696&di=c4df4c11f5b631e58937c6cf6529b228&imgtype=0&src=http%3A%2F%2Fi2.7414.cn%2Fb%2F105168.jpg");
        list2.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537979001696&di=362ab4a8870517e4b23296286e9b7cf2&imgtype=0&src=http%3A%2F%2Fuploads.5068.com%2Fallimg%2F1801%2F85-1P11GA454-51.jpg");
        list2.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1537979001696&di=5d4d6b614c942c866a35ac7dfef54339&imgtype=0&src=http%3A%2F%2Fgss0.baidu.com%2F-Po3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2F5bafa40f4bfbfbed782a1a987ff0f736aec31fe1.jpg");
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(list1);
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        banner.setOnBannerListener(position -> {
            switch (position) {
                case 0:
                    ToastUtils.showLong("111111111");
                    break;
                case 1:
                    ToastUtils.showLong("2222222222222222");
                    break;
                case 2:
                    ToastUtils.showLong("3333333333333333");
                    break;
                case 3:
                    ToastUtils.showLong("444444444444444");
                    break;
                default:
                    break;
            }
        });
        banner.setImages(list2);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.start();*/

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
}