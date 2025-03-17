package com.example.cvbuilderapp;

import android.app.DatePickerDialog;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Certification extends AppCompatActivity {
    EditText etCName,etIssueOrganization,etDate;
    Calendar calendar;
    Button btnCancel,btnSave;
    String issueDate,certificateName,issuingOrganization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_certification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        etDate.setOnClickListener(v -> openDatePicker());
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
        etCName = findViewById(R.id.etCName);
        etIssueOrganization = findViewById(R.id.etIssueOrganization);
        etDate = findViewById(R.id.etIssueDate);
        calendar = Calendar.getInstance();
        certificateName =  getIntent().getStringExtra("cName");
        issuingOrganization = getIntent().getStringExtra("issOrg");
        issueDate = getIntent().getStringExtra("issDate");
        etCName.setText(certificateName);
        etDate.setText(issueDate);
        etIssueOrganization.setText(issuingOrganization);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

    }

    private void openDatePicker() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    calendar.set(selectedYear, selectedMonth, selectedDay);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                    issueDate = sdf.format(calendar.getTime());

                    etDate.setText(issueDate);
                },
                year, month, day
        );
        datePickerDialog.getDatePicker().setMaxDate(today.getTimeInMillis());

        datePickerDialog.show();
    }
    private void handleSave()
    {
        certificateName = etCName.getText().toString();
        issuingOrganization = etIssueOrganization.getText().toString();
        issueDate = etDate.getText().toString();

        if(certificateName.isEmpty() || issuingOrganization.isEmpty() || issueDate.isEmpty())
        {
            Toast.makeText(this,"Please Enter complete Certification Detail",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent();
        i.putExtra("cName",certificateName);
        i.putExtra("issOrg",issuingOrganization);
        i.putExtra("issDate",issueDate);
        setResult(RESULT_OK,i);
        finish();
    }
}
