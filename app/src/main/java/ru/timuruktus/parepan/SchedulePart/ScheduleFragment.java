package ru.timuruktus.parepan.SchedulePart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.timuruktus.parepan.BaseFragment;
import ru.timuruktus.parepan.R;

public class ScheduleFragment extends BaseFragment {

    public View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.schedule_fragment, container, false);

        return rootView;
    }
}
