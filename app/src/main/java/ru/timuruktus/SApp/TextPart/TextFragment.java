package ru.timuruktus.SApp.TextPart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;
import ru.timuruktus.SApp.R;


public class TextFragment extends BaseFragment {

    public View rootView;
    public TextView titleText;
    public TextView text;
    private TextPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.text_fragment, container, false);

        Bundle bundle = this.getArguments();
        loadMagazine(bundle.getString("Path"));
        return rootView;
    }

    public void setTitleText(String text){

    }

    public void setText(String[] text){

    }

    public void loadMagazine(String textPath){
        WebView webView = (WebView) rootView.findViewById(R.id.webView);
        webView.loadUrl(textPath);
    }









    @Override
    public void eventCallback(BaseEvent event) {

    }


}
