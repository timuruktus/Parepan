package ru.timuruktus.SApp.MainPart;

import ru.timuruktus.SApp.BaseEvent;

public class EChangeToolbarVisible implements BaseEvent {

    public boolean isVisible() {
        return visible;
    }

    public boolean visible;

    public EChangeToolbarVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void callback() {

    }
}
