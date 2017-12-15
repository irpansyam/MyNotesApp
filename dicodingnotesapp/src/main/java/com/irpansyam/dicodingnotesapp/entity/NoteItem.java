package com.irpansyam.dicodingnotesapp.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.irpansyam.dicodingnotesapp.db.DatabaseContract;

import static com.irpansyam.dicodingnotesapp.db.DatabaseContract.getColumnInt;
import static com.irpansyam.dicodingnotesapp.db.DatabaseContract.getColumnString;

/**
 * Created by SONY on 12/15/2017.
 */

public class NoteItem implements Parcelable{
    private int id;
    private String title, description, date;



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(date);
    }
    public NoteItem(){

    }
    public NoteItem(Cursor cursor){
        this.id = getColumnInt(cursor, DatabaseContract.NoteColumns._ID);
        this.title = getColumnString(cursor, DatabaseContract.NoteColumns.TITLE);
        this.description = getColumnString(cursor, DatabaseContract.NoteColumns.DESCRIPTION);
        this.date = getColumnString(cursor, DatabaseContract.NoteColumns.DATE);
    }
    protected NoteItem(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<NoteItem> CREATOR = new Parcelable.Creator<NoteItem>() {
        @Override
        public NoteItem createFromParcel(Parcel in) {
            return new NoteItem(in);
        }

        @Override
        public NoteItem[] newArray(int size) {
            return new NoteItem[size];
        }
    };
}