package com.example.coreproject.sqlite.table;

public class ExampleTable {

    public static final String TABLE_NAME_EXAMPLE ="example";
    public static final String EXAMPLE_COLUMN_ID = "id";
    public static final String EXAMPLE_COLUMN_NAMA = "nama";
    public static final String EXAMPLE_COLUMN_EMAIL = "email";
    public static final String EXAMPLE_COLUMN_PHONE = "phone";
    public static final String EXAMPLE_COLUMN_ALAMAT = "alamat";

    public static String createTable(){
        return "CREATE TABLE IF NOT EXISTS " + ExampleTable.TABLE_NAME_EXAMPLE + " ( "
                +ExampleTable.EXAMPLE_COLUMN_ID + " INTEGER PRIMARY KEY,"
                +ExampleTable.EXAMPLE_COLUMN_NAMA + " TEXT ,"
                +ExampleTable.EXAMPLE_COLUMN_EMAIL + " TEXT ,"
                +ExampleTable.EXAMPLE_COLUMN_PHONE + " TEXT ,"
                +ExampleTable.EXAMPLE_COLUMN_ALAMAT + " TEXT )";
    }

}
