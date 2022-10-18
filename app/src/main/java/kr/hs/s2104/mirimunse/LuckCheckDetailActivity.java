package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

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

    int[] imgId = {
            R.drawable.img_riches_good, R.drawable.img_riches_good, R.drawable.img_friendship_good, R.drawable.img_friendship_bad, R.drawable.img_study_good,
            R.drawable.img_study_bad, R.drawable.img_health_good, R.drawable.img_health_bad, R.drawable.img_employ_good, R.drawable.img_employ_bad
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_check_detail);

        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.dialog01, findViewById(R.id.edit_save));

        //하단 바

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

        cardDetail = findViewById(R.id.card_detail);
        textCheckTit = findViewById(R.id.text_check_tit);
        textCheckCont = findViewById(R.id.text_check_cont);

        //랜덤 기능
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM ContentsFortunes;", null);

        Random random = new Random();
        int i = (int)(Math.random()*9)+1;
        for(int j=0; j<i; j++) c.moveToNext();

        fortuneTit = c.getString(1);
        fortuneCont = c.getString(2);
        fortuneImg = imgId[i-1];

        textCheckTit.setText(fortuneTit);
        textCheckCont.setText(fortuneCont);
        cardDetail.setImageResource(fortuneImg);

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new CustomDialog(LuckCheckDetailActivity.this);
                dialog.show();
            }
        });
    }


}