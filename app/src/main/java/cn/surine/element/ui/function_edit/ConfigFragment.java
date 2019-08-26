package cn.surine.element.ui.function_edit;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.surine.element.R;
import cn.surine.element.base.BaseConfig;
import cn.surine.element.base.controller.AbctractSingleTon;
import cn.surine.element.base.controller.BaseFragment;
import cn.surine.element.ui.function_edit.edit_item.ColorItem;
import cn.surine.element.ui.function_edit.edit_item.NormalItem;
import cn.surine.element.ui.function_edit.edit_item.OnEditItemClickListener;
import cn.surine.element.ui.function_edit.edit_item.OnEditItemValueClickListener;
import cn.surine.element.ui.function_edit.edit_item.SwitchItem;

import static android.graphics.Color.*;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-19 16:36
 */
public class ConfigFragment extends BaseFragment {

    LinearLayout layout;

    public static AbctractSingleTon<ConfigFragment> abt = new AbctractSingleTon<ConfigFragment>() {
        @Override
        protected ConfigFragment newObj(Bundle bundle) {
            return new ConfigFragment(bundle);
        }
    };
    private NormalItem n3;

    public ConfigFragment(Bundle bundle) {
    }

    @Override
    public int layoutId() {
        return R.layout.scroller;
    }

    @Override
    public void onInit(View parent) {
        final EditActivity editActivity = activity();
        layout = parent.findViewById(R.id.container);
        RelativeLayout n1 = new NormalItem(activity()).set(R.drawable.ic_layout_color,"布局视图","点击修改相应视图属性").setOnEditItemClickListener(new OnEditItemClickListener() {
            @Override
            public void onClick() {
                activity().open(ElementListFragment.abt.getInstance());
            }
        });
        String colorStr = editActivity.productView.getBackgroundColor();
        int color = TRANSPARENT;
        if(colorStr != null){
            color = parseColor(colorStr);
        }
        RelativeLayout n2 = new ColorItem(activity()).set(R.drawable.ic_color_lens_24dp,"小部件背景颜色","对小部件的背景颜色进行修改",color).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                editActivity.productView.setBackgroundColor(Integer.toHexString((int)value).toUpperCase());
                editActivity.drawBackColor((int)value);
            }
        });
        n3 = new NormalItem(activity()).set(R.drawable.ic_gesture_black_24dp,"小部件全局点击事件","当前："+editActivity.productView.getAction()).setOnEditItemClickListener(new OnEditItemClickListener() {
            @Override
            public void onClick() {
                editActivity.startEventActivity(-1);
            }
        });
        RelativeLayout n4 = new SwitchItem(activity()).set(R.drawable.ic_wh_helper_24dp,"开启全局宽高辅助线框","点击切换辅助线框状态，默认开启",true).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                editActivity.WHhelper = (boolean) value;
                editActivity.drawDisplay();
            }
        });
        RelativeLayout n5 = new SwitchItem(activity()).set(R.drawable.ic_helper_black_24dp,"开启全局背景辅助线","点击切换辅助线状态，标准宽度单位15dp，默认开启",true).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                editActivity.mHelperLine = (boolean) value;
                editActivity.getHelperLine();
            }
        });

        layout.addView(n1);
        layout.addView(n2);
        layout.addView(n3);
        layout.addView(n4);
        layout.addView(n5);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        //更新action显示
        if(BaseConfig.BUS.UPDATA_ACTION.equals(event)){
            EditActivity e = activity();
            n3.set("当前"+e.productView.getAction());
        }
    }

}
