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

import ru.timuruktus.SApp.LocalData.Database;
import ru.timuruktus.SApp.LocalData.ECheckPDFDownload;
import ru.timuruktus.SApp.LocalData.ECheckTextDownload;
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
    private RelativeLayout cell;
    private TextView viewTitle,viewViewsCount,viewPreview;
    private final int BACKGROUND_COLOR_DISABLED = 0x616161;
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
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.magazine_cell, parent, false);
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
        ImageView magazineImage =  (ImageView) view.findViewById(R.id.magazineImage);
        Picasso.with(context).load(imageURL).into(magazineImage);
        cell = (RelativeLayout) view.findViewById(R.id.cell);
        cell.setOnClickListener(viewsClickListener);

        buttons.add(downloadPDF = (Button) view.findViewById(R.id.downloadPDF));
        buttons.add(downloadText = (Button) view.findViewById(R.id.downloadText));
        buttons.add(readText = (Button) view.findViewById(R.id.readText));
        buttons.add(readPDF = (Button) view.findViewById(R.id.readPDF));
        createListenersForButton();
        setPreviewMode();
        setStartButtonsColors();
        return view;
    }


    private void isTextDownload(String objectId){
        //TODO EventBus to LocalData package
        Database d = new Database();
        EventBus.getDefault().post(new ECheckTextDownload(this, objectId));
    }

    private void isPDFDownload(String objectId){
        //TODO EventBus to LocalData package
        EventBus.getDefault().post(new ECheckPDFDownload(this, objectId));
    }

    @Subscribe
    private void LocalDataEventsListener(LocalDataEvent event){
        //TODO: Handle events
    }

    private void setStartButtonsColors(){
        for(Magazine m : magazines){
            setButtonsColors(m);
        }
    }



    private void setButtonsColors(Magazine m){
            if(m.getPdfUrl() == null || m.getPdfUrl().equals("")){
                setButtonEnabled(downloadPDF, false);
                setButtonEnabled(readPDF, false);
            }else{
                isPDFDownload(m.getObjectId());
            }
            if(m.getTextUrl() == null || m.getTextUrl().equals("")){
                setButtonEnabled(downloadText, false);
                setButtonEnabled(readText, false);
            }else{
                isTextDownload(m.getObjectId());
            }
    }

    private void setButtonEnabled(Button b, boolean enabled){
        b.setEnabled(enabled);
        if(enabled){
            if (b.equals(readText)){
                readText.setBackgroundColor(READ_COLOR);
            }else if(b.equals(readPDF)){
                readPDF.setBackgroundColor(READ_COLOR);
            }else if(b.equals(downloadPDF)){
                downloadPDF.setBackgroundColor(DOWNLOAD_PDF_COLOR);
            }else if(b.equals(downloadText)){
                downloadText.setBackgroundColor(DOWNLOAD_TEXT_COLOR);
            }
        }else{
            if (b.equals(readText)){
                setDisabledColor(readText);
            }else if(b.equals(readPDF)){
                setDisabledColor(readPDF);
            }else if(b.equals(downloadPDF)){
                setDisabledColor(downloadPDF);
            }else if(b.equals(downloadText)){
                setDisabledColor(downloadText);
            }
        }
    }

    private void setDisabledColor(View v){
        v.setBackgroundColor(BACKGROUND_COLOR_DISABLED);
    }

    private String getMagazineId(int position){
        return magazines.get(position).getObjectId() + "";
    }

    private void createListenersForButton(){
        for(Button b : buttons){
            b.setOnClickListener(viewsClickListener);
        }
    }

    // товар по позиции
    private Magazine getProduct(int position) {
        return (Magazine) getItem(position);
    }

    private void switchShowMode(){
        if(isDownloadMode()){
            setPreviewMode();
        }else{
            setDownloadMode();
        }
    }

    private void setDownloadMode(){
        enableView(viewPreview, false);
        for(Button b : buttons){
            enableView(b, true);
        }
    }

    private void setPreviewMode(){
        enableView(viewPreview, true);
        for(Button b : buttons){
            enableView(b, false);
        }
    }

    private boolean isDownloadMode(){
        return downloadPDF.isEnabled();
    }


    private View.OnClickListener viewsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id == R.id.downloadPDF){
                //TODO
            }else if(id == R.id.downloadText){

            }else if(id == R.id.readPDF){

            }else if(id == R.id.readText){

            }else if (id == R.id.cell){
                switchShowMode();
            }
        }
    };

    private void enableView(View v, boolean enable){
        v.setEnabled(enable);
        if(enable){
            v.setAlpha(ALPHA_VISIBLE);
        }else{
            v.setAlpha(ALPHA_INVISIBLE);
        }
    }

    private boolean isViewEnabled(View v){
        return v.isEnabled();
    }
}
