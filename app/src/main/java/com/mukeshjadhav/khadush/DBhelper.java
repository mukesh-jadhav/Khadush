package com.mukeshjadhav.khadush;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "Transactions";

    // Table columns
    public static final String _ID = "_id";
    public static final String TID = "transaction_id";
    public static final String TBEARER = "transaction_bearer";
    public static final String TDATE = "transaction_date";
    public static final String TTYPE = "transaction_type";
    public static final String TAMOUNT = "transaction_amount";


    // Database Information
    static final String DB_NAME = "KHADUSHDBTRANSACTIONS.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TID + " TEXT, " + TBEARER + " TEXT, " + TDATE + " TEXT, " + TTYPE + " TEXT, " + TAMOUNT + " TEXT);";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
