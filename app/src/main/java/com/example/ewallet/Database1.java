package com.example.ewallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database1 extends SQLiteOpenHelper {
    public static final String TableName="Transaction1";
    public static final String DatabaseName="EWallet1.db";
    public static final String col1="Id";
    public static final String col2="SenderId";
    public static final String col3="ReceiverId";
    public static final String col4="Operator";
    public static final String col5="Amount";
    public static final String col6="Message";
    public static final String col7="Date";


    public Database1(Context c1)
    {
        super(c1,DatabaseName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String a1="create table "+TableName+"("+col1+" integer primary key autoincrement,"+col2+" integer,"+col3+" integer,"+col4+" Text,"+col5+" integer,"+col6+" Text,"+col7+" Text)";
        sqLiteDatabase.execSQL(a1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String a1="drop table if exists "+TableName;
        sqLiteDatabase.execSQL(a1);
        onCreate(sqLiteDatabase);
    }

    public boolean insert(String sid, String rid, String op, String amt, String msg, String date)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col2,sid);
        cv.put(col3,rid);
        cv.put(col4,op);
        cv.put(col5,amt);
        cv.put(col6,msg);
        cv.put(col7,date);
        long res= db.insert(TableName,null,cv);
        if(res>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean delete(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        long res= db.delete(TableName,"Id=?",new String[]{id});
        if(res>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean update(String id,String sid, String rid, String op, String amt, String msg, String date)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col2,sid);
        cv.put(col3,rid);
        cv.put(col4,op);
        cv.put(col5,amt);
        cv.put(col6,msg);
        cv.put(col7,date);
        long res= db.update(TableName,cv,"Id=?",new String[]{id});
        if(res>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor DisplayAll()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+TableName,null);
        return res;
    }
    public Cursor DisplayByPhone(String ph)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+TableName+" where "+col2+"='"+ph.trim()+"' or "+col3+"='"+ph.trim()+"'",null);
        return res;
    }
}