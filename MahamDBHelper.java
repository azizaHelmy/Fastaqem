package com.example.doaa.fastaqem;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by doaa on 26-Mar-18.
 */

public class MahamDBHelper extends SQLiteOpenHelper {
    SQLiteDatabase db ;

    public static final String DATABASE_NAME = "maham.db" ;

    public static final int DATABASE_VERSION = 1 ;
    public static final int DATABASE_NEWVERSION = 2 ;
    public MahamDBHelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_NEWVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final  String SQL_CREATE_TABLE = "CREATE TABLE " + MahamContract.MahamEntry.TABLE_NAME +
                "(" + MahamContract.MahamEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MahamContract.MahamEntry.COLUMNS_Text_NAME + " TEXT NOT NULL, " +
               MahamContract.MahamEntry.COLUMNS_Topic_NAME + " TEXT NOT NULL " +//i add it
               ");" ;
        db.execSQL(SQL_CREATE_TABLE);

    }
   @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE " + MahamContract.MahamEntry.TABLE_NAME +
                    " ADD COLUMN "+ MahamContract.MahamEntry.COLUMNS_Topic_NAME+
                    " INTEGER DEFAULT 0" );
            onCreate(db);
        }
    }

        public long addNote(String title){
            ContentValues cv = new ContentValues();
            cv.put(MahamContract.MahamEntry.COLUMNS_Text_NAME,title );
            cv.put(MahamContract.MahamEntry.COLUMNS_Topic_NAME,title );// i add it
            return db.insert(MahamContract.MahamEntry.TABLE_NAME, null, cv);



        }
}
