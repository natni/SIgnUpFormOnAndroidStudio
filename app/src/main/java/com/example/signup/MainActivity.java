package com.example.signup;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;

import java.util.Calendar;




public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    /////////////////////////////
    Button signupButton;
    EditText editUserName;
    EditText editPassword;
    EditText editFirstName;
    EditText editLastName;
    EditText editEmailAddress;
    EditText editDateText;
    TextView textCurrentDate;
    ////////////////////////////



    String st;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /////////////////////////////////////////////////////////
        editUserName = findViewById(R.id.userName);
        editDateText = findViewById(R.id.dob);
        editPassword = findViewById(R.id.password);
        editFirstName = findViewById(R.id.firstName);
        editLastName = findViewById(R.id.lastName);
        editEmailAddress = findViewById(R.id.emailAddress);
        textCurrentDate = findViewById(R.id.currentDate);
        /////////////////////////////////////////////////////////



        //Display Current Date on home screen
        ////////////////////////CURRENT DATE////////////////////////////////////

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
        textCurrentDate.setText(currentDate);
        //////////////////////////////////////////////////////////////////////


        // Signup Button Code that takes to the second activity page

        signupButton = findViewById(R.id.btn_signup);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check() && checkAge()){
                    Intent intent = new Intent(MainActivity.this, activity_second.class);
                    st = editUserName.getText().toString();
                    intent.putExtra("Value", "@" + st);
                    startActivity(intent);
                }
            }
        });



        // Display calendar dialog onClick
        // Click on calendar image to select date from calendar dialog

        findViewById(R.id.calendarButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCalendar();
            }
        });

    }


    ///////////////////////Check if all fields are not empty///////////////////////////////////////////////////////////////////
    public boolean check(){
        boolean f;
        if(editUserName.getText().toString().isEmpty() || editPassword.getText().toString().isEmpty() ||
                editFirstName.getText().toString().isEmpty() || editLastName.getText().toString().isEmpty() ||
                !checkAge() ||
                editEmailAddress.getText().toString().isEmpty() || editDateText.getText().toString().isEmpty()){
            Toast.makeText(this, "Sign up failed. Please check for empty fields or make sure you are at least 18", Toast.LENGTH_LONG).show();
            f=false;
        }else{
            Toast.makeText(this, "Sign up Successful. Yay!" , Toast.LENGTH_LONG).show();
            f=true;
        }
        return f;
    }

    public boolean checkAge(){
        String currentYear = textCurrentDate.getText().toString();
        String customerYear = editDateText.getText().toString();
        boolean b = false;

        String currentYearFormatted = ("20" + currentYear.charAt(currentYear.length()-2) + currentYear.charAt(currentYear.length()-1));
        String customerYearFormatted = Character.toString(customerYear.charAt(customerYear.length()-4)) + customerYear.charAt(customerYear.length()-3) +
                customerYear.charAt(customerYear.length()-2) + customerYear.charAt(customerYear.length()-1);

        int ageDifference = Integer.parseInt(currentYearFormatted) - Integer.parseInt(customerYearFormatted);

        b= ageDifference > 17;
        return b;

    }



    public void displayCalendar(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month + "/" + dayOfMonth + "/" + year;
        editDateText.setText(date);
    }



}



