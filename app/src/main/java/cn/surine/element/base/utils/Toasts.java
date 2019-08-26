package cn.surine.element.base.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-13 14:16
 */
public class Toasts {

    /**don't pass the activity context*/
    private static Context context;

    public static void init(Context ctx){
        context = ctx;
    }

    public static void toto(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
