package com.mukeshjadhav.khadush;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.res.ResourcesCompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private static TextView spAppName;
    private Typeface typefaceMedium, typefaceLight, typefaceRegular, typefaceThin;
    private RelativeLayout rlSpLayoutAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        initTypeFaces();
        setComponents();

        launch();
    }

    private void initTypeFaces(){
        typefaceLight = ResourcesCompat.getFont(this, R.font.montserrat_light);
        typefaceMedium = ResourcesCompat.getFont(this, R.font.montserrat_medium);
        typefaceRegular = ResourcesCompat.getFont(this, R.font.montserrat_regular);
        typefaceThin = ResourcesCompat.getFont(this, R.font.montserrat_thin);
    }

    private void setComponents(){
        spAppName = (TextView) findViewById(R.id.tv_sp_app_name);
        spAppName.setTypeface(typefaceRegular);

        rlSpLayoutAnimation = (RelativeLayout) findViewById(R.id.rl_sp_layout_animation);
    }

    private void launch(){
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    rlSpLayoutAnimation.post(new Runnable() {
                        @Override
                        public void run() {
                            animateSplashScreen();
                        }
                    });
                }
            }
        });

        th.start();
    }

    private void animateSplashScreen(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            int x = rlSpLayoutAnimation.getRight();
            int y = rlSpLayoutAnimation.getBottom();

            int startRadius = 0;
            int endRadius = (int) Math.hypot(rlSpLayoutAnimation.getWidth(), rlSpLayoutAnimation.getHeight());

            Animator anim = ViewAnimationUtils.createCircularReveal(rlSpLayoutAnimation, x/2, y/2, startRadius, endRadius);
            anim.setDuration(500);

            rlSpLayoutAnimation.setVisibility(View.VISIBLE);

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    Intent nextActivity = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(nextActivity);
                    overridePendingTransition(0, 0);
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    Integer colorFrom = getResources().getColor(R.color.colorPrimary);
                    Integer colorTo = getResources().getColor(R.color.colorWhite);
                    ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
                    colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                        @Override
                        public void onAnimationUpdate(ValueAnimator animator) {
                            spAppName.setTextColor((Integer)animator.getAnimatedValue());
                        }
                    });
                    colorAnimation.setDuration(500);
                    colorAnimation.start();
                }
            });
            anim.start();

        } else{
            // do something for phones running an SDK before lollipop
        }
    }
}
