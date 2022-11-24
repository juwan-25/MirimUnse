package kr.hs.s2104.mirimunse;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// 마이페이지 : 로그인
public class LoginActivity extends AppCompatActivity {
    //firebase auth
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private EditText editEmail;
    private EditText editPass;
    private Button btnLogin;
    private TextView btnSignTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        editEmail = findViewById(R.id.edit_email);
        editPass = findViewById(R.id.edit_pass);
        btnSignTo = findViewById(R.id.btn_to_sign);
        btnLogin = findViewById(R.id.btn_login);

        // 이메일 입력 여부 확인하기
        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    btnLogin.setClickable(true);
                    btnLogin.setBackgroundResource(R.drawable.btn_login_active);
                    btnLogin.setTextColor(Color.WHITE);

                } else {
                    btnLogin.setClickable(false);
                    btnLogin.setBackgroundResource(R.drawable.btn_login);
                }
            }
        });

        // 회원가입으로 이동
        btnSignTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer colorFrom = getResources().getColor(R.color.btn_color);
                Integer colorTo = getResources().getColor(R.color.white);
                ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
                colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        btnSignTo.setTextColor((Integer)animator.getAnimatedValue());
                    }

                });
                colorAnimation.start();

                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // 이메일, 비밀번호 입력 여부 확인
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editEmail.getText().toString().equals("") && !editPass.getText().toString().equals("")) {
                    loginUser(editEmail.getText().toString(), editPass.getText().toString());
                }
            }
        });
        //로그인 확인 후 마이페이지 이동
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, SettingsActivity.class);
                    intent.putExtra("login", "true" );
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    // 로그인하기
    public void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            firebaseAuth.addAuthStateListener(firebaseAuthListener);
                        } else {
                            // 로그인 실패
                            Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

}