package cn.surine.element.ui.function_event_page;

import android.content.Intent;

import com.google.android.material.tabs.TabLayout;

import cn.surine.element.R;
import cn.surine.element.base.BaseConfig;
import cn.surine.element.base.controller.BaseActivity;
import cn.surine.element.base.utils.Toasts;

import static cn.surine.element.base.BaseConfig.ACTION_NAME;

public class EventActivity extends BaseActivity {


    private TabLayout tabLayout;

    /**action 的3部分*/

    //actionName,在这里用不到
    String globleActionName = null;
    //actionSubName,标识是一种什么类型的action
    String globleActionSubName = null;
    //actionData,标识action数据
    String globleActionData = null;
    Intent intent = new Intent();

    int id;

    @Override
    public int fContainer() {
        return R.id.container;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_event;
    }

    @Override
    public void onInit() {
        tabLayout = findViewById(R.id.tab);
        tabLayout.addTab(tabLayout.newTab().setText("事件库"));

        Intent intent = getIntent();
        if(intent == null){
            Toasts.toto("intent from edit to event is null");
            return;
        }

        id = intent.getIntExtra(BaseConfig.ID,-2);
        if(id == -2){
            Toasts.toto("intent from edit to event value is not valid");
            return;
        }

        open(EventCardFragment.abt.getInstance());
    }
}
