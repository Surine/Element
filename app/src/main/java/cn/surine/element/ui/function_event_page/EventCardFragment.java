package cn.surine.element.ui.function_event_page;

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
import cn.surine.element.bean.event.EventDisplayInfo;
import cn.surine.element.ui.function_edit.EditActivity;

import static cn.surine.element.lib_event.Action.PATTERN;
import static cn.surine.element.lib_event.Action.SETTING;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-23 11:26
 */
public class EventCardFragment extends BaseFragment {
    private EventDiaplayAdapter eventAdapter;
    private BaseRecycleView recyclerView;
    private List<EventDisplayInfo> list = new ArrayList<>();


    public static AbctractSingleTon<EventCardFragment> abt = new AbctractSingleTon<EventCardFragment>() {
        @Override
        protected EventCardFragment newObj(Bundle bundle) {
            return new EventCardFragment();
        }
    };

    @Override
    public int layoutId() {
        return R.layout.recycleview;
    }


    @Override
    public void onInit(View parent) {

        initData();
        eventAdapter = new EventDiaplayAdapter(R.layout.item_event,list);
        recyclerView = parent.findViewById(R.id.rec);
        recyclerView.beGrid(2).setAdapter(eventAdapter);

        eventAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ((EventActivity)activity()).globleActionSubName = list.get(position).getTag();
                open(list.get(position).getTag());
            }
        });

    }

    /**打开*/
    private void open(String tag) {
        open(new EventInfoFragment(tag));
    }

    private void initData() {
        list.clear();
        EventDisplayInfo pattern = new EventDisplayInfo();
        pattern.setTitle("表达式");
        pattern.setTag(PATTERN);
        pattern.setSubTitle("包含特殊情景下的特殊指令,可自由定义");
        pattern.setIcon(R.drawable.ic_action_data);
        pattern.setColor(R.color.pattern);
        list.add(pattern);


        EventDisplayInfo eventDisplayInfo = new EventDisplayInfo();
        eventDisplayInfo.setTitle("设置项");
        eventDisplayInfo.setTag(SETTING);
        eventDisplayInfo.setAction(SETTING);
        eventDisplayInfo.setSubTitle("包含设置项开关等快捷操作");
        eventDisplayInfo.setIcon(R.drawable.ic_action_bluetooth);
        eventDisplayInfo.setColor(R.color.colorPrimary);
        list.add(eventDisplayInfo);
    }
}
