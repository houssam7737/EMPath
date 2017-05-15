package com.example.houssam.empathways;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patricia on 5/14/2017.
 */

public class DetailActivity extends AppCompatActivity {

    String goalTitle;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        id = intent.getStringExtra("DETAIL_ID");

        EmpathHelper my_empath_helper = new EmpathHelper(DetailActivity.this);
        SQLiteDatabase db = my_empath_helper.getWritableDatabase();
        SQLiteDatabase dbR = my_empath_helper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                data_pat.empathEntry.GOALS_COLUMN_TITLE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = data_pat.empathEntry._ID + " = ?";
        String[] selectionArgs = {id};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                data_pat.empathEntry._ID + " DESC";

        Cursor cursor = db.query(
                data_pat.empathEntry.GOALS_TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while(cursor.moveToNext()) {

            goalTitle = cursor.getString(
                    cursor.getColumnIndexOrThrow(data_pat.empathEntry.GOALS_COLUMN_TITLE));
        }
        cursor.close();

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(id + ": " + goalTitle);
    }

    public void deleteGoal(View view) {

        EmpathHelper my_empath_helper = new EmpathHelper(DetailActivity.this);
        SQLiteDatabase db = my_empath_helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(data_pat.empathEntry.GOALS_COLUMN_TITLE, goalTitle);

        String selection = data_pat.empathEntry._ID + " = ?";
        String[] selectionArgs = {id};
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.delete(data_pat.empathEntry.GOALS_TABLE_NAME, selection,selectionArgs);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
