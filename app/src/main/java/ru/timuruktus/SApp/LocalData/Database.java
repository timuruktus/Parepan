package ru.timuruktus.SApp.LocalData;

import android.util.Log;

import com.orm.query.Condition;
import com.orm.query.Select;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import ru.timuruktus.SApp.MagazinePart.ESetReadButtonEnabled;
import ru.timuruktus.SApp.MagazinePart.Magazine;
import ru.timuruktus.SApp.R;

public class Database {

    ArrayList<String> objectIds;

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

    @Subscribe
    public void saveMagazines(ESaveMagazines event){
        ArrayList<Magazine> magazines = event.getMagazines();
        for(Magazine magazine : magazines){
            if(!findObjectId(magazine.getObjectId())) {
                magazine.save();
            }
        }
    }

    private boolean findObjectId(String objectId){
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



}
