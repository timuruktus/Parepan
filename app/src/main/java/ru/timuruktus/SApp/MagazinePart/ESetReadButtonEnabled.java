package ru.timuruktus.SApp.MagazinePart;

import android.widget.Button;

import ru.timuruktus.SApp.LocalDataEvent;

public class ESetReadButtonEnabled implements LocalDataEvent {
    Button b;
    boolean enabled;

    public ESetReadButtonEnabled(Button b, boolean enabled) {
        this.b = b;
        this.enabled = enabled;
    }

    public Button getB() {
        return b;
    }

    public void setB(Button b) {
        this.b = b;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    @Override
    public void callback() {

    }
}
