package com.example.abdi.crossword;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues x1 = new ContentValues();
        ContentValues x2 = new ContentValues();
        ContentValues x3 = new ContentValues();
        ContentValues x4 = new ContentValues();
        ContentValues x5 = new ContentValues();
        ContentValues x6 = new ContentValues();
        ContentValues x7 = new ContentValues();
        ContentValues x8 = new ContentValues();
        ContentValues x9 = new ContentValues();
        ContentValues x10 = new ContentValues();

        x1.put(COL_2,"ALE");
        x1.put(COL_3,"Relative of beer");
        x2.put(COL_2,"ELSE");
        x2.put(COL_3,"It may follow something");
        x3.put(COL_2,"IDEA");
        x3.put(COL_3,"Something to think about");
        x4.put(COL_2,"ARENA");
        x4.put(COL_3,"It often has a ring in the middle");
        x5.put(COL_2,"ONE");
        x5.put(COL_3,"Last number in countdown");
        x6.put(COL_2,"ERA");
        x6.put(COL_3,"Time worth remembering");
        x7.put(COL_2,"ATE");
        x7.put(COL_3,"Had a meal");
        x8.put(COL_2,"ALOE");
        x8.put(COL_3,"Healing balm");
        x9.put(COL_2,"ANTE");
        x9.put(COL_3,"Card player's stake");
        x10.put(COL_2,"EDEN");
        x10.put(COL_3,"Adam and Eve locale");

        long result = db.insert(TABLE_NAME,null ,x1);
        db.insert(TABLE_NAME,null,x2);
        db.insert(TABLE_NAME,null,x3);
        db.insert(TABLE_NAME,null,x4);
        db.insert(TABLE_NAME,null,x5);
        db.insert(TABLE_NAME,null,x6);
        db.insert(TABLE_NAME,null,x7);
        db.insert(TABLE_NAME,null,x8);
        db.insert(TABLE_NAME,null,x9);
        db.insert(TABLE_NAME,null,x10);

        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public void deleteAllData()
    {
        SQLiteDatabase sdb= this.getWritableDatabase();
        sdb.delete(TABLE_NAME, null, null);

    }
}