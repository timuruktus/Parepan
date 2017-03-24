package ru.timuruktus.SApp.LoginPart;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import org.greenrobot.eventbus.EventBus;

import fr.ganfra.materialspinner.MaterialSpinner;
import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;
import ru.timuruktus.SApp.MainPart.EChangeToolbarVisible;
import ru.timuruktus.SApp.MainPart.EOnFragmentChanged;
import ru.timuruktus.SApp.R;

public class LoginFragment extends BaseFragment implements View.OnClickListener {

    MaterialSpinner citySpinner, schoolSpinner;
    Button continueButton;
    public View rootView;
    String city, school;
    private static final String[] CITIES = {
            "Моего города нет в списке", "Екатеринбург"};
    private static final String[] SCHOOLS_EKB = {
            "Моей школы нет в списке", "177"};
    private static final String[] SCHOOLS_NO = {
            "Моей школы нет в списке"};
    private static final String[] SCHOOLS_EMPTY = {};
    private final int ALPHA_INVISIBLE = 0;
    private final int FIRST_SPINNER_ITEM_POSITION = -1;
    private final boolean DISABLE = false;
    private final boolean ENABLE = true;
    public final String SENDER_ID = "772541416893";
    public static final String APP_PREFERENCES_DEVICE_REGISTERED = "Registered";
    SharedPreferences mSettings;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.login_fragment, container, false);
        LoginPresenter loginPresenter = new LoginPresenter(this);
        loadAllElements();
        return rootView;
    }

    void loadAllElements() {
        citySpinner = (MaterialSpinner) rootView.findViewById(R.id.citySpinner);
        continueButton = (Button) rootView.findViewById(R.id.continueButton);
        schoolSpinner = (MaterialSpinner) rootView.findViewById(R.id.schoolSpinner);
        continueButton.setAlpha(ALPHA_INVISIBLE);
        schoolSpinner.setAlpha(ALPHA_INVISIBLE);
        continueButton.setOnClickListener(this);
        loadFirstSpinner();
    }

    private void loadFirstSpinner(){
        ArrayAdapter<String> cityAdapter = createCitySpinnerAdapter();
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);
        citySpinner.setOnItemSelectedListener(createFirstSpinnerListener());
    }

    private ArrayAdapter<String> createCitySpinnerAdapter(){
        return new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_item, CITIES);
    }

    private AdapterView.OnItemSelectedListener createFirstSpinnerListener(){
        return new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                    View itemSelected, int selectedItemPosition, long selectedId) {
                if(selectedItemPosition != FIRST_SPINNER_ITEM_POSITION) {
                    city = citySpinner.getSelectedItem().toString();
                    loadSecondSpinner(city);
                    enableView(schoolSpinner, ENABLE);
                }else{
                    unsetSetting(city);
                    unsetSetting(school);
                    enableView(schoolSpinner, DISABLE);
                    enableView(continueButton, DISABLE);
                }
            }

        public void onNothingSelected(AdapterView<?> parent) {}
        };
    }


    private void loadSecondSpinner(String city){
        ArrayAdapter<String> schoolAdapter = setAdapterItems(city);
        schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolSpinner.setAdapter(schoolAdapter);
        schoolSpinner.setOnItemSelectedListener(createSecondSpinnerListener());
    }

    private AdapterView.OnItemSelectedListener createSecondSpinnerListener(){
        return new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                if(selectedItemPosition != FIRST_SPINNER_ITEM_POSITION) {
                    school = schoolSpinner.getSelectedItem().toString();
                    enableView(continueButton, ENABLE);
                }else{
                    enableView(continueButton, DISABLE);
                    unsetSetting(school);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        };
    }

    private ArrayAdapter<String> setAdapterItems(String city){
        if(city.equals("Екатеринбург")){
            return new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_item, SCHOOLS_EKB);
        }else{
            return new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_item, SCHOOLS_NO);
        }
    }


    @Override
    public void onResume(){
        super.onResume();
        //changeFonts(this.getView());
        EventBus.getDefault().post(new EOnFragmentChanged(this));
        EventBus.getDefault().post(new EChangeToolbarVisible(false));
        Log.d("BaseFragment", "Event was sent");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.continueButton){
            registerDevicePushNotifications();
            EventBus.getDefault().post(new ELoginConfirmed(city,school, this));

        }
    }

    public void registerDevicePushNotifications(){
        String channel = "";
        if(city.equals(CITIES[0])) {
            channel = "default";
        }else if(city.equals(CITIES[1])){
            channel = "Yekaterinburg_";
        }
        channel += school;
        Backendless.Messaging.unregisterDeviceOnServer(gerUnregisterCallback());
        Backendless.Messaging.registerDevice(SENDER_ID, channel, getRegisterCallback());

    }

    private AsyncCallback<Boolean> gerUnregisterCallback(){
        return new AsyncCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean response) {
                Log.d("mytag", "LoginFragment.gerUnregisterCallback() unregistration success " + response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d("mytag", "LoginFragment.gerUnregisterCallback() unregistration fault " + fault.toString());
            }
        };
    }

    private AsyncCallback<Void> getRegisterCallback(){
        return new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                Log.d("mytag", "LoginFragment.getRegisterCallback() device registered");
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d("mytag", "LoginFragment.getRegisterCallback() registration fault " + fault.toString());
            }
        };
    }

    //ELoginConfirmed
    @Override
    public void eventCallback(BaseEvent event){
        Toast.makeText(rootView.getContext(), R.string.login_your_data, Toast.LENGTH_LONG).show();
    }

    private void unsetSetting(String setting){
        setting = "";
    }


}
