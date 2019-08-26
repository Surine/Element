package cn.surine.element.base;

/**
 * Intro：基础数据
 * @author sunliwei
 * @date 2019-08-10 15:53
 */
public class BaseConfig {

    /**包名*/
    public static final String PKG = "cn.surine.element";

    /**context所用的tag*/
    public static final String CTX = "intent_context";

    /**common id*/
    public static final String ID = "id";

    /**传递app widget id所用的tag*/
    public static final String APP_WIDGET_ID = "app_widget_id";
    /**传递action name 用的tag*/
    public static final String ACTION_NAME = "action_name";
    /**监听事件子事件*/
    public static final String ACTION_SUB_EVENT = "action_sub_event";
    /**监听事件数据*/
    public static final String ACTION_EVENT_DATA = "action_event_data";

    /**传递display必要字段*/
    public static final String DISPLAY_ID = "display_id";
    public static final String DISPLAY_NAME = "display_name";

    /**通知渠道*/
    public static final String NOTIFICATION_CHANNEL = "notification_channel";
    /**传递属性*/
    public static final String PROPERTIES = "properties";

    public static final int REQUEST_CODE_FOR_IMAGE = 1001;

    public static final String TAG = "TAG";
    /**传递action*/
    public static final int INTENT_FOR_ACTION = 1002;


    public static class BUS{
        /**更新action显示*/
        public static final String UPDATA_ACTION = "updata_action";
    }
}
