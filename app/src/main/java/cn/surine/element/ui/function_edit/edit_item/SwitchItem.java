package cn.surine.element.ui.function_edit.edit_item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import cn.surine.element.R;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-20 11:51
 */
public class SwitchItem extends RelativeLayout {

    private ImageView icon;
    private TextView title;
    private TextView subTitle;
    private Switch funcView;
    private Context context;
    private LinearLayout layout;
    private OnEditItemValueClickListener onEditItemValueClickListener;

    public SwitchItem setOnEditItemValueClickListener(OnEditItemValueClickListener onEditItemValueClickListener) {
        this.onEditItemValueClickListener = onEditItemValueClickListener;
        return this;
    }

    public SwitchItem(Context context) {
        super(context);
        init(context);
    }

    public SwitchItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context ctx) {
        this.context = ctx;
        inflate(context, R.layout.edit_switch,this);
        icon = findViewById(R.id.icon);
        title = findViewById(R.id.title);
        subTitle = findViewById(R.id.subTitle);
        funcView = findViewById(R.id.funcView);
        layout = findViewById(R.id.itemBody);

        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
              if(funcView.isChecked()){
                  funcView.setChecked(false);
              }else{
                  funcView.setChecked(true);
              }
              if(onEditItemValueClickListener != null){
                  onEditItemValueClickListener.onClick(funcView.isChecked());
              }
            }
        });

        funcView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(onEditItemValueClickListener != null){
                    onEditItemValueClickListener.onClick(b);
                }
            }
        });
    }


    public SwitchItem set(int srcValue, String titleString, String subTitleString,boolean value){
        icon.setImageResource(srcValue);
        title.setText(titleString);
        funcView.setChecked(value);
        subTitle.setText(subTitleString);
        return this;
    }


    public RelativeLayout set(int value){
        funcView.setBackgroundColor(value);
        return this;
    }

}
