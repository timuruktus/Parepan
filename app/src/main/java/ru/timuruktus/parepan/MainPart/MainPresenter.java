package ru.timuruktus.parepan.MainPart;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ru.timuruktus.parepan.BookPart.BookFragment;
import ru.timuruktus.parepan.R;
import ru.timuruktus.parepan.SchedulePart.ScheduleFragment;
import ru.timuruktus.parepan.WelcomePart.WelcomeFragment;

import static android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE;

public class MainPresenter {

    private MainActivity mainActivity;

    public MainPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void changeFragment(EChangeFragment event){
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(event.isAddToBackStack()) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.fragmentContainer, event.getFragment());
        fragmentTransaction.setTransition(TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    @Subscribe
    public void changeNavItem(EOnFragmentChanged event){
        if(event.getFragment() instanceof BookFragment){
            mainActivity.navigationView.setCheckedItem(R.id.nav_book);
            Log.d("mytag", "book event");
        }else if(event.getFragment() instanceof ScheduleFragment){
            mainActivity.navigationView.setCheckedItem(R.id.nav_schedule);
        }else if(event.getFragment() instanceof WelcomeFragment){
            mainActivity.navigationView.setCheckedItem(R.id.nav_main);
        }
    }
}
