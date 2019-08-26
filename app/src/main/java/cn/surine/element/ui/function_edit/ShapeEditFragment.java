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

import java.util.List;
import java.util.Map;

import cn.surine.element.R;
import cn.surine.element.base.BaseConfig;
import cn.surine.element.base.controller.BaseFragment;
import cn.surine.element.base.utils.Toasts;
import cn.surine.element.bean.product.ProductAttrs;
import cn.surine.element.bean.product.ProductElement;
import cn.surine.element.core.TypeMap;
import cn.surine.element.ui.function_edit.edit_item.ColorItem;
import cn.surine.element.ui.function_edit.edit_item.NormalItem;
import cn.surine.element.ui.function_edit.edit_item.OnEditItemClickListener;
import cn.surine.element.ui.function_edit.edit_item.OnEditItemValueClickListener;
import cn.surine.element.ui.function_edit.edit_item.SwitchItem;
import cn.surine.element.ui.function_edit.edit_item.ValueInput;

import static android.graphics.Color.*;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-19 16:41
 */
public class ShapeEditFragment extends BaseFragment {
    private LinearLayout layout;
    private int id;
    private NormalItem r7;

    public ShapeEditFragment(int finalI) {
        id = finalI;
    }

    @Override
    public int layoutId() {
        return R.layout.scroller;
    }

    @Override
    public void onInit(View parent) {
        layout = parent.findViewById(R.id.container);
        final EditActivity editActivity = activity();
        List<ProductElement> productElements = ((EditActivity) activity()).productElementList;
        final ProductElement p = productElements.get(id);
        Map<String, String> map = TypeMap.getMap(p);

        RelativeLayout r1 = new ValueInput(activity()).set(R.drawable.ic_wh_black_24dp,"形状宽度(dp)","设置形状宽度，长按可快速调整",p.getWidth()).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                p.setWidth(Integer.valueOf((String)value));
                editActivity.drawDisplay();
            }
        });
        RelativeLayout r2 = new ValueInput(activity()).set(R.drawable.ic_wh_black_24dp,"形状高度(dp)","设置形状高度，长按可快速调整",p.getHeight()).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                p.setHeight(Integer.valueOf((String)value));
                editActivity.drawDisplay();
            }
        });
        RelativeLayout r3 = new ValueInput(activity()).set(R.drawable.ic_border_style_black_24dp,"X轴偏移(dp)","设置形状X轴偏移，左上角为原点，向右为X轴正方向，长按可快速调整",p.getX_offset()).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                p.setX_offset(Integer.valueOf((String)value));
                editActivity.drawDisplay();
            }
        });
        RelativeLayout r4 = new ValueInput(activity()).set(R.drawable.ic_border_style_black_24dp,"Y轴偏移(dp)","设置形状Y轴偏移，左上角为原点，向下为Y轴正方向，长按可快速调整",p.getY_offset()).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                p.setY_offset(Integer.valueOf((String)value));
                editActivity.drawDisplay();
            }
        });

        int corner = map.containsKey(ProductAttrs.Shape.CORNERS) ? Integer.parseInt(map.get(ProductAttrs.Shape.CORNERS)): 0;
        RelativeLayout r5 = new ValueInput(activity(),180).set(R.drawable.ic_rounded_corner_black_24dp,"形状圆角","设置形状圆角，最大值180",corner).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                editActivity.changeProperties(p, ProductAttrs.Image.CORNERS,(String)value);
                editActivity.drawDisplay();
            }
        });
        RelativeLayout r6 = new ColorItem(activity()).set(R.drawable.ic_color_lens_24dp,"形状背景颜色","对形状背景颜色进行修改",map.containsKey(ProductAttrs.Shape.BACKGROUND_COLOR) ? parseColor(map.get(ProductAttrs.Shape.BACKGROUND_COLOR)) : TRANSPARENT).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                editActivity.changeProperties(p,ProductAttrs.Shape.BACKGROUND_COLOR,"#"+Integer.toHexString((int)value).toUpperCase());
                editActivity.drawDisplay();
            }
        });
        r7 = new NormalItem(activity()).set(R.drawable.ic_gesture_black_24dp,"监听事件","当前："+p.getAction()).setOnEditItemClickListener(new OnEditItemClickListener() {
            @Override
            public void onClick() {
                EditActivity e = activity();
                e.startEventActivity(id);
            }
        });
        RelativeLayout r8 = new SwitchItem(activity()).set(R.drawable.ic_helper_black_24dp,"显示与隐藏","选择是否隐藏该组件，默认显示",p.getVisibility() == ProductElement.VISIBILITY).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                p.setVisibility((boolean)value ? ProductElement.VISIBILITY : ProductElement.GONE);
                editActivity.drawDisplay();
            }
        });

        layout.addView(r1);
        layout.addView(r2);
        layout.addView(r3);
        layout.addView(r4);
        layout.addView(r5);
        layout.addView(r6);
        layout.addView(r7);
        layout.addView(r8);

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
        if(BaseConfig.BUS.UPDATA_ACTION.equals(event)){
            EditActivity e = activity();
            r7.set("当前"+e.productElementList.get(id).getAction());
        }
    }

}
