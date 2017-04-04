package com.kvbashkirov.app1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kvbashkirov on 03.04.2017.
 */

public class App1DbHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ", ";

    private static final String SQL_CREATE_STUDENTS_ENTRIES =
            "CREATE TABLE " + App1Contract.StudentEntry.TABLE_NAME + " (" +
                    App1Contract.StudentEntry._ID + " INTEGER PRIMARY KEY," +
                    App1Contract.StudentEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    App1Contract.StudentEntry.COLUMN_NAME_SNAME + TEXT_TYPE + COMMA_SEP +
                    App1Contract.StudentEntry.COLUMN_NAME_PHONE + TEXT_TYPE +
                    " )";

    private static final String SQL_CREATE_FEE_ENTRIES =
            "CREATE TABLE " + App1Contract.FeeEntry.TABLE_NAME + " (" +
                    App1Contract.FeeEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    App1Contract.FeeEntry.COLUMN_NAME_STUDENT + INT_TYPE + COMMA_SEP +
                    App1Contract.FeeEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                    App1Contract.FeeEntry.COLUMN_NAME_AMOUNT + INT_TYPE + COMMA_SEP +
                    " FOREIGN KEY (" + App1Contract.FeeEntry.COLUMN_NAME_STUDENT + ") REFERENCES " +
                        App1Contract.StudentEntry.TABLE_NAME + " (" + App1Contract.StudentEntry._ID + ")" +
                     " )";

    private static final String SQL_CREATE_PRESENCES_ENTRIES =
            "CREATE TABLE " + App1Contract.PresenceEntry.TABLE_NAME + " (" +
                    App1Contract.PresenceEntry._ID + " INTEGER PRIMARY KEY," +
                    App1Contract.PresenceEntry.COLUMN_NAME_STUDENT + INT_TYPE + COMMA_SEP +
                    App1Contract.PresenceEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                    App1Contract.PresenceEntry.COLUMN_NAME_COST + INT_TYPE + COMMA_SEP +
                    " FOREIGN KEY (" + App1Contract.PresenceEntry.COLUMN_NAME_STUDENT +
                        ") REFERENCES " + App1Contract.StudentEntry.TABLE_NAME +
                         " (" + App1Contract.StudentEntry._ID + ")" +
                    " )";

    private static final String SQL_DELETE_STUDENTS_ENTRIES =
            "DROP TABLE IF EXISTS " + App1Contract.StudentEntry.TABLE_NAME;

    private static final String SQL_DELETE_FEE_ENTRIES =
            "DROP TABLE IF EXISTS " + App1Contract.FeeEntry.TABLE_NAME;

    private static final String SQL_DELETE_PRESENCES_ENTRIES =
            "DROP TABLE IF EXISTS " + App1Contract.PresenceEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "app1_db";

    public App1DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STUDENTS_ENTRIES);
        db.execSQL(SQL_CREATE_FEE_ENTRIES);
        db.execSQL(SQL_CREATE_PRESENCES_ENTRIES);
        initData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PRESENCES_ENTRIES);
        db.execSQL(SQL_DELETE_FEE_ENTRIES);
        db.execSQL(SQL_DELETE_STUDENTS_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private long insertStudent(SQLiteDatabase db, String name, String sname, String phone) {
        ContentValues values = new ContentValues();
        values.put(App1Contract.StudentEntry.COLUMN_NAME_NAME, name);
        values.put(App1Contract.StudentEntry.COLUMN_NAME_SNAME, sname);
        values.put(App1Contract.StudentEntry.COLUMN_NAME_PHONE, phone);

        return db.insert(
                App1Contract.StudentEntry.TABLE_NAME,
                null,
                values);
    }

    private void initData(SQLiteDatabase db) {
        insertStudent(db, "Вася", "Петров", "+79031234567");
        insertStudent(db, "Ваня", "Смирнов", "+79031111111");
        insertStudent(db, "Настя", "Сидорова", "+79032222222");
    }

}
