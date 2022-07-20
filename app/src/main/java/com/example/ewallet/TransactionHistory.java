package com.example.ewallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TransactionHistory extends AppCompatActivity {
    Database obj=new Database(this);
    Database1 obj1=new Database1(this);
    ImageButton ib1;
    ListView lv;
    String ph="";
    ArrayList<String> Arr = new ArrayList<String>();
    ArrayAdapter<String> ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        ib1=findViewById(R.id.imageButton28);
        lv=findViewById(R.id.lv);
        ph=getIntent().getStringExtra("ph");
        Cursor r1=obj1.DisplayByPhone(ph);
        if(r1==null)
        {
            Toast.makeText(this,"No Record Found",Toast.LENGTH_SHORT).show();
        }
        else
        {
            String s2="";
            Arr.add("id    Sender  Receiver  Amount  Msg  Date");
            while(r1.moveToNext())
            {
                s2="";
                if(r1.getString(1).equals(ph))
                {
                    s2=s2+"-"+r1.getString(4);
                }
                else
                {
                    s2=s2+"+"+r1.getString(4);
                }
                Arr.add(r1.getString(0)+"   "+r1.getString(1)+"   "+r1.getString(2)+"   "+s2+"   "+r1.getString(5)+"   "+r1.getString(6));
            }
            ad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Arr);
            lv.setAdapter(ad);
        }


        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(TransactionHistory.this,Home.class);
                startActivity(i1);
            }
        });
    }
}