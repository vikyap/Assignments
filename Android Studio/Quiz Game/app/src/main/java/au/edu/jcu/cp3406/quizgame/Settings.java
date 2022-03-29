package au.edu.jcu.cp3406.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Settings extends AppCompatActivity {

    public final static int SETTINGS_REQUEST = 111;
    private RadioButton easy, normal, hard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        easy = (RadioButton) findViewById(R.id.easy);
        normal = (RadioButton) findViewById(R.id.normal);
        hard = (RadioButton) findViewById(R.id.hard);
    }

    public void clickDone(View view) {
        // get the string (easy, normal, hard) passed back to game activity as intent data
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int id = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(id);
        String difficulty = radioButton.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("difficulty", difficulty);
        setResult(RESULT_OK, intent);
        finish();
    }


    public void clickBack(View view) {
        Intent intent = new Intent(this, Landing.class);
        startActivity(intent);
    }


}