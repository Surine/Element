package cn.surine.element.ui.function_edit.edit_item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.surine.element.R;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-19 19:46
 */
public class ValueInput extends RelativeLayout {

    private OnEditItemValueClickListener onEditItemValueClickListener;
    private ImageView icon;
    private TextView title;
    private LinearLayout layout;
    private EditText input;
    private ImageView valueUp;
    private ImageView valueDown;
    private int maxValue = 800;
    private TextView subTitle;


    public ValueInput setOnEditItemValueClickListener(OnEditItemValueClickListener onEditItemValueClickListener) {
        this.onEditItemValueClickListener = onEditItemValueClickListener;
        return this;
    }

    public ValueInput(Context context,int maxValue) {
        super(context);
        this.maxValue = maxValue;
        init(context);
    }

    public ValueInput(Context context) {
        super(context);
        init(context);
    }

    public ValueInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.edit_value_input,this);
        icon = findViewById(R.id.icon);
        subTitle = findViewById(R.id.subTitle);
        title = findViewById(R.id.title);
        valueUp = findViewById(R.id.valueUp);
        valueDown = findViewById(R.id.valueDown);
        input = findViewById(R.id.input);
        layout = findViewById(R.id.itemBody);


        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        valueUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentValue = Integer.parseInt(input.getText().toString());
                if(currentValue + 1 <= maxValue){
                    input.setText(String.valueOf(currentValue + 1));
                }
                sendValue(input.getText().toString());
            }
        });

        valueDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentValue = Integer.parseInt(input.getText().toString());
                if(currentValue - 1 >= 0){
                    input.setText(String.valueOf(currentValue - 1));
                }
                sendValue(input.getText().toString());
            }
        });

        valueUp.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int currentValue = Integer.parseInt(input.getText().toString());
                if(currentValue + 10 <= maxValue){
                    input.setText(String.valueOf(currentValue + 10));
                }
                sendValue(input.getText().toString());
                return true;
            }
        });

        valueDown.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int currentValue = Integer.parseInt(input.getText().toString());
                if(currentValue - 10 >= 0){
                    input.setText(String.valueOf(currentValue - 10));
                }
                sendValue(input.getText().toString());
                return true;
            }
        });


        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_DONE){
                    if(textView.getText().toString().isEmpty()){
                        input.setText("0");
                    }

                    if(Integer.parseInt(textView.getText().toString()) > maxValue){
                        input.setText(String.valueOf(maxValue));
                    }
                }
                input.clearFocus();
                sendValue(input.getText().toString());
                return false;
            }
        });
    }

    private void sendValue(String value) {
        if(onEditItemValueClickListener != null){
            onEditItemValueClickListener.onClick(value);
        }
    }


    public ValueInput set(int srcValue, String titleString, String subTitleString,int value){
        icon.setImageResource(srcValue);
        title.setText(titleString);
        subTitle.setText(subTitleString);
        input.setText(String.valueOf(value));
        return this;
    }

}
