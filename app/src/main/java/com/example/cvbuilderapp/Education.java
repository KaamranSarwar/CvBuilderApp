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

public class Education extends AppCompatActivity {
    EditText etEdu1,etEdu2,etEdu3;

    Button btnCancel,btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_education);
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
    private void init()
    {
        etEdu1 = findViewById(R.id.etEdu1);
        etEdu2 = findViewById(R.id.etEdu2);
        etEdu3 = findViewById(R.id.etEdu3);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
    }
    private void handleSave()
    {
        String edu1 = etEdu1.getText().toString();
        String edu2 = etEdu2.getText().toString();
        String edu3 = etEdu3.getText().toString();

        if(edu1.isEmpty() && edu2.isEmpty() && edu3.isEmpty())
        {
            Toast.makeText(this,"Please Enter At least one Education Detail",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent();
        i.putExtra("edu1",edu1);
        i.putExtra("edu2",edu2);
        i.putExtra("edu3",edu3);
        setResult(RESULT_OK,i);
        finish();
    }
}