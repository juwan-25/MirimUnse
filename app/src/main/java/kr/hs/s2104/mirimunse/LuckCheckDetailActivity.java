package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LuckCheckDetailActivity extends AppCompatActivity {
    ImageView checkMain;
    TextView recordMain, toolMain;
    Dialog dlg1;
    ImageView btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_check_detail);

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
                //제목과 함꼐 카드 뽑은 내용이 저장
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