package cn.surine.element.ui.function_edit;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.surine.element.R;
import cn.surine.element.base.controller.AbctractSingleTon;
import cn.surine.element.base.controller.BaseFragment;
import cn.surine.element.bean.product.ProductElement;
import cn.surine.element.core.TypeMap;
import cn.surine.element.ui.function_edit.edit_item.NormalItem;
import cn.surine.element.ui.function_edit.edit_item.OnEditItemClickListener;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-19 16:40
 */
public class ElementListFragment extends BaseFragment {

    private LinearLayout layout;

    public static AbctractSingleTon<ElementListFragment> abt = new AbctractSingleTon<ElementListFragment>() {
        @Override
        protected ElementListFragment newObj(Bundle bundle) {
            return new ElementListFragment();
        }
    };


    @Override
    public int layoutId() {
        return R.layout.scroller;
    }

    @Override
    public void onInit(View parent) {
        layout = parent.findViewById(R.id.container);
        List<ProductElement> productElements = ((EditActivity) activity()).productElementList;
        RelativeLayout relativeLayout = null;
        for (int i = 0; i < productElements.size(); i++) {
            final ProductElement p = productElements.get(i);
            final int finalI = i;

            if (TypeMap.IMAGE.equals(p.getType())) {
                relativeLayout = new NormalItem(activity()).set(R.drawable.ic_image_black_24dp, p.getType(), p.getName()).setOnEditItemClickListener(new OnEditItemClickListener() {
                    @Override
                    public void onClick() {
                        open(new ImageViewEditFragment(finalI));
                    }
                });
            } else if (TypeMap.TEXT.equals(p.getType())) {
                relativeLayout = new NormalItem(activity()).set(R.drawable.ic_font_download_black_24dp, p.getType(), p.getName()).setOnEditItemClickListener(new OnEditItemClickListener() {
                    @Override
                    public void onClick() {
                        open(new TextEditFragment(finalI));
                    }
                });
            } else if (TypeMap.SHAPE.equals(p.getType())) {
                relativeLayout = new NormalItem(activity()).set(R.drawable.ic_layers_black_24dp, p.getType(), p.getName()).setOnEditItemClickListener(new OnEditItemClickListener() {
                    @Override
                    public void onClick() {
                        open(new ShapeEditFragment(finalI));
                    }
                });
            }
            if (relativeLayout != null) {
                layout.addView(relativeLayout);
            }
        }

    }
}
