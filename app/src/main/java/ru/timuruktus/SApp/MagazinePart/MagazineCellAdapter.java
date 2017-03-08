package ru.timuruktus.SApp.MagazinePart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ru.timuruktus.SApp.R;


public class MagazineCellAdapter extends BaseAdapter {
    Context context;
    LayoutInflater lInflater;
    ArrayList<Magazine> magazines;
    private Button downloadPDF;
    private Button downloadText;
    private Button read;
    private ArrayList<Button> buttons =  new ArrayList<>();

    MagazineCellAdapter(Context context, ArrayList<Magazine> magazines) {
        this.context = context;
        this.magazines = magazines;
        lInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return magazines.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return magazines.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.magazine_cell, parent, false);
        }

        Magazine magazine = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        String viewsCount = String.valueOf(magazine.getViewsCount());
        String title = magazine.getTitle();
        String imageURL = magazine.getTitleImage();
        String preview = magazine.getPreview();
        ((TextView) view.findViewById(R.id.title)).setText(title);
        ((TextView) view.findViewById(R.id.viewsCount)).setText(viewsCount);
        ((TextView) view.findViewById(R.id.preview)).setText(preview);
        ImageView magazineImage =  (ImageView) view.findViewById(R.id.magazineImage);
        Picasso.with(context).load(imageURL).into(magazineImage);


        buttons.add(downloadPDF = (Button) view.findViewById(R.id.downloadPDF));
        buttons.add(downloadText = (Button) view.findViewById(R.id.downloadText));
        buttons.add(read = (Button) view.findViewById(R.id.read));
        createListenersForButton();

        return view;
    }

    private void createListenersForButton(){
        for(Button b : buttons){
            b.setOnClickListener(buttonClickListener);
        }
    }

    // товар по позиции
    Magazine getProduct(int position) {
        return (Magazine) getItem(position);
    }


    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id == R.id.downloadPDF){
                //TODO
            }
        }
    };
}
