package com.faisaljaved.wission.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.faisaljaved.wission.Adapter.DBEntryAdapter;
import com.faisaljaved.wission.db.DBEntry;
import com.faisaljaved.wission.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * DataBase Reference variable fetching data to listview
     */
    DatabaseReference databaseReference;

    /**
     * All values are added to this list
     * after fetching from realtime database
     */
    List<DBEntry> entrylist = new ArrayList<>();

    /**
     * list in displayed inside this listview element
     */
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseReference = FirebaseDatabase.getInstance().getReference("Entries");

        listView = (ListView) findViewById(R.id.listview);

        //attaching listener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
                DBEntry entry = entrylist.get(i);

                //creating an intent
                Intent intent = new Intent(getApplicationContext(), DataEntryView.class);

                //putting entries to intent
                intent.putExtra("ENTRY_NAME", entry.getName());
                intent.putExtra("ENTRY_EMAIL", entry.getEmail());
                intent.putExtra("ENTRY_PHONE", entry.getPhone());

                //starting the activity with intent
                startActivity(intent);
            }
        });        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    entrylist.clear();

                    for (DataSnapshot dbEntrySnapshot : dataSnapshot.getChildren()) {

                        DBEntry dbEntry = dbEntrySnapshot.getValue(DBEntry.class);
                        entrylist.add(dbEntry);
                    }

                    DBEntryAdapter adapter = new DBEntryAdapter(MainActivity.this, entrylist);

                    listView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (Exception e){

        }
    }


}
