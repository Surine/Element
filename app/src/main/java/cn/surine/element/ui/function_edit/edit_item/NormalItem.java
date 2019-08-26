package cn.surine.element.ui.function_edit.edit_item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.surine.element.R;

/**
 * Intro：
 * @author sunliwei
 * @date 2019-08-19 16:57
 */
public class NormalItem extends RelativeLayout {

    private OnEditItemClickListener onEditItemClickListener;
    private ImageView icon;
    private TextView title;
    private TextView subTitle;
    private LinearLayout layout;

    private OnEditItemValueClickListener onEditItemValueClickListener;

    public NormalItem setOnEditItemClickListener(OnEditItemClickListener onEditItemClickListener) {
        this.onEditItemClickListener = onEditItemClickListener;
        return this;
    }

    public NormalItem setOnEditItemValueClickListener(OnEditItemValueClickListener onEditItemValueClickListener) {
        this.onEditItemValueClickListener = onEditItemValueClickListener;
        return this;
    }

    public NormalItem(Context context) {
        super(context);
        init(context);
    }

    public NormalItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.edit_normal,this);
        icon = findViewById(R.id.icon);
        title = findViewById(R.id.title);
        subTitle = findViewById(R.id.subTitle);
        layout = findViewById(R.id.itemBody);
        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onEditItemClickListener == null) {
                    return;
                }
                onEditItemClickListener.onClick();
            }
        });
    }


    public NormalItem set(int srcValue, String titleString, String subTitleString){
        icon.setImageResource(srcValue);
        title.setText(titleString);
        subTitle.setText(subTitleString);
        return this;
    }

    public NormalItem set(String titleString){
        subTitle.setText(titleString);
        return this;
    }

}
