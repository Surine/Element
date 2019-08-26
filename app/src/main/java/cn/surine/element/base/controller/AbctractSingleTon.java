package cn.surine.element.base.controller;

import android.os.Bundle;

/**
 * Intro：singleton abstract
 * @author sunliwei
 * @date 2019-06-22 23:10
 */
public abstract class AbctractSingleTon<T> {
    /**
     *  require all fragments use singleton mode
     *  you should override this method and return instance specially
     * */
    protected abstract T newObj(Bundle bundle);


    volatile T obj;


    /**
     * singleton mode with bundle equals null
     * */
    public  T getInstance(){
        return getInstance(null);
    }


    /**
     * singleton mode with bundle
     * */
    public  T getInstance(Bundle bundle){
        if(obj == null){
            synchronized (this){
                if(obj == null){
                    obj = newObj(bundle);
                }
            }
        }
        return obj;
    }
}
