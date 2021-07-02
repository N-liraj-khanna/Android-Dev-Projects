package com.example.databasefreecodecamp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.databasefreecodecamp.MainActivity;
import com.example.databasefreecodecamp.Model.Person;
import com.example.databasefreecodecamp.R;
import com.example.databasefreecodecamp.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class DataBAseHandler extends SQLiteOpenHelper {
    public DataBAseHandler(@Nullable Context context) {
        super(context, Util.DB_NAME, null, Util.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " ( "
                + Util.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Util.NAME
                + " TEXT, " + Util.AGE + " INT, " + Util.ACTIVE_CHECKED + " BOOL " + " ) ";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String DROP_TABLE = String.valueOf(R.string.dropTable);
//        db.execSQL(DROP_TABLE);
//
//        onCreate(db);
    }

    public boolean addPerson(Person person){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Util.NAME,person.getName());
        cv.put(Util.AGE,person.getAge());
        cv.put(Util.ACTIVE_CHECKED,person.isChecked());

        long insert = db.insert(Util.TABLE_NAME, null, cv);

        if(insert==-1) return false;

        db.close();
        return true;
    }

    public boolean deleteOnePerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteCMD = "DELETE FROM "+Util.TABLE_NAME+" WHERE "+Util.ID+" = "+person.getId();

        Cursor cursor = db.rawQuery(deleteCMD,null);
        if(cursor.moveToFirst()){
            cursor.close();
            db.close();
            return true;
        }
        else{
            cursor.close();
            db.close();
            return false;
        }
    }

    public List<Person> getEveryone(){
        List<Person> allPeople=new ArrayList<>();
        String cmd = "SELECT * FROM "+Util.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(cmd,null);

        if(cursor.moveToFirst()){
            do{
                int id =cursor.getInt(0);
                String name=cursor.getString(1);
                int age = cursor.getInt(2);
                boolean isChecked = cursor.getInt(3)==1?true:false;
                allPeople.add(new Person(id,name,age,isChecked));
            }while(cursor.moveToNext());
        }

        db.close();

        return allPeople;
    }
}
