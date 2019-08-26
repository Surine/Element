package cn.surine.element.bean.event;

import java.util.List;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-23 10:10
 */
public class EventDisplayInfo {

    /**点击标识*/
    private String tag;

    /**展示名*/
    private String title;
    /**详情*/
    private String subTitle;
    /**图标*/
    private int icon;
    /**背景色*/
    private int color;

    /**动作标识符,暂时无用*/
    private String action;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public EventDisplayInfo() {
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


}
