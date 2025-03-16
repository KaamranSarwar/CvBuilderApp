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

public class Summary extends AppCompatActivity {

    EditText etSummary;
    Button btnCancel,btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
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
        String summary = etSummary.getText().toString();

        if(summary.isEmpty())
        {
            Toast.makeText(this,"Please Enter Summary First",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent i = new Intent();
        i.putExtra("summary",summary);
        setResult(RESULT_OK,i);
        finish();
    }
    private void init()
    {
        etSummary = findViewById(R.id.etSummary);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
    }

}