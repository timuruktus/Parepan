package ru.timuruktus.SApp.MagazinePart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import ru.timuruktus.SApp.LocalData.ECheckDownload;
import ru.timuruktus.SApp.LocalDataEvent;
import ru.timuruktus.SApp.R;


public class MagazineCellAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater lInflater;
    private ArrayList<Magazine> magazines = null;
    private Button downloadPDF;
    private Button downloadText;
    private Button readText;
    private Button readPDF;
    private ArrayList<Button> buttons =  new ArrayList<>();
    private ArrayList<View> views =  new ArrayList<>();
    private RelativeLayout cell;
    private TextView viewTitle,viewViewsCount,viewPreview;
    private final int BACKGROUND_COLOR_DISABLED = 0xAA191919;
    private final int ALPHA_INVISIBLE = 0;
    private final int ALPHA_VISIBLE = 1;
    private final int DOWNLOAD_TEXT_COLOR = 0xffffbb33;
    private final int DOWNLOAD_PDF_COLOR = 0xff99cc00;
    private final int READ_COLOR = 0xffff4444;

    MagazineCellAdapter(Context context, ArrayList<Magazine> magazines) {
        this.context = context;
        this.magazines = magazines;
        lInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        EventBus.getDefault().register(this);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return magazines.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return magazines.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.magazine_cell, parent, false);
            views.add(view);
        }

        Magazine magazine = getProduct(position);

        String viewsCount = String.valueOf(magazine.getViewsCount());
        String title = magazine.getTitle();
        String imageURL = magazine.getTitleImage();
        String preview = magazine.getPreview();
        viewTitle = (TextView) view.findViewById(R.id.title);
        viewTitle.setText(title);
        viewViewsCount = (TextView) view.findViewById(R.id.viewsCount);
        viewViewsCount.setText(viewsCount);
        viewPreview = (TextView) view.findViewById(R.id.preview);
        viewPreview.setText(preview);
        viewPreview.setOnClickListener(getViewsClickListener(position));
        ImageView magazineImage =  (ImageView) view.findViewById(R.id.magazineImage);
        Picasso.with(context).load(imageURL).into(magazineImage);
        cell = (RelativeLayout) view.findViewById(R.id.cell);
        cell.setOnClickListener(getViewsClickListener(position));

        downloadPDF = (Button) view.findViewById(R.id.downloadPDF);
        downloadPDF.setOnClickListener(getViewsClickListener(position));
        downloadText = (Button) view.findViewById(R.id.downloadText);
        downloadText.setOnClickListener(getViewsClickListener(position));
        readText = (Button) view.findViewById(R.id.readText);
        readText.setOnClickListener(getViewsClickListener(position));
        readPDF = (Button) view.findViewById(R.id.readPDF);
        readPDF.setOnClickListener(getViewsClickListener(position));
        setPreviewMode(position);
        setButtonsColors(magazine, view);
        return view;
    }


    private void isTextDownload(String objectId, Button b){
        //TODO EventBus to LocalData package
        EventBus.getDefault().post(new ECheckDownload(this, objectId, b));
    }

    private void isPDFDownload(String objectId, Button b){
        //TODO EventBus to LocalData package
        EventBus.getDefault().post(new ECheckDownload(this, objectId, b));
    }

    @Subscribe
    public void LocalDataEventsListener(LocalDataEvent event){
        //TODO: Handle events
        if(event instanceof ESetReadButtonEnabled){
            ESetReadButtonEnabled currentEvent = (ESetReadButtonEnabled) event;
            setButtonEnabled(currentEvent.getB(), currentEvent.isEnabled());
        }
    }

    private void setButtonsColors(Magazine m, View v){
            if(m.getPdfUrl() == null || m.getPdfUrl().equals("")){
                setButtonEnabled(getButton(v, R.id.downloadPDF), false);
                setButtonEnabled(getButton(v, R.id.readPDF), false);
            }else{
                isPDFDownload(m.getObjectId(), getButton(v, R.id.readPDF));
            }
            if(m.getTextUrl() == null || m.getTextUrl().equals("")){
                setButtonEnabled(getButton(v, R.id.downloadText), false);
                setButtonEnabled(getButton(v, R.id.readText), false);
            }else{
                isTextDownload(m.getObjectId(), getButton(v, R.id.readText));
            }
    }

    private void setButtonEnabled(Button b, boolean enabled){
        b.setEnabled(enabled);
        if(enabled){
            setActivatedButtonColor(b);
        }else{
            setDisabledColor(b);
        }
    }

    private void setActivatedButtonColor(Button b){
        if (b.equals(readText) || b.equals(readPDF)){
            b.setBackgroundColor(READ_COLOR);
        }else if(b.equals(downloadPDF)){
            b.setBackgroundColor(DOWNLOAD_PDF_COLOR);
        }else if(b.equals(downloadText)){
            b.setBackgroundColor(DOWNLOAD_TEXT_COLOR);
        }
    }

    private void setDisabledColor(View v){
        v.setBackgroundColor(BACKGROUND_COLOR_DISABLED);
    }

    private String getMagazineId(int position){
        return magazines.get(position).getObjectId() + "";
    }

    // товар по позиции
    private Magazine getProduct(int position) {
        return (Magazine) getItem(position);
    }

    private void switchShowMode(int viewPosition){
        if(isDownloadMode(viewPosition)){
            setPreviewMode(viewPosition);
        }else{
            setDownloadMode(viewPosition);
        }
    }

    private void setDownloadMode(int viewPosition){
        setMode(viewPosition, false);
    }

    private void setPreviewMode(int viewPosition){
        setMode(viewPosition, true);
    }

    private void setMode(int viewPosition, boolean enable){
        View currentView = views.get(viewPosition);
        TextView currentViewPreview = (TextView) currentView.findViewById(R.id.preview);
        enableView(currentViewPreview, enable);
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(getButton(currentView, R.id.downloadPDF));
        buttons.add(getButton(currentView, R.id.downloadText));
        buttons.add(getButton(currentView, R.id.readPDF));
        buttons.add(getButton(currentView, R.id.readText));
        for(Button b : buttons) {
            enableView(b, !enable);
        }
    }

    private Button getButton(View currentView, int resId){
        return (Button) currentView.findViewById(resId);
    }

    private boolean isDownloadMode(int viewPosition){
        View v = views.get(viewPosition);
        return v.findViewById(R.id.downloadPDF).isEnabled();
    }

    private void enableView(View v, boolean enable){
        v.setEnabled(enable);
        if(enable){
            v.setAlpha(ALPHA_VISIBLE);
        }else{
            v.setAlpha(ALPHA_INVISIBLE);
        }
    }

    private View.OnClickListener getViewsClickListener(final int pos) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if(id == R.id.downloadPDF){
                    //TODO
                }else if(id == R.id.downloadText){

                }else if(id == R.id.readPDF){

                }else if(id == R.id.readText){

                }else if (id == R.id.cell){
                    switchShowMode(pos);
                }else if(id == R.id.preview){
                    switchShowMode(pos);
                }
            }
        };
    }
}
