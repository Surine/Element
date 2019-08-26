package cn.surine.element.ui;

import android.media.AudioManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;

import org.litepal.LitePal;

import java.io.ObjectStreamException;
import java.util.List;

import cn.surine.element.R;
import cn.surine.element.base.controller.BaseActivity;
import cn.surine.element.base.utils.AssetHelper;
import cn.surine.element.base.utils.Logs;
import cn.surine.element.base.utils.Toasts;
import cn.surine.element.bean.WidgetInfo;
import cn.surine.element.lib_event.Action;
import cn.surine.element.lib_event.Common;
import cn.surine.element.lib_js_runner.JsEngine;
import cn.surine.element.lib_js_runner.JsSelector;

import static cn.surine.element.base.BaseConfig.APP_WIDGET_ID;

public class MainActivity extends BaseActivity {

    private Button db;
    private Button addSetting;
    private Button addPill;
    private Button js;
    private Button parseJson;
    private WidgetInfo currentView = new WidgetInfo();

    /**
     * custom_widget_id = 1
     * backgroundColor = color.white
     * backgroundRadius = 180
     * function1 = openWifi
     * function2 = openBlueTooth
     * function3 = openData
     * f1_img_close = wifi.png
     * f1_img_open = wifi_open.png
     * ……
     * */
    private String json = "json_demo";

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onInit() {
        db = findViewById(R.id.db);
        addSetting = findViewById(R.id.addSetting);
        addPill = findViewById(R.id.addPill);
        parseJson = findView(R.id.parseJson);
        js = findView(R.id.js);

        final int id = getIntent().getIntExtra(APP_WIDGET_ID,-1);
        if(id != -1){
            List<WidgetInfo> list = LitePal.where("appWidgetId = ?", String.valueOf(id)).find(WidgetInfo.class);
            if(list.size() > 0){
                currentView = list.get(0);
            }
        }


        db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String json = AssetHelper.getWidgetJson(MainActivity.this,"test.json");
                if(id == -1){
                    return;
                }
                currentView.setAppWidgetId(id);
                currentView.setCustomWidgetId("测试服");
                currentView.setJson(json);
                if(currentView.save()){
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    Action.sendUpdate(MainActivity.this);
                }else{
                    Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            }
        });



        parseJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String json = AssetHelper.getWidgetJson(MainActivity.this,"plan2_chat.json");
                if(id == -1){
                    return;
                }
                currentView.setAppWidgetId(id);
                currentView.setCustomWidgetId("测试服");
                currentView.setJson(json);
                if(currentView.save()){
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    Action.sendUpdate(MainActivity.this);
                }else{
                    Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

        js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excuJs();
            }
        });
    }

    private void excuJs() {
      //  Logs.d(""+JsSelector.excuMethodAsset("1.js","openWifiJs"));
        JsSelector.excuAsset("1.js");
    }


}
