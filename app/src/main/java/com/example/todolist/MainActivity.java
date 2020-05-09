package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    EditText etItem;
    FloatingActionButton btnAdd;
    SwipeMenuListView lvItems;
    //Created an arrayList of name items
    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    private static final String TAG = "myApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etItem = findViewById(R.id.etItem);
        btnAdd = findViewById(R.id.btnAdd);
        lvItems = findViewById(R.id.lvItems);


        items = FileHelper.readData(this); // ReadData returns
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        lvItems.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etItem.getText().toString().equals("")) {
                    String data = etItem.getText().toString();
                    adapter.add(data);
                    etItem.setText("");

                    FileHelper.writeData(items,MainActivity.this);
                    Toast.makeText(MainActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        lvItems.setOnItemClickListener(MainActivity.this);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(150);
                
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }

        };
        lvItems.setMenuCreator(creator);

        lvItems.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (1) {
                    case 1:
                        Log.d(TAG, "onMenuItemClick: Click on delete");
                        items.remove(position);
                        adapter.notifyDataSetChanged();
                        FileHelper.writeData(items, getApplicationContext());
                        Toast.makeText(MainActivity.this, "Task Deleted", Toast.LENGTH_SHORT).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

    }



}
