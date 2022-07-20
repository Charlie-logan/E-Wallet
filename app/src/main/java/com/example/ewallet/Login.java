package com.example.ewallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Database obj=new Database(this);
    EditText e1,e2;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=findViewById(R.id.editTextPhone);
        e2=findViewById(R.id.editTextTextPassword);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a1=e1.getText().toString();
                String a2=e2.getText().toString();
                Cursor r1=obj.DisplayByPhonePassword(a1,a2);
                if(r1.moveToNext())
                {
                    Intent i1=new Intent(Login.this,Home.class);
                    i1.putExtra("ph",a1);
                    startActivity(i1);
                }
                else
                {
                    Toast.makeText(Login.this, "Invalid Login Details!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Login.this,Register.class);
                startActivity(i1);
            }
        });
    }
}