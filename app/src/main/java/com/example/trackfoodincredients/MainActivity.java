package com.example.trackfoodincredients;

        import android.app.Dialog;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;

public class MainActivity extends AppCompatActivity {
    DatabaseConnection db;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDialog=new Dialog(this);
        db = new DatabaseConnection(this);


    }

    public void Register(View view) {
        Intent intent = new Intent(getApplicationContext(),Register_product.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void displayProducts(View view) {
        Intent intent = new Intent(getApplicationContext(),Display_products.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void availability(View view) {
        Intent intent = new Intent(getApplicationContext(),Availability.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void edit(View view) {
        Intent intent = new Intent(getApplicationContext(),Edit_products.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void search(View view) {
        Intent intent = new Intent(getApplicationContext(),Search_products.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void recipe(View view) {
        Intent intent = new Intent(getApplicationContext(),Recipes.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
