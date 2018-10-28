package example.com.playandroid.base.test;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import example.com.playandroid.BR;
import example.com.playandroid.base.Mult;

/**
 * @author admin
 * @des 2018/10/26
 */
public class BindingAdapter extends RecyclerView.Adapter<BindingAdapter.BindingHolder>{
    protected List<Mult> data = new ArrayList<>();

    /**
     * @return 返回的是adapter的view
     */
    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new BindingHolder(binding);
    }

    public List<Mult> getList() {
        return data;
    }

    public void setList(List<Mult> items) {
        data = items;
    }

    public void addList(List<Mult> addItems) {
        addList(getItemCount(),addItems);
    }

    public void addList(int position, List<Mult> addItems) {
        if (addItems != null && (addItems.size() != 0) && data != null) {
            data.addAll(position,addItems);
            notifyDataSetChanged();
        }
    }

    public void addItem(Mult addItems,int position) {
        if (addItems != null && data != null) {
            data.add(position, addItems);
            notifyDataSetChanged();
        }
    }

    /*
     * 数据绑定
     * */
    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        holder.bindData(data.get(position));
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    @Override
    public int getItemViewType(int position) {
        return data.get(position).getViewType();
    }


   protected static class BindingHolder extends RecyclerView.ViewHolder {

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
