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

public class References extends AppCompatActivity {

    EditText etRefPerson,etRefJob,etRefCompany,etRefEmail;
    String refPerson,refJob,refCompany,refEmail;

    Button btnCancel,btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_references);
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
        etRefPerson = findViewById(R.id.etRefPerson);
        etRefJob = findViewById(R.id.etRefJob);
        etRefCompany = findViewById(R.id.etRefCompany);
        etRefEmail = findViewById(R.id.etRefEmail);
        refPerson = getIntent().getStringExtra("refPerson");
        refEmail = getIntent().getStringExtra("refEmail");
        refCompany = getIntent().getStringExtra("refCompany");
        refJob = getIntent().getStringExtra("refJob");
        etRefPerson.setText(refPerson);
        etRefEmail.setText(refEmail);
        etRefJob.setText(refJob);
        etRefCompany.setText(refCompany);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

    }
    private void handleSave()
    {
        refCompany = etRefCompany.getText().toString();
        refJob = etRefJob.getText().toString();
        refEmail = etRefEmail.getText().toString();
        refPerson = etRefPerson.getText().toString();

        if(refPerson.isEmpty() || refEmail.isEmpty() || refCompany.isEmpty() || refJob.isEmpty())
        {
            Toast.makeText(this,"Please Enter complete Details of reference",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent();
        i.putExtra("refCompany",refCompany);
        i.putExtra("refEmail",refEmail);
        i.putExtra("refPerson",refPerson);
        i.putExtra("refJob",refJob);
        setResult(RESULT_OK,i);
        finish();
    }

}