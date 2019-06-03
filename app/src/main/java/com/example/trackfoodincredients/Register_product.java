package com.example.trackfoodincredients;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_product extends AppCompatActivity {
    Dialog myDialog;
    DatabaseConnection db;
    EditText name;
    EditText weight;
    EditText price;
    EditText description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);


    }

    public void register(View view) {

        name = findViewById(R.id.name);
        weight = findViewById(R.id.weight);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);

        db = new DatabaseConnection(this);

        if (name.getText().toString().equals("") || weight.getText().toString().equals("") ||
                price.getText().toString().equals("") || description.getText().toString().equals("")) {
            name.setHintTextColor(getResources().getColor(R.color.colorWarning));
            weight.setHintTextColor(getResources().getColor(R.color.colorWarning));
            price.setHintTextColor(getResources().getColor(R.color.colorWarning));
            description.setHintTextColor(getResources().getColor(R.color.colorWarning));
            toastMethod("Please fill all fields");
        } else if (!weight.getText().toString().matches("^(0)?[0-9]{0,}((\\.){1}[0-9]{0,2}){0,1}$")) {
            toastMethod("Please Enter Numeric Values");
            weight.setHintTextColor(getResources().getColor(R.color.colorWarning));
        } else if (!price.getText().toString().matches("^(0)?[0-9]{0,}((\\.){1}[0-9]{0,2}){0,1}$")) {
            toastMethod("Please Enter Numeric Values");
            price.setHintTextColor(getResources().getColor(R.color.colorWarning));
        } else {



            AlertDialog.Builder confirmation = new AlertDialog.Builder(this);
            confirmation.setTitle("Ingredients Tracker");
            confirmation.setMessage("Are you sure to register this ingredient").setCancelable(false)
                    .setPositiveButton("Yes"
                            , new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.addToDB(name.getText().toString(), Double.valueOf(weight.getText().toString()),
                                            Double.valueOf(price.getText().toString()), description.getText().toString());
                                    startActivity(getIntent());
                                    overridePendingTransition(0, 0);
                                    name.setText("");
                                    weight.setText("");
                                    price.setText("");
                                    description.setText("");

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

    public void toastMethod(String toast) {
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
    }
}
