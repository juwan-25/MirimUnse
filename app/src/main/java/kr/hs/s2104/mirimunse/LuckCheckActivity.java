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
    ImageView checkMain;
    TextView recordMain;
    TextView recordToDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_check);
        card1 = findViewById(R.id.card1);
        card1.setOnClickListener(cardListener);
        card2 = findViewById(R.id.card1);
        card2.setOnClickListener(cardListener);
        card3 = findViewById(R.id.card1);
        card3.setOnClickListener(cardListener);

        recordToDetail = findViewById(R.id.textv);
        recordToDetail.setOnClickListener(textViewListenerListener);


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

    View.OnClickListener textViewListenerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), LuckRecordDetailActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener cardListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), LuckCheck2Activity.class);
            startActivity(intent);
        }
    };

}