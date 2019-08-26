package cn.surine.element.ui.function_me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import cn.surine.element.R;
import cn.surine.element.base.controller.AbctractSingleTon;
import cn.surine.element.base.controller.BaseFragment;
import cn.surine.element.base.controller.BaseRecycleView;
import cn.surine.element.base.utils.GsonHelper;
import cn.surine.element.bean.WidgetInfo;
import cn.surine.element.bean.product.Product;
import cn.surine.element.bean.product.ProductInfo;
import cn.surine.element.ui.adpater.MainListAdapter;
import cn.surine.element.ui.function_edit.EditActivity;

import static cn.surine.element.base.BaseConfig.APP_WIDGET_ID;

/**
 * Intro：
 * @author sunliwei
 * @date 2019-08-12 19:36
 */
public class MeFragment extends BaseFragment {

    public static AbctractSingleTon<MeFragment> abt = new AbctractSingleTon<MeFragment>() {
        @Override
        protected MeFragment newObj(Bundle bundle) {
            return new MeFragment();
        }
    };
    private BaseRecycleView libRec;
    private List<ProductInfo> list = new ArrayList<>();
    private MainListAdapter adapter;

    @Override
    public int layoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void onInit(View parent) {
        libRec = parent.findViewById(R.id.rec);
        adapter = new MainListAdapter(R.layout.item_widget_display_grid,list, 0);
        libRec.beStagger(2).setAdapter(adapter);

        list.clear();
        final List<WidgetInfo> db = LitePal.findAll(WidgetInfo.class);
        for (WidgetInfo wi:db) {
            Product product = GsonHelper.abt.getInstance().parseData(wi.getJson(), Product.class);
            if(product != null){
                ProductInfo productInfo = product.getProductInfo();
                list.add(productInfo);
            }
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(activity(), EditActivity.class);
                intent.putExtra(APP_WIDGET_ID,db.get(position).getAppWidgetId());
                startActivity(intent);
            }
        });

    }
}
