package com.example.ewallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    Database obj=new Database(this);
    TextView t1;
    Button b1;
    ImageButton ib1,ib2,ib3,ib4,ib5,ib6;
    String ph="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        t1=findViewById(R.id.textView8);
        b1=findViewById(R.id.button4);
        ib1=findViewById(R.id.imageButton);
        ib2=findViewById(R.id.imageButton2);
        ib3=findViewById(R.id.imageButton3);
        ib4=findViewById(R.id.imageButton4);
        ib5=findViewById(R.id.imageButton5);
        ib6=findViewById(R.id.imageButton6);

        ph=getIntent().getStringExtra("ph");
        Cursor r1=obj.DisplayByPhone(ph);
        if(r1.moveToNext())
        {
            t1.setText(r1.getString(1));
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this, "Successfully Logout!", Toast.LENGTH_SHORT).show();
                Intent i1= new Intent(Home.this,Login.class);
                i1.putExtra("ph",ph);
                startActivity(i1);
            }
        });

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Home.this,SendMoney.class);
                i1.putExtra("ph",ph);
                startActivity(i1);
            }
        });

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Home.this,RechargeAndBillPayments.class);
                i1.putExtra("ph",ph);
                startActivity(i1);
            }
        });

        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Home.this,TicketBooking.class);
                i1.putExtra("ph",ph);
                startActivity(i1);
            }
        });

        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Home.this,RentPayment.class);
                i1.putExtra("ph",ph);
                startActivity(i1);
            }
        });

        ib5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Home.this,AccountBalance.class);
                i1.putExtra("ph",ph);
                startActivity(i1);
            }
        });

        ib6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Home.this,TransactionHistory.class);
                i1.putExtra("ph",ph);
                startActivity(i1);
            }
        });

    }
}