package com.example.a10014422.cookieclicker;

import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class CookieClickerActivity extends AppCompatActivity {

    TextView cookieText;
    ImageView mainImage;
    ImageView plus1;
    int counter;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie_clicker);

        cookieText = (TextView)findViewById(R.id.Cookietext);
        mainImage = (ImageView) findViewById(R.id.mainImageView);
        plus1 = (ImageView) findViewById(R.id.plus1image);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintid);


        final ScaleAnimation scale = new ScaleAnimation(0.5f,1.1f,0.5f,1.1f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale.setDuration(200);

        mainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(scale);
                counter+=1;
                cookieText.setText(counter+" cookies");

                final TextView textViewInCode;
                textViewInCode = new TextView(getApplicationContext());
                textViewInCode.setId(View.generateViewId());
                textViewInCode.setText("+1");

                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                textViewInCode.setLayoutParams(params);

                constraintLayout.addView(textViewInCode);

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);

                constraintSet.connect(textViewInCode.getId(),ConstraintSet.TOP,constraintLayout.getId(),ConstraintSet.TOP);
                constraintSet.connect(textViewInCode.getId(),ConstraintSet.BOTTOM,constraintLayout.getId(),ConstraintSet.BOTTOM);
                constraintSet.connect(textViewInCode.getId(),ConstraintSet.RIGHT,constraintLayout.getId(),ConstraintSet.RIGHT);
                constraintSet.connect(textViewInCode.getId(),ConstraintSet.LEFT,constraintLayout.getId(),ConstraintSet.LEFT);
                constraintSet.setHorizontalBias(textViewInCode.getId(), (float) (Math.random()*.5+.25));
                constraintSet.setVerticalBias(textViewInCode.getId(), 0.25f);

                constraintSet.applyTo(constraintLayout);

                Animation scale1 = new TranslateAnimation(0,0,200,0);
                scale1.setDuration(1000);
                textViewInCode.startAnimation(scale1);
                textViewInCode.setVisibility(View.INVISIBLE);

            }
        });
    }


}
