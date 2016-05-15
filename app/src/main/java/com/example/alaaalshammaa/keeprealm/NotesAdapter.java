package com.example.alaaalshammaa.keeprealm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import co.moonmonkeylabs.realmrecyclerview.LoadMoreListItemView;
import io.realm.Realm;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by Alaa Alshammaa on 4/28/2016.
 */
public class NotesAdapter extends RealmBasedRecyclerViewAdapter<Note,NotesAdapter.ViewHolder> {
    public static final String EDITEDNOTEPOSITION = "EDITEDNOTEPOSITION";
    Context context;
    public NotesAdapter(Context context, RealmResults<Note> realmResults, boolean automaticUpdate, boolean animateResults, String animateExtraColumnName) {
        super(context, realmResults, automaticUpdate, animateResults, animateExtraColumnName);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindRealmViewHolder(final ViewHolder viewHolder,final int i) {
        final Note note = realmResults.get(i);
        viewHolder.title.setText(note.getTitle());
        viewHolder.desc.setText(note.getDesc());
        viewHolder.itemView.setBackgroundColor(Color.parseColor("#FFEB3B"));
        // I know that this is stupid :P, but this is not a production code. This code is just for fun :P
        viewHolder.desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), AddNoteActivity.class);
                intent.putExtra(EDITEDNOTEPOSITION, i+1);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), AddNoteActivity.class);
                intent.putExtra(EDITEDNOTEPOSITION, i+1);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    public class ViewHolder extends RealmViewHolder{
        TextView title,desc;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.note_item_title);
            desc = (TextView) itemView.findViewById(R.id.note_item_desc);

        }

//        public ViewHolder(TextView headerTextView) {
//            super(headerTextView);
//        }
//
//        public ViewHolder(LoadMoreListItemView loadMoreView) {
//            super(loadMoreView);
//        }
    }
}
