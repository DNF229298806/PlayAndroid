package example.com.playandroid.content.home.net;

import example.com.playandroid.base.BaseEntity;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/16 22:30
 */
public class ArticleEntity extends BaseEntity {

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
    private String title;
    private int type;
    private int userId;
    private int visible;
    private int zan;
    private ArticleTagsEntity tags;

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

    public ArticleTagsEntity getTags() {
        return tags;
    }

    public void setTags(ArticleTagsEntity tags) {
        this.tags = tags;
    }
}
