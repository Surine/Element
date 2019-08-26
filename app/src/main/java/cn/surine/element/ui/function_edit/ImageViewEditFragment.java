package cn.surine.element.ui.function_edit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import cn.surine.element.R;
import cn.surine.element.base.BaseConfig;
import cn.surine.element.base.controller.BaseActivity;
import cn.surine.element.base.controller.BaseFragment;
import cn.surine.element.base.utils.Glide4Engine;
import cn.surine.element.base.utils.Logs;
import cn.surine.element.base.utils.Toasts;
import cn.surine.element.bean.product.ProductAttrs;
import cn.surine.element.bean.product.ProductElement;
import cn.surine.element.core.TypeMap;
import cn.surine.element.ui.function_edit.edit_item.NormalItem;
import cn.surine.element.ui.function_edit.edit_item.OnEditItemClickListener;
import cn.surine.element.ui.function_edit.edit_item.OnEditItemValueClickListener;
import cn.surine.element.ui.function_edit.edit_item.SwitchItem;
import cn.surine.element.ui.function_edit.edit_item.ValueInput;

import static android.app.Activity.RESULT_OK;
import static cn.surine.element.base.BaseConfig.PKG;
import static cn.surine.element.base.BaseConfig.REQUEST_CODE_FOR_IMAGE;

/**
 * Intro：
 *
 * @author sunliwei
 * @date 2019-08-19 16:42
 */
public class ImageViewEditFragment extends BaseFragment {

    private LinearLayout layout;
    private int id;
    private EditActivity editActivity;
    private Uri uritempFile;
    private NormalItem r6;

    public ImageViewEditFragment(int i) {
        id = i;
    }

    @Override
    public int layoutId() {
        return R.layout.scroller;
    }

    @Override
    public void onInit(View parent) {
        layout = parent.findViewById(R.id.container);
        editActivity = activity();
        List<ProductElement> productElements = ((EditActivity)activity()).productElementList;
        final ProductElement p = productElements.get(id);
        Map<String,String> map = TypeMap.getMap(p);

        RelativeLayout r1 = new ValueInput(activity()).set(R.drawable.ic_wh_black_24dp,"图片宽度(dp)","设置图片宽度，长按可快速调整",p.getWidth()).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                p.setWidth(Integer.valueOf((String)value));
                editActivity.drawDisplay();
            }
        });
        RelativeLayout r2 = new ValueInput(activity()).set(R.drawable.ic_wh_black_24dp,"图片高度(dp)","设置图片高度，长按可快速调整",p.getHeight()).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                p.setHeight(Integer.valueOf((String)value));
                editActivity.drawDisplay();
            }
        });
        RelativeLayout r3 = new ValueInput(activity()).set(R.drawable.ic_border_style_black_24dp,"X轴偏移(dp)","设置图片X轴偏移，左上角为原点，向右为X轴正方向，长按可快速调整",p.getX_offset()).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                p.setX_offset(Integer.valueOf((String)value));
                editActivity.drawDisplay();
            }
        });
        RelativeLayout r4 = new ValueInput(activity()).set(R.drawable.ic_border_style_black_24dp,"Y轴偏移(dp)","设置图片Y轴偏移，左上角为原点，向下为Y轴正方向，长按可快速调整",p.getY_offset()).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                p.setY_offset(Integer.valueOf((String)value));
                editActivity.drawDisplay();
            }
        });
        RelativeLayout r5 = new NormalItem(activity()).set(R.drawable.ic_image_black_24dp,"选择图片","从图库中选择合适的图片，宽高将受到数值约束").setOnEditItemClickListener(new OnEditItemClickListener() {
            @Override
            public void onClick() {
                chooseIamge();
            }
        });
        r6 = new NormalItem(activity()).set(R.drawable.ic_gesture_black_24dp,"监听事件","当前："+p.getAction()).setOnEditItemClickListener(new OnEditItemClickListener() {
            @Override
            public void onClick() {
            EditActivity e = activity();
            e.startEventActivity(id);
            }
        });
        int corner = map.containsKey(ProductAttrs.Image.CORNERS) ? Integer.parseInt(map.get(ProductAttrs.Image.CORNERS)) : 0;
        RelativeLayout r7 = new ValueInput(activity(),180).set(R.drawable.ic_rounded_corner_black_24dp,"图片圆角","设置图片圆角，最大值180",corner).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                editActivity.changeProperties(p,ProductAttrs.Image.CORNERS,(String)value);
                editActivity.drawDisplay();
            }
        });
        RelativeLayout r8 = new SwitchItem(activity()).set(R.drawable.ic_helper_black_24dp,"显示与隐藏","选择是否隐藏该组件，默认显示",p.getVisibility() == ProductElement.VISIBILITY).setOnEditItemValueClickListener(new OnEditItemValueClickListener() {
            @Override
            public void onClick(Object value) {
                p.setVisibility((boolean)value ? ProductElement.VISIBILITY : ProductElement.GONE);
                editActivity.drawDisplay();
            }
        });

        layout.addView(r1);
        layout.addView(r2);
        layout.addView(r3);
        layout.addView(r4);
        layout.addView(r5);
        layout.addView(r6);
        layout.addView(r7);
        layout.addView(r8);
    }


    /**选择图片*/
    private void chooseIamge() {
        Matisse.from(this)
                .choose(MimeType.ofImage())
                .countable(true)
                .maxSelectable(1)
                .thumbnailScale(0.85f)
                .imageEngine(new Glide4Engine())
                .forResult(REQUEST_CODE_FOR_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logs.d("slw"+requestCode+"/"+resultCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case REQUEST_CODE_FOR_IMAGE :
                    List<Uri> mSelected = Matisse.obtainResult(data);
                    ProductElement image = editActivity.productElementList.get(id);
                    Toasts.toto(mSelected.get(0).getPath());
                    break;
                default:break;
            }
        }else{
            Toasts.toto("element: select picture or crop error");
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if(BaseConfig.BUS.UPDATA_ACTION.equals(event)){
            EditActivity e = activity();
            r6.set("当前"+e.productElementList.get(id).getAction());
        }
    }

}
