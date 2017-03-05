package ru.timuruktus.SApp.WelcomePart;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BasePresenter;

public class WelcomePresenter implements BasePresenter {

    private WelcomeFragment welcomeFragment;

    public WelcomePresenter(WelcomeFragment welcomeFragment){
        this.welcomeFragment = welcomeFragment;

    }


    @Override
    public void eventCallback(BaseEvent event) {

    }
}
