package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog01);

        Button submit = findViewById(R.id.btn_sp);
        if(submit.getId() == R.id.btn_sp) {
            Toast toast = Toast.makeText(getApplicationContext(), "저장에 성공했습니다!", Toast.LENGTH_SHORT);
        }
        else {
            finish();
        }
    }
}