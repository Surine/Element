package cn.surine.element.ui.adpater;

import android.graphics.BitmapFactory;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.palette.graphics.Palette;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.surine.element.R;
import cn.surine.element.bean.product.ProductInfo;

/**
 * Intro：首页控件展示列表
 * @author sunliwei
 * @date 2019-08-13 13:03
 */
public class MainListAdapter extends BaseQuickAdapter<ProductInfo, BaseViewHolder> {

    /**0 垂直，1水平，2-n多列*/
    private int layoutStyle;

    public MainListAdapter(int layoutResId, @Nullable List<ProductInfo> data, int i) {
        super(layoutResId, data);
        layoutStyle = i;
    }

    @Override
    protected void convert(@NonNull final BaseViewHolder helper, ProductInfo item) {
        helper.setText(R.id.widget_title,item.getName());
        helper.setText(R.id.widget_intro,item.getDescription());
        helper.setImageResource(R.id.widget_src,item.getSrc());

        if(layoutStyle >= 2){
            Palette.from(BitmapFactory.decodeResource(mContext.getResources(),item.getSrc())).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(@Nullable Palette palette) {
                    Palette.Swatch swatch = palette.getDarkVibrantSwatch();
                    if(swatch != null){
                        CardView cardView = helper.getConvertView().findViewById(R.id.widget_card);
                        cardView.setCardBackgroundColor(swatch.getRgb());
                        TextView title = helper.getConvertView().findViewById(R.id.widget_title);
                        TextView intro = helper.getConvertView().findViewById(R.id.widget_intro);
                        title.setTextColor(swatch.getBodyTextColor());
                        intro.setTextColor(swatch.getTitleTextColor());
                    }
                }
            });
        }
    }
}
