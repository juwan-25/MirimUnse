package kr.hs.s2104.mirimunse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //DB 생성
    public DatabaseHelper(Context context) {
        super(context, "fortuneDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE recordTBL(" +
                "R_DATE date," +
                "R_TITLE text," +
                "R_CONTEXT text"+
                ");");

        db.execSQL("CREATE TABLE contentsTBL(" +
                "F_TITLE text," +
                "F_CONTEXT text"+
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS recordTBL");
        onCreate(db);
    }

}
