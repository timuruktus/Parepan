package ru.timuruktus.SApp.LocalData;

import android.util.Log;

import com.orm.query.Condition;
import com.orm.query.Select;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import ru.timuruktus.SApp.MagazinePart.Magazine;

public class Database {

    ArrayList<String> objectIds;

    public Database(){
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void getAllMagazines(EGetMagazinesDB event){
            Log.d("mytag", "Database.getAllMagazines()");
            List<Magazine> magazineDB = Select.from(Magazine.class)
                    .where(Condition.prop("city").eq(event.getCity()),
                            Condition.prop("school").eq(event.getSchool()))
                    .list();
            event.setMagazines(magazineDB);
            event.callback();
    }

    @Subscribe
    public void saveMagazines(ESaveMagazines event){
        ArrayList<Magazine> magazines = event.getMagazines();
        for(Magazine magazine : magazines){
            if(!isAlreadySaved(magazine.getObjectId())) {
                magazine.save();
                Log.d("mytag", "Database.saveMagazines() save magazine");
            }
        }
    }

    @Subscribe
    public void clearMagazine(EClearDownloadedMagazine event){
        boolean isPDF = event.isPDF();
        String path = event.getPath();
        if(isPDF) {
            List<Magazine> magazines = Select.from(Magazine.class)
                    .where(Condition.prop("downloaded_pdf_path").eq(path))
                    .list();
            Magazine magazine = magazines.get(0);
            Log.d("mytag", "Database.clearMagazine() pdf path = " + magazine.getDownloadedPDFPath());
            magazine.setDownloadedPDFPath("");
            magazine.setDownloadedPDF(false);
            magazine.save();
        }else{
            List<Magazine> magazines = Select.from(Magazine.class)
                    .where(Condition.prop("downloaded_text_path").eq(path))
                    .list();
            Magazine magazine = magazines.get(0);
            Log.d("mytag", "Database.clearMagazine() text path = " + magazine.getDownloadedTextPath());
            magazine.setDownloadedTextPath("");
            magazine.setDownloadedText(false);
            magazine.save();
        }
    }

    private boolean isAlreadySaved(String objectId){
        initAllObjectIds();
        return objectIds.contains(objectId);
    }

    private void initAllObjectIds(){
        List<Magazine> magazineArray = Magazine.listAll(Magazine.class);
        ArrayList<String> objectIds = new ArrayList<>();
        for(Magazine magazine : magazineArray){
            objectIds.add(magazine.getObjectId());
        }
        this.objectIds = objectIds;
    }

    @Subscribe
    public void magazineDownloadedEventListener(EMagazineDownloaded event){
        boolean PDF = event.isPDF();
        String objectId = event.getMagazine().getObjectId();
        String destinationPath = event.getDownloadedPath();
        Magazine magazine = getMagazineById(objectId);
        if(PDF){
            magazine.setDownloadedPDF(true);
            magazine.setDownloadedPDFPath(destinationPath);
        }else{
            magazine.setDownloadedText(true);
            magazine.setDownloadedTextPath(destinationPath);
        }
        magazine.save();
    }

    private Magazine getMagazineById(String objectId){
        List<Magazine> magazineDB = Select.from(Magazine.class)
                .where(Condition.prop("object_id").eq(objectId))
                .list();
        return magazineDB.get(0);
    }



    private void clearAllMagazines(){
        Magazine.deleteAll(Magazine.class);
    }




}
