package com.example.alaaalshammaa.keeprealm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alaa Alshammaa on 4/28/2016.
 */
public class Note extends RealmObject {
    @PrimaryKey
    int id;

    String title;
    String desc;

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
