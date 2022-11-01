package kr.hs.s2104.mirimunse;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class CustomDialog extends Dialog implements DialogInterface.OnClickListener {
    Button btn_save, btn_cancel;
    EditText editSave;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    static String title, cont, img;

    // 다이얼로그 온클릭
    @Override
    public void onClick(DialogInterface dialog, int which) {
    }

    public CustomDialog(@NonNull Context context) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog01);

        editSave = findViewById(R.id.edit_save);
        btn_save = findViewById(R.id.btn_sp);
        btn_cancel = findViewById(R.id.btn_sn);

        btn_save.setOnClickListener(btnListener);
        btn_cancel.setOnClickListener(btnListener);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onClick(View v) {
            dbHelper = new DatabaseHelper(getContext());
            db = dbHelper.getWritableDatabase();

            LayoutInflater inflater = getLayoutInflater();
            View layoutToast = inflater.inflate(R.layout.toast_custom, (ViewGroup) findViewById(R.id.layout_toast));
            TextView textToast = layoutToast.findViewById(R.id.text_toast);

            Toast toast = new Toast(getContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layoutToast);

            switch (v.getId()){
                case R.id.btn_sp:
                    if(editSave.length() == 0){
                        // TODO: 커스텀 토스트 xml 적용시키기
                        textToast.setText("제목을 입력해주세요.");
                        toast.show();
                    }
                    else{
                        db.execSQL("INSERT INTO RecordFortunes values('" +
                                LocalDate.now().toString()+" "+editSave.getText().toString() + "', '" + title + "', '"+cont+"', "+img+
                                ");");

                        ((MainActivity)MainActivity.mContext).changeActivity();
                    }

                    dismiss();
                case R.id.btn_sn:
                    dismiss();
            }
        }
    };

}
