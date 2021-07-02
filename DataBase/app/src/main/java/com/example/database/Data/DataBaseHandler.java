package com.example.database.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.database.Model.Contact;
import com.example.database.R;
import com.example.database.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE "+Util.TABLE_NAME+" ( "+Util.ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+Util.NAME+" TEXT, "+
                Util.PHONE_NUMBER+" TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = String.valueOf(R.string.DROP_TABLE);
        db.execSQL(DROP_TABLE);

        onCreate(db);
    }

    public int updateContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv =new ContentValues();
        cv.put(Util.NAME,contact.getName());
        cv.put(Util.PHONE_NUMBER,contact.getPhoneNumber());

        return db.update(Util.TABLE_NAME,cv,Util.ID+" =? ",
                new String[]{String.valueOf(contact.getId())});
    }
//    public boolean deleteConta

    public boolean addContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Util.NAME,contact.getName());
        cv.put(Util.PHONE_NUMBER,contact.getPhoneNumber());

        long insert = db.insert(Util.TABLE_NAME, null, cv);
        db.close();

        if(insert==1) return true;
        else return false;
    }

    public int deleteContact(Contact c){
        SQLiteDatabase db=this.getWritableDatabase();

        int retVal = db.delete(Util.TABLE_NAME,Util.ID+" =? ",
                new String[]{String.valueOf(c.getId())});
        db.close();
        return retVal;
    }

    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME,new String[]{Util.ID,Util.NAME,Util.PHONE_NUMBER},
                Util.ID+" =? ",new String[]{String.valueOf(id)}
                ,null,null,null);
        if(cursor.moveToFirst()){
            int contactId= Integer.parseInt(cursor.getString(0));
            String name = cursor.getString(1);
            String phoneNumber = cursor.getString(2);
            Contact c = new Contact(contactId,name,phoneNumber);
            return c;
        }
        return null;
    }

    public int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String count = "SELECT * FROM "+Util.TABLE_NAME;

        Cursor cursor = db.rawQuery(count,null);
        return cursor.getCount();
    }

    public List<Contact> getAllContact(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Contact> allContacts=new ArrayList<>();

        String queryAll = "SELECT * FROM "+Util.TABLE_NAME;

        Cursor cursor = db.rawQuery(queryAll,null);

        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(1);
                String phoneNumber = cursor.getString(2);
                allContacts.add(new Contact(name,phoneNumber));
            }while (cursor.moveToNext());
            return allContacts;
        }
        return null;
    }
}
