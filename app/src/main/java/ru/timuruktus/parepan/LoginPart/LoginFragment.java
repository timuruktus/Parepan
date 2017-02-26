package ru.timuruktus.parepan.LoginPart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import ru.timuruktus.parepan.BaseFragment;
import ru.timuruktus.parepan.MainPart.EChangeToolbarVisible;
import ru.timuruktus.parepan.MainPart.EOnFragmentChanged;
import ru.timuruktus.parepan.R;

public class LoginFragment extends BaseFragment {

    TextView loginChooseText;

    private static final String[] CITIES = {
            "Моего города нет в списке", "Екатеринбург",};

    public View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.login_fragment, container, false);

        return rootView;
    }

    void loadAllElements(){
        loginChooseText = (TextView) rootView.findViewById(R.id.loginChooseText);

    }

    @Override
    public void onResume(){
        super.onResume();
        //changeFonts(this.getView());
        EventBus.getDefault().post(new EOnFragmentChanged(this));
        EventBus.getDefault().post(new EChangeToolbarVisible(false));
        Log.d("BaseFragment", "Event was sent");
    }

}
