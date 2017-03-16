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



}
