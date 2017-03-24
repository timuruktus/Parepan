package ru.timuruktus.SApp.MainPart;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BasePresenter;
import ru.timuruktus.SApp.LoginPart.LoginFragment;
import ru.timuruktus.SApp.MagazinePart.MagazineFragment;
import ru.timuruktus.SApp.R;
import ru.timuruktus.SApp.SchedulePart.ScheduleFragment;
import ru.timuruktus.SApp.WelcomePart.WelcomeFragment;

import static android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE;

public class MainPresenter implements BasePresenter {

    private MainActivity mainActivity;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_CITY = "City";
    public static final String APP_PREFERENCES_SCHOOL = "School";
    private SharedPreferences settings;
    FragmentTransaction fragmentTransaction;

    MainPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void changeFragment(EChangeFragment event){
        Fragment fragment = event.getFragment();
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        fragmentManager.popBackStack();
        fragmentTransaction = fragmentManager.beginTransaction();
        if(event.isAddToBackStack()) {
            fragmentTransaction.addToBackStack(null);
        }
        setNewFragment(fragment);
        fragmentTransaction.setTransition(TRANSIT_FRAGMENT_FADE);
        changeToolbarVisibility(true);
        fragmentTransaction.commit();
    }

    private void configureToolbar(Fragment fragment){
        disableAllToolbarButtons();
        if(fragment instanceof MagazineFragment) {
            enableToolbarButton(R.id.magazine_refresh, true);
        }else if(fragment instanceof WelcomeFragment){
            disableAllToolbarButtons();
        }else if(fragment instanceof LoginFragment){

        }else if(fragment instanceof ScheduleFragment){

        }
    }

    private void setNewFragment(Fragment fragment){
        if(isJoined() && (fragment instanceof MagazineFragment || fragment instanceof ScheduleFragment)) {
            fragmentTransaction.replace(R.id.fragmentContainer, fragment);
            return;
        }else if (!isJoined() && (fragment instanceof MagazineFragment || fragment instanceof ScheduleFragment)){
            fragmentTransaction.replace(R.id.fragmentContainer, new LoginFragment());
            return;
        }
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
    }

    @Subscribe
    public void changeNavItem(EOnFragmentChanged event){
        configureToolbar(event.getFragment());
        if(event.getFragment() instanceof MagazineFragment){
            mainActivity.navigationView.setCheckedItem(R.id.nav_book);
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
        RelativeLayout fragmentContainer = (RelativeLayout)
                mainActivity.findViewById(R.id.fragmentContainer);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if(visible){
            mainActivity.getSupportActionBar().show();
            int totalMargin = convertDpToPx(56);
            layoutParams.setMargins(0,totalMargin,0,0);
            fragmentContainer.setLayoutParams(layoutParams);
        }else{
            mainActivity.getSupportActionBar().hide();
            layoutParams.setMargins(0,0,0,0);
            fragmentContainer.setLayoutParams(layoutParams);
        }
    }

    private int convertDpToPx(int pixels){
        float dp = mainActivity.getResources().getDisplayMetrics().density;
        return (int) dp * pixels;
    }

    @Subscribe
    public void setToolbarInvisible(EChangeToolbarVisible event){
        changeToolbarVisibility(event.isVisible());
    }


    @Override
    public void eventCallback(BaseEvent event) {

    }

    private void enableToolbarButton(int resId, boolean enabled){
        mainActivity.menu.findItem(resId).setEnabled(enabled).setVisible(enabled);
    }

    private void disableAllToolbarButtons(){
        enableToolbarButton(R.id.magazine_refresh, false);
        enableToolbarButton(R.id.as, false);
    }
}
