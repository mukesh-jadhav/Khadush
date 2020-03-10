package com.mukeshjadhav.khadush;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DBhelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBhelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String TID, String TBEARER, String TDATE, String TTYPE, String TAMOUNT) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbHelper.TID, TID);
        contentValue.put(dbHelper.TBEARER, TBEARER);
        contentValue.put(dbHelper.TDATE, TDATE);
        contentValue.put(dbHelper.TTYPE, TTYPE);
        contentValue.put(dbHelper.TAMOUNT, TAMOUNT);
        database.insert(dbHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { dbHelper._ID, dbHelper.TID, dbHelper.TBEARER, dbHelper.TDATE, dbHelper.TTYPE, dbHelper.TAMOUNT};
        Cursor cursor = database.query(dbHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String TID, String TBEARER, String TDATE, String TTYPE, Integer TAMOUNT) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.TID, TID);
        contentValues.put(dbHelper.TBEARER, TBEARER);
        contentValues.put(dbHelper.TDATE, TDATE);
        contentValues.put(dbHelper.TTYPE, TTYPE);
        contentValues.put(dbHelper.TAMOUNT, TAMOUNT);
        int i = database.update(dbHelper.TABLE_NAME, contentValues, dbHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbHelper.TABLE_NAME, dbHelper._ID + "=" + _id, null);
    }

    public void deleteTable() {
        database.rawQuery("DROP TABLE " + dbHelper.TABLE_NAME, null, null);
    }
}

