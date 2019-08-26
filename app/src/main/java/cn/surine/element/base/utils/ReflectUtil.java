package cn.surine.element.base.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static cn.surine.element.base.BaseConfig.PKG;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-14 16:32
 */
public class ReflectUtil {

    /**
     * 获取
     * @param name 名字
     * 的反射对象
     * */
    public static <T> T getObj(String name){
            try {
                Class<T> cls = (Class<T>) Class.forName(name);
                T t = cls.newInstance();
                return t;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        return null;
    }


    /**
     * 获取event包下的类
     * */
    public static <T> T getEvent(String event){
        return getObj(PKG+".lib_event."+event);
    }



    /**
     * 执行静态方法
     * */
    public static Object invoke(String path, String method, Object... value) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Method clsMethod = null;
        Class<?> cls = Class.forName(path);
            if(value.length != 0){
                clsMethod = cls.getMethod(method,String.class);
            }else{
                clsMethod = cls.getMethod(method);
            }
            return clsMethod.invoke(null,value);
    }

    /**
     * 执行common库方法
     * */
    public static Object invokeCommon(String method, Object... args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(method.split("\\.").length == 2){
            return invoke(PKG+".lib_event."+method.split("\\.")[0],method.split("\\.")[1],args);
        }else{
            return invoke(PKG+".lib_event.Common",method,args);
        }
    }


    /**
     * 获取所有方法名
     * */
    public static List<String> getAllMethod(String path,boolean prefix){
        Class<?> cls = null;
        List<String> list = new ArrayList<>();
        try {
            cls = Class.forName(path);
            Method[] methods = cls.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                list.add((prefix ? cls.getSimpleName()+"." :"")+methods[i].getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 获取支持类中所有方法名，其中common类无需使用前缀
     * 支持列表：
     * Common：公共方法
     * Device：设备相关
     * */
    public static List<String> getAllMethod(){
        List<String> strings = new ArrayList<>();
        strings.addAll(getAllMethod(PKG+".lib_event.Common",false));
        strings.addAll(getAllMethod(PKG+".lib_event.Device",true));
        return strings;
    }
}
