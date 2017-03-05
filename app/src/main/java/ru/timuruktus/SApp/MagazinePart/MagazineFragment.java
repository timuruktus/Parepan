package ru.timuruktus.SApp.MagazinePart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;
import ru.timuruktus.SApp.R;


public class MagazineFragment extends BaseFragment {

    public View rootView;
    ListView magazinesListView;
    MagazinePresenter magazinePresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.magazine_fragment, container, false);
        magazinePresenter = new MagazinePresenter(this);
        initAllViews();
        EventBus.getDefault().post(new EGetMagazines(this));
        return rootView;
    }

    @Override
    public void eventCallback(BaseEvent event) {
        if(event instanceof EGetMagazines){
            MagazineCellAdapter adapter = new MagazineCellAdapter(magazinesListView.getContext(), ((EGetMagazines) event).getMagazines());
            magazinesListView.setAdapter(adapter);
        }
    }

    private void initAllViews(){
        magazinesListView = (ListView) rootView.findViewById(R.id.magazinesListView);
    }


}
