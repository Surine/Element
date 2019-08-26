package cn.surine.element.core;

import java.util.HashMap;
import java.util.Map;

import cn.surine.element.R;
import cn.surine.element.bean.product.ProductElement;
import cn.surine.element.bean.product.ProductProperties;

/**
 * Intro：组件类型映射
 * @author sunliwei
 * @date 2019-08-14 16:47
 */
public class TypeMap {

    public static final String IMAGE = "Image";
    public static final String SHAPE = "Shape";
    public static final String STACK = "Stack";
    public static final String TEXT = "Text";
    public static final String ROW = "Row";
    public static final String COLUMN = "Column";


    /**
     * 根据type获取对应操作id
     */
    public static int getIdByType(String type){
        int value = 0;
        switch (type){
            case TEXT:
            case IMAGE:
            case SHAPE:
                value = R.id.widget_imageview;
                break;
            default:break;
        }
        return value;
    }



    /**
     * 根据type获取对应layout
     */
    public static int getLayoutByType(String type){
        int value = 0;
        switch (type){
            case IMAGE:
            case SHAPE:
            case TEXT:
                value = R.layout.widget_imageview;break;
            default:break;
        }
        return value;
    }



    /**
     * 判断type对应的是否是viewgroup
     * */
    public static boolean isViewGroup(String type){
        boolean value = false;
        switch (type){
            case IMAGE:
            case TEXT:
            case SHAPE:
                value = false;
                break;
            case STACK:
            case COLUMN:
            case ROW:
                value = true;
                break;
                default:break;
        }
        return value;
    }


    public static Map<String,String> getMap(ProductElement productElement){
        Map<String,String> map = new HashMap<>();
        for (ProductProperties p :productElement.getProperties()) {
            map.put(p.getPropertyName(),p.getPropertyValue());
        }
        return map;
    }

}
