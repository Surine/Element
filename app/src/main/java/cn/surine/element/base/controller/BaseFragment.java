package cn.surine.element.base.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Intro：
 * @author sunliwei
 * @date 2019-06-18 16:14
 */
public abstract class BaseFragment extends Fragment {


    /**Host activity*/
    private Activity activity;

    /**
     * override this method return the fragment layout id
     * */
    public abstract int layoutId();

    /**
     * override this method and initialization the fragment
     * @param parent root view of the fragment
     * */
    public abstract void onInit(View parent);


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(activity == null && context instanceof  BaseActivity){
            this.activity = (Activity) context;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(this.activity == null){
            this.activity = activity;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        if(layoutId() != 0){
           view = inflater.inflate(layoutId(),container,false);
        }else{
            view = getViewByFlutter();
        }
        onInit(view);
        return view;
    }

    public View getViewByFlutter() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public <A extends BaseActivity> A activity(){
        return (A)(getActivity() == null ? activity : getActivity());
    }

    /**
     * finish the host activity
     * */
    public void finish(){
        BaseActivity a = activity();
        if(null != a){
            a.finish();
        }
    }

    public void open(Fragment fragment){
        if(null != fragment){
            activity().open(fragment);
        }
    }


    public void close(Fragment fragment){
        if(null != fragment){
            activity().close(fragment);
        }
    }

}
