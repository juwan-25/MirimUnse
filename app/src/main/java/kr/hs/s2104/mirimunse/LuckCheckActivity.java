package kr.hs.s2104.mirimunse;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LuckCheckActivity extends AppCompatActivity {
    ImageView card1, card2, card3;
    TextView recordMain, toolMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_check);
        card1 = findViewById(R.id.card1);
        card1.setOnClickListener(cardListener);
        card2 = findViewById(R.id.card2);
        card2.setOnClickListener(cardListener);
        card3 = findViewById(R.id.card3);
        card3.setOnClickListener(cardListener);

        // 하단 바
        recordMain = findViewById(R.id.text_record);    // 보관함 버튼 연결
        recordMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LuckRecordActivity.class);
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

    View.OnClickListener cardListener = new View.OnClickListener() {    // 카드 선택 시 luck_check2 연결
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), LuckCheck2Activity.class);
            startActivity(intent);
        }
    };

}