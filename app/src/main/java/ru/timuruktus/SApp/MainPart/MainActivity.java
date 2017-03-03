package ru.timuruktus.SApp.MainPart;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.backendless.Backendless;

import org.greenrobot.eventbus.EventBus;

import ru.timuruktus.SApp.LoginPart.LoginFragment;
import ru.timuruktus.SApp.MagazinePart.MagazineFragment;
import ru.timuruktus.SApp.R;
import ru.timuruktus.SApp.SchedulePart.ScheduleFragment;
import ru.timuruktus.SApp.WelcomePart.WelcomeFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    DrawerLayout drawer;
    Toolbar toolbar;
    private final String YOUR_APP_ID = "E8F88DD6-9964-2F7D-FF5C-0443B5ABFD00";
    private final String YOUR_SECRET_KEY = "7C1D5112-2D8B-6EA0-FFE3-9A0498991700";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String appVersion = "v1";
        Backendless.initApp(this, YOUR_APP_ID, YOUR_SECRET_KEY, appVersion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MainPresenter mainPresenter = new MainPresenter(this);
        loadFragment();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_book) {
            EventBus.getDefault().post(new EChangeFragment(new MagazineFragment(), true));
        } else if (id == R.id.nav_schedule) {
            EventBus.getDefault().post(new EChangeFragment(new ScheduleFragment(), true));
        } else if (id == R.id.nav_main) {
            EventBus.getDefault().post(new EChangeFragment(new WelcomeFragment(), true));
        } else if (id == R.id.nav_login) {
            EventBus.getDefault().post(new EChangeFragment(new LoginFragment(), true));
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Load fragment
     */
    private void loadFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WelcomeFragment mainFragment = new WelcomeFragment();
        fragmentTransaction.replace(R.id.fragmentContainer, mainFragment);
        fragmentTransaction.commit();
    }
}
