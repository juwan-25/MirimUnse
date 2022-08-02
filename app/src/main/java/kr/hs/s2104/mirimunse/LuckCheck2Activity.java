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

public class LuckCheck2Activity extends AppCompatActivity {
    ImageView checkMain;
    TextView recordMain;
    ImageView cardCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_check2);

        cardCheck = findViewById(R.id.card_check);
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
        }, 4000);

        //하단바 연결
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
}