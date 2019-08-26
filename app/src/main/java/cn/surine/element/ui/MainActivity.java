package cn.surine.element.ui;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

import cn.surine.element.R;
import cn.surine.element.base.controller.BaseActivity;
import cn.surine.element.bean.WidgetInfo;
import cn.surine.element.core.WidgetProvider;
import cn.surine.element.main.PillsWidget;
import cn.surine.element.main.SettingWidget;

import static cn.surine.element.base.BaseConfig.APP_WIDGET_ID;

public class MainActivity extends BaseActivity {

    private Button db;
    private Button addSetting;
    private Button addPill;
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
                if(id == -1){
                    return;
                }
                List<WidgetInfo> widgetInfoList = LitePal.findAll(WidgetInfo.class);
                for (WidgetInfo widgetInfo :widgetInfoList) {
                    Log.d("slw",widgetInfo.getAppWidgetId()+"widget");
                    Log.d("slw",widgetInfo.getCustomWidgetId()+"custom");
                    Log.d("slw",widgetInfo.getJson()+"json");
               //     WidgetProvider.updateRemoteUi(WidgetsManager.getInstance().get(widgetInfo.getAppWidgetId()).build(WidgetsManager.getInstance().getContext()),MainActivity.this);
                    WidgetProvider.updateRemoteUi(MainActivity.this,id);
                }
            }
        });
        addSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id == -1){
                    return;
                }
                currentView.setAppWidgetId(id);
                currentView.setCustomWidgetId(SettingWidget.class.getName());
                currentView.setJson(json);
                if(currentView.save()){
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            }
        });



        parseJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    }

}
