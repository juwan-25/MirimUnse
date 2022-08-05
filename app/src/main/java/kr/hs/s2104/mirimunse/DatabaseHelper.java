package kr.hs.s2104.mirimunse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //DB 생성
    public DatabaseHelper(Context context) {
        super(context, "MirimUnsae.db", null, 1);
        Log.d(this.getClass().getName() ,"***DatabaseHelper 생성자 실행");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

}
