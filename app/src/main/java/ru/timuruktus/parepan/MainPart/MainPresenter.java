package ru.timuruktus.parepan.MainPart;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ru.timuruktus.parepan.BookPart.BookFragment;
import ru.timuruktus.parepan.LoginPart.LoginFragment;
import ru.timuruktus.parepan.R;
import ru.timuruktus.parepan.SchedulePart.ScheduleFragment;
import ru.timuruktus.parepan.WelcomePart.WelcomeFragment;

import static android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE;

public class MainPresenter {

    private MainActivity mainActivity;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_CITY = "City";
    public static final String APP_PREFERENCES_SCHOOL = "School";
    private SharedPreferences settings;

    MainPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void changeFragment(EChangeFragment event){
        Fragment fragment = event.getFragment();
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        fragmentManager.popBackStack();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(event.isAddToBackStack()) {
            fragmentTransaction.addToBackStack(null);
        }
        if(isJoined() && (fragment instanceof BookFragment || fragment instanceof ScheduleFragment)) {
            fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        }else if (!isJoined() && (fragment instanceof BookFragment || fragment instanceof ScheduleFragment)){
            fragmentTransaction.replace(R.id.fragmentContainer, new LoginFragment());
        }else if(fragment instanceof LoginFragment){
            fragmentTransaction.replace(R.id.fragmentContainer, new LoginFragment());
        }
        fragmentTransaction.setTransition(TRANSIT_FRAGMENT_FADE);
        changeToolbarVisibility(true);
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
        }else if(event.getFragment() instanceof LoginFragment){
            mainActivity.navigationView.setCheckedItem(R.id.nav_login);
        }
    }

    /**
     * Checks if user chose his city and school
     * @return - already signed (true), didn't join (false)
     */
    private boolean isJoined(){
        settings = mainActivity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String city = settings.getString(APP_PREFERENCES_CITY, "");
        return !city.equals("");
    }

    private void changeToolbarVisibility(boolean visible){
        if(visible){
            mainActivity.toolbar.setVisibility(View.VISIBLE);
        }else{
            mainActivity.toolbar.setVisibility(View.INVISIBLE);
        }
    }

    @Subscribe
    public void setToolbarInvisible(EChangeToolbarVisible event){
        changeToolbarVisibility(event.isVisible());
    }
}
