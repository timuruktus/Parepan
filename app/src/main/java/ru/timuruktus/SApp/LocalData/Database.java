package ru.timuruktus.SApp.LocalData;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class Database {

    public Database(){
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void checkDownload(ECheckDownload event){
        //TODO : Отправлять другой эвент обратно, используя ORM
        Log.d("mytag", "handled event");
        MagazineDB magazineDBs = new MagazineDB("a", "a", "A", "a", "a", "a", "A", "A", "a");
        List<MagazineDB> magazineDB = MagazineDB.find(MagazineDB.class, "object_id = ?", event.getObjectId());
        if(magazineDB.size() == 0){
            Log.d("mytag", "Database.CheckDownload magazineDB.size() == 0");
        }
        Log.d("mytag", "Database.CheckDownload magazineDB[0] == " + magazineDB.get(0));
        Log.d("mytag", " end of handled event");
    }

}
