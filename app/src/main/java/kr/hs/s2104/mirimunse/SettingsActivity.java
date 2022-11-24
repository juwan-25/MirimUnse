package kr.hs.s2104.mirimunse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// 마이페이지 : 본인 정보 확인
public class SettingsActivity extends AppCompatActivity {

    // firebase
    private FirebaseAuth firebaseAuth;

    ImageView checkMain, imgProfile;
    TextView recordMain, toolMain, textId, textName;
    Button btnToLogin, btnToInfo;
    String btnString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        imgProfile = findViewById(R.id.profile);
        textId = findViewById(R.id.text_set_id);
        textName = findViewById(R.id.text_set_name);
        btnToLogin = findViewById(R.id.btn_to_login);
        btnToInfo = findViewById(R.id.btn_info);
        btnString = "로그인";

        // 하단바
        toolMain = findViewById(R.id.text_tool);    // 마이페이지 연결
        checkMain = findViewById(R.id.btn_check);   // 홈 연결
        recordMain = findViewById(R.id.text_record);    // 보관함 연결

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user;
        if(firebaseAuth.getCurrentUser()==null){ // auth 유저 로그인 여부 확인
            imgProfile.setImageResource(R.drawable.mainunbtn_img); // 초기 레이아웃 설정
            textId.setText("로그인이 필요합니다");
            textName.setText("로그인이 필요합니다");
            btnString = "로그인";
            btnToLogin.setText(btnString);
        } else {
            user = firebaseAuth.getCurrentUser(); // 로그인 후 레이아웃 변경
            imgProfile.setImageResource(R.drawable.mainbtn_img);
            String str[] = user.getEmail().split("@");
            textId.setText(user.getEmail());
            textName.setText(str[0]);
            btnString = "로그아웃";
            btnToLogin.setText(btnString);
        }

        //로그인 연결
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnString.equals("로그인")) { // "로그인" 이면 로그인 페이지로 이동
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    firebaseAuth.signOut();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(intent);
                }
            }
        });

        // 정보 연결
        btnToInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InforActivity.class);
                startActivity(intent);
            }
        });

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


}