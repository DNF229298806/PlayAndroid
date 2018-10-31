package example.com.playandroid.content.home.net;

import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import example.com.playandroid.App;
import example.com.playandroid.R;
import example.com.playandroid.base.BaseEntity;
import example.com.playandroid.base.Mult;
import example.com.playandroid.constant.Constant;
import example.com.playandroid.ui.ArticleDownPopupWindow;
import example.com.playandroid.util.ArouterUtil;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/16 22:30
 */
public class ArticleEntity extends BaseEntity implements Mult {

    /**
     * apkLink :  APK地址
     * author : Ficat   作者
     * chapterId : 340  类别ID?
     * chapterName : 硬件相关   类别
     * collect : false  是否收藏
     * courseId : 13    课程Id
     * desc : EasyBle主要用于，降低BLE开发繁琐程度。本库支持扫描（含自定义过滤条件扫描）、连接（包括设备多连接）、设备服务查询、读写数据（含分批写入）、读取设备信号、设置最大传输单元等BLE操作
     描述

     * envelopePic : http://www.wanandroid.com/resources/image/pc/default_project_img.jpg 封面图
     * fresh : true 是不是新鲜的 一天前？
     * id : 7360    文章ID
     * link : http://www.wanandroid.com/blog/show/2391  文章链接
     * niceDate : 1小时前      更新时间
     * origin :
     * projectLink : https://github.com/Ficat/EasyBle 项目地址
     * publishTime : 1539693716000 发布时间
     * superChapterId : 294
     * superChapterName : 开源项目主Tab
     */
    /**
     * title : 简化安卓BLE开发流程 EasyBle
     * type : 0
     * userId : -1
     * visible : 1
     * zan : 0
     */

    private String apkLink;
    private String author;
    private int chapterId;
    private String chapterName;
    private boolean collect;
    private int courseId;
    private String desc;
    private String envelopePic;
    private boolean fresh;
    private int id;
    private String link;
    private String niceDate;
    private String origin;
    private String projectLink;
    private long publishTime;
    private int superChapterId;
    private String superChapterName;
    private List<ArticleTagsEntity> tags;
    private String title;
    private int type;
    private int userId;
    private int visible;
    private int zan;

    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }


    public List<ArticleTagsEntity> getTags() {
        return tags;
    }

    public void setTags(List<ArticleTagsEntity> tags) {
        this.tags = tags;
    }


    public void onItemClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.link, link);
        bundle.putString(Constant.article_title, title);
        ArouterUtil.navigation(Constant.ActivityPath.WebViewActivity, bundle);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ArticleEntity))
            return false;
        ArticleEntity that = (ArticleEntity) o;
        return getId() == that.getId() &&
                Objects.equals(getLink(), that.getLink());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLink());
    }


    @Override
    public int getViewType() {
        return R.layout.holder_article_item;
    }

    @Override
    public ViewDataBinding getDataBinding() {
        return null;
    }

    @Override
    public void setDataBinding(ViewDataBinding viewDataBinding) {

    }

    public void onDownClick(View view) {
        /*// 用于PopupWindow的View
        View contentView = LayoutInflater.from(App.getCurrentActivity()).inflate(R.layout.pop_article_down, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        PopupWindow window = new PopupWindow(contentView, ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
        window.showAsDropDown(view, 0, 100);
        // 或者也可以调用此方法显示PopupWindow，其中：
        // 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
        // 第三和第四个参数分别是PopupWindow相对父View的x、y偏移
         //
        // window.showAtLocation(view, Gravity.BOTTOM, 10, 10);*/
        ArticleDownPopupWindow pop = new ArticleDownPopupWindow(App.getCurrentActivity(), ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        pop.setEntity(this);
        pop.showPopupWindow(view);
    }
}
