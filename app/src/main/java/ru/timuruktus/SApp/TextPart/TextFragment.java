package ru.timuruktus.SApp.TextPart;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;
import ru.timuruktus.SApp.MainPart.EChangeToolbarVisible;
import ru.timuruktus.SApp.R;


public class TextFragment extends BaseFragment {

    public View rootView;
    public TextView titleText;
    public TextView text;
    private TextPresenter presenter;
    private LinearLayout linearLayout;
    private static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    private static final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static final int BLACK = 0xFF000000;
    private static final int GRAY = 0xFF777777;
    LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
            MATCH_PARENT, WRAP_CONTENT);

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.text_fragment, container, false);
        presenter = new TextPresenter();
        Bundle bundle = this.getArguments();
        loadMagazine(bundle.getString("Path"));

        return rootView;
    }

    public void setTitleText(String text){
        linearLayout = (LinearLayout) rootView.findViewById(R.id.textLayout);
        int margin = convertDpToPx(10);
        int textHeight = convertDpToPx(8);
        lParams.setMargins(margin, margin * 3, margin * 2, 0);
        TextView title = new TextView(rootView.getContext());
        title.setText(text);
        title.setGravity(View.TEXT_ALIGNMENT_CENTER);
        title.setTextColor(BLACK);
        title.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "Roboto-MediumItalic.ttf"));
        title.setTextSize(textHeight);
        linearLayout.addView(title, lParams);
        presenter.detachListener();
    }

    public void setText(String text){
        setText(text,false,false);
    }

    public void setText(String text, boolean isBold, boolean isItalic){
        //TODO: \n
        text = text.replaceAll("\\s+", " ");
        text = text.replaceAll("/n", "\n");
        text = text.trim();
        if(text.equals(" ") || text.equals("")) return;
        Log.d("mytag", "TextFragment.setText() text = " + text);
        int margin = convertDpToPx(10);
        int textHeight = convertDpToPx(6);
        lParams.setMargins(margin, margin * 2, margin, 0);
        TextView textView = new TextView(rootView.getContext());
        textView.setText(text);
        textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextColor(GRAY);
        if(isBold && isItalic){
            textView.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "Roboto-BoldItalic.ttf"));
        }else if(isBold) {
            textView.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "Roboto-Bold.ttf"));
        }else if(isItalic){
            textView.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "Roboto-LightItalic.ttf"));
        }else{
            textView.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "Roboto-Regular.ttf"));
        }
        textView.setTextSize(textHeight);
        linearLayout.addView(textView, lParams);
    }

    public void setImage(String url){
        int margin = convertDpToPx(15);
        lParams.setMargins(margin, margin * 2, margin, 0);
        ImageView image = new ImageView(rootView.getContext());
        linearLayout.addView(image, lParams);
        Picasso.with(rootView.getContext()).load(url).into(image);
    }

    public void loadMagazine(String textPath){
        EventBus.getDefault().post(new EGetText(textPath, this));
    }

    @Override
    public void onResume(){
        super.onResume();
        EventBus.getDefault().post(new EChangeToolbarVisible(false));
    }

    @Override
    public void eventCallback(BaseEvent event) {

    }


}
