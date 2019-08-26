package cn.surine.element.base.utils;

import android.os.Bundle;

import com.google.gson.Gson;

import cn.surine.element.base.controller.AbctractSingleTon;

/**
 * Intro：
 * @author sunliwei
 * @date 2019-08-14 15:04
 */
public class GsonHelper{

    private final Gson gson;
    public static AbctractSingleTon<GsonHelper> abt = new AbctractSingleTon<GsonHelper>() {
        @Override
        protected GsonHelper newObj(Bundle bundle) {
            return new GsonHelper();
        }
    };

    public GsonHelper() {
        gson = new Gson();
    }

    /**
     * 从
     * @param json 字符串中
     * 解析
     * @param cls 类对象
     * */
    public <T> T parseData(String json, Class<T> cls){
        T t = null;
        if(gson != null){
            t = gson.fromJson(json,cls);
        }
        return t;
    }
}
