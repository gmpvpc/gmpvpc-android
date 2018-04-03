package com.gmpvpc.android.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmpvpc.android.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BaseSeries;
import com.jjoe64.graphview.series.DataPoint;


/**
 * A simple {@link Fragment} subclass.
 */
public class GraphFragment extends Fragment {

    private GraphView graphView;

    public GraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graph, container, false);

        this.graphView = view.findViewById(R.id.graph_fragment);

        return view;
    }

    public void addSeries(BaseSeries<DataPoint> series) {
        this.graphView.addSeries(series);
    }

    public GraphView getGraphView() {
        return graphView;
    }
}
