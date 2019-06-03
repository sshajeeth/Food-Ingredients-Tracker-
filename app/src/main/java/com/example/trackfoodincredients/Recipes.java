package com.example.trackfoodincredients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Recipes extends AppCompatActivity {
   String api="https://www.food2fork.com/api/search?key=d050363f253fd663f357b2d5468e5430&q=chicken%20breast&page=2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
    }

    public static void main(String[] args) {

    }



}
