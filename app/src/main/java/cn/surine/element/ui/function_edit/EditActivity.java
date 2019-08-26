package cn.surine.element.ui.function_edit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.surine.element.R;
import cn.surine.element.base.BaseConfig;
import cn.surine.element.base.controller.BaseActivity;
import cn.surine.element.base.utils.CurdManager;
import cn.surine.element.base.utils.GsonHelper;
import cn.surine.element.base.utils.Logs;
import cn.surine.element.base.utils.Toasts;
import cn.surine.element.bean.WidgetInfo;
import cn.surine.element.bean.product.Product;
import cn.surine.element.bean.product.ProductElement;
import cn.surine.element.bean.product.ProductInfo;
import cn.surine.element.bean.product.ProductProperties;
import cn.surine.element.bean.product.ProductView;
import cn.surine.element.core.Painter;
import cn.surine.element.core.TypeMap;
import cn.surine.element.lib_event.Action;
import cn.surine.element.ui.function_event_page.EventActivity;

import static cn.surine.element.base.BaseConfig.ACTION_EVENT_DATA;
import static cn.surine.element.base.BaseConfig.ACTION_SUB_EVENT;
import static cn.surine.element.base.BaseConfig.ID;
import static cn.surine.element.base.BaseConfig.INTENT_FOR_ACTION;
import static cn.surine.element.base.utils.ScreenUtil.dp2px;
import static cn.surine.element.lib_event.Action.IMAGE_CLICK;

public class EditActivity extends BaseActivity {


    //宽高辅助线框
    boolean WHhelper = true;
    boolean mHelperLine = true;

    Product product;
    ProductInfo productInfo;
    ProductView productView;
    List<ProductElement> productElementList;
    int appWidgetId;
    ImageView display;
    private int widgetWidth;
    private int widgetHeight;
    private TabLayout tabLayout;
    private ImageView canvas;
    private BottomSheetBehavior<View> mBehavior;

    @Override
    public int layoutId() {
        return R.layout.activity_edit;
    }

    @Override
    public void onInit() {
        display = findViewById(R.id.displayInfo);
        tabLayout = findViewById(R.id.tab);

        canvas = findViewById(R.id.canvas);
        tabLayout.addTab(tabLayout.newTab().setText("定制"));


        if (getIntent() != null) {
            appWidgetId = getIntent().getIntExtra(BaseConfig.APP_WIDGET_ID, -1);
        }

        if (appWidgetId == -1) {
            Toasts.toto("Main2Edit: Miss AppWidgetId");
        }


        List<WidgetInfo> widgetInfoList = CurdManager.getWidgetById(appWidgetId);
        if (widgetInfoList.size() == 0) {
            Toasts.toto("Edit: Widget List Size Exception");
        }

        product = GsonHelper.abt.getInstance().parseData(widgetInfoList.get(0).getJson(), Product.class);
        productInfo = product.getProductInfo();
        productView = product.getRootView();

        widgetWidth = productView.getWidth() == -1 ? widgetInfoList.get(0).getWidth() : productView.getWidth();
        widgetHeight = productView.getHeight() == -1 ? widgetInfoList.get(0).getHeight() : productView.getHeight();

        init();
    }

    private void init() {
        productElementList = productView.getViews();
        drawDisplay();
        open(ConfigFragment.abt.getInstance());
        drawBackColor(0);
        getHelperLine();

    }

    @Override
    public int fContainer() {
        return R.id.content;
    }


    /**
     * 设置背景颜色
     */
    public void drawBackColor(int color) {
        //0
        if (Color.TRANSPARENT != color) {
            canvas.setBackgroundColor(color);
        }
    }

    /**
     * 绘制辅助线
     */
    public void getHelperLine() {
        if(!mHelperLine){
            canvas.setImageBitmap(null);
            return;
        }
        int offset = (int) dp2px(15);
        Rect rect = new Rect(0, 0, (int) dp2px(widgetWidth), (int) dp2px(widgetHeight));
        Bitmap bitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        int color = Color.GRAY;
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        Canvas c = new Canvas(bitmap);
        p.setColor(color);
        for (int i = 0; i <= rect.width(); i += offset) {
           c.drawLine(i,0,i,rect.height(),p);
        }
        for (int j = 0; j <= rect.height(); j += offset) {
            c.drawLine(0,j,rect.width(),j,p);
        }
        p.setTextSize(30);
        c.drawText("s:15dp",rect.width() - 100,rect.height() - 20,p);
        canvas.setImageBitmap(bitmap);
    }


    /**
     * 绘制展示布局
     */
    public void drawDisplay() {
        Rect rect = new Rect(0, 0, (int) dp2px(widgetWidth), (int) dp2px(widgetHeight));
        Bitmap bitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        for (ProductElement pe : productElementList) {
            //如果组件没有被隐藏，就绘制
            if(pe.getVisibility() == ProductElement.GONE){
                continue;
            }
            if (TypeMap.IMAGE.equals(pe.getType())) {
                Painter.paintImage(bitmap, pe);
            } else if (TypeMap.SHAPE.equals(pe.getType())) {
                Painter.paintShape(bitmap, pe);
            } else if (TypeMap.TEXT.equals(pe.getType())) {
                Painter.paintText(bitmap, pe);
            }
        }

        //如果选择了显示辅助线框，并且该组件没有被隐藏，那么久绘制辅助线框
        if (WHhelper) {
            for (ProductElement pe : productElementList) {
                if(pe.getVisibility() == ProductElement.GONE){
                    continue;
                }
                Painter.paintClickHelper(bitmap, pe);
            }
        }

        display.setImageBitmap(bitmap);
    }


    /**
     * 修改属性值并更新
     * */
    public void changeProperties(ProductElement p, String name, String value){
        if(p.getProperties() == null){
            return;
        }
        List<ProductProperties> productPropertiesList = p.getProperties();
        for (ProductProperties productProperties :productPropertiesList){
            if(!name.equals(productProperties.getPropertyName())){
                continue;
            }
            productProperties.setPropertyValue(value);
        }
    }


    /**
     * 事件
     * */
    public void startEventActivity(int id){
        //这里需要区分一下是谁调起的，其中id全局为-1，其余的为组件排序号
        Intent intent = new Intent(this, EventActivity.class);
        intent.putExtra(ID,id);
        startActivityForResult(intent, INTENT_FOR_ACTION);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logs.d("slw:"+requestCode+"/"+resultCode);
        if(INTENT_FOR_ACTION == requestCode){
            if(RESULT_OK == resultCode){
                String subName = data.getStringExtra(ACTION_SUB_EVENT);
                String actionData = data.getStringExtra(ACTION_EVENT_DATA);
                int id = data.getIntExtra(ID,-2);

                //是否是一次合法的回传
                if(id == -2){
                    Toasts.toto("intent value is not valid");
                    return;
                }

                //分情况来设置响应事件
                if(id == -1){
                    productView.setAction(IMAGE_CLICK+"/"+subName+"/"+actionData);
                }else {
                    productElementList.get(id).setAction(IMAGE_CLICK+"/"+subName+"/"+actionData);
                }
                EventBus.getDefault().post(BaseConfig.BUS.UPDATA_ACTION);
            }
        }
    }
}
