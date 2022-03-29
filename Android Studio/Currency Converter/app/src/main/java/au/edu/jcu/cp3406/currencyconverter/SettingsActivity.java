package au.edu.jcu.cp3406.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    public final static int SETTINGS_REQUEST = 123;
    // reference point for the intent object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void clickDone(View view) {
        EditText settings = (EditText) findViewById(R.id.inputSettings);
        String inputSettings = settings.getText().toString();
        int decimalPlace = Integer.parseInt(inputSettings);
        Intent intent = new Intent();
        intent.putExtra("decimalPlace", decimalPlace);
        // passing back additional information on the specific decimal places
        setResult(RESULT_OK, intent);
        finish();
    }
}