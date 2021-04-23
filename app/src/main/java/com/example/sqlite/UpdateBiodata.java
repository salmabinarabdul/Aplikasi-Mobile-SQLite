package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateBiodata extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnSimpan, btnCancel;
    EditText text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);

        dbHelper = new DataHelper(this);
        text1 = findViewById(R.id.editText1);
        text2 = findViewById(R.id.editText2);
        text3 = findViewById(R.id.editText3);
        text4 = findViewById(R.id.editText4);
        text5 = findViewById(R.id.editText5);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        cursor = db.rawQuery(String.format("SELECT * FROM biodata WHERE nama = '%s'", getIntent().getStringExtra("nama")), null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0));
            text2.setText(cursor.getString(1));
            text3.setText(cursor.getString(2));
            text4.setText(cursor.getString(3));
            text5.setText(cursor.getString(4));
        }

        btnSimpan = findViewById(R.id.button1);
        btnCancel = findViewById(R.id.button2);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelUpdate();
            }
        });

    }

    public void updateData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL(String.format("UPDATE biodata SET nama='%s', tgl='%s', jk='%s', alamat='%s' WHERE no='%s'",
                    text2.getText().toString(),
                    text3.getText().toString(),
                    text4.getText().toString(),
                    text5.getText().toString(),
                    text1.getText().toString()
            ));
            Toast.makeText(getApplicationContext(), "Data berhasil diperbarui", Toast.LENGTH_LONG).show();
            MainActivity.mainActivity.refreshList();
            finish();
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Data gagal diperbarui: " + e.getMessage(), Toast.LENGTH_LONG).show();
            MainActivity.mainActivity.refreshList();
            finish();
        }

    }

    public void cancelUpdate() {
        finish();
    }
}