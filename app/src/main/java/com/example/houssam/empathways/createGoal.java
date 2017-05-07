package com.example.houssam.empathways;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.example.houssam.empathways.R.layout.activity_main;
public class createGoal extends AppCompatActivity {

    Button createButton;
    EditText goalField;
    String inputGoal;
    Button createGoalButton;
    public static final String EXTRA_MESSAGE = "EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        EmpathHelper my_empath_helper = new EmpathHelper(this);

        // Gets the data repository in write mode
        SQLiteDatabase db = my_empath_helper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(data_pat.empathEntry.GOALS_COLUMN_NAME, "Patricia");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(data_pat.empathEntry.GOALS_TABLE_NAME, null, values);


    }
    public void createNewGoal(View view) {
        // Do something in response to button
        // get EditText by id
        goalField = (EditText)findViewById(R.id.goalText);
        inputGoal = goalField.getText().toString();
        //ContentValues goals = new ContentValues();
        //goals.put(data_pat.empathEntry.GOALS_COLUMN_TITLE, inputGoal);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, inputGoal);
        startActivity(intent);

    }

}
