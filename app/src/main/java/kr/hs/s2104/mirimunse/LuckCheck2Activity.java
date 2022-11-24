package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

// 홈 : 카드 클릭 애니메이션
public class LuckCheck2Activity extends AppCompatActivity {
    ImageView checkMain;
    TextView recordMain, toolMain;
    ImageView cardCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_check2);

        cardCheck = findViewById(R.id.card_check);

        // 하단 바
        toolMain = findViewById(R.id.text_tool);    // 마이페이지 연결
        checkMain = findViewById(R.id.btn_check);   // 홈 연결
        recordMain = findViewById(R.id.text_record);    // 보관함 연결

        // 카드 클릭 시 애니메이션
        cardCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.wave);
                cardCheck.startAnimation(animation);
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LuckCheck2Activity.this, LuckCheckDetailActivity.class);
                startActivity(intent);
            }
        }, 4000); // 화면 전환시 4초 딜레이

        // 하단바
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
        checkMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LuckCheckActivity.class);
                startActivity(intent);
            }
        });
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

    }
}