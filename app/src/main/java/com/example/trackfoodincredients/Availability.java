package com.example.trackfoodincredients;

import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class Availability extends AppCompatActivity {
    DatabaseConnection db;
    ArrayList<String>namesAvailable;
    ArrayList<CheckBox> isCheckedList;
    CheckBox checkBox;
    RelativeLayout layout;
    LinearLayout linear;
    Button save;
    int i=0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);
        db = new DatabaseConnection(this);

        layout = findViewById(R.id.layout);
        linear = findViewById(R.id.linear);
        save = findViewById(R.id.save);



        Cursor cs = db.getAvailability();
        namesAvailable=new ArrayList<>();
        isCheckedList=new ArrayList<>();
        while (cs.moveToNext()) {
            namesAvailable.add(cs.getString(1));
        }

        for (i = 0; i < namesAvailable.size(); i++) {
            checkBox = new CheckBox(this);
            checkBox.setText(namesAvailable.get(i));
            checkBox.setTextSize(23);
            checkBox.setTextColor(getResources().getColor(R.color.colorHint));
            checkBox.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorHint)));
            checkBox.setChecked(true);

            isCheckedList.add(checkBox);
            linear.addView(checkBox);


        }


    }

    public void save(View view) {

        db.updateAvailability(isCheckedList);
    }
}
