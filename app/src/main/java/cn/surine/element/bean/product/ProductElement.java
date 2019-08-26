package cn.surine.element.bean.product;

import java.io.Serializable;
import java.util.List;

/**
 * Intro：
 * @author sunliwei
 * @date 2019-08-17 19:19
 */
public class ProductElement implements Serializable {
    private String type;
    private String action;


    public static final int VISIBILITY = 0;
    public static final int GONE = 1;
    /**是否隐藏，1隐藏，0显示*/
    private int visibility;

    /**宽高*/
    private int width;
    private int height;

    /**偏移*/
    private int x_offset;
    private int y_offset;

    /**点击事件偏移，用于点击事件不准确时候使用*/
    private List<Integer> action_offset;

    /**组件名*/
    private String name;

    /**属性列表*/
    private List<ProductProperties> properties;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getAction_offset() {
        return action_offset;
    }

    public void setAction_offset(List<Integer> action_offset) {
        this.action_offset = action_offset;
    }


    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getType() {
        return type;
    }

    public String getAction() {
        return action;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX_offset() {
        return x_offset;
    }

    public int getY_offset() {
        return y_offset;
    }

    public List<ProductProperties> getProperties() {
        return properties;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setX_offset(int x_offset) {
        this.x_offset = x_offset;
    }

    public void setY_offset(int y_offset) {
        this.y_offset = y_offset;
    }

    public void setProperties(List<ProductProperties> properties) {
        this.properties = properties;
    }
}
