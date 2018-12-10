package example.com.playandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

import example.com.playandroid.App;
import example.com.playandroid.R;
import example.com.playandroid.base.BaseBindingAdapter;
import example.com.playandroid.base.BindingViewHolder;
import example.com.playandroid.content.system.SystemEntity;
import example.com.playandroid.databinding.HolderSystemBinding;
import timber.log.Timber;

/**
 * @author admin
 * @des 2018/12/10
 */
public class SystemBindingAdapter extends BaseBindingAdapter<SystemEntity, HolderSystemBinding> {

    public SystemBindingAdapter(List<SystemEntity> data, Context context) {
        super(data, context);
    }

    @Override
    public int setLayout() {
        return R.layout.holder_system;
    }

    @NonNull
    @Override
    public BindingViewHolder<HolderSystemBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<HolderSystemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        getData().get(position);
        /*HolderSystemBinding binding = holder.getBinding();
        if (binding.getEntity() != null && binding.getEntity().getChildren() != null && binding.getEntity().getChildren().size() != 0) {
            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            for (SystemEntity systemEntity : binding.getEntity().getChildren()) {
                TextView textView = new TextView(context);
                textView.setPadding(20, 20, 20, 20);
                textView.setLayoutParams(params);
                textView.setGravity(Gravity.CENTER);
                textView.setText(systemEntity.getName());
                textView.setBackground(App.getResDrawable(R.drawable.rectangle_frame));
                binding.flex.addView(textView);
            }
        }*/
        if (getData().get(position).getChildren() != null) {
            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            holder.getBinding().flex.removeAllViews();
            for (SystemEntity systemEntity : getData().get(position).getChildren()) {
                TextView textView = new TextView(context);
                textView.setPadding(20, 20, 20, 20);
                textView.setLayoutParams(params);
                textView.setGravity(Gravity.CENTER);
                textView.setText(systemEntity.getName());
                textView.setBackground(App.getResDrawable(R.drawable.rectangle_frame));
                holder.getBinding().flex.addView(textView);
                //binding.flex.addView(textView);
            }
        }

        Timber.i("now position=%s", position);
       /* ViewGroup.LayoutParams lp =  holder.getBinding().getRoot().getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp =
                    (FlexboxLayoutManager.LayoutParams)  holder.getBinding().getRoot().getLayoutParams();
            flexboxLp.setFlexGrow(1.0f);
        }*/


    }


}
