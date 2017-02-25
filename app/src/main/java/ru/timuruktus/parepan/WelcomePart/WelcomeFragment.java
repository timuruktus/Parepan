package ru.timuruktus.parepan.WelcomePart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import ru.timuruktus.parepan.BaseFragment;
import ru.timuruktus.parepan.BookPart.BookFragment;
import ru.timuruktus.parepan.MainPart.EChangeFragment;
import ru.timuruktus.parepan.R;
import ru.timuruktus.parepan.SchedulePart.ScheduleFragment;


public class WelcomeFragment extends BaseFragment implements View.OnClickListener {

    public View rootView;
    public CardView bookCard, scheduleCard;

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
        bookCard = (CardView) rootView.findViewById(R.id.bookCard);
        bookCard.setOnClickListener(this);
        scheduleCard = (CardView) rootView.findViewById(R.id.scheduleCard);
        scheduleCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.bookCard){
            EventBus.getDefault().post(new EChangeFragment(new BookFragment(), true));
        }else if (id == R.id.scheduleCard){
            EventBus.getDefault().post(new EChangeFragment(new ScheduleFragment(), true));
        }

    }


}
