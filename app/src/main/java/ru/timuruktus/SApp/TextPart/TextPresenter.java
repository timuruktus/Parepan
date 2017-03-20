package ru.timuruktus.SApp.TextPart;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ru.timuruktus.SApp.BaseEvent;
import ru.timuruktus.SApp.BasePresenter;
import ru.timuruktus.SApp.MagazinePart.Magazine;



public class TextPresenter implements BasePresenter {

    Magazine magazine;
    TextFragment fragment;
    public static final String IMAGE_TAG = "<<<";
    public static final String BOLD_TAG = "**";
    public static final String ITALIC_TAG = "__";
    public static final String BOLD_AND_ITALIC_TAG = "*_";
    public static final ArrayList<String> ALL_TAGS = new ArrayList<>();

    public TextPresenter(){
        EventBus.getDefault().register(this);
        ALL_TAGS.add(IMAGE_TAG);
        ALL_TAGS.add(BOLD_TAG);
        ALL_TAGS.add(ITALIC_TAG);
        ALL_TAGS.add(BOLD_AND_ITALIC_TAG);
    }

    public TextPresenter(Magazine magazine){
        this.magazine = magazine;

    }

    @Subscribe
    public void getTextListener(EGetText event){
        this.fragment = event.fragment;
        String path = event.pathToText;
        File myFile = new File(path);
        try {
            FileInputStream inputStream = new FileInputStream(myFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }
                String text = stringBuilder.toString();
                Log.d("mytag", "TextPresenter.getTextListener() line = " + text);
                parseText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parseText(String text){
        String[] splittedText = text.split(" ");
        fragment.setTitleText(findTextTitle(splittedText));
        sendAllText(splittedText);
    }



    private String findTextTitle(String[] splittedText){
        String answer = "";
        boolean findedSecondStop = false;
        int secondStop = 0;
        for(int i = 1; i < splittedText.length; i++){
            if(!findedSecondStop) {
                if(splittedText[i].equals("<<")) {
                    findedSecondStop = !findedSecondStop;
                    secondStop = i;
                }
            }
        }
        for(int i = 1; i < secondStop; i++){
            answer += splittedText[i] + " ";
        }
        deletePartOfText(splittedText, 0, ++secondStop);
        return answer;
    }

    private void sendAllText(String[] splittedText){
        String answer = "";
        for(int i = 0; i < splittedText.length; i++){
            String currentWord = splittedText[i];
            if(!isWordATag(currentWord)){
                answer += currentWord + " ";
            }else if(BOLD_TAG.contains(currentWord)){
                fragment.setText(answer);
                answer = "";
                sendNonDefaultText(BOLD_TAG, i, splittedText);
            }else if(BOLD_AND_ITALIC_TAG.contains(currentWord)){
                fragment.setText(answer);
                answer = "";
                sendNonDefaultText(BOLD_AND_ITALIC_TAG, i, splittedText);
            }else if(ITALIC_TAG.contains(currentWord)){
                fragment.setText(answer);
                answer = "";
                sendNonDefaultText(ITALIC_TAG, i, splittedText);
            }else if(IMAGE_TAG.contains(currentWord)){
                fragment.setText(answer);
                answer = "";
                fragment.setImage(splittedText[++i]);
                deletePartOfText(splittedText, ++i, i + 3);
            }
        }
        fragment.setText(answer);
        answer = "";
    }

    /**
     * We don't include start and end
     * @param splittedText
     * @param start
     * @param end
     * @return
     */
    public String getTextBetween(String[] splittedText, int start, int end){
        String answer = "";
        for(int i = ++start; i < end; i++){
            String currentWord = splittedText[i];
            answer += currentWord + " ";
        }
        return answer;
    }


    private boolean isWordATag(String word){
        return ALL_TAGS.contains(word);
    }

    public void sendNonDefaultText(String tag, int i, String[] splittedText){
        int endOfTag = findNextTagNum(splittedText, i, tag);
        String tempString = getTextBetween(splittedText, i, endOfTag);
        if(tag.equals(BOLD_AND_ITALIC_TAG)) {
            fragment.setText(tempString, true, true);
        }else{
            fragment.setText(tempString, tag.equals(BOLD_TAG), tag.equals(ITALIC_TAG));
        }
        deletePartOfText(splittedText, i, endOfTag);
    }


    public int findNextTagNum(String[] splittedText, int currentNum, String tag){
        for(int i = ++currentNum; i < splittedText.length; i++){
            if(splittedText[i].equals(tag)){
                return i;
            }
        }
        return splittedText.length;
    }

    public void deletePartOfText(String[] splittedText, int start, int end){
        for(int i = start; i <= end; i++){
            splittedText[i] = "";
        }
    }

    public void detachListener(){
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void eventCallback(BaseEvent event) {

    }
}
