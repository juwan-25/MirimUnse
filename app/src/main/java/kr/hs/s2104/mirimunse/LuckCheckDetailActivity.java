package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LuckCheckDetailActivity extends AppCompatActivity {
    ImageView checkMain;
    TextView recordMain, toolMain;
    Dialog dlg1;
    ImageView btnSave, btnShare;
    ImageView cardDetail;
    TextView textCheckTit, textCheckCont;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_check_detail);

        dbHelper = new DatabaseHelper(this);

        toolMain = findViewById(R.id.text_tool);
        toolMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        checkMain = findViewById(R.id.btn_check);
        checkMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LuckCheckActivity.class);
                startActivity(intent);
            }
        });

        recordMain = findViewById(R.id.text_record);
        recordMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LuckRecordActivity.class);
                startActivity(intent);
            }
        });

        cardDetail = findViewById(R.id.card_detail);
        textCheckTit = findViewById(R.id.text_check_tit);
        textCheckCont = findViewById(R.id.text_check_cont);

//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM ContentsFortunes;", null);
//
//        //랜덤 기능
//        Random random = new Random();
//        int i = (int)(Math.random()*9)+1;
//        for(int j=0; j<i; j++) c.moveToNext();
//
//        textCheckTit.setText(c.getString(1));
//        textCheckCont.setText(c.getString(2));
//        cardDetail.setImageResource(c.getInt(3));


        dlg1 = new Dialog(this);
        dlg1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg1.setContentView(R.layout.dialog01);

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDlg1();
            }
        });
 //       db.close();
    }

    public void showDlg1(){
        dlg1.show();
        dlg1.findViewById(R.id.btn_sp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //제목과 함꼐 카드 뽑은 내용이 저장
                Toast toast = Toast.makeText(getApplicationContext(), "저장에 성공했습니다!", Toast.LENGTH_SHORT);
                toast.show();
                dlg1.dismiss();
            }
        });
        dlg1.findViewById(R.id.btn_sn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg1.dismiss();
            }
        });
    }
}