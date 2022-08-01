package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db; // DB 클래스 불러오기
    //insertData: 데이터 삽입 메서드
    //viewData: 데이터 조회 메서드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

    }
}