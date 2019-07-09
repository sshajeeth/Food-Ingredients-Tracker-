package com.example.trackfoodincredients;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;


public class Search_products extends AppCompatActivity {
SearchView search;
ListView list;
ArrayList<String> names;
ArrayAdapter<String>adapter;
DatabaseConnection db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);

        search=findViewById(R.id.search);
        list = findViewById(R.id.list);
        db= new DatabaseConnection(this);

        names=new ArrayList<>();

        db = new DatabaseConnection(this);

        Cursor cs = db.getAll();
        while (cs.moveToNext()) {
            names.add(cs.getString(1));
        }

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,names);
        list.setAdapter(adapter);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                list.setVisibility(1);
                return false;
            }
        });


    }

    public void home(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
