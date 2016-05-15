package com.example.alaaalshammaa.keeprealm;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AddNoteActivity extends AppCompatActivity {
    EditText etTitle,etDesc;
    String title,desc;
    Realm realm;
    RealmConfiguration realmConfig;
    FloatingActionButton fabDone;
    int i = 0;
    boolean editing = false;
    boolean edited;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        realmConfig = new RealmConfiguration.Builder(this).build();
        realm = Realm.getInstance(realmConfig);
        etTitle = (EditText) findViewById(R.id.addnote_title);
        etDesc = (EditText) findViewById(R.id.addnote_desc);
        fabDone = (FloatingActionButton) findViewById(R.id.addnote_fab);
        fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
                Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        i = getIntent().getIntExtra(NotesAdapter.EDITEDNOTEPOSITION, 0);
        if (i != 0) {
            editing = true;
            Note note = realm.where(Note.class).equalTo("id", i).findFirst();
            etTitle.setText(note.getTitle());
            etDesc.setText(note.getDesc());
        }
    }

    private void addNote() {
        title = etTitle.getText().toString();
        desc = etDesc.getText().toString();
        if(editing){
            editing = false;
            edited = true;
            Note note = realm.where(Note.class).equalTo("id", i).findFirst();
            realm.beginTransaction();
            note.setTitle(title);
            note.setDesc(desc);
            realm.commitTransaction();
            return;
        }
        Note note = new Note();
        note.setTitle(title);
        note.setDesc(desc);
        long size = realm.where(Note.class).count();
        final int i = (int) (size + 1);
        note.setId(i);
        realm.beginTransaction();
        realm.copyToRealm(note);
        realm.commitTransaction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
