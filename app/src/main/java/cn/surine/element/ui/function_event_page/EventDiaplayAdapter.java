package cn.surine.element.ui.function_event_page;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.surine.element.R;
import cn.surine.element.bean.event.EventDisplayInfo;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-23 10:10
 */
public class EventDiaplayAdapter extends BaseQuickAdapter<EventDisplayInfo, BaseViewHolder> {
    public EventDiaplayAdapter(int layoutResId, @Nullable List<EventDisplayInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, EventDisplayInfo item) {
        helper.setText(R.id.title,item.getTitle());
        helper.setImageResource(R.id.icon,item.getIcon());
        helper.setText(R.id.subTitle,item.getSubTitle());
        CardView cardView = helper.getConvertView().findViewById(R.id.eventContainer);
        cardView.setCardBackgroundColor(mContext.getResources().getColor(item.getColor()));
    }
}
