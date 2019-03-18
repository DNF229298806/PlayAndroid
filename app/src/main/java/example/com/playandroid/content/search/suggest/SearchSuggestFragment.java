package example.com.playandroid.content.search.suggest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseFragment;
import example.com.playandroid.content.search.SearchActivity;
import example.com.playandroid.databinding.FragmentSearchSuggestBinding;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/18 16:06
 */
public class SearchSuggestFragment extends BaseFragment<SearchActivity,SearchSuggestModel, FragmentSearchSuggestBinding> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setModel(new SearchSuggestModel((SearchActivity) getActivity(), this));
        return getBinding().getRoot();
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_search_suggest;
    }
}
