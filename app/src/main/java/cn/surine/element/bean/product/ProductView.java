package cn.surine.element.bean.product;

import java.io.Serializable;
import java.util.List;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-17 19:18
 */
public class ProductView implements Serializable {

    private String action;
    /**
     * 小部件宽高
     * */
    private int width;
    private int height;

    private String backgroundColor;

    /**
     * 小部件views
     * */
    private List<ProductElement> views;

    public String getAction() {
        return action;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    public void setAction(String action) {
        this.action = action;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setViews(List<ProductElement> views) {
        this.views = views;
    }

    public List<ProductElement> getViews() {
        return views;
    }
}
