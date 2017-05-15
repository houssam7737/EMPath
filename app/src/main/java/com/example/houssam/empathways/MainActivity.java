package com.example.houssam.empathways;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.klinker.android.link_builder.Link;
import com.klinker.android.link_builder.LinkBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.example.houssam.empathways.R.id.button;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(createGoal.EXTRA_MESSAGE);

        EmpathHelper my_empath_helper = new EmpathHelper(MainActivity.this);
        SQLiteDatabase db = my_empath_helper.getWritableDatabase();

        if (message != null && !message.trim().isEmpty()) {

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(data_pat.empathEntry.GOALS_COLUMN_TITLE, message);

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(data_pat.empathEntry.GOALS_TABLE_NAME, null, values);

        }

        SQLiteDatabase dbR = my_empath_helper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                data_pat.empathEntry._ID,
                data_pat.empathEntry.GOALS_COLUMN_TITLE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = data_pat.empathEntry.GOALS_COLUMN_TITLE + " = ?";
        String[] selectionArgs = {};

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
                null                                 // The sort order
        );

        List itemIds = new ArrayList<>();
        List goalTitles = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean firstLine = true;
        while(cursor.moveToNext()) {
            if (!firstLine) sb.append("\n\n");
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(data_pat.empathEntry._ID));
            String goalTitle = cursor.getString(
                    cursor.getColumnIndexOrThrow(data_pat.empathEntry.GOALS_COLUMN_TITLE));
            itemIds.add(itemId);
            goalTitles.add(goalTitle);
            sb.append(itemId + ": " + goalTitle);
            firstLine = false;
        }
        cursor.close();

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.displayGoal);
        textView.setText(sb.toString());

        // Add the links and make the links clickable
        LinkBuilder.on(textView)
                .addLinks(getExampleLinks(itemIds))
                .build();
    }
    private List<Link> getExampleLinks(List<Long> ids) {
        List<Link> links = new ArrayList<>();

        Collections.reverse(ids);
        for (Long id : ids) {
            // match the numbers that I created
            Link link = new Link(id + ":");
            link.setBold(true);
            link.setTextColor(Color.parseColor("#52A291"));
            link.setOnClickListener(new Link.OnClickListener() {
                @Override
                public void onClick(String clickedText) {
                    openLink(clickedText);
                }
            });
            links.add(link);
        }
        return links;
    }

    private void openLink(String link) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("DETAIL_ID", link);
        startActivity(intent);
    }

    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }



    /** Called when the user taps the Send button */
    public void onCreate(View view) {
        Intent intent = new Intent(this, createGoal.class);
        startActivity(intent);
    }
}
