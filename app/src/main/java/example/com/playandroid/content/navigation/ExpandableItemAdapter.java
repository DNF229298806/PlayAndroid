package example.com.playandroid.content.navigation;

import android.util.Log;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import example.com.playandroid.R;

/**
 * @author admin
 * @des 2018/12/14
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_PERSON = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.itme_navigation_head);
        addItemType(TYPE_PERSON, R.layout.itme_navigation_child);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final Level0Item lv0 = (Level0Item) item;
                holder.setText(R.id.tv_head, lv0.title);
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
                final Person person = (Person) item;
                holder.setText(R.id.tv_child, person.name + " parent pos: " + getParentPosition(person));
                holder.itemView.setOnClickListener(view -> {
                    int pos = holder.getAdapterPosition();
                    Log.d(TAG, "Person item pos: " + pos);
                    remove(pos);
                });
                break;
            default:
                break;
        }
    }
}
