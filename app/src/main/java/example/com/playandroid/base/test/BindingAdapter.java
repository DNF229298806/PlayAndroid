package example.com.playandroid.base.test;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import example.com.playandroid.App;
import example.com.playandroid.BR;
import example.com.playandroid.base.Mult;
import example.com.playandroid.content.home.net.BannerEntity;
import example.com.playandroid.databinding.HolderBannerBinding;
import example.com.playandroid.network.transform.RestfulTransformer;
import example.com.playandroid.util.GlideImageLoader;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author admin
 * @des 2018/10/26
 */
public class BindingAdapter extends RecyclerView.Adapter<BindingAdapter.BindingHolder>{
    public List<Mult> getItems() {
        return items;
    }

    public void setItems(List<Mult> items) {
        this.items = items;
    }
    private List<Mult> items = new ArrayList<>();

    /**
     * @return 返回的是adapter的view
     */
    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        if (binding instanceof HolderBannerBinding) {
            Banner banner = ((HolderBannerBinding) binding).banner;
            App.api.getBannerEntity()
                    .compose(new RestfulTransformer<>())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(bannerEntities -> {
                        List<String> titleList = new ArrayList<>();
                        List imagesList = new ArrayList<>();
                        for (BannerEntity bannerEntity : bannerEntities) {
                            titleList.add(bannerEntity.getTitle());
                            imagesList.add(bannerEntity.getImagePath());
                        }
                        banner.setImageLoader(new GlideImageLoader());
                        banner.setBannerAnimation(Transformer.Default);
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
                        banner.setImages(imagesList);
                        banner.setBannerTitles(titleList);
                        banner.start();
                    }, a -> {
                        a.printStackTrace();
                        ToastUtils.showLong("获取数据失败");
                    });
        }
        return new BindingHolder(binding);
    }



    /*
     * 数据绑定
     * */
    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        holder.bindData(items.get(position));
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }


    static class BindingHolder extends RecyclerView.ViewHolder {

        ViewDataBinding binding;
        /**
         * @param binding   可以看作是这个hodler代表的布局的马甲，getRoot()方法会返回整个holder的最顶层的view
         * */
        public BindingHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(Mult item) {
            item.setDataBinding(binding);
            binding.setVariable(BR.entity,item);
        }
    }

}
