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

public class Experience extends AppCompatActivity {
    EditText etExp1,etExp2,etExp3;

    Button btnCancel,btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_experience);
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
        etExp1 = findViewById(R.id.etExp1);
        etExp2 = findViewById(R.id.etExp2);
        etExp3 = findViewById(R.id.etExp3);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
    }
    private void handleSave()
    {
        String exp1 = etExp1.getText().toString();
        String exp2 = etExp2.getText().toString();
        String exp3 = etExp3.getText().toString();

        if(exp1.isEmpty() && exp2.isEmpty() && exp3.isEmpty())
        {
            Toast.makeText(this,"Please Enter At least one Experience Detail",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent();
        i.putExtra("exp1",exp1);
        i.putExtra("exp2",exp2);
        i.putExtra("exp3",exp3);
        setResult(RESULT_OK,i);
        finish();
    }
}