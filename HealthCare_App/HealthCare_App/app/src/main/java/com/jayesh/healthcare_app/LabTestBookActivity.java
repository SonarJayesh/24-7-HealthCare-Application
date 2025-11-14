package com.jayesh.healthcare_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;
    String price,date,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname = findViewById(R.id.editTextLTBFullName);
        edaddress = findViewById(R.id.editTextLTBAddress);
        edpincode = findViewById(R.id.editTextLTBPinCode);
        edcontact = findViewById(R.id.editTextLTBContact);
        btnBooking = findViewById(R.id.buttonLTBBooking);

        Bundle bundle = getIntent().getExtras();

       Intent intent = getIntent();

        if (bundle != null){
            price = bundle.getString("price");
            date = bundle.getString("date");
            time = bundle.getString("time");

        }

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                try {

                    Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                    db.addOrder(username.toString(), edname.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(),
                    edpincode.getText().toString(), date, time, price, "lab");
                    db.removeCart(username, "lab");
                }catch (Exception e){


                }
                Toast.makeText(getApplicationContext(),"Your Booking is done Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LabTestBookActivity.this,HomeActivity.class));
                finish();
            }
        });
    }
}