package com.example.houssam.empathways;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static com.example.houssam.empathways.R.attr.subtitle;
import static com.example.houssam.empathways.R.attr.title;
import static com.example.houssam.empathways.R.layout.activity_main;
import com.example.houssam.empathways.FeedReaderDbHelper;

public class createGoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_create_goal);

    }

    //Method called once user clicks on button to create goal
    //Input in database the goal and date (goal only for now)

    public void inputInDatabase(View view){
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(createGoal.this);

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert("GoalsDataBase", null, values);

        
    }
}
