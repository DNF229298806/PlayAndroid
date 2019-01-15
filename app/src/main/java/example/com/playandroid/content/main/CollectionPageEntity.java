package example.com.playandroid.content.main;

import java.util.List;

/**
 * @author Richard_Y_Wang
 * @des 2019/1/15 12:29
 */
public class CollectionPageEntity {


    /**
     * curPage : 1
     * datas : [{"author":"Ruheng","chapterId":26,"chapterName":"基础UI控件","courseId":13,"desc":"详解Android图文混排实现。","envelopePic":"","id":30679,"link":"http://www.jianshu.com/p/6843f332c8df","niceDate":"2018-11-04","origin":"","originId":1165,"publishTime":1541323939000,"title":"Android图文混排实现方式详解","userId":12445,"visible":0,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 1
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<CollectionArticleEntity> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CollectionArticleEntity> getDatas() {
        return datas;
    }

    public void setDatas(List<CollectionArticleEntity> datas) {
        this.datas = datas;
    }
}
