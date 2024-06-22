package edu.birzeit.proj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseHelper_offer extends android.database.sqlite.SQLiteOpenHelper {

    public DataBaseHelper_offer(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create the Offer table
        sqLiteDatabase.execSQL("CREATE TABLE Offer (" +
                "type TEXT PRIMARY KEY," +
                "size TEXT," +
                "prize REAL," +
                "date TEXT" +
                ")");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertOffer(Offer offer) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", offer.getType());
        contentValues.put("size", offer.getSize());
        contentValues.put("prize", offer.getPrize());
        contentValues.put("date", offer.getDate());

        sqLiteDatabase.insert("Offer", null, contentValues);
    }

    public Cursor getAllOffer() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Offer", null);
    }
    public void deleteAllOffers() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("Offer", null, null);
        sqLiteDatabase.close(); // Close the database connection
    }



}