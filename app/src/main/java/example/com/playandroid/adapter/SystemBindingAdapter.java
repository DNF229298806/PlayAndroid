package example.com.playandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseBindingAdapter;
import example.com.playandroid.base.BindingViewHolder;
import example.com.playandroid.content.system.SystemEntity;
import example.com.playandroid.databinding.HolderSystemBinding;

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
        BindingViewHolder<HolderSystemBinding> holder = super.onCreateViewHolder(parent, viewType);

       /* while (binding.getEntity().getChildren() != null) {
            TextView textView = new TextView(parent.getContext());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(5, 5, 5, 5);
            textView.setText(binding.getEntity().getChildren());
        }*/
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<HolderSystemBinding> holder, int position) {
        super.onBindViewHolder(holder, position);
        HolderSystemBinding binding = holder.getBinding();
        if (binding.getEntity()!=null&&binding.getEntity().getChildren() != null&&binding.getEntity().getChildren().size()!=0) {
            for (SystemEntity systemEntity : binding.getEntity().getChildren()) {
                TextView textView = new TextView(context);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(params);
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(5, 5, 5, 5);
                textView.setText(systemEntity.getName());
                binding.flex.addView(textView);
            }
        }
        ViewGroup.LayoutParams lp =  holder.getBinding().getRoot().getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp =
                    (FlexboxLayoutManager.LayoutParams)  holder.getBinding().getRoot().getLayoutParams();
            flexboxLp.setFlexGrow(1.0f);
        }


    }
}
