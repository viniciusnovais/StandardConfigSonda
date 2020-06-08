package br.com.pdasolucoes.standardconfig;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import br.com.pdasolucoes.standardconfig.utils.MyApplication;


public class SplashActivity extends Activity {

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView pda = findViewById(R.id.splashmove);
        final Animation logoMoveAnimation = AnimationUtils.loadAnimation(this, R.anim.up);
        pda.startAnimation(logoMoveAnimation);


        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                finish();
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        }, 3000);
    }

}
