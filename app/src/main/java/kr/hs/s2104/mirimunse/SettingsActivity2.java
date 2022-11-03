package kr.hs.s2104.mirimunse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SettingsActivity2 extends AppCompatActivity {
    ImageView checkMain;
    TextView recordMain, toolMain;
    Button btnToLoginout, btnToInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);

        //로그아웃 버튼 연결
        btnToLoginout = findViewById(R.id.btn_to_loginout);
        btnToLoginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnToInfo = findViewById(R.id.btn_info);
        btnToInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InforActivity.class);
                startActivity(intent);
            }
        });

        // 하단 바
        recordMain = findViewById(R.id.text_record);    // 보관함 버튼 연결
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
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(1)
                        .playOn(findViewById(R.id.text_tool));
                Intent intent = new Intent(getApplicationContext(), SettingsActivity2.class);
                startActivity(intent);
            }
        });

    }
}