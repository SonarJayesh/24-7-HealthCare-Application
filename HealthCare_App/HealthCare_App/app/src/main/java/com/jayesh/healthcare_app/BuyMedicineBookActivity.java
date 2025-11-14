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

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname = findViewById(R.id.editTextBMBFullName);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edpincode = findViewById(R.id.editTextBMBPinCode);
        edcontact = findViewById(R.id.editTextBMBContact);
        btnBooking = findViewById(R.id.buttonBMBBooking);

        Intent intent = getIntent();
        String price = intent.getStringExtra("price");
        String date = intent.getStringExtra("date");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                try {

                    Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                   /* Log.i("test",username);
                    Log.i("test",edname.getText().toString());
                    Log.i("test",edname.getText().toString());
                    Log.i("test",edcontact.getText().toString());
                    Log.i("test",edpincode.getText().toString());
                    Log.i("test",date.toString());
                    Log.i("test",price);*/
                    db.addOrder(username, edname.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(),
                     edpincode.getText().toString(), date,"0",price,"medicine");
                    db.removeCart(username, "medicine");
                }catch (Exception e){


                }
                Toast.makeText(getApplicationContext(),"Your Booking is done Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BuyMedicineBookActivity.this,HomeActivity.class));
                finish();
            }
        });
    }
}