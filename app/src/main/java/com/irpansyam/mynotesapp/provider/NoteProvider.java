package com.irpansyam.mynotesapp.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.irpansyam.mynotesapp.db.DatabaseContract;
import com.irpansyam.mynotesapp.db.NoteHelper;

import static com.irpansyam.mynotesapp.db.DatabaseContract.AUTHORITY;
import static com.irpansyam.mynotesapp.db.DatabaseContract.CONTENT_URI;

/**
 * Created by SONY on 12/14/2017.
 */

public class NoteProvider extends ContentProvider {
    private static final int NOTE = 1;
    private static final int NOTE_ID = 2;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        // content://com.irpansyam.mynotesapp/note
        sUriMatcher.addURI(AUTHORITY, DatabaseContract.TABLE_NOTE, NOTE);

        // content://com.irpansyam.mynotesapp/note/id
        sUriMatcher.addURI(AUTHORITY, DatabaseContract.TABLE_NOTE+ "/#", NOTE_ID);
    }
    private NoteHelper noteHelper;
    @Override
    public boolean onCreate() {
        noteHelper = new NoteHelper(getContext());
        noteHelper.open();
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] strings,  String s, String[] strings1, String s1) {
        Cursor cursor;

        switch (sUriMatcher.match(uri)){
            case NOTE:
                cursor = noteHelper.queryProvider();
                break;
            case NOTE_ID:
                cursor = noteHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        if (cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        long added;

        switch (sUriMatcher.match(uri)){
            case NOTE:
                added = noteHelper.insertProvider(contentValues);
                break;
            default:
                added = 0;
                break;
        }
        if (added >0){
            getContext().getContentResolver().notifyChange(uri, null);

        }
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        int deleted;
        switch (sUriMatcher.match(uri)){
            case NOTE_ID:
                deleted = noteHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }
        if (deleted > 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        int updated;
        switch (sUriMatcher.match(uri)){
            case NOTE_ID:
                updated = noteHelper.updateProvider(uri.getLastPathSegment(),contentValues);
                break;
            default:
                updated = 0;
                break;
        }
        if (updated >0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updated;
    }
}
