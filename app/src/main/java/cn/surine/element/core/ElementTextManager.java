package cn.surine.element.core;

import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.surine.element.base.controller.AbctractSingleTon;
import cn.surine.element.base.utils.Logs;
import cn.surine.element.base.utils.ReflectUtil;

import static cn.surine.element.base.BaseConfig.PKG;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-16 11:28
 */
public class ElementTextManager {

    public static final String PATTERN_RULE = "(?<=\\{)[^}]*(?=\\})";
    public static final Pattern p = Pattern.compile(PATTERN_RULE);

    public static AbctractSingleTon<ElementTextManager> abt = new AbctractSingleTon<ElementTextManager>() {
        @Override
        protected ElementTextManager newObj(Bundle bundle) {
            return new ElementTextManager();
        }
    };


    public String parseString(String str){

        StringBuffer sbuf = new StringBuffer();

        Matcher m = p.matcher(str);


        while(m.find()){
            String matchedStr = m.group();
            String[] splitStr = matchedStr.split("\\.");
            if(splitStr.length == 2){
                m.appendReplacement(sbuf,reflectStr(splitStr[0],splitStr[1]));
            }
        }
        m.appendTail(sbuf);
        return sbuf.toString();
    }


    public String reflectStr(String cls,String mtd){
        Object obj = ReflectUtil.getObj(PKG+".lib_database."+cls);
        if(obj == null){
            return cls+"."+mtd;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            if(!f.getName().equals(mtd)){
                continue;
            }
            try {
                Object o = f.get(obj);
                if(o != null){
                    return String.valueOf(o);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return cls+"."+mtd;
    }
}
