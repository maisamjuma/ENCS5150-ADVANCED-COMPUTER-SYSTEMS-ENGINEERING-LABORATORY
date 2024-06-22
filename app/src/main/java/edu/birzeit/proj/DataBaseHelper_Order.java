package edu.birzeit.proj;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
public class DataBaseHelper_Order extends android.database.sqlite.SQLiteOpenHelper {

    public DataBaseHelper_Order(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Order_pizza (" +
                "Email TEXT PRIMARY KEY," +
                "Ord TEXT" +
                ")");
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertOrder(Order_pizza order) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", order.getEmail());
        contentValues.put("Ord", order.getOrderr());
//        contentValues.put("Extra", order.getExtra());

        sqLiteDatabase.insert("order_pizza", null, contentValues);
    }

    public void deleteAllOrders() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("order_pizza", null, null);
        sqLiteDatabase.close(); // Close the database connection
    }
    public Cursor getAllOrders() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Order_pizza", null);
    }
    public Cursor getOrders() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT Ord FROM Order_pizza", null);
    }
    public Cursor SearchforOrder(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] selectionArgs = {email};
        return sqLiteDatabase.rawQuery("SELECT * FROM Order_pizza WHERE Email=?", selectionArgs);
    }
    public int updateOrders(String email, String Orders_pizza,String extra) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Ord", Orders_pizza);
//        values.put("Extra", extra);

        String selection = "Email = ?";
        String[] selectionArgs = {email};
        try {
            return db.update("order_pizza", values, selection, selectionArgs);
        } catch (Exception e) {
            Log.e(TAG, "Error updating Order", e);
            return 0;
        }
    }

    public int settingOrder(String email,String orders) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Ord", orders);

        String selection = "Email = ?";
        String[] selectionArgs = {email};
        try {
            return db.update("order_pizza", values, selection, selectionArgs);
        } catch (Exception e) {
            Log.e(TAG, "Error updating User orders", e);
            return 0;
        }
    }
}