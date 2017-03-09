package ru.timuruktus.SApp.LocalData;

import org.greenrobot.eventbus.EventBus;

public class Database {

    public Database(){
        EventBus.getDefault().register(this);
    }
}
