package com.example.a10014422.cookieclicker;

import android.graphics.Color;
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
    TextView grandCostText;
    TextView factCostText;

    ImageView upgrade2pic;
    ImageView factoryPic;
    int factoryCost = 10;
    TextView cpsText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie_clicker);

        cookieText = (TextView)findViewById(R.id.Cookietext);
        mainImage = (ImageView) findViewById(R.id.mainImageView);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintid);
        upgrade1pic = (ImageView) findViewById(R.id.id_upgrade1);
        upgrade2pic = (ImageView) findViewById(R.id.upgradepic2_id);
        grandCostText = (TextView) findViewById(R.id.grandCostText_id);
        factCostText = (TextView) findViewById(R.id.factCostText_id);
        cpsText = (TextView) findViewById(R.id.cpsText_id);


        counter = new AtomicInteger(0);

        final ScaleAnimation scale = new ScaleAnimation(0.5f,1.1f,0.5f,1.1f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale.setDuration(200);

        final AlphaAnimation fadeIn = new AlphaAnimation(0f,1f);
        final AlphaAnimation fadeOut = new AlphaAnimation(1f,0f);
        fadeIn.setDuration(300);
        fadeOut.setDuration(300);

        final myThread upThread = new myThread(0);
        upThread.start();

        mainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(scale);
                counter.getAndAdd(1);
                cookieText.setText(counter+" cookie(s)");
                plusOneAnimation();

                if (counter.get() >= grandmaCost ){
                    upgrade1pic.setVisibility(View.VISIBLE);
                    upgrade1pic.setClickable(true);
                    grandCostText.setVisibility(View.VISIBLE);
                    grandCostText.setText("Cost: "+ grandmaCost);
                }

                upgrade1pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        counter.addAndGet(-grandmaCost);
                        if (counter.get() < grandmaCost) {
                            upgrade1pic.setVisibility(View.INVISIBLE);
                            upgrade1pic.setClickable(false);
                            grandCostText.setVisibility(View.INVISIBLE);
                        }
                        if (counter.get() < factoryCost) {
                            upgrade2pic.setVisibility(View.INVISIBLE);
                            upgrade2pic.setClickable(false);
                            factCostText.setVisibility(View.INVISIBLE);
                            upgrade2pic.startAnimation(fadeOut);
                        }
                        upgrade1pic.startAnimation(fadeOut);
                        cookieText.setText(counter+" cookie(s)");
                        moveGrandma();
                        grandmaCost+=5;
                        grandCostText.setText("Cost: "+ grandmaCost);
                        upThread.cps += 1;
                        cpsText.setText("per second: "+ upThread.cps);
                    }
                });
                
                if (counter.get() >= factoryCost){
                    upgrade2pic.setVisibility(View.VISIBLE);
                    upgrade2pic.setClickable(true);
                    factCostText.setVisibility(View.VISIBLE);
                    factCostText.setText("Cost: "+ factoryCost);
                }
                
                upgrade2pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        counter.addAndGet(-factoryCost);
                        if (counter.get() < factoryCost) {
                            upgrade2pic.setVisibility(View.INVISIBLE);
                            upgrade2pic.setClickable(false);
                            factCostText.setVisibility(View.INVISIBLE);
                        }
                        if (counter.get() < grandmaCost) {
                            upgrade1pic.setVisibility(View.INVISIBLE);
                            upgrade1pic.setClickable(false);
                            grandCostText.setVisibility(View.INVISIBLE);
                            upgrade1pic.startAnimation(fadeOut);
                        }
                        upgrade2pic.startAnimation(fadeOut);
                        cookieText.setText(counter+" cookie(s)");
                        moveFactory();
                        factoryCost+=5;
                        factCostText.setText("Cost: "+ factoryCost);
                        upThread.cps += 3;
                        cpsText.setText("per second: "+ upThread.cps);
                    }
                });
                
            }
        });

    }

    public class myThread extends Thread {

        int cps;

        public myThread(int cps){
            this.cps = cps;
        }

        public void run (){
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                counter.getAndAdd(cps);

                upgrade1pic.post(new Runnable() {
                    @Override
                    public void run() {
                        if (counter.get() >= grandmaCost ){
                            upgrade1pic.setVisibility(View.VISIBLE);
                            upgrade1pic.setClickable(true);
                            grandCostText.setVisibility(View.VISIBLE);
                            grandCostText.setText("Cost: "+ grandmaCost);
                        }
                        if (counter.get() >= factoryCost){
                            upgrade2pic.setVisibility(View.VISIBLE);
                            upgrade2pic.setClickable(true);
                            factCostText.setVisibility(View.VISIBLE);
                            factCostText.setText("Cost: "+ factoryCost);
                        }
                    }
                });

                cookieText.post(new Runnable() {
                    @Override
                    public void run() {
                        cookieText.setText(counter + " cookie(s)");
                    }
                });


            }
        }
    }
    
    public void moveFactory(){
        factoryPic = new ImageView(getApplicationContext());
        factoryPic.setId(View.generateViewId());
        factoryPic.setImageResource(R.drawable.factoryimg);

        ConstraintLayout.LayoutParams paramsF = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        factoryPic.setLayoutParams(paramsF);

        constraintLayout.addView(factoryPic);

        ConstraintSet constraintSet4 = new ConstraintSet();
        constraintSet4.clone(constraintLayout);

        constraintSet4.connect(factoryPic.getId(),ConstraintSet.TOP,constraintLayout.getId(),ConstraintSet.TOP);
        constraintSet4.connect(factoryPic.getId(),ConstraintSet.BOTTOM,constraintLayout.getId(),ConstraintSet.BOTTOM);
        constraintSet4.connect(factoryPic.getId(),ConstraintSet.RIGHT,constraintLayout.getId(),ConstraintSet.RIGHT);
        constraintSet4.connect(factoryPic.getId(),ConstraintSet.LEFT,constraintLayout.getId(),ConstraintSet.LEFT);
        constraintSet4.setHorizontalBias(factoryPic.getId(), (float) (Math.random()*.4+.6));
        constraintSet4.setVerticalBias(factoryPic.getId(), 0.92f);

        constraintSet4.applyTo(constraintLayout);

        final AlphaAnimation fadeIn = new AlphaAnimation(0f,1f);
        fadeIn.setDuration(300);
        factoryPic.startAnimation(fadeIn);
    }

    public void moveGrandma() {
        grandmaPicSmall = new ImageView(getApplicationContext());
        grandmaPicSmall.setId(View.generateViewId());
        grandmaPicSmall.setImageResource(R.drawable.grandma);

        ConstraintLayout.LayoutParams paramsG = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        grandmaPicSmall.setLayoutParams(paramsG);

        constraintLayout.addView(grandmaPicSmall);

        ConstraintSet constraintSet3 = new ConstraintSet();
        constraintSet3.clone(constraintLayout);

        constraintSet3.connect(grandmaPicSmall.getId(),ConstraintSet.TOP,constraintLayout.getId(),ConstraintSet.TOP);
        constraintSet3.connect(grandmaPicSmall.getId(),ConstraintSet.BOTTOM,constraintLayout.getId(),ConstraintSet.BOTTOM);
        constraintSet3.connect(grandmaPicSmall.getId(),ConstraintSet.RIGHT,constraintLayout.getId(),ConstraintSet.RIGHT);
        constraintSet3.connect(grandmaPicSmall.getId(),ConstraintSet.LEFT,constraintLayout.getId(),ConstraintSet.LEFT);
        constraintSet3.setHorizontalBias(grandmaPicSmall.getId(), (float) (Math.random()*.4));
        constraintSet3.setVerticalBias(grandmaPicSmall.getId(), 0.92f);

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
        textViewInCode.setTextColor(Color.WHITE);

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
        constraintSet1.setVerticalBias(textViewInCode.getId(), 0.35f);

        constraintSet1.applyTo(constraintLayout);

        Animation scale1 = new TranslateAnimation(0,0,200,0);
        scale1.setDuration(1000);
        textViewInCode.startAnimation(scale1);
        scale1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                constraintLayout.removeView(textViewInCode);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }



}
