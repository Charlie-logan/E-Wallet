package com.example.ewallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RentPayment extends AppCompatActivity {
    Database obj=new Database(this);
    Database1 obj1=new Database1(this);
    EditText e1,e2;
    Spinner s1;
    Button b1;
    ImageButton ib1;
    String ph="";
    int wallet=0;
    int id1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_payment);
        e1=findViewById(R.id.editTextNumber9);
        e2=findViewById(R.id.editTextNumber10);
        s1=findViewById(R.id.spinner6);
        b1=findViewById(R.id.button13);
        ib1=findViewById(R.id.imageButton26);
        ph=getIntent().getStringExtra("ph");
        Cursor r1=obj.DisplayByPhone(ph);

        if(r1.moveToNext())
        {
            id1=Integer.parseInt(r1.getString(0));
            wallet=Integer.parseInt(r1.getString(5));
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.rental, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String data=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String a1 = e1.getText().toString();
                String a2 = e2.getText().toString();
                int a3=Integer.parseInt(a2);
                if(a3>wallet)
                {
                    Toast.makeText(getApplicationContext(), "Insufficient balance", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());


                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                    boolean r1 = obj1.insert(ph, a1, "", a2, "rent payment",currentDate+" "+currentTime);
                    if (r1 == true) {
                        Toast.makeText(getApplicationContext(), "Paid Successfully!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Payment Failed!", Toast.LENGTH_SHORT).show();
                    }
                    wallet=wallet-a3;
                    boolean r2 = obj.updatewallet(String.valueOf(id1),String.valueOf(wallet));
                    if (r2 == true) {
                        Toast.makeText(getApplicationContext(), "Wallet Updated!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i1= new Intent(RentPayment.this,Home.class);
                startActivity(i1);
            }
        });
    }
}