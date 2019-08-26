package cn.surine.element.ui;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import cn.surine.element.R;
import cn.surine.element.base.controller.BaseActivity;
import cn.surine.element.ui.adpater.MyPagerAdapter;
import cn.surine.element.ui.function_me.MeFragment;
import cn.surine.element.ui.function_widget_lib.WidgetLibFragment;

public class CoreActivity extends BaseActivity {

    private TabLayout tab;
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.activity_core;
    }

    @Override
    public void onInit() {
        titles.add("我的");
        titles.add("库");
        fragmentList.add(MeFragment.abt.getInstance());
        fragmentList.add(WidgetLibFragment.abt.getInstance());
        viewPager = findView(R.id.viewpager);
        tab = findView(R.id.tab);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),fragmentList,titles);
        viewPager.setAdapter(pagerAdapter);
        tab.setupWithViewPager(viewPager);

    }
}
