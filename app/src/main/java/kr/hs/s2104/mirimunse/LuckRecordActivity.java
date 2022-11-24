package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

// 보관함 : 저장 운세 리스트
public class LuckRecordActivity extends AppCompatActivity {

    // 저장한 운세된 이름 정보
    private ArrayList<FriendItem> mfriendItems;

    // 리사이클러뷰
    RecyclerView mRecyclerView;
    MyRecyclerAdapter mRecyclerAdapter;

    TextView toolMain;
    ImageView checkMain;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_record);

        // DB
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor cCnt = db.rawQuery("SELECT count(*) FROM RecordFortunes;", null);
        cCnt.moveToNext();
        Cursor cTitle = db.rawQuery("SELECT * FROM RecordFortunes;", null);
        String unseTitle; //db에서 운세 title 받아올 용도

        int recodeCount = cCnt.getInt(0);

        // 리사이클러 뷰
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerAdapter = new MyRecyclerAdapter();
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        mfriendItems = new ArrayList<>();

        // 하단바
        toolMain = findViewById(R.id.text_tool);    // 마이페이지 연결
        checkMain = findViewById(R.id.btn_check);   // 홈 연결

        for(int i = 0; i<recodeCount; i++){
            cTitle.moveToNext();
            // 새롭게 만든 TextView.setText(unseTitle);
            unseTitle = cTitle.getString(0);
            // TextView 생성
            TextView view1 = new TextView(this);
            view1.setId(i); // 새로 생성된 textview id값
            view1.setText(unseTitle);
            mfriendItems.add(new FriendItem(R.drawable.dot, unseTitle, R.drawable.threedot));
        }

        mRecyclerAdapter.setFriendList(mfriendItems);
        mRecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemCliskListener() {
            @Override
            public void onItemClick(View v, int pos) {
                // getCertInfo = mfriendItems.get(pos);
                LuckRecordDetailActivity.getContet(pos);
                Log.d("값 전달", "보냇어유"+pos);
            }
        });

        MyRecyclerAdapter adapter = new MyRecyclerAdapter();
        adapter.setOnItemClickListener(
                new MyRecyclerAdapter.OnItemCliskListener() {
                    @Override
                    public void onItemClick(View v, int pos) {
                    }
                }
        );

        // 하단 바
        toolMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    }


}