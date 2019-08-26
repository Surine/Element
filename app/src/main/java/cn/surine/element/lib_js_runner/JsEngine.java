package cn.surine.element.lib_js_runner;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Function;
import com.eclipsesource.v8.utils.MemoryManager;

/**
 * Intro：JsEngine:使用时候需要手动kill，否则会报异常
 * @author sunliwei
 * @date 2019-08-24 15:40
 */
public class JsEngine {

    private V8 runtime;
    private V8Array params;

    public static JsEngine init(){
        JsEngine jsEngine = new JsEngine();
        jsEngine.runtime = V8.createV8Runtime();
        JsBridge.abt.getInstance().init(jsEngine.runtime);
        return jsEngine;
    }

    /**
     * 执行一段js脚本
     * @param s js
     * */
    public Object execute(String s){
        check();
        try {
            return runtime.executeScript(s);
        }catch (Exception e){
            return e.getMessage();
        }
    }


    /**环境检查*/
    private void check() {
        if(runtime == null){
            throw new IllegalArgumentException("runtime is null");
        }
    }


    /**
     * 执行Js里定义的方法并传参
     * @param js js
     * @param method 方法名
     * */
    public Object execMethod(String js,String method,Object...args){
        execute(js);
        params = new V8Array(runtime);

        //装载字段
        fillParam(args);

        try {
            Object result =  runtime.executeFunction(method,params);
            return result;
        }catch (Exception e){
            return e.getMessage();
        }finally {
            killParam();
        }
    }

    private void fillParam(Object[] args) {
        if(args.length > 0){
            if(args[0] instanceof Integer){
                for (int i = 0; i < args.length; i++) {
                    params.push((Integer) args[i]);
                }
            }else if(args[0] instanceof Boolean){
                for (int i = 0; i < args.length; i++) {
                    params.push((Boolean) args[i]);
                }
            }else if(args[0] instanceof Double){
                for (int i = 0; i < args.length; i++) {
                    params.push((Double) args[i]);
                }
            }else if(args[0] instanceof String){
                for (int i = 0; i < args.length; i++) {
                    params.push((String) args[i]);
                }
            }
        }
    }


    public Object execMethodByCall(String js,String method,Object... args){
        execute(js);
        //判断是不是函数
        if (runtime.getType(method) == V8.V8_FUNCTION){

            fillParam(args);

            V8Function call = (V8Function) runtime.getObject(method);

            try {
                return call.call(null, params);
            }catch (Exception e){
                return e.getMessage();
            }finally {
                killCall(call);
                killParam();
            }
        }else{
            return method + "not a js function!";
        }
    }



    private void killCall(V8Function call) {
        if(call != null){
            call.release();
        }
    }


    /**
     * 释放内存
     * 不论是否执行完，或者有任何变量在环境中，都清理
     * */
    public void kill() {
        if(runtime != null){
            MemoryManager manager = new MemoryManager(runtime);
            manager.release();
        }
    }


    public void killParam(){
        if(params != null){
            params.release();
        }
    }

}
