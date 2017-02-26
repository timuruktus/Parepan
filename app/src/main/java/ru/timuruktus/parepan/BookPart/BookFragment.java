package ru.timuruktus.parepan.BookPart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.timuruktus.parepan.BaseFragment;
import ru.timuruktus.parepan.R;


public class BookFragment extends BaseFragment {

    public View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.book_fragment, container, false);

        return rootView;
    }
}
