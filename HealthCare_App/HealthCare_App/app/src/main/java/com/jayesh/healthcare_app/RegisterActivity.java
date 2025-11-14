package com.jayesh.healthcare_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword, edConfirm;
    Button btnRegister;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        edUsername = findViewById(R.id.editTextRegUsername);
        edEmail = findViewById(R.id.editTextRegEmail);
        edPassword = findViewById(R.id.editTextRegPassword);
        edConfirm = findViewById(R.id.editTextRegConfirmPass);
        btnRegister = findViewById(R.id.btnRegister);
        tv = findViewById(R.id.textviewAlredyRegister);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirmPass = edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                String emailPattern1 = "^[a-zA-Z0-9]{1,20}@[gmail]{5}.[com]{3}$";
                String emailPattern2 = "^[a-zA-Z0-9]{1,20}.[a-zA-Z0-9]{5}@[gmail]{1,20}.[com]{3}$";

                String editEmail = edEmail.getText().toString();

                if (editEmail.matches(emailPattern1)){

                    Toast.makeText(RegisterActivity.this,"",Toast.LENGTH_SHORT).show();

                } else if (editEmail.matches(emailPattern2)) {

                    Toast.makeText(RegisterActivity.this,"",Toast.LENGTH_SHORT).show();

                }else {
                    edEmail.setError("Invalid Email");
                }


                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill All details", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirmPass)==0){
                        if (isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(),"Record Inserted",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(),"Password must contain at least 8 characters, having letter , digit and special symbol",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Password and Confirm Password didn't match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    public static boolean isValid(String passwordhere){

        int f1=0 ,f2=0, f3=0;
        if (passwordhere.length() <8){
            return false;
        }else {
            for (int p = 0; p < passwordhere.length(); p++){
                if (Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++){
                if (Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for (int s = 0; s < passwordhere.length(); s++){
                char c = passwordhere.charAt(s);
                if (c>=33&&c<=46||c==64){
                    f3=1;
                }
            }

            if (f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }
}