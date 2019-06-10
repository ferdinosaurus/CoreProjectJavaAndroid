package com.example.coreproject.sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coreproject.sqlite.DatabaseHelper;
import com.example.coreproject.sqlite.parcelable.ExampleParcelable;
import com.example.coreproject.sqlite.table.ExampleTable;

import java.util.ArrayList;
import java.util.List;

public class ExampleDao {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public ExampleDao(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public long insert(ExampleParcelable exampleParcelable){
        open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ExampleTable.EXAMPLE_COLUMN_NAMA,exampleParcelable.getName());
        contentValues.put(ExampleTable.EXAMPLE_COLUMN_EMAIL,exampleParcelable.getEmail());
        contentValues.put(ExampleTable.EXAMPLE_COLUMN_PHONE,exampleParcelable.getPhone());
        contentValues.put(ExampleTable.EXAMPLE_COLUMN_ALAMAT,exampleParcelable.getAlamat());
        long returnValue = database.insert(ExampleTable.TABLE_NAME_EXAMPLE, null, contentValues);

        close();

        return returnValue;
    }

    public ExampleParcelable fetchRow(Cursor cursor){
        return new ExampleParcelable(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
    }

    public List<ExampleParcelable> getAll(){
        List<ExampleParcelable> exampleParcelableList = new ArrayList<>();
        open();
        String query = "select * from " + ExampleTable.TABLE_NAME_EXAMPLE;
        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            ExampleParcelable exampleParcelable = fetchRow(cursor);
            exampleParcelableList.add(exampleParcelable);
            cursor.moveToNext();
        }
        close();

        return exampleParcelableList;
    }

    public void delete(long id){
        open();
        database.delete(ExampleTable.TABLE_NAME_EXAMPLE,ExampleTable.EXAMPLE_COLUMN_ID+" = "+id,null);
        close();
    }

    public ExampleParcelable getByID(long id){
        ExampleParcelable exampleParcelable = new ExampleParcelable();

        open();

        String query = "SELECT * FROM "+ExampleTable.TABLE_NAME_EXAMPLE+" WHERE "+ExampleTable.EXAMPLE_COLUMN_ID+" = "+id;
        Cursor cursor = database.rawQuery(query,null);

        cursor.moveToFirst();
        exampleParcelable = fetchRow(cursor);

        close();

        return exampleParcelable;
    }

    public long update(ExampleParcelable exampleParcelable){
        open();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ExampleTable.EXAMPLE_COLUMN_NAMA,exampleParcelable.getName());
        contentValues.put(ExampleTable.EXAMPLE_COLUMN_EMAIL,exampleParcelable.getEmail());
        contentValues.put(ExampleTable.EXAMPLE_COLUMN_PHONE,exampleParcelable.getPhone());
        contentValues.put(ExampleTable.EXAMPLE_COLUMN_ALAMAT,exampleParcelable.getAlamat());
        long returnValue = database.update(ExampleTable.TABLE_NAME_EXAMPLE,contentValues,ExampleTable.EXAMPLE_COLUMN_ID+" = "+exampleParcelable.getId(),null);

        close();

        return returnValue;
    }
}
