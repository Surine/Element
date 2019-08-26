package cn.surine.element.bean.event;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-23 11:33
 */
public class EventInfo {
    private int icon;
    private String name;
    private String action;
    private boolean isNeedValue;

    public EventInfo(String name, String action, boolean isNeedValue) {
        this.name = name;
        this.action = action;
        this.isNeedValue = isNeedValue;
    }

    public EventInfo(int icon, String name, String action) {
        this.icon = icon;
        this.name = name;
        this.action = action;
    }

    public EventInfo(String name, String action) {
        this.name = name;
        this.action = action;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public boolean isNeedValue() {
        return isNeedValue;
    }

    public void setNeedValue(boolean needValue) {
        isNeedValue = needValue;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
