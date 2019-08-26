package cn.surine.element.lib_js_runner;

import cn.surine.element.base.controller.App;
import cn.surine.element.base.utils.AssetHelper;

/**
 * Intro：选择文件执行
 * @author sunliwei
 * @date 2019-08-24 20:06
 */
public class JsSelector {

    /**
     * 执行某js文件，无返回值
     * */
    public static Object excuAsset(String fileName){
        JsEngine je = JsEngine.init();
        Object result = je.execute(AssetHelper.getJs(App.getContext(),fileName));
        je.kill();
        return result;
    }


    /**
     * 执行某js文件中的方法
     * */
    public static Object excuMethodAsset(String fileName, String method, Object... args){
        JsEngine je = JsEngine.init();
        Object result = je.execMethod(AssetHelper.getJs(App.getContext(),fileName),method,args);
        je.kill();
        return result;
    }
}
