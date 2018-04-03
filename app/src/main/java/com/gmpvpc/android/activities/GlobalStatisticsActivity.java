package com.gmpvpc.android.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gmpvpc.android.R;
import com.gmpvpc.android.fragments.AchievementFragment;

public class GlobalStatisticsActivity extends AppCompatActivity {
    private AchievementFragment punch_speed;
    private AchievementFragment punch_accuracy;
    private AchievementFragment training_time;
    private AchievementFragment punch_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_statistics);

        this.punch_speed = (AchievementFragment) getFragmentManager().findFragmentById(R.id.punch_speed);
        this.punch_accuracy = (AchievementFragment) getFragmentManager().findFragmentById(R.id.punch_accuracy);
        this.training_time = (AchievementFragment) getFragmentManager().findFragmentById(R.id.training_time);
        this.punch_count = (AchievementFragment) getFragmentManager().findFragmentById(R.id.punch_count);

        // set first achievement content
        this.punch_speed.getCircle_img().setColorFilter(getResources().getColor(R.color.colorYellow));
        this.punch_speed.getAchievement_title().setText("Punch speed");
        this.punch_speed.getSubtitle().setText("You are a king fighter");
        this.punch_speed.getCircle_txt().setText("#1");
        this.punch_speed.getSide_value().setText("12 m/s");

        // set second achievement content
        this.punch_accuracy.getCircle_img().setColorFilter(getResources().getColor(R.color.colorBrown));
        this.punch_accuracy.getAchievement_title().setText("Punch accuracy");
        this.punch_accuracy.getSubtitle().setText("You're the #2 keep going !");
        this.punch_accuracy.getCircle_txt().setText("#2");
        this.punch_accuracy.getSide_value().setText("89 %");

        // set second achievement content
        this.training_time.getCircle_img().setColorFilter(getResources().getColor(R.color.colorGrey));
        this.training_time.getAchievement_title().setText("Average training time");
        this.training_time.getSubtitle().setText("Not so bad, you can do it !");
        this.training_time.getCircle_txt().setText("#3");
        this.training_time.getSide_value().setText("37 minutes");

        // set second achievement content
        this.punch_count.getCircle_img().setColorFilter(getResources().getColor(R.color.colorMediumGrey));
        this.punch_count.getAchievement_title().setText("Punch count");
        this.punch_count.getSubtitle().setText("Hey, you're in the Top 5 fighters");
        this.punch_count.getCircle_txt().setText("#5");
        this.punch_count.getSide_value().setText("150+");
    }
}
