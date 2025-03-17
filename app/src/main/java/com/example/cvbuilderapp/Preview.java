package com.example.cvbuilderapp;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Preview extends AppCompatActivity {

    ImageView ivProfile;
    TextView tvName, tvEmail, tvPhone, tvSummary, tvEducation, tvCertification, tvExperience, tvReferences;
    String name, email, phone, summary, eduInstitute, eduDegree, eduYear, exp1, exp2, exp3;
    String issueDate, certificateName, issuingOrganization;
    String refPerson, refJob, refCompany, refEmail, imageString;
    Button btnCancel, btnSave;
    String notMentioned;
    String shareCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        checkPermissions();
        init();

        btnCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
        btnSave.setOnClickListener(v->{
            if(shareCV.isEmpty())
            {
                Toast.makeText(this,"Please enter you Personal Details",Toast.LENGTH_SHORT).show();
                return;
            }

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareCV);
            startActivity(Intent.createChooser(shareIntent, "Share your CV details via"));


        });
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
            }
        }
    }


    private void init() {
        ivProfile = findViewById(R.id.ivMyProfile);
        tvName = findViewById(R.id.tvPersonName);
        tvEmail = findViewById(R.id.tvPersonEmail);
        tvPhone = findViewById(R.id.tvPersonPhone);
        tvSummary = findViewById(R.id.summary);
        tvEducation = findViewById(R.id.education);
        tvExperience = findViewById(R.id.experience);
        tvCertification = findViewById(R.id.certifications);
        tvReferences = findViewById(R.id.references);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        shareCV = "";

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        summary = getIntent().getStringExtra("summary");
        eduInstitute = getIntent().getStringExtra("eduInstitute");
        eduDegree = getIntent().getStringExtra("eduDegree");
        eduYear = getIntent().getStringExtra("eduYear");
        exp1 = getIntent().getStringExtra("exp1");
        exp2 = getIntent().getStringExtra("exp2");
        exp3 = getIntent().getStringExtra("exp3");
        certificateName = getIntent().getStringExtra("certificateName");
        issuingOrganization = getIntent().getStringExtra("issuingOrganization");
        issueDate = getIntent().getStringExtra("issueDate");
        refPerson = getIntent().getStringExtra("refPerson");
        refEmail = getIntent().getStringExtra("refEmail");
        refJob = getIntent().getStringExtra("refJob");
        refCompany = getIntent().getStringExtra("refCompany");
        imageString = getIntent().getStringExtra("imageString");
        notMentioned = "Not Mentioned";
        shareCV = "Personal Details:\nName: "+ name + "\nEmail: " +
                email + "\nPhone: "+ phone +"\n\n";

        tvName.setText(name);
        tvEmail.setText(email);
        tvPhone.setText(phone);
        if(summary.isEmpty())
            tvSummary.setText(notMentioned);
        else
        {
            tvSummary.setText(summary);
            shareCV = shareCV + "Summary:\n"+summary+"\n\n";
        }
        if(eduDegree.isEmpty() && eduInstitute.isEmpty() && eduYear.isEmpty())
            tvEducation.setText(notMentioned);
        else
        {
            String edu =eduInstitute + "   " + eduYear+"\n"+eduDegree;
            tvEducation.setText(edu);
            shareCV = shareCV + "Education:\n"+edu+"\n\n";

        }
        if(exp1.isEmpty() &&exp2.isEmpty() )
            tvExperience.setText(notMentioned);
        else
        {
            String exp = exp1+"\n"+exp2+"\n"+exp3;
            tvExperience.setText(exp);
            shareCV = shareCV + "Experience:\n"+exp+"\n\n";

        }
        if(certificateName.isEmpty() && issuingOrganization.isEmpty() && issueDate.isEmpty())
            tvCertification.setText(notMentioned);
        else
        {
            String cer = certificateName + "( " +issueDate + ")"+"\n"+issuingOrganization;
            tvCertification.setText(cer);
            shareCV = shareCV + "Certification:\n"+cer+"\n\n";
        }
        if(refPerson.isEmpty() && refCompany.isEmpty() && refJob.isEmpty()&& refEmail.isEmpty())
            tvReferences.setText(notMentioned);
        else
        {
            String ref = refPerson +"\n" +refJob+"\n"+refCompany+"\n"+refEmail;
            tvReferences.setText(ref);
            shareCV = shareCV + "Reference:\n"+ref+"\n\n";
        }
        if(name.isEmpty())
            shareCV = "";

        setProfileImage();
    }

    private void setProfileImage() {
        if (imageString != null && !imageString.isEmpty()) {
            try {
                Uri imageUri = Uri.parse(imageString);
                Log.d("DEBUG", "Parsed URI: " + imageUri.toString());

                // Use ContentResolver to access the image
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    ivProfile.setImageBitmap(android.graphics.ImageDecoder.decodeBitmap(
                            android.graphics.ImageDecoder.createSource(getContentResolver(), imageUri)
                    ));
                } else {
                    ivProfile.setImageURI(imageUri);
                }

            } catch (SecurityException e) {
                Log.e("ERROR", "Security Exception: " + e.getMessage(), e);
                Toast.makeText(this, "Cannot access the selected image. Please try again.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("ERROR", "Error setting image: " + e.getMessage(), e);
                Toast.makeText(this, "Error setting image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e("ERROR", "Invalid imageString: " + imageString);
        }
    }

}
