package edu.birzeit.proj;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseHelper extends android.database.sqlite.SQLiteOpenHelper {

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE User (" +
                "Email TEXT PRIMARY KEY," +
                "Password TEXT," +
                "PHONE TEXT," +
                "GENDER TEXT," +
                "Fname TEXT," +
                "Lname TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertUser(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", user.getEmail());
        contentValues.put("Password", user.getPassword());
        contentValues.put("PHONE", user.getmPhone());
        contentValues.put("GENDER", user.getGender());
        contentValues.put("Fname", user.getFname());
        contentValues.put("Lname", user.getLname());

        sqLiteDatabase.insert("User", null, contentValues);
    }

    public void deleteAllUsers() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("User", null, null);
        sqLiteDatabase.close(); // Close the database connection
    }
    public Cursor getAllUser() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM User", null);
    }
    public Cursor SearchforUser(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] selectionArgs = {email};
        return sqLiteDatabase.rawQuery("SELECT * FROM User WHERE Email=?", selectionArgs);
    }


}

