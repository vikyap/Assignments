package au.edu.jcu.cp3406.currencyconverter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private TextView display;
    private int decimalPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.input);
        display = (TextView) findViewById(R.id.display);
        if (savedInstanceState != null) {
            // re-construct the instance using the previous values
            display.setText(savedInstanceState.getString("displayValue"));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("displayValue", display.getText().toString());
        // takes in value of current instance to be saved
    }

    public boolean isEmpty() {
        if (input.getText().length() == 0) {
            // check for blank input
            Toast toast = Toast.makeText(this, "Please enter an amount", Toast.LENGTH_LONG);
            toast.show();
            return true;
        }
        return false;
    }


    public void clickUSD(View view) {
        if (!isEmpty()) {
            double amountToExchange = Double.parseDouble(input.getText().toString());
            String amountExchanged = Double.toString(Convert.round(Convert.convertUSD(amountToExchange), decimalPlace));
            display.setText(amountExchanged);
        }
    }

    public void clickGBP(View view) {
        if (!isEmpty()) {
            double amountToExchange = Double.parseDouble(input.getText().toString());
            String amountExchanged = Double.toString(Convert.round(Convert.convertGBP(amountToExchange), decimalPlace));
            display.setText(amountExchanged);
        }
    }

    public void clickEUR(View view) {
        if (!isEmpty()) {
            double amountToExchange = Double.parseDouble(input.getText().toString());
            String amountExchanged = Double.toString(Convert.round(Convert.convertEUR(amountToExchange), decimalPlace));
            display.setText(amountExchanged);
        }
    }

    public void clickAUD(View view) {
        if (!isEmpty()) {
            double amountToExchange = Double.parseDouble(input.getText().toString());
            String amountExchanged = Double.toString(Convert.round(Convert.convertAUD(amountToExchange), decimalPlace));
            display.setText(amountExchanged);
        }
    }

    public void clickJPY(View view) {
        if (!isEmpty()) {
            double amountToExchange = Double.parseDouble(input.getText().toString());
            String amountExchanged = Double.toString(Convert.round(Convert.convertJPY(amountToExchange), decimalPlace));
            display.setText(amountExchanged);
        }
    }

    public void clickCHF(View view) {
        if (!isEmpty()) {
            double amountToExchange = Double.parseDouble(input.getText().toString());
            String amountExchanged = Double.toString(Convert.round(Convert.convertCHF(amountToExchange), decimalPlace));
            display.setText(amountExchanged);
        }
    }

    public void clickClear(View view) {
        input.setText("");
        display.setText("");
    }

    public void clickSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    // extract the additional information that was passed back
                    decimalPlace = data.getIntExtra("decimalPlace", 0);

                }
            }
        }
    }
}