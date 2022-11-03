package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class LuckRecordActivity extends AppCompatActivity {

    private static final float FONT_SIZE = 15;
    private LinearLayout container; // 부모 뷰

    // 저장한 운세 이름 정보
    private ArrayList<FriendItem> mfriendItems;

//  리사이클러뷰
    RecyclerView mRecyclerView;
    MyRecyclerAdapter mRecyclerAdapter;

    TextView toolMain, unseRecord;
    ImageView checkMain;
    LinearLayout list;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    RecyclerView record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_record);
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
//        list = findViewById(R.id.original_list);
//        unseRecord = findViewById(R.id.name);
//        list.setVisibility(View.INVISIBLE); // 기본 목록 텍스트뷰 안 보이게 설정

        Cursor cCnt = db.rawQuery("SELECT count(*) FROM RecordFortunes;", null);
        cCnt.moveToNext();
        Cursor cTitle = db.rawQuery("SELECT * FROM RecordFortunes;", null);
        String unseTitle; //db에서 운세 title 받아올 용도
        int recodeCount = cCnt.getInt(0);

        // 리사이클러 뷰
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        /* initiate adapter */
        mRecyclerAdapter = new MyRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        mfriendItems = new ArrayList<>();
//        // 예시 데이터 넣기
//        for(int i=0;i<recodeCount;i++){
//            mfriendItems.add(new FriendItem(R.drawable.dot, i+"번째 사람",R.drawable.threedot));
//        }
//        Intent intent = new Intent(this, FriendItem.class);
//        intent.putExtra("이름", unseTitle);

        // 리사이클러뷰 데이터값 설정
        mfriendItems = new ArrayList<>();
           int id = -1;
           // db테이블 행 개수로 바꾸기
           for(int i=0; i<recodeCount; i++) {
              mfriendItems.add(new FriendItem(R.drawable.dot, unseTitle, R.drawable.threedot));
           }


        mRecyclerAdapter.setFriendList(mfriendItems);

        //부모 뷰
//        container = (LinearLayout) findViewById(R.id.parent);

//        for(int i = 0; i<recodeCount; i++){
//            cTitle.moveToNext();
//            unseTitle = cTitle.getString(0);
//            if(i==0) unseRecord.setText(unseTitle);
//            //TextView 새롭게 만드는 코드
//            //새롭게 만든 TextView.setText(unseTitle);
//
//            //TextView 생성
//            TextView view1 = new TextView(this);
//            view1.setId(i); // 새로 생성된 textview id값
//            view1.setText(unseTitle);
//            view1.setTextSize(FONT_SIZE);
//            view1.setCompoundDrawablesWithIntrinsicBounds( R.drawable.dot, 0, R.drawable.threedot, 0);  // 양쪽 버튼 이미지
//            view1.setTextColor(Color.rgb(251,218,218)); //글자색상 rgb로 코드 변환
//
//        }

        // recyclerview
//        record = findViewById(R.id.recyclerView);
//        record.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), LuckRecordDetailActivity.class);
//                startActivity(intent);
//            }
//        });

        // 하단 바
        toolMain = findViewById(R.id.text_tool);
        toolMain.setOnClickListener(new View.OnClickListener() {    // 설정 버튼 연결
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
        checkMain = findViewById(R.id.btn_check);   // 홈 버튼 연결
        checkMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LuckCheckActivity.class);
                startActivity(intent);
            }
        });

    }


}