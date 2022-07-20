package com.example.ewallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    Database obj=new Database(this);
    EditText e1,e2,e3,e4;
    Button b1;
    ImageButton ib1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1=findViewById(R.id.editTextTextPersonName);
        e2=findViewById(R.id.editTextTextEmailAddress);
        e3=findViewById(R.id.editTextPhone2);
        e4=findViewById(R.id.editTextTextPassword2);
        b1=findViewById(R.id.button3);
        ib1=findViewById(R.id.imageButton29);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a1 = e1.getText().toString();
                String a2 = e2.getText().toString();
                String a3 = e3.getText().toString();
                String a4 = e4.getText().toString();
                int i=50;
                String a5 = String.valueOf(i);
                if (a1.trim().isEmpty() || a2.trim().isEmpty() || a3.trim().isEmpty() || a4.trim().isEmpty()) {
                    Toast.makeText(Register.this, "Please fill properly!", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor s1 = obj.DisplayByEmail(a2);
                    Cursor s2 = obj.DisplayByPhone(a3);
                    //Toast.makeText(Register.this, s1.getCount() + "--" + s2.getCount(), Toast.LENGTH_SHORT).show();

                    if (s1.getCount() > 0) {
                        Toast.makeText(Register.this, "Email id already exist", Toast.LENGTH_SHORT).show();
                    } else if (s2.getCount() > 0) {
                        Toast.makeText(Register.this, "Phone number already exist", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean r1 = obj.insert(a1, a2, a3, a4, a5);
                        if (r1 == true) {
                            Toast.makeText(Register.this, "Inserted Successfully!", Toast.LENGTH_SHORT).show();

                            Intent i1= new Intent(Register.this,Login.class);
                            i1.putExtra("var1",a1);
                            startActivity(i1);
                        } else {
                            Toast.makeText(Register.this, "Insertion Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Register.this,Login.class);
                startActivity(i1);
            }
        });
    }
}