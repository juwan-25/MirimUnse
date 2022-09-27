package kr.hs.s2104.mirimunse;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog implements DialogInterface.OnClickListener {
    Button btnSave, btnCancel;
    EditText editSave;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    String fortuneTit, fortuneCont;
    int fortuneImg;

    public CustomDialog(@NonNull Context context, String fortuneTit, String fortuneCont, int fortuneImg) {
        super(context);
        this.fortuneTit = fortuneTit;
        this.fortuneCont = fortuneCont;
        this.fortuneImg = fortuneImg;
    }

    public CustomDialog(@NonNull Context context) {
        super(context);
    }


    public void showDialog(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog01);
        show();

        editSave = findViewById(R.id.edit_save);
        btnSave = findViewById(R.id.btn_sp);
        btnCancel = findViewById(R.id.btn_sn);

        btnSave.setOnClickListener(btnListener);
        btnCancel.setOnClickListener(btnListener);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dbHelper = new DatabaseHelper(getContext());
            db = dbHelper.getWritableDatabase();

            switch (v.getId()){
                case R.id.btn_sp:
                    db.execSQL("INSERT INTO RecordFotunes values('" +
                            editSave.getText().toString() + "', '" + fortuneTit + "', '"+fortuneCont+"', "+fortuneImg+
                            ");");
                    Toast.makeText(getContext(), "저장 성공", Toast.LENGTH_SHORT).show();
                    dismiss();

                case R.id.btn_sn:
                    dismiss();
            }

            Intent intent = new Intent(getContext(), LuckRecordActivity.class);
            getContext().startActivity(intent);
        }
    };

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
