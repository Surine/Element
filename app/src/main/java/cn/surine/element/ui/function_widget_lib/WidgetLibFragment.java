package cn.surine.element.ui.function_widget_lib;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.surine.element.R;
import cn.surine.element.base.controller.AbctractSingleTon;
import cn.surine.element.base.controller.BaseFragment;
import cn.surine.element.base.controller.BaseRecycleView;
import cn.surine.element.bean.product.ProductInfo;
import cn.surine.element.ui.adpater.MainListAdapter;

/**
 * Intro：
 * @author sunliwei
 * @date 2019-08-12 19:38
 */
public class WidgetLibFragment extends BaseFragment {

    BaseRecycleView libRec;

    public static AbctractSingleTon<WidgetLibFragment> abt = new AbctractSingleTon<WidgetLibFragment>() {
        @Override
        protected WidgetLibFragment newObj(Bundle bundle) {
            return new WidgetLibFragment();
        }
    };
    private List<ProductInfo> list = new ArrayList<>();
    private MainListAdapter adapter;

    @Override
    public int layoutId() {
        return R.layout.fragment_widget_lib;
    }

    @Override
    public void onInit(View parent) {
        libRec = parent.findViewById(R.id.rec);
        adapter = new MainListAdapter(R.layout.item_widget_display_grid,list,2);
        libRec.beStagger(2).setAdapter(adapter);
    }


}
