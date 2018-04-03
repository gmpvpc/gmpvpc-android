package com.gmpvpc.android.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmpvpc.android.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementFragment extends Fragment {

    private TextView circle_txt;
    private TextView achievement_title;
    private TextView subtitle;
    private TextView side_value;
    private ImageView circle_img;

    public AchievementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_achievement, container, false);

        this.circle_txt = rootView.findViewById(R.id.circle_txt);
        this.achievement_title = rootView.findViewById(R.id.achievement_title);
        this.subtitle = rootView.findViewById(R.id.subtitle);
        this.side_value = rootView.findViewById(R.id.side_txt);
        this.circle_img= rootView.findViewById(R.id.circle_img);

        return rootView;
    }

    public TextView getCircle_txt() {
        return circle_txt;
    }

    public void setCircle_txt(TextView circle_txt) {
        this.circle_txt = circle_txt;
    }

    public TextView getAchievement_title() {
        return achievement_title;
    }

    public void setAchievement_title(TextView achievement_title) {
        this.achievement_title = achievement_title;
    }

    public TextView getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(TextView subtitle) {
        this.subtitle = subtitle;
    }

    public TextView getSide_value() {
        return side_value;
    }

    public void setSide_value(TextView side_value) {
        this.side_value = side_value;
    }

    public ImageView getCircle_img() {
        return circle_img;
    }

    public void setCircle_img(ImageView circle_img) {
        this.circle_img = circle_img;
    }
}
