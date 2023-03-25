/* --------------- STUDENT DETAILS ----------------
Name: Kane Landolina
ID: 218692411
UNIT Code: SIT708
 */



package com.example.a218692411_task2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {
//creating variables
    Spinner sourceSpinner;
    Spinner destinationSpinner;
    String selectedSource;
    String selectedDestination;
    Button convertButton;
    TextView sourceValueText;
    TextView convertedValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adding items to the variables for access
        sourceSpinner = findViewById(R.id.sourceSpinner);
        destinationSpinner = findViewById(R.id.destinationSpinner);
        convertButton = findViewById(R.id.convertButton);
        sourceValueText = findViewById(R.id.sourceValueText);
        convertedValueTextView = findViewById(R.id.convertedValueTextView);

        //create adapter for array to show variables from strings.xml file
        ArrayAdapter<CharSequence> sourceAdapter = ArrayAdapter.createFromResource(this,
                R.array.sourceArray, android.R.layout.simple_spinner_item);
        sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceSpinner.setAdapter(sourceAdapter);

        //create adapter for array to show variables from strings.xml file
        ArrayAdapter<CharSequence> destinationAdapter = ArrayAdapter.createFromResource(this,
                R.array.destinationArray, android.R.layout.simple_spinner_item);
        destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationSpinner.setAdapter(destinationAdapter);

        //setting spinners to use OnItemSelectedListener functions
        sourceSpinner.setOnItemSelectedListener(this);
        destinationSpinner.setOnItemSelectedListener(this);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the input value from text entry and convert to string
                String inputValue = sourceValueText.getText().toString().trim();

                if (inputValue.isEmpty()) {
                    //if empty, create a toast
                    Toast.makeText(MainActivity.this, "Please enter a value.", Toast.LENGTH_SHORT).show();
                }
                else if (!inputValue.matches("^[0-9]*\\.?[0-9]*$")) {
                    //if input is not only digits and one decimal, notify the user
                    Toast.makeText(MainActivity.this, "Please enter only digits with up to one decimal place.", Toast.LENGTH_SHORT).show();
                }
                else if (inputValue.matches("^[0-9]*\\.?[0-9]*$")) {
                    //else if input is correctly entered, convert the value and print the result
                    // NOTE: ONLY FOOT to CENTIMETER has been completed
                    // if foot and centimeter are selected, complete the conversion
                    if (selectedSource.equals("Foot") && selectedDestination.equals("Centimeter")) {
                        //Log.v("Selected", selectedSource + " " + selectedDestination);
                        Double number = Double.parseDouble(inputValue);
                        number = number * 30.48;
                        Log.v("Selected", String.valueOf(number));
                        convertedValueTextView.setText(String.valueOf(number) + " " + selectedDestination + "s");
                    }
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        /*Toast.makeText(adapterView.getContext(),
                "Item : " + adapterView.getItemAtPosition(i).toString(),
                Toast.LENGTH_SHORT).show();
        */
        //when the item is selected on the spinner, store the result for later use when button is pressed
        if (adapterView.getId() == R.id.sourceSpinner) {
            selectedSource = adapterView.getItemAtPosition(i).toString();
            Log.v("Source Spinner", selectedSource);
        }
        else if (adapterView.getId() == R.id.destinationSpinner) {
            selectedDestination = adapterView.getItemAtPosition(i).toString();
            Log.v("Destination Spinner", selectedDestination);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}