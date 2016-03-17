package anibalbastias.hnmobiletest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import anibalbastias.hnmobiletest.R;
import anibalbastias.hnmobiletest.util.Libcomun;

public class SplashScreenActivity extends AppCompatActivity {

    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide title bar
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        //opening transition animations
        Libcomun.setAnimationActivity(SplashScreenActivity.this);
        setContentView(R.layout.activity_splash_screen);

        setHandler();
    }

    private void setHandler() {
        // Logo Fade in Animation
        (findViewById(R.id.logo_splash)).startAnimation(AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.fade_in));

        // Set Textviews fonts
        Libcomun.setFontTextViewBlack((TextView) findViewById(R.id.title), SplashScreenActivity.this);
        Libcomun.setFontTextViewRegular((TextView) findViewById(R.id.subtitle), SplashScreenActivity.this);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent().setClass(SplashScreenActivity.this, MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);

                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

}
