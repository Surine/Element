package cn.surine.element.base.utils;

import org.litepal.LitePal;

import java.util.List;

import cn.surine.element.bean.WidgetInfo;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-15 18:28
 */
public class CurdManager {

    /**
     * 通过id查找widgetInfo
     * @param id
     * */
    public static List<WidgetInfo> getWidgetById(int id){
        List<WidgetInfo> list = LitePal.where("appWidgetId = ?", String.valueOf(id)).find(WidgetInfo.class);
        return list;
    }


}
