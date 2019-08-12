package cn.surine.element.bean;

/**
 * Intro：事件状态信息，用于读取某时间下某事件的状态
 * @author sunliwei
 * @date 2019-08-11 22:19
 */
public class StatusInfo {
    private String actionName;
    private String statusValue;

    public StatusInfo(String actionName, String statusValue) {
        this.actionName = actionName;
        this.statusValue = statusValue;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }
}
