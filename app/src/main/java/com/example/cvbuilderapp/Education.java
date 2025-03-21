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
    String edu1,edu2,edu3;

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

        edu1 = getIntent().getStringExtra("edu1");
        edu2 = getIntent().getStringExtra("edu2");
        edu3 = getIntent().getStringExtra("edu3");
        etEdu1.setText(edu1);
        etEdu2.setText(edu2);
        etEdu3.setText(edu3);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
    }
    private void handleSave()
    {
        edu1 = etEdu1.getText().toString();
        edu2 = etEdu2.getText().toString();
        edu3 = etEdu3.getText().toString();

        if(edu1.isEmpty() || edu2.isEmpty() || edu3.isEmpty())
        {
            Toast.makeText(this,"Please Enter complete Education Detail",Toast.LENGTH_SHORT).show();
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