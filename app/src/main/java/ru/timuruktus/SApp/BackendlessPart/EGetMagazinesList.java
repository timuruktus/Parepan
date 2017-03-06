package ru.timuruktus.SApp.BackendlessPart;

import android.util.Log;

import java.util.List;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BaseFragment;
import ru.timuruktus.SApp.BasePresenter;
import ru.timuruktus.SApp.MagazinePart.Magazine;

public class EGetMagazinesList implements BaseEvent {

    private BaseFragment fragment;
    private BasePresenter presenter;
    private String whereClause;
    private List<Magazine> magazines;


    public EGetMagazinesList() {
    }

    public EGetMagazinesList(String whereClause, BasePresenter presenter) {
        this.whereClause = whereClause;
        this.presenter = presenter;
    }

    public EGetMagazinesList(String whereClause, BaseFragment fragment) {
        this.whereClause = whereClause;
        this.fragment = fragment;
    }

    public EGetMagazinesList(BaseFragment fragment) {
        this.fragment = fragment;
    }

    public EGetMagazinesList(BasePresenter presenter) {
        this.presenter = presenter;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public List<Magazine> getMagazines() {
        return magazines;
    }

    public void setMagazines(List<Magazine> magazines) {
        this.magazines = magazines;
    }


    @Override
    public void callback() {
        Log.d("EGetMagazinesList", "callback()");
        if(presenter != null){
            presenter.eventCallback(this);
        }else if(fragment != null){
            fragment.eventCallback(this);
        }
    }
}
