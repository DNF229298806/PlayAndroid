package example.com.playandroid.content.openapi;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import example.com.playandroid.R;
import example.com.playandroid.base.BaseModel;
import example.com.playandroid.databinding.ActivityOpenApiBinding;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static example.com.playandroid.network.Api.HOST;
import static example.com.playandroid.network.Api.OPEN_API;

/**
 * @author admin
 * @des 2018/12/21
 */
public class OpenApiModel extends BaseModel<OpenApiActivity, ActivityOpenApiBinding> {
    public OpenApiModel(OpenApiActivity activity) {
        super(activity);
    }

    ExpandableItemAdapter adapter;

    /**
     * subscribeOn 用于指定上游线程，observeOn 用于指定下游线程，
     * 多次用 subscribeOn 指定上游线程只有第一次有效，多次用 observeOn 指定下次线程，每次都有效；
     * just 和 create操作符的区别
     * 如果Observable.just(Jsoup.connect(HOST + OPEN_API).get()) 这种写法
     * 他是先网络请求再把返回的结果放进去的 所以不行 会报networkOnMainThread的错误
     * 所以这里的线程切换实际上是没有用的 相当于1+1 在RxJava外面执行的 然后把结果2通过just发射出去
     * 如果Observable.create的话 是通过接口然后走的回调接口 所以这里的线程切换是有用的
     * 这样解析之后可以拿到头部的数据 之后再拿到里面的li标签的数据就可以了
     */
    @Override
    public void onCreate() {
        super.onCreate();
        RecyclerView recyclerView = getBinding().recyclerView;
        adapter = new ExpandableItemAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        adapter.setEmptyView(R.layout.loading_view, (ViewGroup) recyclerView.getParent());
        addDisposable(Observable.<Document>create(e -> e.onNext(Jsoup.connect(HOST + OPEN_API).get()))
                .subscribeOn(Schedulers.newThread())//控制上游线程在子线程跑
                .observeOn(AndroidSchedulers.mainThread())//控制下游线程在主线程跑
                .subscribe(doc -> {
                    List<OpenApiEntity> entities = getOpenApiData(doc);
                    ArrayList<MultiItemEntity> res = new ArrayList<>();
                    for (OpenApiEntity entity : entities) {
                        for (ContentEntity contentEntity : entity.getList()) {
                            entity.addSubItem(contentEntity);
                        }
                        res.add(entity);
                    }
                    adapter.setNewData(res);
                    adapter.expandAll();
                }, throwable -> {adapter.setEmptyView(R.layout.error_view, (ViewGroup) recyclerView.getParent());throwable.printStackTrace();}));
    }

    /**
     * 网页地址为：http://www.wanandroid.com/openapis
     *
     * @param doc 传入Jsoup的一个Document对象 Document对象可以使用
     *            Jsoup.connect(HOST + OPEN_API).get()方法拿到
     *            也可以使用Jsoup.parse("123")方法传入一个字符串
     *            把字符串转化成Document 进行操作
     * @return 返回一个从网页上解析下来的List<OpenApiEntity>的list
     */
    @NonNull
    private List<OpenApiEntity> getOpenApiData(Document doc) {
        List<OpenApiEntity> entities = new ArrayList<>();
        //选中<div class="area_r">这个div中的class=navi_div的div
        Elements select = doc.select("div.area_r").select("div.navi_div");
        for (Element element : select) {
            OpenApiEntity entity = new OpenApiEntity();
            List<ContentEntity> tempList = new ArrayList<>();
            entity.setList(tempList);
            // <h3 id="349">日历</h3> 直接拿出<h3>标签里面的值
            for (Element h3 : element.select("h3")) {
                String title = h3.childNode(0).toString();
                if (!TextUtils.isEmpty(title)) {
                    entity.setTitle(title);
                }
            }
            // <a href="https://mmp.51wnl.com/apiDetail_calandar.html" target="_blank">万年历API文档</a>
            // 拿出a标签中的值和链接
            for (Element a : element.select("a")) {
                ContentEntity contentEntity = new ContentEntity();
                String link = a.attr("href");
                String name = a.childNode(0).toString();
                if (!TextUtils.isEmpty(link)) {
                    contentEntity.setLink(link);
                }
                if (!TextUtils.isEmpty(name)) {
                    contentEntity.setName(name);
                }
                tempList.add(contentEntity);
            }
            entities.add(entity);
        }
        return entities;
    }

    public void navigationClick(View view) {
        getActivity().finish();
    }
}
