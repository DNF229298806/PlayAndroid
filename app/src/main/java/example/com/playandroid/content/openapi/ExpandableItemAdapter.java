package example.com.playandroid.content.openapi;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import example.com.playandroid.R;
import example.com.playandroid.databinding.ItemOpenapiChildBinding;
import example.com.playandroid.databinding.ItemOpenapiHeadBinding;

import static example.com.playandroid.constant.Constant.TitleType.TYPE_LEVEL_0;
import static example.com.playandroid.constant.Constant.TitleType.TYPE_PERSON;

/**
 * @author admin
 * @des 2018/12/21
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_openapi_head);
        addItemType(TYPE_PERSON, R.layout.item_openapi_child);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding;
        if (layoutResId == R.layout.item_openapi_head) {
            ItemOpenapiHeadBinding b1 = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
            binding = b1;
        } else if (layoutResId == R.layout.item_openapi_child) {
            ItemOpenapiChildBinding b2 = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
            binding = b2;
        } else {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    @Override
    protected void convert(BaseViewHolder holder, MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final OpenApiEntity lv0 = (OpenApiEntity) item;
                ItemOpenapiHeadBinding binding = (ItemOpenapiHeadBinding) holder.itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
                binding.setTitle(lv0);
                holder.setImageResource(R.id.iv, lv0.isExpanded() ? R.mipmap.icon_navigation_down : R.mipmap.icon_navigation_right);
                holder.itemView.setOnClickListener(v -> {
                    int pos = holder.getAdapterPosition();
                    Log.d(TAG, "Level 0 item pos: " + pos);
                    if (lv0.isExpanded()) {
                        collapse(pos);
                    } else {
                        expand(pos);
                    }
                });
                break;
            case TYPE_PERSON:
                final ContentEntity article = (ContentEntity) item;
                ItemOpenapiChildBinding binding1 = (ItemOpenapiChildBinding) holder.itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
                binding1.setArticle(article);
                break;
            default:
                break;
        }
    }
}
