package kr.hs.s2104.mirimunse;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

// 마이페이지 : 회원가입
public class SignUpActivity extends AppCompatActivity {

    private  EditText editName;
    private static final String TAG = "SignUpActivity";

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editName = findViewById(R.id.edit_sign_name);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        sign_up = findViewById(R.id.btn_signup);

        // 이름 입력 여부
        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    sign_up.setClickable(true);
                    sign_up.setBackgroundResource(R.drawable.btn_login_active);
                    sign_up.setTextColor(Color.WHITE);

                } else {
                    sign_up.setClickable(false);
                    sign_up.setBackgroundResource(R.drawable.btn_login);
                }
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void signUp() {
        //이름
        String name = editName.getText().toString();

        // 이메일
        EditText emailEdit = findViewById(R.id.edit_sign_email);
        String email = emailEdit.getText().toString();

        // 비밀번호
        EditText passEdit = findViewById(R.id.edit_sign_pass);
        String password = passEdit.getText().toString();
        EditText passckEdit = findViewById(R.id.edit_sign_passck);
        String ckpassword = passckEdit.getText().toString();

        // 이름, 이메일과 비밀번호가 공백이 아닌 경우
        if (!password.equals("") && !email.equals("") && !name.equals("") && password.equals(ckpassword)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // uid에 task, 선택된 사진 등을 file에 할당

                                final String uid = task.getResult().getUser().getUid();

                                UserModel userModel = new UserModel();
                                userModel.userName = name;
                                userModel.uid = uid;

                                mDatabase.getReference().child("users").child(uid)
                                        .setValue(userModel);

                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Log.w(TAG, "회원가입 실패", task.getException());
                                return;
                            }
                        }
                    });
        } else if (password.equals("") && email.equals("")){
            // 이메일과 비밀번호가 공백인 경우
            Toast.makeText(SignUpActivity.this, "계정과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
        } else if (!password.equals(ckpassword)) {
            // 비밀번호와 확인이 일치하지 않는 경우
            Toast.makeText(SignUpActivity.this, "비밀번호 확인이 다릅니다.", Toast.LENGTH_LONG).show();
        }

    }

    Intent intent = getIntent();

}