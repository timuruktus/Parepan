package ru.timuruktus.SApp.TextPart;

import ru.timuruktus.SApp.BaseEvent;

public class EGetText implements BaseEvent {

    public String pathToText;
    public TextFragment fragment;

    public EGetText(String pathToText, TextFragment fragment) {
        this.pathToText = pathToText;
        this.fragment = fragment;
    }

    @Override
    public void callback() {
        fragment.eventCallback(this);
    }
}
