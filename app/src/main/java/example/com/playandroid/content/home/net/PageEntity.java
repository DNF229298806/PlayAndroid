package example.com.playandroid.content.home.net;

import java.util.List;

/**
 * @author Richard_Y_Wang
 * @des 2018/10/20 16:03
 */
public class PageEntity {

    /**
     * curPage : 1
     * datas : 文章数据
     * offset : 0
     * over : false
     * pageCount : 279
     * size : 20
     * total : 5567
     */

    private int curPage;
    private List<ArticleEntity> datas;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;

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

    public List<ArticleEntity> getArticleEntities() {
        return datas;
    }

    public void setArticleEntities(List<ArticleEntity> articleEntities) {
        this.datas = articleEntities;
    }
}
