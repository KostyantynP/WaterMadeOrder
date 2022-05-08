package com.example.wotermadeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextNumberOfPhone;
    private EditText editTextAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextName =  findViewById(R.id.editTextName);
        editTextNumberOfPhone = findViewById(R.id.editTextPhone);
        editTextAddress =  findViewById(R.id.editTextAddress);
        Log.i("show","0");
    }

    public void onClickCreateOrder(View view) {

        String name;
        String phone;
        String address;


           name = editTextName.getText().toString().trim();
           phone = editTextNumberOfPhone.getText().toString().trim();
           address = editTextAddress.getText().toString().trim();


        if (!name.isEmpty()) {
            Intent intent = new Intent(this, createOrderActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("phone", phone);
            intent.putExtra("address", address);
            Log.i("show","2");
            startActivity(intent);
        }else {
            Toast.makeText(this, R.string.warning_fill_fields, Toast.LENGTH_SHORT).show();
        }

    }
}