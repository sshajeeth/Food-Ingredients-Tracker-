package com.example.trackfoodincredients;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class DatabaseConnection extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ingredients.db";
    private static final String TABLE_NAME = "ingredients";
    private static final String COL_1 = "id";
    private static final String COL_2 = "name";
    private static final String COL_3 = "weight";
    private static final String COL_4 = "price";
    private static final String COL_5 = "description";
    private static final String COL_6 = "availability";
    private static final int DATABASE_VERSION = 1;


    public DatabaseConnection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, WEIGHT NUMERIC, " +
                "PRICE NUMERIC, DESCRIPTION TEXT, AVAILABILITY TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addToDB(String name, double weight, double price, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, name);
        values.put(COL_3, weight);
        values.put(COL_4, price);
        values.put(COL_5, description);
        db.insertOrThrow(TABLE_NAME, null, values);

    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cs = db.rawQuery("select * from " + TABLE_NAME, null);
        return cs;
    }

    public void addToAvailability(ArrayList<CheckBox> arrayList){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        for (int i=0;i<arrayList.size();i++){
                if (arrayList.get(i).isChecked()){
                    values.put(COL_6,"available");
                    db.update(TABLE_NAME, values, "name = ?",new String[]{arrayList.get(i).getText().toString()});
                }
        }
    }

    public Cursor getAvailability(){
        SQLiteDatabase db = this.getWritableDatabase();
        String uname="available";
        String query = "select * from " + TABLE_NAME + " where "+ COL_6 + " = '" + uname + "'";
        Cursor cur = db.rawQuery(query, null);
        return cur;
    }

    public void updateAvailability(ArrayList<CheckBox>arrayList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        for (int i = 0; i < arrayList.size(); i++) {
            if (!arrayList.get(i).isChecked()) {
                values.put(COL_6, (String) null);
                db.update(TABLE_NAME, values, "name = ?", new String[]{arrayList.get(i).getText().toString()});
            }
        }
    }

        public Cursor getSpecifiedName(String name){
            SQLiteDatabase db = this.getWritableDatabase();

            String query = "select * from " + TABLE_NAME + " where "+ COL_2 + " = '" + name + "'";
            Cursor cur = db.rawQuery(query, null);
            return cur;
        }

    public void updateAll(String updateName,EditText name,EditText weight, EditText price, EditText description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

                values.put(COL_2,name.getText().toString());
                values.put(COL_3,weight.getText().toString());
                values.put(COL_4,price.getText().toString());
                values.put(COL_5,description.getText().toString());

                db.update(TABLE_NAME, values, "name = ?", new String[]{updateName});


    }


    }
