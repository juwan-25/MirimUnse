package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LuckRecordActivity extends AppCompatActivity {
    TextView toolMain, unseRecord;
    ImageView checkMain;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_record);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor cTitle = db.rawQuery("SELECT * FROM ContentsFortunes;", null);

        String unseTitle = cTitle.getString(1); //db에서 운세 title 받아올 용도
        unseRecord = findViewById(R.id.textv);
        unseRecord.setText(unseTitle); //제일 처음만 xml에서 만든 textView 사용

        Cursor cCnt = db.rawQuery("SELECT count(*) FROM ContentsFortunes;", null);
        int recodeCount = cCnt.getInt(0);
        for(int i = 0; i<recodeCount; i++){
            //TextView 새롭게 만드는 코드
            unseTitle = cTitle.getString(1);
            //새롭게 만든 TextView.setText(unseTitle);
        }

        unseRecord.setOnClickListener(new View.OnClickListener() {    // luck_record_detail 연결
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LuckCheckDetailActivity.class);
                startActivity(intent);
            }
        });

        // 하단 바
        toolMain = findViewById(R.id.text_tool);
        toolMain.setOnClickListener(new View.OnClickListener() {    // 설정 버튼 연결
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
        checkMain = findViewById(R.id.btn_check);   // 홈 버튼 연결
        checkMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LuckCheckActivity.class);
                startActivity(intent);
            }
        });

    }
}