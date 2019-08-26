package cn.surine.element.base.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Intro：AssetHelper
 * @author sunliwei
 * @date 2019-08-15 10:23
 */
public class AssetHelper {

    public static final String WIDGET = "widget/";


    /**
     * 测试获取Json
     * */
    public static String getWidgetJson(Context context,String fileName){
        try {
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            int length = inputStream.available();
            byte[] buffer = new byte[length];
            inputStream.read(buffer);
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String getJs(Context context,String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
            String line;
            while ((line = bf.readLine()) != null){
                stringBuilder.append(line).append("\r\n");
            }
            bf.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
