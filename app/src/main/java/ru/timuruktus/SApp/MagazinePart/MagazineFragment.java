package ru.timuruktus.SApp.MagazinePart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;
import ru.timuruktus.SApp.R;
import ru.timuruktus.SApp.ToolbarEvents.ERefreshMagazinesList;


public class MagazineFragment extends BaseFragment {

    public View rootView;
    ListView magazinesListView;
    MagazinePresenter magazinePresenter;
    MagazineCellAdapter adapter;
    private ArrayList<Magazine> magazinesArray = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.magazine_fragment, container, false);
        magazinePresenter = new MagazinePresenter(this);
        initAllViews();
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new LEGetMagazines(this, false));
        return rootView;
    }

    @Override
    public void eventCallback(BaseEvent event) {
        if(event instanceof LEGetMagazines){
            LEGetMagazines currentEvent = (LEGetMagazines) event;
            magazinesArray = currentEvent.getMagazines();
            adapter = new MagazineCellAdapter(magazinesListView.getContext(), magazinesArray);
            if(!currentEvent.isFromWeb()) {
                magazinesListView.setAdapter(adapter);
            }else{
                magazinesListView.setAdapter(adapter);
            }
        }
    }

    @Subscribe
    public void refreshMagazinesList(ERefreshMagazinesList event){
        EventBus.getDefault().post(new LEGetMagazines(this, true));
        Log.d("mytag", "MagazineFragment.refreshMagazinesList() event handled");
    }

    private void initAllViews(){
        magazinesListView = (ListView) rootView.findViewById(R.id.magazinesListView);
    }

    @Override
    public void onStop(){
        magazinePresenter.detachListeners();
        super.onStop();
    }


}
