package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private static int SPLASH_SCREEN=5000;
    //variables
    Animation topAnim,bottomAnim;
    ImageView image;
    TextView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);

        //Animations
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image=findViewById(R.id.imageView);
        logo=findViewById(R.id.textView);


        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent=new Intent(MainActivity2.this,name.class);
                startActivity(intent);
                finish();
            }

        },SPLASH_SCREEN);

    }
}
