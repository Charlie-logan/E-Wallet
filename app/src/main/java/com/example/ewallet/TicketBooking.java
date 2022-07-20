package com.example.ewallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TicketBooking extends AppCompatActivity {
    Database obj=new Database(this);
    ImageButton ib1,ib2,ib3,ib4,ib5;
    String ph="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_booking);
        ib1=findViewById(R.id.imageButton17);
        ib2=findViewById(R.id.imageButton18);
        ib3=findViewById(R.id.imageButton19);
        ib4=findViewById(R.id.imageButton20);
        ib5=findViewById(R.id.imageButton21);

        ph=getIntent().getStringExtra("ph");
        Cursor r1=obj.DisplayByPhone(ph);

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(TicketBooking.this,MovieTicket.class);
                i1.putExtra("ph",ph);
                startActivity(i1);
            }
        });

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(TicketBooking.this,BusTicket.class);
                i1.putExtra("ph",ph);
                startActivity(i1);
            }
        });

        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(TicketBooking.this,TrainTicket.class);
                i1.putExtra("ph",ph);
                startActivity(i1);
            }
        });

        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(TicketBooking.this,FlightTicket.class);
                i1.putExtra("ph",ph);
                startActivity(i1);
            }
        });

        ib5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(TicketBooking.this,Home.class);
                startActivity(i1);
            }
        });
    }
}