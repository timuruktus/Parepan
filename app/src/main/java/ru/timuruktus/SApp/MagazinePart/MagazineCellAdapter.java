package ru.timuruktus.SApp.MagazinePart;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.thin.downloadmanager.DefaultRetryPolicy;
import com.thin.downloadmanager.DownloadRequest;
import com.thin.downloadmanager.DownloadStatusListenerV1;
import com.thin.downloadmanager.ThinDownloadManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import ru.timuruktus.SApp.LocalData.EMagazineDownloaded;
import ru.timuruktus.SApp.LocalDataEvent;
import ru.timuruktus.SApp.R;
import ru.timuruktus.SApp.ToolbarEvents.ERefreshMagazinesList;


public class MagazineCellAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater lInflater;
    private ArrayList<Magazine> magazines = null;
    private Button downloadPDF;
    private Button downloadText;
    private Button readText;
    private Button readPDF;
    private ArrayList<View> views =  new ArrayList<>();
    private RelativeLayout cell;
    private TextView viewTitle,viewViewsCount,viewPreview;
    private final int BACKGROUND_COLOR_DISABLED = 0xAA191919;
    private final int ALPHA_INVISIBLE = 0;
    private final int ALPHA_VISIBLE = 1;
    private final int DOWNLOAD_TEXT_COLOR = 0xffffbb33;
    private final int DOWNLOAD_PDF_COLOR = 0xff99cc00;
    private final int READ_COLOR = 0xffff4444;
    ArrayList<Integer> downloadIds = new ArrayList<>();

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

    public ArrayList<Magazine> getAllViews(){
        return magazines;
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


    private boolean isTextDownload(Magazine magazine){
        return magazine.isDownloadedText();
    }

    private boolean isPDFDownload(Magazine magazine){
        return magazine.isDownloadedPDF();
    }

    @Subscribe
    public void LocalDataEventsListener(LocalDataEvent event){

    }

    private void setButtonsColors(Magazine m, View v){
            if(m.getPdfUrl() == null){
                setButtonEnabled(getButton(v, R.id.downloadPDF), false);
                setButtonEnabled(getButton(v, R.id.readPDF), false);
            }else{
                boolean enable = isPDFDownload(m);
                setButtonEnabled(getButton(v, R.id.readPDF), enable);
            }
            if(m.getTextUrl() == null){
                setButtonEnabled(getButton(v, R.id.downloadText), false);
                setButtonEnabled(getButton(v, R.id.readText), false);
            }else{
                boolean enable = isTextDownload(m);
                setButtonEnabled(getButton(v, R.id.readText), enable);
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
                Magazine m = magazines.get(pos);
                View view = views.get(pos);
                if(id == R.id.downloadPDF){
                    if(!m.isDownloadedPDF()) {
                        startDownload(m, view, true);
                    }else{
                        Toast.makeText(context,R.string.magazine_already_downloaded,Toast.LENGTH_LONG).show();
                    }
                }else if(id == R.id.downloadText){
                    if(!m.isDownloadedPDF()) {
                        startDownload(m, view, false);
                    }else{
                        Toast.makeText(context,R.string.magazine_already_downloaded,Toast.LENGTH_LONG).show();
                    }
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

    private void startDownload(final Magazine magazine, final View v, final boolean PDF){
        String url;
        final ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        final String destinationToDownload = getDestinationToDownload(magazine);
        if(PDF){
            url = magazine.getPdfUrl();
        }else{
            url = magazine.getTextUrl();
        }
        Uri downloadUri = Uri.parse(url);
        Uri destinationUri = Uri.parse(destinationToDownload);
        DownloadRequest downloadRequest = new DownloadRequest(downloadUri)
                .setRetryPolicy(new DefaultRetryPolicy())
                .setDestinationURI(destinationUri).setPriority(DownloadRequest.Priority.HIGH)
                .setStatusListener(new DownloadStatusListenerV1(){
                    @Override
                    public void onDownloadComplete(DownloadRequest downloadRequest){
                        setReadButtonEnabled(PDF, v);
                        EventBus.getDefault().post(new EMagazineDownloaded(destinationToDownload, PDF, magazine));
                        progressBar.setProgress(0);
                        EventBus.getDefault().post(new ERefreshMagazinesList());
                    }

                    @Override
                    public void onDownloadFailed(DownloadRequest downloadRequest, int errorCode, String errorMessage){
                        Toast.makeText(context,R.string.error,Toast.LENGTH_LONG).show();
                        Log.d("mytag", "MagazineCellAdapter.startDownload().onDownloadFailed() error message - " + errorMessage);
                    }

                    @Override
                    public void onProgress(DownloadRequest downloadRequest, long totalBytes, long downloadedBytes, int progress){
                        progressBar.setProgress(progress);
                    }
                });

        ThinDownloadManager downloadManager = new ThinDownloadManager();
        int downloadId = downloadManager.add(downloadRequest);

    }

    private String getDestinationToDownload(Magazine m){
        return context.getExternalCacheDir().toString()+"/" + m.getObjectId();
    }

    private void setReadButtonEnabled(boolean PDF, View v){
        Button button;
        if(PDF){
            button = (Button) v.findViewById(R.id.readPDF);
        }else{
            button = (Button) v.findViewById(R.id.readText);
        }
        MagazineCellAdapter.this.setButtonEnabled(button,true);
    }


}
