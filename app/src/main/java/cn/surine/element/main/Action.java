package cn.surine.element.main;

/**
 * Intro：点击事件标识
 * @author sunliwei
 * @date 2019-08-10 15:23
 */
public class Action {

    /**update*/
    public static final String UPDATA = "android.appwidget.action.APPWIDGET_UPDATE";
    /**点击空视图时候的事件*/
    public static final String NO_VIEW_CLICK_EVENT = "no_view_click_event";



    /**点击图片的事件*/
    public static final String IMAGE_VIEW_CLICK = "image_view_click";


    public static class Setting {
        /**点击wifi按钮*/
        public static final String CLICK_WIFI = "click_wifi";
        /**点击蓝牙按钮*/
        public static final String CLICK_BLUE_TOOTH = "click_blue_tooth";
        /**点击数据按钮*/
        public static final String CLICK_DATA = "click_data";

    }

    public static class App{
        /**打开app按钮: rule = OPEN_APP + 包名*/
        public static final String OPEN_APP = "open_app";
    }

}
