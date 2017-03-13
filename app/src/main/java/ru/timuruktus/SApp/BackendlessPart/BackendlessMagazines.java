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

    EGetMagazinesWeb eGetMagazinesWeb;

    public BackendlessMagazines(){
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void getMagazines(EGetMagazinesWeb event){
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        eGetMagazinesWeb = event;
        try{
            dataQuery.setWhereClause(event.getWhereClause());
        }catch(NullPointerException ex){
            dataQuery.setWhereClause("");
        }finally {
            Log.d("mytag", "BackendlessMagazines.getMagazines() print where clause: " + event.getWhereClause());
            getData(dataQuery);
        }

    }

    private void getData(BackendlessDataQuery  dataQuery){
        Backendless.Persistence.of(Magazine.class).find(dataQuery,
                new AsyncCallback<BackendlessCollection<Magazine>>(){
                    @Override
                    public void handleResponse( BackendlessCollection<Magazine> foundMagazines ) {
                        eGetMagazinesWeb.setMagazines(foundMagazines.getCurrentPage());
                        eGetMagazinesWeb.callback();
                    }
                    @Override
                    public void handleFault( BackendlessFault fault ) {
                        Log.d("MagFragmentFault", fault.getCode());
                    }
                });
    }
}
