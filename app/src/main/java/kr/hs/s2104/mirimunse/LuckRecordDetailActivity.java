package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class LuckRecordDetailActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    ImageView checkMain;
    TextView recordMain, toolMain;

    ImageView imgCard;
    TextView textTitle, textCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_record_detail);

        imgCard = findViewById(R.id.card_detail);
        textTitle = findViewById(R.id.text_record_tit);
        textCont = findViewById(R.id.text_record_cont);

        // 운세 정보 불러오기
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM RecordFortunes;", null);
        //아이디값만큼 for문 돌리기
        c.moveToNext();
        imgCard.setImageResource(0); //DB에서 이미지 불러오는 문제 해결한 뒤 수정
        textTitle.setText(c.getString(1));
        textCont.setText(c.getString(2));

        // 하단 바
        recordMain = findViewById(R.id.text_record);    // 보관함 버튼 연결
        recordMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LuckRecordActivity.class);
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
        toolMain = findViewById(R.id.text_tool);    // 설정 버튼 연결
        toolMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

    }
}