package kr.hs.s2104.mirimunse;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Random;

// 홈 : 카드 선택 결과
public class LuckCheckDetailActivity extends AppCompatActivity {
    ImageView checkMain;
    TextView recordMain, toolMain;
    ImageView btnSave;
    ImageView cardDetail;
    TextView textCheckTit, textCheckCont;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    String fortuneTit, fortuneCont;
    int fortuneImg;

    CustomDialog dialog;

    // 운세별 이미지 배열
    int[] imgId = {
            R.drawable.img_riches_good, R.drawable.img_riches_good, R.drawable.img_friendship_good, R.drawable.img_friendship_bad, R.drawable.img_study_good,
            R.drawable.img_study_bad, R.drawable.img_health_good, R.drawable.img_health_bad, R.drawable.img_employ_good, R.drawable.img_employ_bad
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_check_detail);

        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.dialog01, findViewById(R.id.edit_save));

        cardDetail = findViewById(R.id.card_detail);
        textCheckTit = findViewById(R.id.text_check_tit);
        textCheckCont = findViewById(R.id.text_check_cont);

        btnSave = findViewById(R.id.btn_save);

        // 하단바
        toolMain = findViewById(R.id.text_tool);    // 마이페이지 연결
        checkMain = findViewById(R.id.btn_check);   // 홈 연결
        recordMain = findViewById(R.id.text_record);    // 보관함 연결

        // 랜덤 기능
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM ContentsFortunes;", null);

        Random random = new Random();
        int i = (int)(Math.random()*9)+1;
        for(int j=0; j<i; j++) c.moveToNext();

        fortuneTit = c.getString(1);
        fortuneCont = c.getString(2).replace("\\n", "\n");
        fortuneImg = imgId[i-1];

        // DB에 저장할 수 있도록 값 넘기기
        CustomDialog.title = fortuneTit;
        CustomDialog.cont = fortuneCont;
        CustomDialog.img = Integer.toString(fortuneImg);

        // 텍스트뷰의 텍스트 지정
        textCheckTit.setText(fortuneTit);
        textCheckCont.setText(fortuneCont);
        cardDetail.setImageResource(fortuneImg);

        // 저장을 위한 다이얼로그 불러오기
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new CustomDialog(LuckCheckDetailActivity.this);
                dialog.show();
            }
        });

        //하단 바
        recordMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(1)
                        .playOn(findViewById(R.id.text_record));
                Intent intent = new Intent(getApplicationContext(), LuckRecordActivity.class);
                startActivity(intent);
            }
        });
        checkMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LuckCheckActivity.class);
                startActivity(intent);
            }
        });
        toolMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(1)
                        .playOn(findViewById(R.id.text_tool));
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}