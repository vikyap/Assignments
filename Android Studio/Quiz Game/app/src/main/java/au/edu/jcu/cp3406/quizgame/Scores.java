package au.edu.jcu.cp3406.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Scores extends AppCompatActivity {

    private ListView viewScoreList;
    private ArrayList<String> scoreList;
    private Cursor cursor;
    private ListAdapter adapter;
    HighScoresDatabaseHelper highScoresDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        viewScoreList = (ListView) findViewById(R.id.viewScoreList);
        highScoresDatabaseHelper = new HighScoresDatabaseHelper(this);
        scoreList = new ArrayList<>();

        // obtain cursor object containing database information
        cursor = highScoresDatabaseHelper.getScore();

        while (cursor.moveToNext()) {
            // add the information from the cursor object into a list
            scoreList.add(cursor.getString(1) + "    " + cursor.getString(2));
            // display the list into a listview
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoreList);
            viewScoreList.setAdapter(adapter);

        }

    }

    public void clickBack(View view) {
        Intent intent = new Intent(this, Landing.class);
        startActivity(intent);
    }


}