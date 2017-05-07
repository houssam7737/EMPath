package com.example.houssam.empathways;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.example.houssam.empathways.R.id.button;

public class MainActivity extends AppCompatActivity{

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(createGoal.EXTRA_MESSAGE);


        EmpathHelper my_empath_helper = new EmpathHelper(MainActivity.this);
        SQLiteDatabase db = my_empath_helper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(data_pat.empathEntry.GOALS_COLUMN_TITLE, message);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(data_pat.empathEntry.GOALS_TABLE_NAME, null, values);

        SQLiteDatabase dbR = my_empath_helper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                data_pat.empathEntry._ID,
                data_pat.empathEntry.GOALS_COLUMN_TITLE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = data_pat.empathEntry.GOALS_COLUMN_TITLE + " = ?";
        String[] selectionArgs = {null};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                data_pat.empathEntry._ID + " DESC";

        Cursor cursor = db.query(
                data_pat.empathEntry.GOALS_TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List itemIds = new ArrayList<>();
        List goalTitles = new ArrayList<>();
        while(cursor.moveToNext()) {
            Long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(data_pat.empathEntry._ID));
            String goalTitle = cursor.getString(
                    cursor.getColumnIndexOrThrow(data_pat.empathEntry.GOALS_COLUMN_TITLE));
            itemIds.add(itemId);
            goalTitles.add(goalTitle);
        }
        cursor.close();

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.displayGoal);
        textView.setText("IDs: " + itemIds.toString() + "Goals: " + goalTitles.toString());
    }

    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, createGoal.class);
                startActivity(intent);

            }

        });

    }
    /** Called when the user taps the Send button */
    public void onCreate(View view) {
        Intent intent = new Intent(this, createGoal.class);
        startActivity(intent);
    }
}
