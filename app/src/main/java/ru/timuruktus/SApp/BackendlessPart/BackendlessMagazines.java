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

    public BackendlessMagazines(){
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void getMagazines(final EGetMagazinesList event){
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        try{
            dataQuery.setWhereClause(event.whereClause);
        }catch(NullPointerException ex){
            dataQuery.setWhereClause("");
        }finally {
            Log.d("BackendlessMagazines", event.getWhereClause());
            Backendless.Persistence.of(Magazine.class).find(dataQuery,
                    new AsyncCallback<BackendlessCollection<Magazine>>(){
                        @Override
                        public void handleResponse( BackendlessCollection<Magazine> foundMagazines ) {
                            event.setMagazines(foundMagazines.getCurrentPage());
                            event.callback();
                        }
                        @Override
                        public void handleFault( BackendlessFault fault ) {
                            Log.d("MagFragmentFault", fault.getCode());
                        }
                    });
        }

    }
}
