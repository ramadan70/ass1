package com.example.ass1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    EditText user;
    EditText pass;
    ImageButton logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        logIn = findViewById(R.id.login);
        user = findViewById(R.id.userName);
        pass = findViewById(R.id.passWord);


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.getText() != null && pass.getText() != null) {
                    if (user.getText().toString().equals("admin")  && pass.getText().toString().equals("admin") ) {
                        Intent in = new Intent(Login.this,Activity2.class);
                        startActivity(in);
                    } else {
                        Toast.makeText(Login.this, "Enter user and Password (admin)", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Please Enter User Name and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}