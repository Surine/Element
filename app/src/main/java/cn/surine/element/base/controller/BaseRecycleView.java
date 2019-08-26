package cn.surine.element.base.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * Intro：自定义RecycleView
 * @author sunliwei
 * @date 2019-05-30 19:34
 */
public class BaseRecycleView extends RecyclerView {

    public BaseRecycleView(@NonNull Context context) {
        super(context);
    }

    public BaseRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseRecycleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 网格布局
     * @param span 列
     * */
    public BaseRecycleView beGrid(int span){
        LayoutManager layoutManager = new GridLayoutManager(getContext(),span);
        setLayoutManager(layoutManager);
        return this;
    }


    public BaseRecycleView beStagger(int span){
        LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        setLayoutManager(layoutManager);
        return this;
    }


    /**
     * 垂直滑动列表
     * */
    @SuppressLint("WrongConstant")
    public BaseRecycleView beLinerV(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        setLayoutManager(layoutManager);
        return this;
    }

    /**
     * 水平滑动列表
     * */
    public BaseRecycleView beLinerH(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(layoutManager);
        return this;
    }

}
