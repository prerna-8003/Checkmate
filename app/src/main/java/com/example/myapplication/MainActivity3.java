package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

import java.util.ArrayList;
import java.util.List;
public class MainActivity3 extends AppCompatActivity {
    FloatingActionButton mcreatenotesfab;
    RecyclerView mrecyclerview;
    StaggeredGridLayoutManager sgridManager;
    FirebaseFirestore firebaseFirestore;

    FirestoreRecyclerAdapter<firebasemodel, NoteViewHolder> noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mcreatenotesfab = findViewById(R.id.createnotefab);
        firebaseFirestore = firebaseFirestore.getInstance();
        getSupportActionBar().setTitle("All notes");

        mcreatenotesfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, cratenote.class));
            }
        });
        Query query = firebaseFirestore.collection("user").orderBy("title", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<firebasemodel> usernotes = new FirestoreRecyclerOptions.Builder<firebasemodel>().setQuery(query, firebasemodel.class).build();


        noteAdapter = new FirestoreRecyclerAdapter<firebasemodel, NoteViewHolder>(usernotes) {
            @Override
            protected void onBindViewHolder(@NonNull NoteViewHolder holder, int i, @NonNull firebasemodel model) {
                //code to add random color

                ImageView popupbutton=holder.itemView.findViewById(R.id.menupopbutton);

                int colourcode=getRandomColor();
                holder.mnote.setBackgroundColor(holder.itemView.getResources().getColor(colourcode,null));
                holder.notetitle.setText(model.getTitle());
                holder.notecontent.setText(model.getContent());

                String docId=noteAdapter.getSnapshots().getSnapshot(i).getId();
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //we have to open detail activity
                        Intent intent=new Intent(v.getContext(), nodedetails.class);
                        intent.putExtra("title",model.getTitle());
                        intent.putExtra("content",model.getContent());
                        intent.putExtra("noteId",docId);

                        v.getContext().startActivity(intent);
                        // Toast.makeText(getApplicationContext(), "This is clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                popupbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popupMenu=new PopupMenu(v.getContext(),v);
                        popupMenu.setGravity(Gravity.END);
                        popupMenu.getMenu().add("Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                                Intent intent=new Intent(v.getContext(),editnoteactivity.class);
                                intent.putExtra("title",model.getTitle());
                                intent.putExtra("content",model.getContent());
                                intent.putExtra("noteId",docId);
                                v.getContext().startActivity(intent);
                                return false;
                            }
                        });

                        popupMenu.getMenu().add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {

                                // Toast.makeText(v.getContext(), "This node is deleted", Toast.LENGTH_SHORT).show();
                                DocumentReference documentReference=firebaseFirestore.collection("user").document(docId);
                                documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(v.getContext(), "this node is deleted", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(v.getContext(), "Failed to Delete", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                return false;
                            }
                        });
                        popupMenu.show();
                    }
                });

            }

            @NonNull
            @Override
            public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_layout,parent,false);
                return new NoteViewHolder(view);
            }
        };
        mrecyclerview=findViewById(R.id.recyclerview);
        mrecyclerview.setHasFixedSize(true);
        sgridManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mrecyclerview.setLayoutManager(sgridManager);
        mrecyclerview.setAdapter(noteAdapter);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        private TextView notetitle;
        private TextView notecontent;
        LinearLayout mnote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            notetitle=itemView.findViewById(R.id.notetitle);
            notecontent=itemView.findViewById(R.id.notecontent);
            mnote=itemView.findViewById(R.id.note);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        noteAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(noteAdapter!=null) {
            noteAdapter.stopListening();
        }

    }

    private int getRandomColor(){
        List<Integer> colorcode=new ArrayList<>();
        colorcode.add(R.color.gray);
        colorcode.add(R.color.green);
        colorcode.add(R.color.lightgreen);
        colorcode.add(R.color.color1);
        colorcode.add(R.color.color3);
        colorcode.add(R.color.color4);
        colorcode.add(R.color.color5);
        colorcode.add(R.color.pink);
        colorcode.add(R.color.skyblue);
        colorcode.add(R.color.color2);

        Random random=new Random();
        int number=random.nextInt(colorcode.size());
        return colorcode.get(number);

    }

}
