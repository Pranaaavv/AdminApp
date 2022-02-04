package com.example.adminapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteEvent extends AppCompatActivity {

    private RecyclerView deleteNoticeRecycler;
//    private ProgressBar progressBar;
    private ArrayList<NoticeData> list;
    private NoticeAdapter adapter;

    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_event);
        deleteNoticeRecycler= findViewById(R.id.deleteNoticeRecycler);
//        progressBar = findViewById(R.id.progressBar);

        reference = FirebaseDatabase.getInstance().getReference("Notice");

        deleteNoticeRecycler.setLayoutManager(new LinearLayoutManager(this));
        deleteNoticeRecycler.setHasFixedSize(true);

        deleteNoticeRecycler = findViewById(R.id.deleteNoticeRecycler);
        reference = FirebaseDatabase.getInstance().getReference("Notice");
        deleteNoticeRecycler.setLayoutManager(new LinearLayoutManager(this));
        deleteNoticeRecycler.setHasFixedSize(true);

        list = new ArrayList<>();
        adapter =new NoticeAdapter(this,list);
        deleteNoticeRecycler.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    NoticeData data = dataSnapshot.getValue(NoticeData.class);
                    list.add(data);


                }
                adapter=new NoticeAdapter(DeleteEvent.this,list);
                adapter.notifyDataSetChanged();

                deleteNoticeRecycler.setAdapter(adapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}