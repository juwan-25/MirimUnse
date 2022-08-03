package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.LocaleDisplayNames;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class LuckCheckDetailActivity extends AppCompatActivity {
    ImageView checkMain;
    TextView recordMain;
    Dialog dlg1;
    ImageView btnSave, btnShare;
    ImageView cardDetail;
    TextView textCheckTit, textCheckCont;
    String[] randomTit;
    String[] randomCont;
    int[] randomImgId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_check_detail);

        cardDetail = findViewById(R.id.card_detail);
        textCheckTit = findViewById(R.id.text_check_tit);
        textCheckCont = findViewById(R.id.text_check_cont);

        //랜덤 기능
        randomImgId = new int[10];
        randomTit = new String[10];
        randomCont = new String[10];

        Random random = new Random();
        int i = random.nextInt(randomTit.length-1);

//        textCheckTit.setText(randomTit[i]);
//        textCheckCont.setText(randomCont[i]);
//        cardDetail.setImageResource(randomImgId[i]);


        //대화상자
        dlg1 = new Dialog(this);
        dlg1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dlg1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg1.setContentView(R.layout.dialog01);

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDlg1();
            }
        });

        btnShare = findViewById(R.id.btn_share);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //하단바
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

    }

    //대화상자
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