package com.example.librarydatabase;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Library.db", null, 1);
    }
 
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table BookDetails(bookid TEXT primary key, bookname TEXT, authorname TEXT, pubname TEXT, publishyear TEXT, price TEXT, stock TEXT)");
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists BookDetails");
    }
 
    public Boolean insertBookData(String bookid, String bookname, String authorname, String pubname, String publishyear, String price, String stock) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookid", bookid);
        contentValues.put("bookname", bookname);
        contentValues.put("authorname", authorname);
        contentValues.put("pubname", pubname);
        contentValues.put("publishyear", publishyear);
        contentValues.put("price", price);
        contentValues.put("stock", stock);
        long result = DB.insert("BookDetails", null, contentValues);
        return result != -1;
    }
 
    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from BookDetails", null);
    }
}