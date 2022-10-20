package kr.hs.s2104.mirimunse;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class CustomDialog extends Dialog implements DialogInterface.OnClickListener {
    Button btn_save, btn_cancel;
    EditText editSave;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

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

            switch (v.getId()){
                case R.id.btn_sp:
                    db.execSQL("INSERT INTO RecordFortunes values('" +
                            LocalDate.now().toString()+" "+editSave.getText().toString() + "', '" + "1" + "', '"+"2"+"', "+"3"+
                            ");");

                    ((MainActivity)MainActivity.mContext).changeActivity();

                    dismiss();
                case R.id.btn_sn:
                    dismiss();
            }
        }
    };

    // 다이얼로그 온클릭
    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
