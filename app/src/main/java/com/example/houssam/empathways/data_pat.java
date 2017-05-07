package com.example.houssam.empathways;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Patricia on 4/22/2017.
 */
public final class data_pat {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private data_pat() {
    }


    /* Inner class that defines the table contents */
    public static class empathEntry implements BaseColumns {
        public static final String DATABASE_NAME = "empath_test.db";
        public static final String GOALS_TABLE_NAME = "goals";
        public static final String GOALS_COLUMN_NAME = "name";
        public static final String GOALS_COLUMN_TITLE = "title";
        public static final String GOALS_COLUMN_DEADLINE = "deadline";
        public static final String GOALS_COLUMN_REWARD = "reward";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + empathEntry.GOALS_TABLE_NAME + " (" +
                    empathEntry._ID + " INTEGER PRIMARY KEY," +
                    empathEntry.GOALS_COLUMN_NAME + " TEXT," +
                    empathEntry.GOALS_COLUMN_TITLE + " TEXT," +
                    empathEntry.GOALS_COLUMN_DEADLINE + " TEXT," +
                    empathEntry.GOALS_COLUMN_REWARD + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + empathEntry.GOALS_TABLE_NAME;

}
