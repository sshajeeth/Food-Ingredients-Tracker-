package com.example.trackfoodincredients;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Edit extends AppCompatActivity {
    DatabaseConnection db;
    EditText name;
    EditText weight;
    EditText price;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        name = findViewById(R.id.name);
        weight = findViewById(R.id.weight);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);

        System.out.println(Edit_products.selectedName);

        db = new DatabaseConnection(this);

        Cursor cs = db.getSpecifiedName(Edit_products.selectedName.toString());


        while (cs.moveToNext()) {
           name.setText(cs.getString(1));
           weight.setText(cs.getString(2));
           price.setText(cs.getString(3));
           description.setText(cs.getString(4));


        }

    }

    public void change(View view) {
        AlertDialog.Builder confirmation = new AlertDialog.Builder(this);
        confirmation.setTitle("Ingredients Tracker");
        confirmation.setMessage("Are you sure to Change Details of this ingredient")
                .setCancelable(false)
                .setPositiveButton("Yes"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.updateAll(Edit_products.selectedName,name,weight,price,description);
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
