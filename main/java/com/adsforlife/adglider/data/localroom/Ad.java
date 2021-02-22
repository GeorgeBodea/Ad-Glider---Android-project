package com.adsforlife.adglider.data.localroom;

import android.media.Image;
import android.view.View;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "adEntity")
public class Ad {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo( name = "id" )
    private int id;

    @ColumnInfo( name = "title" )
    @NotNull
    private String title;

    @ColumnInfo( name = "company" )
    @NotNull
    private String company;

    @ColumnInfo( name = "body" )
    @NotNull
    private String body;

    public Ad( @NotNull String title, @NotNull String company, @NotNull String body) {
        this.title = title;
        this.company = company;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    @NotNull
    public String getCompany() {
        return company;
    }

    public void setCompany(@NotNull String company) {
        this.company = company;
    }

    @NotNull
    public String getBody() {
        return body;
    }

    public void setBody(@NotNull String body) {
        this.body = body;
    }


}
