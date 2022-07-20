package com.example.ewallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String TableName="User";
    public static final String DatabaseName="EWallet.db";
    public static final String col1="Id";
    public static final String col2="Name";
    public static final String col3="Email";
    public static final String col4="Phone";
    public static final String col5="Password";
    public static final String col6="Wallet";


    public Database(Context c1)
    {
        super(c1,DatabaseName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String a1="create table "+TableName+"("+col1+" integer primary key autoincrement,"+col2+" Text,"+col3+" Text,"+col4+" Text,"+col5+" Text,"+col6+" Text)";
        sqLiteDatabase.execSQL(a1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String a1="drop table if exists "+TableName;
        sqLiteDatabase.execSQL(a1);
        onCreate(sqLiteDatabase);
    }

    public boolean insert(String nm,String em, String ph, String pwd, String wal)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col2,nm);
        cv.put(col3,em);
        cv.put(col4,ph);
        cv.put(col5,pwd);
        cv.put(col6,wal);
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
    public boolean update(String id,String nm,String em,String ph,String pwd, String wal)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col2,nm);
        cv.put(col3,em);
        cv.put(col4,ph);
        cv.put(col5,pwd);
        cv.put(col6,wal);
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
    public boolean updatewallet(String id,String wal)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col6,wal);
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
    public Cursor DisplayById(String id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+TableName+" where "+col1+"='"+id+"'",null);
        return res;
    }
    public Cursor DisplayByEmail(String em)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+TableName+" where "+col3+"='"+em.trim()+"'",null);
        return res;
    }
    public Cursor DisplayByPhone(String ph)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+TableName+" where "+col4+"='"+ph.trim()+"'",null);
        return res;
    }
    public Cursor DisplayByPhonePassword(String ph,String pwd)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TableName+" where "+col4+"='"+ph.trim()+"'"+" and "+col5+"='"+pwd.trim()+"'",null);
        return res;
    }

}
