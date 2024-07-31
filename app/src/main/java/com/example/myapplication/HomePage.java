package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class HomePage extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        ImageView send= findViewById(R.id.button);

        ImageView send1=findViewById(R.id.button2);
        ImageView send2=findViewById(R.id.button3);
        ImageView send3=findViewById(R.id.button5);





        TextView tv = (TextView) findViewById(R.id.textView2);
        tv.setText("Hello, \n"+getIntent().getExtras().getString("username")+"\nStart planning your day");


        send.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent send=new Intent(HomePage.this ,MainActivity.class);

                                        startActivity(send);
                                    }
                                }
        );
        send1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent send=new Intent(HomePage.this ,MainActivity1.class);

                                        startActivity(send);
                                    }
                                }
        );
        send2.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {

                                         Intent send=new Intent(HomePage.this ,MainActivity3.class);

                                         startActivity(send);
                                     }
                                 }
        );
        send3.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {

                                         Intent send=new Intent(HomePage.this ,MainActivity4.class);

                                         startActivity(send);
                                     }
                                 }
        );




    }




}
