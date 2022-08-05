package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
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
    Dialog dlg1;
    ImageView btnSave, btnShare;
    ImageView cardDetail;
    TextView textCheckTit, textCheckCont;
    EditText editSave;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    String fortuneTit, fortuneCont;
    int fortuneImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_check_detail);
        editSave = findViewById(R.id.edit_save);

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

        //랜덤 기능
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM ContentsFortunes;", null);
        for(int j=0; j<(int)(Math.random()*9)+1; j++) c.moveToNext();

        fortuneTit = c.getString(1);
        fortuneCont = c.getString(2);
        fortuneImg = c.getInt(3);

        textCheckTit.setText(fortuneTit);
        textCheckCont.setText(fortuneCont);
        cardDetail.setImageResource(fortuneImg);

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
    }

    public void showDlg1(){
        dlg1.show();
        dlg1.findViewById(R.id.btn_sp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //제목과 함께 카드 뽑은 내용이 저장
                Log.d(this.getClass().getName(), "***저장버튼 클릭");
//                db.execSQL("INSERT INTO RecordFortunes values(" +
//                        editSave + ", " + fortuneTit + ", "+fortuneCont+", "+fortuneImg+
//                        ")");

                Toast toast = Toast.makeText(getApplicationContext(), "저장에 성공했습니다!", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(getApplicationContext(), LuckRecordActivity.class);
                startActivity(intent);

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