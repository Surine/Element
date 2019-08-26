package cn.surine.element.ui.function_event_page;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.transition.Visibility;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.surine.element.R;
import cn.surine.element.bean.event.EventInfo;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-23 11:37
 */
public class EventInfoAdapter extends BaseQuickAdapter<EventInfo, BaseViewHolder> {


    public EventInfoAdapter(int layoutResId, @Nullable List<EventInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, EventInfo item) {

        ImageView icon = helper.getConvertView().findViewById(R.id.icon);
        if(item.getIcon() == 0){
            icon.setVisibility(View.GONE);
        }
        helper.setText(R.id.title,item.getName());
        helper.setText(R.id.subTitle,item.getAction());
    }
}
