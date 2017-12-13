package com.irpansyam.mynotesapp.db;

import android.provider.BaseColumns;

/**
 * Created by SONY on 12/3/2017.
 */

public class DatabaseContract {
    static String TABLE_NOTE = "note";
    static final class NoteColumns implements BaseColumns {
        //Note title
        static String TITLE = "title";
        //Note description
        static String DESCRIPTION = "description";
        //Note date
        static String DATE = "date";
    }
}
