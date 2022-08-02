package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LuckRecordActivity extends AppCompatActivity {
    TextView textV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_record);
        textV = findViewById(R.id.textv);
        textV.setOnClickListener(textViewListenerListener);
    }

    View.OnClickListener textViewListenerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), LuckRecordDetailActivity.class);
            startActivity(intent);
        }
    };

}