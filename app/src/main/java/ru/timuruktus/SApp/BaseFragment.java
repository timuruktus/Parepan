package ru.timuruktus.SApp;

import android.app.Fragment;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import ru.timuruktus.SApp.MainPart.EChangeToolbarVisible;
import ru.timuruktus.SApp.MainPart.EOnFragmentChanged;


public abstract class BaseFragment extends Fragment {

    @Override
    public void onResume(){
        super.onResume();
        //changeFonts(this.getView());
        EventBus.getDefault().post(new EOnFragmentChanged(this));
        EventBus.getDefault().post(new EChangeToolbarVisible(true));
        Log.d("BaseFragment", "Event was sent");
    }


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

    public void enableView(View v, boolean enable){
        v.setEnabled(enable);
        if(enable){
            v.setAlpha(1);
        }else{
            v.setAlpha(0);
        }
    }

    public abstract void eventCallback(BaseEvent event);
}
