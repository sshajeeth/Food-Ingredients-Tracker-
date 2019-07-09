package com.example.trackfoodincredients;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Edit_products extends AppCompatActivity {
    DatabaseConnection db;
    RelativeLayout layout;
    LinearLayout linear;
    TextView textView;
    ArrayList<String> namesFromDB;
    ArrayList<TextView> onClickList;
    int i = 0;
    int j=0;
    int count=0;
    int index=0;
    boolean clicked;
    static String selectedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_products);
        layout = findViewById(R.id.layout);
        linear = findViewById(R.id.linear);

        db = new DatabaseConnection(this);

        Cursor cs = db.getAll();


        namesFromDB = new ArrayList<>();
        onClickList = new ArrayList<>();

        while (cs.moveToNext()) {
            namesFromDB.add(cs.getString(1));


        }

        for (i = 0; i < namesFromDB.size(); i++) {
            textView = new TextView(this);
            textView.setText(namesFromDB.get(i));
            textView.setTextSize(23);
            textView.setTextColor(getResources().getColor(R.color.colorHint));

            onClickList.add(textView);
            linear.addView(textView);


textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedName=textView.getText().toString();
                    Intent intent = new Intent(getApplicationContext(),Edit.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
            });


        }







    }


    public void home(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
