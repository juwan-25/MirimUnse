package kr.hs.s2104.mirimunse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class SettingsActivity extends AppCompatActivity {
    ImageView checkMain, imgProfile;
    TextView recordMain, toolMain, textId, textName;
    Button btnToLogin, btnToInfo;
    String btnString;
    static boolean loginCk = false;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        imgProfile = findViewById(R.id.profile);
        textId = findViewById(R.id.text_set_id);
        textName = findViewById(R.id.text_set_name);
        btnToLogin = findViewById(R.id.btn_to_login);
        btnString = "로그인";

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user;
        if(firebaseAuth.getCurrentUser()==null){
            imgProfile.setImageResource(R.drawable.mainunbtn_img);
            textId.setText("로그인이 필요합니다");
            textName.setText("로그인이 필요합니다");
            btnString = "로그인";
            btnToLogin.setText(btnString);
        } else {
            user = firebaseAuth.getCurrentUser();
            imgProfile.setImageResource(R.drawable.mainbtn_img);
            String str[] = user.getEmail().split("@");
            textId.setText(user.getEmail());
            textName.setText(str[0]);
            btnString = "로그아웃";
            btnToLogin.setText(btnString);
        }




        //로그인 버튼 연결
        btnToLogin = findViewById(R.id.btn_to_login);
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnString.equals("로그인")) {
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

        btnToInfo = findViewById(R.id.btn_info);
        btnToInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InforActivity.class);
                startActivity(intent);
            }
        });

        // 하단 바
        recordMain = findViewById(R.id.text_record);    // 보관함 버튼 연결
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