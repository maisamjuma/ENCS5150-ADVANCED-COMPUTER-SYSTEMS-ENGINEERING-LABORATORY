package edu.birzeit.proj;


import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    public int updateUser(String email, String newFirstName, String newLastName, String newPassword, String newPhoneNumber) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Fname", newFirstName);
        values.put("Lname", newLastName);
        values.put("Password", newPassword);
        values.put("PHONE", newPhoneNumber);

        String selection = "Email = ?";
        String[] selectionArgs = {email};
        try {
            return db.update("User", values, selection, selectionArgs);
        } catch (Exception e) {
            Log.e(TAG, "Error updating User", e);
            return 0;
        }
    }
//
//    public String getCurrentUserEmail() {
//        SQLiteDatabase sqLiteDatabase = null;
//        String email = null;
//        try {
//            sqLiteDatabase = getReadableDatabase();
//            Cursor cursor = sqLiteDatabase.rawQuery("SELECT Email FROM User LIMIT 1", null);
//            if (cursor.moveToFirst()) {
//                email = cursor.getString(0);
//            }
//            cursor.close();
//        } catch (Exception e) {
//            Log.e("DataBaseHelper", "Error retrieving current user email", e);
//        } finally {
//            if (sqLiteDatabase != null) {
//                sqLiteDatabase.close();
//            }
//        }
//        return email;
//    }


}