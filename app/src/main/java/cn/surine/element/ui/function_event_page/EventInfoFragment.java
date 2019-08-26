package cn.surine.element.ui.function_event_page;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.surine.element.R;
import cn.surine.element.base.BaseConfig;
import cn.surine.element.base.controller.AbctractSingleTon;
import cn.surine.element.base.controller.BaseFragment;
import cn.surine.element.base.controller.BaseRecycleView;
import cn.surine.element.base.utils.Toasts;
import cn.surine.element.bean.event.EventInfo;
import cn.surine.element.lib_event.Action;
import cn.surine.element.ui.function_edit.EditActivity;
import cn.surine.element.ui.view.dialog.OnPositiveClickListener;
import cn.surine.element.ui.view.dialog.Sialog;

import static android.app.Activity.RESULT_OK;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-23 11:30
 */
public class EventInfoFragment extends BaseFragment {
    String tag;
    private List<EventInfo> list = new ArrayList<>();

    public EventInfoFragment(String tag) {
        this.tag = tag;
    }

    @Override
    public int layoutId() {
        return R.layout.recycleview;
    }


    @Override
    public void onInit(View parent) {
        BaseRecycleView br = parent.findViewById(R.id.rec);
        initData();
        EventInfoAdapter eventInfoAdapter = new EventInfoAdapter(R.layout.edit_normal,list);
        br.beLinerV().setAdapter(eventInfoAdapter);
        eventInfoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(list.get(position).getAction() != null){

                    //所有操作都对宿主activity进行操作
                    EventActivity a = activity();

                    //对data部分进行赋值
                    a.globleActionData = list.get(position).getAction();

                    //对id部分进行包装
                    a.intent.putExtra(BaseConfig.ID,a.id);

                    //对data部分进行包装
                    if(a.globleActionData != null){
                        a.intent.putExtra(BaseConfig.ACTION_EVENT_DATA,a.globleActionData);
                    }

                    //对sub部分进行包装，已在event_card_fragment中赋值
                    if(a.globleActionSubName != null){
                        a.intent.putExtra(BaseConfig.ACTION_SUB_EVENT,a.globleActionSubName);
                    }

                    //设置返回结果
                    activity().setResult(RESULT_OK,a.intent);
                    finish();
                }
            }
        });
    }

    private void initData() {
        list.clear();
        switch (tag){
            default:break;
            case Action.SETTING:
                list.add(new EventInfo("打开Wifi",Action.WIFI_OPEN));
                list.add(new EventInfo("关闭Wifi",Action.WIFI_CLOSE));
                list.add(new EventInfo("打开蓝牙",Action.BLUETOOTH_OPEN));
                list.add(new EventInfo("关闭蓝牙",Action.BLUETOOTH_CLOSE));
                list.add(new EventInfo("增加媒体音量",Action.MEDIA_VOL_UP));
                list.add(new EventInfo("降低媒体音量",Action.MEDIA_VOL_DONW));
                break;

            case Action.PATTERN:
                String p1 = "["+Action.WIFI_IS_CLOSE+"]{"+Action.WIFI_OPEN+"};["+Action.WIFI_IS_OPEN+"]{"+Action.WIFI_CLOSE+"}";
                String p2 = "["+Action.BLUETOOTH_IS_CLOSE+"]{"+Action.BLUETOOTH_OPEN+"};["+Action.BLUETOOTH_IS_OPEN+"]{"+Action.BLUETOOTH_CLOSE+"}";
                String p3 = "("+Action.MEDIA_VOL_VALUE+"<"+50+"){"+Action.MEDIA_VOL_UP+"}";
                String p4 = "("+Action.MEDIA_VOL_VALUE+">"+50+"){"+Action.MEDIA_VOL_DONW+"}";
                list.add(new EventInfo("如果Wifi关闭，则打开，反之亦然",p1));
                list.add(new EventInfo("如果蓝牙关闭，则打开，反之亦然",p2));
                list.add(new EventInfo("如果音量小于50，加到50",p3,true));
                list.add(new EventInfo("如果音量大于50，减到50",p4,true));
                break;
        }
    }

}
