package ru.timuruktus.SApp.LocalData;

import android.util.Log;

import com.orm.query.Condition;
import com.orm.query.Select;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import ru.timuruktus.SApp.MagazinePart.ESetReadButtonEnabled;
import ru.timuruktus.SApp.MagazinePart.Magazine;
import ru.timuruktus.SApp.R;

public class Database {

    public Database(){
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void checkDownload(ECheckDownload event){
        Log.d("mytag", "Database.checkDownload() handled event");
        Log.d("mytag", "id = " + event.getObjectId());
        List<Magazine> magazines = Magazine.find(Magazine.class, "object_id = ?",
                event.getObjectId());

        if(magazines.size() == 0){
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



}
