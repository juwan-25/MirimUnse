package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LuckCheckActivity extends AppCompatActivity {
    ImageView card1, card2, card3;

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
    }

    View.OnClickListener cardListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), LuckCheck2Activity.class);
            startActivity(intent);
        }
    };

}