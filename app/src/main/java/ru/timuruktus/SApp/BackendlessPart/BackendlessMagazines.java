package ru.timuruktus.SApp.BackendlessPart;

import android.util.Log;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ru.timuruktus.SApp.MagazinePart.Magazine;

public class BackendlessMagazines {

    EGetMagazinesList eGetMagazinesList;

    public BackendlessMagazines(){
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void getMagazines(EGetMagazinesList event){
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        eGetMagazinesList = event;
        try{
            dataQuery.setWhereClause(event.getWhereClause());
        }catch(NullPointerException ex){
            dataQuery.setWhereClause("");
        }finally {
            Log.d("BackendlessMagazines", event.getWhereClause());
            getData(dataQuery);
        }

    }

    private void getData(BackendlessDataQuery  dataQuery){
        Backendless.Persistence.of(Magazine.class).find(dataQuery,
                new AsyncCallback<BackendlessCollection<Magazine>>(){
                    @Override
                    public void handleResponse( BackendlessCollection<Magazine> foundMagazines ) {
                        eGetMagazinesList.setMagazines(foundMagazines.getCurrentPage());
                        eGetMagazinesList.callback();
                    }
                    @Override
                    public void handleFault( BackendlessFault fault ) {
                        Log.d("MagFragmentFault", fault.getCode());
                    }
                });
    }
}
