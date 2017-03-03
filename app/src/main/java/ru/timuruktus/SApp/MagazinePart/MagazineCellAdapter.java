package ru.timuruktus.SApp.MagazinePart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    private View.OnClickListener defaultRequestBtnClickListener;
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
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
            // binding view parts to view holder
            viewHolder.price = (TextView) cell.findViewById(R.id.title_price);
            viewHolder.time = (TextView) cell.findViewById(R.id.title_time_label);
            viewHolder.date = (TextView) cell.findViewById(R.id.title_date_label);
            viewHolder.fromAddress = (TextView) cell.findViewById(R.id.titleName);
            viewHolder.toAddress = (TextView) cell.findViewById(R.id.title_to_address);
            viewHolder.requestsCount = (TextView) cell.findViewById(R.id.viewsCount);
            viewHolder.pledgePrice = (TextView) cell.findViewById(R.id.title_pledge);
            viewHolder.contentRequestBtn = (TextView) cell.findViewById(R.id.content_request_btn);
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
        viewHolder.price.setText(item.getPrice());
        viewHolder.time.setText(item.getTime());
        viewHolder.date.setText(item.getDate());
        viewHolder.fromAddress.setText(item.getFromAddress());
        viewHolder.toAddress.setText(item.getToAddress());
        viewHolder.requestsCount.setText(String.valueOf(item.getRequestsCount()));
        viewHolder.pledgePrice.setText(item.getPledgePrice());

        // set custom btn handler for list item from that item
        if (item.getRequestBtnClickListener() != null) {
            viewHolder.contentRequestBtn.setOnClickListener(item.getRequestBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item
            viewHolder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
        }


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

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView titleImage;
        TextView createdTime;
        TextView fromAddress;
        TextView toAddress;
        TextView requestsCount;
        TextView date;
        TextView time;
        textUrl;
        private String title;
        private String titleImage;
        private String createdTime;
        private String preview;
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
