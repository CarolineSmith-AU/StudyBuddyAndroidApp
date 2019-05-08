package com.example.studybuddy;

import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer timer;
    private int SLEEP_TIMER = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView logoImageView = findViewById(R.id.logoImageView);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() { //runOnUiThread() allows...
                    @Override
                    public void run() {
                        Intent toLoginActivity = new Intent(MainActivity.this, LoginActivity.class);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                                logoImageView, getString(R.string.shared_element_1)); //shared element transition - transition name of both activities must be the same
                        startActivity(toLoginActivity, options.toBundle());
                        finish(); //Ensures the back button does not return to this activity
                    }
                });
            }
        }, SLEEP_TIMER);
    }
}
