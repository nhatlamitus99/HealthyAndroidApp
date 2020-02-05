package com.example.healthy;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.StrictMath.abs;

public class MyDataBase extends SQLiteOpenHelper {

    public MyDataBase(@Nullable Context context) {
        super(context, "food_db5", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table food (time text, typ int, total int,primary key (time,typ))");
        db.execSQL("create table sleep (start text primary key, en text)");
        db.execSQL("create table exercise (time text primary key, type int, total int)");
        db.execSQL("create table state (time text primary key, state int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertFood(String time, int type, int q) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("time", time);
        cv.put("typ", type);
        cv.put("total", q);
        if (db.insert("food", null, cv) == 1) {
            Log.d("**************", "thêm thành công");
        }
    }

    public void insertExercise(String time, int type, int q) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("start", time);
        cv.put("type", type);
        cv.put("total", q);
        if (db.insert("exercise", null, cv) == 1) {
            Log.d("**************", "thêm thành công");
        }
    }

    public void insertSleep(String times, String timee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("start", times);
        cv.put("en", timee);
        if (db.insert("sleep", null, cv) == 1) {
            Log.d("**************", "thêm thành công");
        }
    }

    public void insertState(String time, int state) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("time", time);
        cv.put("state", state);
        if (db.insert("state", null, cv) == 1) {
            Log.d("**************", "thêm thành công");
        }
    }

    int datediff1(Date d1, Date d2) {
        long diffInMillies = abs(d2.getTime() - d2.getTime());
        if (diffInMillies <= 86400000) {
            return 1;
        }
        return 0;
    }

    public ArrayList<Integer> RecommendFood() throws ParseException {
        ArrayList<Integer> list = new ArrayList<Integer>(Collections.nCopies(30, 0));
        Cursor res = getReadableDatabase().rawQuery("select * from state", null);
        res.moveToFirst();

        //Log.d("**************",Integer.toString(res2.getCount()));

        //while(res2.isAfterLast() == false)
        //{
        //    Log.d("*********",res2.getString(0));
        //    res2.moveToNext();
        // }

        Cursor res2 = getReadableDatabase().rawQuery("select * from food ", null);
        res2.moveToFirst();

        int c=1;
        while (res.isAfterLast() == false) {
            //Date d=new Date("12-04-2019");
            //DateFormat
            //Log.d("*********",Integer.toString(d.getDay())+" "+Integer.toString(d.getMonth())+" "+Integer.toString(d.getYear()));
            //Log.d("*********",res.getString(0));

            if (Integer.parseInt(res.getString(1))==1 || Integer.parseInt(res.getString(1))==2){//res.getString(1) == "1" && res.getString(1) == "2") {


                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date d = sdf.parse(res.getString(0));


                res2.moveToFirst();
                c++;
                while (res2.isAfterLast() == false) {
                    Date d2=sdf.parse(res2.getString(0));
                    if (datediff1(d,d2)==1 && d.compareTo(d2)>0)
                    {
                        int index=Integer.parseInt(res2.getString(1));
                        int inc=Integer.parseInt(res2.getString(2));
                        list.set(index,list.get(index)+inc);

                    }
                    res2.moveToNext();
                }

            }
            res.moveToNext();
        }

        for (int i=0;i<list.size();i++)
        {
            list.set(i,list.get(i)/c);
        }
        Log.d("*******",list.toString());
        Log.d("*******",Integer.toString(c));
        return list;
    }

    void datatest()
    {
//        insertState("10/01/2020",3);
//        insertState("11/01/2020",1);
//        insertState("12/01/2020",2);
//        insertState("13/01/2020",4);
//        insertState("14/01/2020",2);
//
//        insertFood("10/01/2020",2,2);
//        insertFood("10/01/2020",3,1);
//        insertFood("11/01/2020",1,3);
//        insertFood("12/01/2020",4,3);
    }

}
