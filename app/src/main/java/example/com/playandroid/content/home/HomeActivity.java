package example.com.playandroid.content.home;

import android.os.Bundle;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*mModel = new HomeModel(this,getResources());
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mBinding.setVm(mModel);
        setSupportActionBar(mBinding.toolbar);
        Banner banner = mBinding.banner;
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
    }

    @Override
    public int setLayout() {
        return R.layout.activity_home;
    }
}
