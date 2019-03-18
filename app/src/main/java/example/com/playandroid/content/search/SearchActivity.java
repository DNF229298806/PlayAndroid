package example.com.playandroid.content.search;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseActivity;
import example.com.playandroid.content.search.result.SearchResultFragment;
import example.com.playandroid.content.search.suggest.SearchSuggestFragment;
import example.com.playandroid.databinding.ActivitySearchBinding;

public class SearchActivity extends BaseActivity<SearchModel, ActivitySearchBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setModel(new SearchModel(this));
        setSupportActionBar(getBinding().toolbar);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new SearchSuggestFragment())
                .commit();
    }

    @Override
    public int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        //设置searchView颜色与样式
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        final EditText editText = (EditText)searchView.findViewById(R.id.search_src_text);
        final ImageView im = (ImageView) searchView.findViewById(R.id.search_close_btn);
        im.setColorFilter(ContextCompat.getColor(this,R.color.md_white_1000));
        editText.setTextColor(ContextCompat.getColor(this,R.color.md_white_1000));
        editText.setHintTextColor(ContextCompat.getColor(this,R.color.md_white_1000));
        searchView.setQueryHint(getString(R.string.search_view_hint));
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 跳转到搜索 fragment ，同时传入关键词
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,
                                SearchResultFragment.newInstance(query))
                        .commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
