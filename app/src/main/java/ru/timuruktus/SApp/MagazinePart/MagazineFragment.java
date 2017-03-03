package ru.timuruktus.SApp.MagazinePart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;
import ru.timuruktus.SApp.R;


public class MagazineFragment extends BaseFragment {

    public View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.magazine_fragment, container, false);
        MagazinePresenter magazinePresenter = new MagazinePresenter(this);
        EventBus.getDefault().post(new EGetUserLoginInfo());
        return rootView;
    }

    @Override
    public void eventCallback(BaseEvent event) {
        if(event instanceof EGetUserLoginInfo){

        }
    }
}
