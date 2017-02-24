package ru.timuruktus.parepan;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class BaseFragment extends Fragment {


    /**
     * Changes fonts for all view's in Fragment's rootView
     * @param rootView - Fragment's rootView
     */
    public void changeFonts(View rootView){
        Typeface font = Typeface.createFromAsset(rootView.getContext().getAssets(), "OpenSans.ttf");
        try {
            ViewGroup container = (ViewGroup) rootView;
            int childViewCount = container.getChildCount();
            for (int i=0; i<childViewCount;i++){
                View v = container.getChildAt(i);
                if (v instanceof TextView) {
                    ((TextView) v).setTypeface(font);
                } else if (v instanceof ViewGroup)
                    changeFonts(rootView);
            }
        } catch (ClassCastException e){
            //Not a viewGroup here
        } catch (NullPointerException e){
            //Root view is null
        }
    }
}
