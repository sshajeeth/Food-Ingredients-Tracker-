package com.example.trackfoodincredients;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class Display_products extends AppCompatActivity {
    DatabaseConnection db;
    RelativeLayout layout;
    LinearLayout linear;
    Button addToKitchen;
    CheckBox checkBox;
    ArrayList<CheckBox> isCheckedList;
    ArrayList<String> namesFromDB;
    int count = 0;
    int i = 0;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);
        layout = findViewById(R.id.layout);
        linear = findViewById(R.id.linear);
        addToKitchen = findViewById(R.id.add);


        db = new DatabaseConnection(this);

        Cursor cs = db.getAll();


        namesFromDB = new ArrayList<>();
        isCheckedList = new ArrayList<>();

        while (cs.moveToNext()) {
            namesFromDB.add(cs.getString(1));


        }

        for (i = 0; i < namesFromDB.size(); i++) {
            checkBox = new CheckBox(this);
            checkBox.setText(namesFromDB.get(i));
            checkBox.setTextSize(23);
            checkBox.setTextColor(getResources().getColor(R.color.colorHint));
            checkBox.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorHint)));
            checkBox.setChecked(false);

            isCheckedList.add(checkBox);
            linear.addView(checkBox);


        }


    }


    public void addToKitchen(View view) {
        AlertDialog.Builder confirmation = new AlertDialog.Builder(this);
        confirmation.setTitle("Ingredients Tracker");
        confirmation.setMessage("Are you sure to Add these ingredients to Kitchen").setCancelable(false)
                .setPositiveButton("Yes"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.addToAvailability(isCheckedList);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                overridePendingTransition(0, 0);
                                finish();
                            }
                        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = confirmation.create();
        alertDialog.show();
    }


}
