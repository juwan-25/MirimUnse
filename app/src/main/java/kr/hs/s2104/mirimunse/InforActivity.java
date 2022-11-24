package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import kr.hs.s2104.mirimunse.recyclerviewarcodian.DataInfor;
import kr.hs.s2104.mirimunse.recyclerviewarcodian.RecyclerVierAdapter;

public class InforActivity extends AppCompatActivity {
    ImageView checkMain;
    TextView recordMain, toolMain;
    RecyclerVierAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);

        // 아코디언
        init();
        getData();

        // 하단 바
        recordMain = findViewById(R.id.text_record);    // 보관함 버튼 연결
        recordMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

    }


    private void init(){
        RecyclerView recyclerView = findViewById(R.id.recycler_infor);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerVierAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData(){

        DataInfor data = new DataInfor("개발툴", "Android Studio Java");
        adapter.addItem(data);
        data = new DataInfor("개발 참여자", "미림마이스터고 김윤서, 백지민, 이주완");
        adapter.addItem(data);
        data = new DataInfor("개발기간", "2022.07.25~");
        adapter.addItem(data);
    }
}