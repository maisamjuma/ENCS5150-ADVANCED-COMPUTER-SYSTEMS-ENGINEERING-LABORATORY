package edu.birzeit.proj;


import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseHelperAdmin extends android.database.sqlite.SQLiteOpenHelper {

    public DataBaseHelperAdmin(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Admin (" +
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

    public void insertAdmin(Admin admin) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", admin.getEmail());
        contentValues.put("Password", admin.getPassword());
        contentValues.put("PHONE", admin.getmPhone());
        contentValues.put("GENDER", admin.getGender());
        contentValues.put("Fname", admin.getFname());
        contentValues.put("Lname", admin.getLname());

        sqLiteDatabase.insert("Admin", null, contentValues);
    }

    public void deleteAllAdmin() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("Admin", null, null);
        sqLiteDatabase.close(); // Close the database connection
    }
    public Cursor getAllAdmin() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Admin", null);
    }
    public Cursor SearchforAdmin(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] selectionArgs = {email};
        return sqLiteDatabase.rawQuery("SELECT * FROM Admin WHERE Email=?", selectionArgs);
    }
    public int updateAdmin(String email, String newFirstName, String newLastName, String newPassword, String newPhoneNumber) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Fname", newFirstName);
        values.put("Lname", newLastName);
        values.put("Password", newPassword);
        values.put("PHONE", newPhoneNumber);

        String selection = "Email = ?";
        String[] selectionArgs = {email};
        try {
            return db.update("Admin", values, selection, selectionArgs);
        } catch (Exception e) {
            Log.e(TAG, "Error updating Admin", e);
            return 0;
        }
    }


}