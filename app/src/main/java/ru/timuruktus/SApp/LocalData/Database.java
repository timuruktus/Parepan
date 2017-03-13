package ru.timuruktus.SApp.LocalData;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import ru.timuruktus.SApp.MagazinePart.ESetReadButtonEnabled;
import ru.timuruktus.SApp.R;

public class Database {

    public Database(){
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void checkDownload(ECheckDownload event){
        //TODO : Отправлять другой эвент обратно, используя ORM
        Log.d("mytag", "Database.checkDownload() handled event");
        List<MagazineDB> magazineDB = MagazineDB.find(MagazineDB.class, "object_id = ?",
                event.getObjectId());
        if(magazineDB.size() == 0){
            Log.d("mytag", "Database.CheckDownload magazineDB.size() == 0");
            if(event.getB().getId() == R.id.readText){
                EventBus.getDefault().post(new ESetReadButtonEnabled(event.getB(), false));
            }else if(event.getB().getId() == R.id.readPDF){
                EventBus.getDefault().post(new ESetReadButtonEnabled(event.getB(), false));
            }
        }else{
            EventBus.getDefault().post(new ESetReadButtonEnabled(event.getB(), true));
        }
        Log.d("mytag", " end of handled event");
    }



}
