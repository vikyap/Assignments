package au.edu.jcu.cp3406.quizgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Landing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

    public void clickPlay(View view) {
        Intent intent = new Intent(this, MemoryQuiz.class);
        startActivity(intent);

    }


    public void clickScores(View view) {
        Intent intent = new Intent(this, Scores.class);
        startActivity(intent);

    }

    public void clickInstructions(View view) {
        Intent intent = new Intent(this, Instructions.class);
        startActivity(intent);

    }


}