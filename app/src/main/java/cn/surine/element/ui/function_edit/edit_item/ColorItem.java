package cn.surine.element.ui.function_edit.edit_item;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import cn.surine.element.R;

import static android.graphics.Color.TRANSPARENT;

/**
 * Intro：
 * @author sunliwei
 * @date 2019-08-19 17:24
 */
public class ColorItem extends RelativeLayout {

    private ImageView icon;
    private TextView title;
    private TextView subTitle;
    private ImageView funcView;
    private Context context;
    private LinearLayout layout;
    private OnEditItemValueClickListener onEditItemValueClickListener;

    public ColorItem setOnEditItemValueClickListener(OnEditItemValueClickListener onEditItemValueClickListener) {
        this.onEditItemValueClickListener = onEditItemValueClickListener;
        return this;
    }

    public ColorItem(Context context) {
        super(context);
        init(context);
    }

    public ColorItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context ctx) {
        this.context = ctx;
        inflate(context, R.layout.edit_color,this);
        icon = findViewById(R.id.icon);
        title = findViewById(R.id.title);
        subTitle = findViewById(R.id.subTitle);
        funcView = findViewById(R.id.funcView);
        layout = findViewById(R.id.itemBody);
        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPicker();
            }
        });
        funcView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPicker();
            }
        });
    }

    private void showColorPicker() {
        ColorPickerDialogBuilder.with(context)
                .setTitle("选择颜色")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {

                    }
                }).setPositiveButton("确定", new ColorPickerClickListener() {
            @Override
            public void onClick(DialogInterface d, int lastSelectedColor, Integer[] allColors) {
                if(onEditItemValueClickListener != null){
                    onEditItemValueClickListener.onClick(lastSelectedColor);
                    funcView.setBackgroundColor(lastSelectedColor);
                }
            }
        }).build().show();
    }


    public ColorItem set(int srcValue, String titleString, String subTitleString, int color){
        icon.setImageResource(srcValue);
        title.setText(titleString);
        subTitle.setText(subTitleString);
        if(color != 0){
            funcView.setBackgroundColor(color);
        }else {
            funcView.setBackgroundColor(TRANSPARENT);
        }
        return this;
    }


    public RelativeLayout set(int color){
        if(color != 0){
            funcView.setBackgroundColor(color);
        }else{
            funcView.setBackgroundColor(android.graphics.Color.WHITE);
        }
        return this;
    }

}
