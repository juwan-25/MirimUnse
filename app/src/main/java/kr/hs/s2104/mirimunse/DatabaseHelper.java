package kr.hs.s2104.mirimunse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Fortunes.db";
    private static final String DB_TABLE = "Forturnes_Table";

    //columns
    private static final String TITLE = "TITLE";
    private static final String DATE = "DATE";
    private static final String CONTEXTS = "CONTEXTS";

    private static final String CREATE_TABLE = "CREATE TABLE "+DB_TABLE+" ("+
            TITLE+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            DATE+" DATE,"+
            CONTEXTS+" TEXT "+")";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    //데이터 삽입 메서드
    public boolean insertData(String title, String date, String contents){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, title);
        long result = db.insert(DB_TABLE, null, contentValues);

        contentValues.put(DATE, date);
        result = db.insert(DB_TABLE, null, contentValues);

        contentValues.put(CONTEXTS, contents);
        result = db.insert(DB_TABLE, null, contentValues);

        return result != -1; //if result = -1 data dodsent insert
    }

    //데이터 SELECT
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

}
