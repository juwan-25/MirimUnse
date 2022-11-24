package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

// 보관함 : 저장한 운세 내용
public class LuckRecordDetailActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    ImageView checkMain;
    TextView recordMain, toolMain;

    ImageView imgCard;
    TextView textUserTitle, textTitle, textCont;

    private Intent intent;
    String name;

    static int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_record_detail);

        intent = getIntent();
        name = intent.getStringExtra("mname");

        imgCard = findViewById(R.id.card_detail);
        textUserTitle = findViewById(R.id.text_user_title);
        textTitle = findViewById(R.id.text_record_tit);
        textCont = findViewById(R.id.text_record_cont);

        // 하단바
        toolMain = findViewById(R.id.text_tool);    // 마이페이지 연결
        checkMain = findViewById(R.id.btn_check);   // 홈 연결
        recordMain = findViewById(R.id.text_record);    // 보관함 연결

        // 운세 정보 불러오기
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM RecordFortunes;", null);

        //아이디값만큼 for문 돌리기
        for(int i = 0; i<=id; i++)
            c.moveToNext();

        imgCard.setImageResource(Integer.parseInt(c.getString(3)));
        textUserTitle.setText(c.getString(0).toString());
        textTitle.setText(c.getString(1).toString());
        textCont.setText(c.getString(2).toString());

        textUserTitle.setPaintFlags(textUserTitle.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        // 하단 바
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
        checkMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LuckCheckActivity.class);
                startActivity(intent);
            }
        });
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

    }

    public static void getContet(int pos){
        id = pos;
        Log.d("값 전달", id+"를 받앗슈");
    }
}