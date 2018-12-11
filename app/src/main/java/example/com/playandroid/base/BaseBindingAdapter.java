package example.com.playandroid.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import example.com.playandroid.BR;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/14 20:43
 */
public abstract class BaseBindingAdapter<K extends BaseEntity,T extends ViewDataBinding> extends RecyclerView.Adapter<BindingViewHolder<T>> {
    /*public class BaseBindingAdapter extends RecyclerView.Adapter<BindingViewHolder<TestItemBinding>> {*/
    private List<K> data;
    protected Context context;
    public BaseBindingAdapter(List<K> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public BindingViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        T binding = DataBindingUtil.inflate(LayoutInflater.from(context), setLayout(), parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<T> holder, int position) {
        holder.getBinding().setVariable(BR.entity,data.get(position));
        // 立刻刷新界面
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(List<K> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<K> getData() {
        return data;
    }

    public void addList(List<K> items) {
        addList(getItemCount(),items);
    }

    public void addList(int position, List<K> items) {
        if (items != null && (items.size() != 0) && data != null) {
            data.addAll(position,items);
            notifyDataSetChanged();
        }
    }

    public void addItem(K item,int position) {
        if (item != null && data != null) {
            data.add(position, item);
            notifyDataSetChanged();
        }
    }

    public void removeItem(K item) {
        data.remove(item);
        notifyDataSetChanged();
    }

    public void clear() {
        if (data != null) {
            data.clear();
            notifyDataSetChanged();
        }
    }

    public abstract @LayoutRes int setLayout();

}
