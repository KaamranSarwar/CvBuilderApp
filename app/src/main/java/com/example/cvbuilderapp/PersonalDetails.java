package com.example.cvbuilderapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PersonalDetails extends AppCompatActivity {
    EditText etName,etEmail,etPhone;
    Button btnCancel,btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        btnCancel.setOnClickListener((v)->{
            setResult(RESULT_CANCELED);
            finish();
        });
        btnSave.setOnClickListener((v)->{
            handleSave();
        });
    }
    private void handleSave()
    {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();

        if(name.isEmpty())
        {
            Toast.makeText(this,"Please Enter your Name First",Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.isEmpty())
        {
            Toast.makeText(this,"Please Enter your Email First",Toast.LENGTH_SHORT).show();
            return;
        }
        if(phone.isEmpty())
        {
            Toast.makeText(this,"Please Enter your Phone Number First",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent i = new Intent();
        i.putExtra("name",name);
        i.putExtra("email",email);
        i.putExtra("phone",phone);
        setResult(RESULT_OK,i);
        finish();
    }




    private void init()
    {
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
    }

}