package ru.timuruktus.SApp.MagazinePart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.HashSet;

import ru.timuruktus.SApp.R;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
public class MagazineCellAdapter extends ArrayAdapter<Magazine> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    Context context;
    ArrayList<Magazine> magazines;


    public MagazineCellAdapter(Context context, ArrayList<Magazine> magazines) {
        super(context, 0, magazines);
        this.context = context;
        this.magazines = magazines;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get item for selected view
        Magazine item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.magazine_cell, parent, false);
            // binding view parts to view holder
            viewHolder.title = (TextView) cell.findViewById(R.id.title);
            viewHolder.magazineImage = (ImageView) cell.findViewById(R.id.magazineImage);
            viewHolder.date = (TextView) cell.findViewById(R.id.date);
            viewHolder.viewsCount = (TextView) cell.findViewById(R.id.viewsCount);
            viewHolder.titleSchool = (TextView) cell.findViewById(R.id.titleSchool);
            viewHolder.preview = (TextView) cell.findViewById(R.id.preview);
            viewHolder.downloadButton = (Button) cell.findViewById(R.id.downloadButton);
            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        // bind data from selected element to view through view holder
        viewHolder.title.setText(item.getTitle());
        Log.d("mytag", item.getTitle() + "");
        //DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        //Date today = item.getDate();
        //String dateString = df.format(today);
        //viewHolder.date.setText(dateString);
        viewHolder.viewsCount.setText(String.valueOf(item.getViewsCount()));
        viewHolder.titleSchool.setText(item.getTitleSchool());
        viewHolder.preview.setText(String.valueOf(item.getPreview()));
        viewHolder.downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    // View lookup cache
    private static class ViewHolder {
        TextView title;
        ImageView magazineImage;
        TextView date;
        TextView viewsCount;
        TextView titleSchool;
        TextView preview;
        Button downloadButton;
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return magazines.size();
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }
}
