package cn.surine.element.lib_js_runner;

import android.os.Bundle;

import com.eclipsesource.v8.JavaCallback;
import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

import java.util.List;

import cn.surine.element.base.controller.AbctractSingleTon;
import cn.surine.element.base.utils.Logs;
import cn.surine.element.base.utils.ReflectUtil;
import cn.surine.element.base.utils.Toasts;

/**
 * Intro：JsBridge
 * @author sunliwei
 * @date 2019-08-24 17:05
 */
public class JsBridge {

    public static AbctractSingleTon<JsBridge> abt = new AbctractSingleTon<JsBridge>() {
        @Override
        protected JsBridge newObj(Bundle bundle) {
            return new JsBridge();
        }
    };


    /**
     * js注册
     * @param method 方法名
     * @param runtime 环境
     * */
    private void register(V8 runtime, final String method){

        JavaCallback callback = new JavaCallback() {
            @Override
            public Object invoke(final V8Object receiver, final V8Array parameters) {
                Object[] args = new String[parameters.length()];
                for (int i = 0; i < parameters.length(); i++) {
                   args[i] = parameters.get(i);
                }
                try {
                   return ReflectUtil.invokeCommon(method, args);
                } catch (Exception e){
                    e.printStackTrace();
                    Toasts.toto("Js脚本中含有不合法的方法！请检查");
                }
                return null;
            }
        };
        runtime.registerJavaMethod(callback,method);
    }


    /**
     * 初始化注册
     * @param runtime*/
    public void init(V8 runtime) {
        List<String> names = ReflectUtil.getAllMethod();
        for (String name :names) {
            register(runtime,name);
        }
    }
}
