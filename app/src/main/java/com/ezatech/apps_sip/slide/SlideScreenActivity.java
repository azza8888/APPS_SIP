 package com.ezatech.apps_sip.slide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ezatech.apps_sip.Helper.SplashPermssionActivity;
import com.ezatech.apps_sip.R;

 public class SlideScreenActivity extends AppCompatActivity {

     private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_screen);

        iv = (ImageView) findViewById(R.id.iv);
//        Animation myanim = new AnimationUtils().loadAnimation(this,R.anim.mytransitions);
//        iv.startAnimation(myanim);
        final Intent i = new Intent(this, SplashPermssionActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000) ;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }

            }
        };
        timer.start();
    }

}
