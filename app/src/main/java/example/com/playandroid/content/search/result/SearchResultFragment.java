package example.com.playandroid.content.search.result;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseFragment;
import example.com.playandroid.content.search.SearchActivity;
import example.com.playandroid.databinding.FragmentSearchResultBinding;

/**
 * @author Richard_Y_Wang
 * @date 2019/3/18 17:00
 */
public class SearchResultFragment extends BaseFragment<SearchActivity, SearchResultModel, FragmentSearchResultBinding> {
    public static Fragment newInstance(String keyword) {
        Bundle args = new Bundle();
        args.putString("QUERY", keyword);
        SearchResultFragment fragment = new SearchResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setModel(new SearchResultModel((SearchActivity) getActivity(), this));
        return getBinding().getRoot();
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_search_result;
    }
}
