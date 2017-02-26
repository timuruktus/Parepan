package ru.timuruktus.parepan.LoginPart;

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

import org.greenrobot.eventbus.EventBus;

import fr.ganfra.materialspinner.MaterialSpinner;
import ru.timuruktus.parepan.BaseFragment;
import ru.timuruktus.parepan.MainPart.EChangeToolbarVisible;
import ru.timuruktus.parepan.MainPart.EOnFragmentChanged;
import ru.timuruktus.parepan.R;

public class LoginFragment extends BaseFragment implements View.OnClickListener {

    MaterialSpinner citySpinner, schoolSpinner;
    Button continueButton;
    public View rootView;
    String city, school;
    private static final String[] CITIES = {
            "Моего города нет в списке", "Екатеринбург"};
    private static final String[] SCHOOLS_EKB = {
            "Моей школы нет в списке", "Гимназия №177"};
    private static final String[] SCHOOLS_NO = {
            "Моей школы нет в списке"};
    private static final String[] SCHOOLS_EMPTY = {};


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

        continueButton.setAlpha(0);
        schoolSpinner.setAlpha(0);

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_item, CITIES);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                if(selectedItemPosition != -1) {
                    city = citySpinner.getSelectedItem().toString();
                    loadSecondSpinner(city);
                    enableView(schoolSpinner, true);
                }else{
                    loadSecondSpinner("");
                    city = "";
                    enableView(schoolSpinner, false);
                    enableView(continueButton, false);
                    school = "";
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });

        continueButton.setOnClickListener(this);

    }

    private void loadSecondSpinner(String city){
        ArrayAdapter<String> schoolAdapter;
        if(city.equals("Екатеринбург")) {
            schoolAdapter = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_item, SCHOOLS_EKB);
        }else if(city.equals("Моего города нет в списке")){
            schoolAdapter = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_item, SCHOOLS_NO);
        }else{
            schoolAdapter = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_item, SCHOOLS_EMPTY);
        }

        schoolAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolSpinner.setAdapter(schoolAdapter);
        schoolSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                if(selectedItemPosition != -1) {
                    school = schoolSpinner.getSelectedItem().toString();
                    enableView(continueButton, true);
                }else{
                    enableView(continueButton, false);
                    school = "";
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });
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
            EventBus.getDefault().post(new ELoginConfirmed(city,school, this));
        }
    }

    //ELoginConfirmed
    public void eventCallback(){
        Toast.makeText(rootView.getContext(), R.string.login_your_data, Toast.LENGTH_LONG).show();
    }


}
