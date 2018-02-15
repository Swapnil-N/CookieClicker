package com.example.a10014422.cookieclicker;

import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class CookieClickerActivity extends AppCompatActivity {

    TextView cookieText;
    ImageView mainImage;
    AtomicInteger counter;
    ConstraintLayout constraintLayout;
    ImageView upgrade1pic;
    ImageView grandmaPicSmall;
    int grandmaCost = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie_clicker);

        cookieText = (TextView)findViewById(R.id.Cookietext);
        mainImage = (ImageView) findViewById(R.id.mainImageView);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintid);
        upgrade1pic = (ImageView) findViewById(R.id.id_upgrade1);

        counter = new AtomicInteger(0);

        final ScaleAnimation scale = new ScaleAnimation(0.5f,1.1f,0.5f,1.1f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale.setDuration(200);

        final AlphaAnimation fadeIn = new AlphaAnimation(0f,1f);
        final AlphaAnimation fadeOut = new AlphaAnimation(1f,0f);
        fadeIn.setDuration(300);
        fadeOut.setDuration(300);


        mainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(scale);
                counter.getAndAdd(1);
                cookieText.setText(counter+" cookies");
                plusOneAnimation();

                if (counter.get() >= grandmaCost ){
                    upgrade1pic.setVisibility(View.VISIBLE);
                }

                upgrade1pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        counter.addAndGet(-grandmaCost);
                        if (counter.get() < 5) {
                            upgrade1pic.setVisibility(View.INVISIBLE);
                            upgrade1pic.startAnimation(fadeOut);
                        }
                        cookieText.setText(counter+" cookies");
                        moveGrandma();
                    }
                });

            }
        });


    }

    public static class myThread extends Thread {

        public void run (){


        }
    }

    public void moveGrandma() {
        grandmaPicSmall = new ImageView(getApplicationContext());
        grandmaPicSmall.setId(View.generateViewId());
        grandmaPicSmall.setImageResource(R.drawable.grandmasmall);

        ConstraintLayout.LayoutParams paramsG = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        grandmaPicSmall.setLayoutParams(paramsG);

        constraintLayout.addView(grandmaPicSmall);

        ConstraintSet constraintSet3 = new ConstraintSet();
        constraintSet3.clone(constraintLayout);

        constraintSet3.connect(grandmaPicSmall.getId(),ConstraintSet.TOP,constraintLayout.getId(),ConstraintSet.TOP);
        constraintSet3.connect(grandmaPicSmall.getId(),ConstraintSet.BOTTOM,constraintLayout.getId(),ConstraintSet.BOTTOM);
        constraintSet3.connect(grandmaPicSmall.getId(),ConstraintSet.RIGHT,constraintLayout.getId(),ConstraintSet.RIGHT);
        constraintSet3.connect(grandmaPicSmall.getId(),ConstraintSet.LEFT,constraintLayout.getId(),ConstraintSet.LEFT);
        constraintSet3.setHorizontalBias(grandmaPicSmall.getId(), (float) (Math.random()*.5+.25));
        constraintSet3.setVerticalBias(grandmaPicSmall.getId(), 0.90f);

        constraintSet3.applyTo(constraintLayout);

        final AlphaAnimation fadeIn = new AlphaAnimation(0f,1f);
        fadeIn.setDuration(300);
        grandmaPicSmall.startAnimation(fadeIn);
    }

    public void plusOneAnimation(){
        
        final TextView textViewInCode;
        textViewInCode = new TextView(getApplicationContext());
        textViewInCode.setId(View.generateViewId());
        textViewInCode.setText("+1");

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        textViewInCode.setLayoutParams(params);

        constraintLayout.addView(textViewInCode);

        ConstraintSet constraintSet1 = new ConstraintSet();
        constraintSet1.clone(constraintLayout);

        constraintSet1.connect(textViewInCode.getId(),ConstraintSet.TOP,constraintLayout.getId(),ConstraintSet.TOP);
        constraintSet1.connect(textViewInCode.getId(),ConstraintSet.BOTTOM,constraintLayout.getId(),ConstraintSet.BOTTOM);
        constraintSet1.connect(textViewInCode.getId(),ConstraintSet.RIGHT,constraintLayout.getId(),ConstraintSet.RIGHT);
        constraintSet1.connect(textViewInCode.getId(),ConstraintSet.LEFT,constraintLayout.getId(),ConstraintSet.LEFT);
        constraintSet1.setHorizontalBias(textViewInCode.getId(), (float) (Math.random()*.5+.25));
        constraintSet1.setVerticalBias(textViewInCode.getId(), 0.25f);

        constraintSet1.applyTo(constraintLayout);

        Animation scale1 = new TranslateAnimation(0,0,200,0);
        scale1.setDuration(1000);
        textViewInCode.startAnimation(scale1);
        textViewInCode.setVisibility(View.INVISIBLE);
        
    }



}
