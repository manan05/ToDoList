package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etItem;
    Button btnAdd;
    ListView lvItems;

    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etItem = findViewById(R.id.etItem);
        btnAdd = findViewById(R.id.btnAdd);
        lvItems = findViewById(R.id.lvItems);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etItem.getText().toString().equals("")) {
                    String data = etItem.getText().toString();
                    adapter.add(data);
                    etItem.setText("");
                    Toast.makeText(MainActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
