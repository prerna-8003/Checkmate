package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.Map;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.firebase.firestore.FirebaseFirestore;



public class cratenote extends AppCompatActivity {
    FloatingActionButton savenote;
    EditText mcreatetitleofnote,mcreatecontentofnote;
    ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnote);
        savenote=findViewById(R.id.savenote);
        mcreatetitleofnote=findViewById(R.id.createtitleofnote);
        mcreatecontentofnote=findViewById(R.id.createcontentofnote);
        progressbar=findViewById(R.id.progressbar);
        // Toolbar toolbar=findViewById(R.id.toolbarofcreatenote);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back button

        savenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mcreatetitleofnote.getText().toString();
                String content = mcreatecontentofnote.getText().toString();


                if (title.isEmpty()||content.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Both fields are required", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    progressbar.setVisibility(view.VISIBLE);


                    Map<String, String> v = new HashMap<>();
                    v.put("title", title);
                    v.put("content", content);

                    FirebaseFirestore.getInstance().collection("user").document().set(v).addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(cratenote.this, "note created Successfully", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(cratenote.this, MainActivity3.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(cratenote.this, "Failed", Toast.LENGTH_SHORT).show();

                        }
                    });


                }



            }
        });




    }
}