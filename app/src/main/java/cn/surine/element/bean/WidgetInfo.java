package cn.surine.element.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * Intro：WidgetInfo
 * @author sunliwei
 * @date 2019-08-12 11:37
 */
public class WidgetInfo extends LitePalSupport {

    /**小部件id*/
    @Column(unique = true,nullable = false)
    private int appWidgetId;

    /**对应的custom widget id （名称唯一）*/
    private String customWidgetId;

    /**对应的小部件属性*/
    private String json;

    /**
     * 当前小部件宽高
     * */
    private int width;
    private int height;

    public WidgetInfo() {
    }


    public WidgetInfo(int appWidgetId, String customWidgetId, String json, int width, int height) {
        this.appWidgetId = appWidgetId;
        this.customWidgetId = customWidgetId;
        this.json = json;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAppWidgetId() {
        return appWidgetId;
    }

    public void setAppWidgetId(int appWidgetId) {
        this.appWidgetId = appWidgetId;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getCustomWidgetId() {
        return customWidgetId;
    }

    public void setCustomWidgetId(String customWidgetId) {
        this.customWidgetId = customWidgetId;
    }
}
