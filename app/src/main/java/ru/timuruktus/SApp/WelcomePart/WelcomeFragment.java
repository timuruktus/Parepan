package ru.timuruktus.SApp.WelcomePart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;
import ru.timuruktus.SApp.MagazinePart.MagazineFragment;
import ru.timuruktus.SApp.MainPart.EChangeFragment;
import ru.timuruktus.SApp.R;


public class WelcomeFragment extends BaseFragment implements View.OnClickListener {

    public View rootView;
    public RelativeLayout bookCard, scheduleCard, homeworkCard;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.welcome_fragment, container, false);
        WelcomePresenter welcomePresenter = new WelcomePresenter(this);
        initAllViews();
        return rootView;
    }

    private void initAllViews(){
        bookCard = (RelativeLayout) rootView.findViewById(R.id.bookCard);
        bookCard.setOnClickListener(this);
        scheduleCard = (RelativeLayout) rootView.findViewById(R.id.scheduleCard);
        scheduleCard.setOnClickListener(this);
        homeworkCard = (RelativeLayout) rootView.findViewById(R.id.homeworkCard);
        homeworkCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.bookCard){
            EventBus.getDefault().post(new EChangeFragment(new MagazineFragment(), true));
        }else if(id == R.id.scheduleCard){
            //EventBus.getDefault().post(new EChangeFragment(new ScheduleFragment(), true));
            Toast.makeText(rootView.getContext(), R.string.unavailable_function, Toast.LENGTH_SHORT).show();
        }else if(id == R.id.homeworkCard){
            Toast.makeText(rootView.getContext(), R.string.unavailable_function, Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void eventCallback(BaseEvent event) {

    }
}
