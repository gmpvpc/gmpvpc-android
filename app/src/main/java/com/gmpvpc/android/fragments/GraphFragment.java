package com.gmpvpc.android.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmpvpc.android.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


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

        GraphView graph = view.findViewById(R.id.graph_fragment);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });

        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 5),
                new DataPoint(1, 3),
                new DataPoint(2, -3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(4, -1)
        });
        series2.setColor(Color.GREEN);

        graph.addSeries(series2);
        graph.addSeries(series);

        return view;
    }

}
