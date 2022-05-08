package com.example.wotermadeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView textViewCustomerData;
    private TextView textViewDescriptionEquipment;
    private TextView textViewCount;
    private TextView textViewPrice;
    private TextView textViewOrderFullPrice;
    private EditText test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Intent intent = getIntent();

        textViewCustomerData = findViewById(R.id.textViewCustomerData);
        textViewDescriptionEquipment = findViewById(R.id.textViewDescriptionEquipment);
        textViewCount = findViewById(R.id.textViewCount);
        textViewPrice = findViewById(R.id.textViewPrice);
        textViewOrderFullPrice = findViewById(R.id.textViewFullPrice);

        test = findViewById(R.id.editTextTextTest);
        test.setText("");



        if (intent.hasExtra("fullOrder") ){//&& intent.hasExtra("orderEquipmentArray")) {
            textViewCustomerData.setText(intent.getStringExtra("fullOrder"));
            textViewDescriptionEquipment.setText(intent.getStringExtra("orderList"));
            textViewCount.setText(intent.getStringExtra("equipmentCount"));
            textViewPrice.setText(intent.getStringExtra("price"));
            textViewOrderFullPrice.setText(intent.getStringExtra("orderFullPrice"));

        } else {
            Intent backToLogin = new Intent(this, LoginActivity.class);
            startActivity(backToLogin);
        }
    }
}