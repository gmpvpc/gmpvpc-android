package com.gmpvpc.android.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gmpvpc.android.R;
import com.gmpvpc.android.adapter.SeriesAdapter;
import com.gmpvpc.android.models.Series;

import java.util.List;

public class SeriesFragment extends Fragment {

    public static final int FRAGMENT_LAYOUT = R.layout.fragment_series;
    private static final int LST_SERIES = R.id.lst_series;

    private ListView lstSeries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(FRAGMENT_LAYOUT, container, false);
        lstSeries = view.findViewById(LST_SERIES);
        return view;
    }

    public void update(List<Series> seriess) {
        SeriesAdapter adapter = new SeriesAdapter(getActivity(), -1, seriess);
        lstSeries.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
