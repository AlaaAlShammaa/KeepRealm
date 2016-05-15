package com.example.alaaalshammaa.keeprealm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    RealmRecyclerView realmRecyclerView;
    NotesAdapter adapter;
    Realm realm;
    RealmConfiguration realmConfig;
    FloatingActionButton fab,fabdel;
    RealmResults<Note> notes;
    boolean edited;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realmRecyclerView = (RealmRecyclerView) findViewById(R.id.realm_recycler_view);
        realmConfig = new RealmConfiguration.Builder(this).build();
        realm = Realm.getInstance(realmConfig);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabdel = (FloatingActionButton) findViewById(R.id.fabDel);
        notes = realm.where(Note.class).findAll();
        adapter = new NotesAdapter(getBaseContext(),
                notes, true, true, null);
        realmRecyclerView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(i);
            }
        });
        fabdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                realm.allObjects(Note.class).deleteAllFromRealm();
                realm.commitTransaction();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
