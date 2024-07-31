package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Map;
import java.util.HashMap;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class editnoteactivity extends AppCompatActivity {
    Intent data;
    EditText medittitleofnote,meditcontentofnote;
    FloatingActionButton msaveditnote;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnoteactivity);

        meditcontentofnote=findViewById(R.id.editcontentofnote);
        medittitleofnote=findViewById(R.id.edittitleofnote);
        msaveditnote=findViewById(R.id.saveeditnote);
//        Toolbar toolbar=findViewById(R.id.toolbarofeditnote);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data=getIntent();
        firebaseFirestore=FirebaseFirestore.getInstance();

        String notetitle=data.getStringExtra("title");
        String notecontent=data.getStringExtra("content");
        String noteId = data.getStringExtra("noteId");
        meditcontentofnote.setText(notecontent);
        medittitleofnote.setText(notetitle);


        msaveditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newtitle=medittitleofnote.getText().toString();
                String newcontent=meditcontentofnote.getText().toString();

                if(newtitle.isEmpty()||newcontent.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Both the fields are required", Toast.LENGTH_SHORT).show();
                }
                else {


                    DocumentReference documentReference = firebaseFirestore.collection("user").document(noteId);
                    Map<String, Object> note = new HashMap<>();

                    note.put("content", newcontent);
                    note.put("title", newtitle);
                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "note updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(editnoteactivity.this, MainActivity3.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "failed to update", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });


    }
}
