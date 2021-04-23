package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Biodata extends AppCompatActivity {

    DataHelper dbHelper;
    EditText text1, text2, text3, text4, text5;
    Button btnSimpan, btnCancel;

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

        btnSimpan = findViewById(R.id.button1);
        btnCancel = findViewById(R.id.button2);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelInsert();
            }
        });
    }

    public void insertData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            db.execSQL(String.format("INSERT INTO biodata (no, nama, tgl, jk, alamat) VALUES('%s', '%s', '%s', '%s','%s')",
                    text1.getText().toString(),
                    text2.getText().toString(),
                    text3.getText().toString(),
                    text4.getText().toString(),
                    text5.getText().toString()
            ));
            Toast.makeText(getApplicationContext(), "Data berhasil disimpan", Toast.LENGTH_LONG).show();
            MainActivity.mainActivity.refreshList();
            finish();

        }catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Data gagal disimpan", Toast.LENGTH_LONG).show();
            MainActivity.mainActivity.refreshList();
            finish();
        }

    }
    public void cancelInsert(){
        finish();
    }
}