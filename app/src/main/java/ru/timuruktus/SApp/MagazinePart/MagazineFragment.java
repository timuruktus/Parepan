package ru.timuruktus.SApp.MagazinePart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

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
            if(currentEvent.isFromWeb()){
                EventBus.getDefault().post(new LEGetMagazines(this, false));
                return;
            }
            magazinesArray = currentEvent.getMagazines();
            adapter = new MagazineCellAdapter(magazinesListView.getContext(), magazinesArray);
            magazinesListView.setAdapter(adapter);
            if(magazinesArray.size() == 0){
                setListViewEnabled(false);
            }else{
                setListViewEnabled(true);
            }
        }
    }

    private void setListViewEnabled(boolean enabled){
        RelativeLayout haveNoMagazines = (RelativeLayout) rootView.findViewById(R.id.have_no_magazines);
        RelativeLayout haveMagazines = (RelativeLayout) rootView.findViewById(R.id.have_magazines);
        if(enabled){
            haveNoMagazines.setVisibility(View.INVISIBLE);
            haveMagazines.setVisibility(View.VISIBLE);
        }else{
            haveNoMagazines.setVisibility(View.VISIBLE);
            haveMagazines.setVisibility(View.INVISIBLE);
            ImageView errorArrow = (ImageView) rootView.findViewById(R.id.error_arrow);
            Animation animation = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.up_to_down);
            errorArrow.startAnimation(animation);
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
