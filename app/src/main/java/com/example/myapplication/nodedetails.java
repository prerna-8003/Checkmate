package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;
import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class nodedetails extends AppCompatActivity {
    private TextView mtitleofnotedetail,mcotentnotedetail;
    FloatingActionButton mgotoeditnote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notesdetails);

        mtitleofnotedetail=findViewById(R.id.titleofnotedetail);
        mtitleofnotedetail.setMovementMethod(new ScrollingMovementMethod());
        mcotentnotedetail=findViewById(R.id.contentofnotedetail);
        mcotentnotedetail.setMovementMethod(new ScrollingMovementMethod());
        mgotoeditnote=findViewById(R.id.gotoeditnote);
        //Toolbar toolbar=findViewById(R.id.toolbarofnotedetail);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back button

        Intent data=getIntent();

        mcotentnotedetail.setText(data.getStringExtra("content"));
        mtitleofnotedetail.setText(data.getStringExtra("title"));
        mgotoeditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),editnoteactivity.class);
                intent.putExtra("title",data.getStringExtra("title"));
                intent.putExtra("content",data.getStringExtra("content"));
                intent.putExtra("noteId",data.getStringExtra("noteId"));


                v.getContext().startActivity(intent);
            }
        });


    }

}
