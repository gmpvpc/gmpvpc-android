package com.gmpvpc.android.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gmpvpc.android.R;
import com.gmpvpc.android.models.Series;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class SeriesAdapter extends ArrayAdapter<Series> {

    private static final int ITEM = R.layout.fragment_achievement;
    private static final int LBL_NUMBER = R.id.circle_txt;
    private static final int LBL_TITLE = R.id.achievement_title;
    private static final int LBL_SUB_TITLE = R.id.subtitle;
    private static final int LBL_SIDE_INFO = R.id.side_txt;

    private List<Series> seriesList;

    public SeriesAdapter(@NonNull Context context, int resource, @NonNull List<Series> seriesList) {
        super(context, resource, seriesList);
        List<Series> seriess = new ArrayList<>();
        for (Series s :seriesList) {
            if (s.getHits() == s.getOccurrence() && s.getOccurrence() != 0) {
                seriess.add(s);
            }
        }
        this.seriesList = seriess;
    }

    public static class ViewHolder {
        TextView lblNumber;
        TextView lblTitle;
        TextView lblSubTitle;
        TextView lblSideInfo;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position >= this.seriesList.size()) {
            return null;
        }
        Series series = this.seriesList.get(position);
        View rowView = convertView;
        ViewHolder holder;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(ITEM, parent, false);
            holder = new ViewHolder();
            holder.lblNumber = rowView.findViewById(LBL_NUMBER);
            holder.lblTitle = rowView.findViewById(LBL_TITLE);
            holder.lblSubTitle = rowView.findViewById(LBL_SUB_TITLE);
            holder.lblSideInfo = rowView.findViewById(LBL_SIDE_INFO);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }
        int number = position +1;
        String title = String.format("Series n°%s",number);
        String subTitle = String.format("Hits: %s/%s", series.getHits(), series.getOccurrence());
        String sideInfo = "";
        holder.lblNumber.setText(String.valueOf(number));
        holder.lblTitle.setText(title);
        holder.lblSubTitle.setText(subTitle);
        holder.lblSideInfo.setText(sideInfo);
        return rowView;
    }
}
