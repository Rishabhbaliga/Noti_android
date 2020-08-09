package com.example.noti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class details extends AppCompatActivity {

    Button save;
    LinearLayout layout;
    String data;
    EditText ed;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        FirebaseApp.initializeApp(this);

        layout = findViewById(R.id.parentLayout);
        save = findViewById(R.id.save);
        ed = findViewById(R.id.editText);

        db = new db(this).getWritableDatabase();

        ed.requestFocus();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = ed.getText().toString();
                Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
                ed.setFocusable(false);

                String myString = data;

                int N=3;
                String nWords="";
                String [] wCount = myString.split("\\s+");

                if(wCount.length >= N) {
                    String [] arr = myString.split("\\s+");
                    for (int i = 0; i < N; i++) {
                        nWords = nWords + " " + arr[i];
                    }

                    ContentValues values = new ContentValues();
                    values.put("title", nWords);
                    values.put("content", data);
                    db.insert("entry", null, values);

                    Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Enter minimum 3 words", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
