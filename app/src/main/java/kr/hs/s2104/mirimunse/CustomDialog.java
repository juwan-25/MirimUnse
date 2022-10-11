package kr.hs.s2104.mirimunse;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

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
        @Override
        public void onClick(View v) {
            dbHelper = new DatabaseHelper(getContext());
            db = dbHelper.getWritableDatabase();

            switch (v.getId()){
                case R.id.btn_sp:
                    db.execSQL("INSERT INTO RecordFotunes values('" +
                            editSave.getText().toString() + "', '" + "1" + "', '"+"2"+"', "+"3"+
                            ");");

                    Toast toast = Toast.makeText(getContext(), "저장에 성공했습니다!"+editSave.getText().toString(), Toast.LENGTH_SHORT);
                    toast.show();

                    dismiss();
                case R.id.btn_sn:
                    dismiss();
            }
        }
    };

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
