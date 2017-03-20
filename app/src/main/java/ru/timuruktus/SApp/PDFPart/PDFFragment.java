package ru.timuruktus.SApp.PDFPart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;

import java.io.File;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;
import ru.timuruktus.SApp.R;

public class PDFFragment extends BaseFragment{

    public View rootView;
    File file;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView =
                inflater.inflate(R.layout.pdf_fragment, container, false);

        Bundle bundle = this.getArguments();
        loadMagazine(bundle.getString("Path"));
        return rootView;
    }

    public void loadMagazine(String path){
        file = new File(path);
        PDFView pdfView = (PDFView) rootView.findViewById(R.id.pdfView);
        pdfView.fromFile(file)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .onLoad(createLoadCompleteListener())
                .onPageChange(createOnPageChangeListener())
                .onPageScroll(createOnPageScrollListener())
                .onError(createOnErrorListener())
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .load();
    }

    private OnLoadCompleteListener createLoadCompleteListener(){
        return null;
    }

    private OnPageChangeListener createOnPageChangeListener(){
        return null;
    }

    private OnPageScrollListener createOnPageScrollListener(){
        return null;
    }

    private OnErrorListener createOnErrorListener(){
        return null;
    }

    @Override
    public void onStop(){
        super.onStop();
        file = null;
    }

    @Override
    public void eventCallback(BaseEvent event) {

    }
}
