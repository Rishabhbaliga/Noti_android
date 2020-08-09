package com.example.noti;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class view extends AppCompatActivity {

    Button save;
    LinearLayout layout;
    String data, osrc;
    EditText ed;
    ArrayList<String> item;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        db = new db(this).getWritableDatabase();

        layout = findViewById(R.id.parent);
        save = findViewById(R.id.update);
        ed = findViewById(R.id.editText1_0);

        final Intent src = getIntent();
        osrc = src.getStringExtra("name");

        item = new ArrayList<>();

        String query = "select * from entry where title LIKE '%" + osrc + "%'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data in Database", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                item.add(cursor.getString(1));
            }
            ed.setText(item.get(0));
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = ed.getText().toString();
                ed.setFocusable(false);

                ContentValues values = new ContentValues();
                values.put("content", data);
                String where = "title=?";
                String[] whereArgs = new String[] {String.valueOf(osrc)};
                db.update("entry", values, where, whereArgs);

                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = ed.getText().toString();
                ed.setFocusable(false);

                Toast.makeText(getApplicationContext(), osrc, Toast.LENGTH_SHORT).show();
                db.execSQL("delete from entry where title like '" + osrc + "%'");

                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();

                Intent back = new Intent(view.getContext(), MainActivity.class);
                startActivity(back);
                finish();
            }
        });
    }

}
