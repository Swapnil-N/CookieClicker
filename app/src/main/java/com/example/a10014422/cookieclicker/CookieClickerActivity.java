package com.example.a10014422.cookieclicker;

import android.provider.ContactsContract;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie_clicker);

        cookieText = (TextView)findViewById(R.id.Cookietext);
        mainImage = (ImageView) findViewById(R.id.mainImageView);
        plus1 = (ImageView) findViewById(R.id.plus1image);

        final ScaleAnimation scale = new ScaleAnimation(0.5f,1.1f,0.5f,1.1f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale.setDuration(200);

        mainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(scale);
                counter+=1;
                cookieText.setText(counter+" cookies");
                final TranslateAnimation scale1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,5.0f,Animation.RELATIVE_TO_SELF,1.0f);
                scale1.setDuration(2000);
                plus1.startAnimation(scale1);
            }
        });
    }



}
