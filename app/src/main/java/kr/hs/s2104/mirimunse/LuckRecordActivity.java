package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LuckRecordActivity extends AppCompatActivity {

    private static final float FONT_SIZE = 10;
    private LinearLayout container;

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
        unseRecord = findViewById(R.id.textv);

        Cursor cCnt = db.rawQuery("SELECT count(*) FROM RecordFotunes;", null);
        cCnt.moveToNext();
        Cursor cTitle = db.rawQuery("SELECT * FROM RecordFotunes;", null);
        String unseTitle; //db에서 운세 title 받아올 용도
        int recodeCount = cCnt.getInt(0);

        //부모 뷰
        container = (LinearLayout) findViewById(R.id.parent);

        for(int i = 0; i<recodeCount; i++){
            cTitle.moveToNext();
            unseTitle = cTitle.getString(1);
            if(i==0) unseRecord.setText(unseTitle);
            //TextView 새롭게 만드는 코드
            //새롭게 만든 TextView.setText(unseTitle);

            // 동그란 이미지 생성
            ImageView img_dot = new ImageView(this);
            img_dot.setImageResource(R.drawable.dot);

            //TextView 생성
            TextView view1 = new TextView(this);
            view1.setText(unseTitle);
            view1.setTextSize(FONT_SIZE);
            view1.setTextColor(Color.rgb(251,218,218)); //글자색상 rgb로 코드 변환
            //layout_width, layout_height, gravity 설정
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER;
            view1.setLayoutParams(lp);
            //부모 뷰에 추가
            container.addView(view1);

            // 우측 버튼 이미지 생성
            ImageView img_threedot = new ImageView(this);
            img_threedot.setImageResource(R.drawable.threedot);

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