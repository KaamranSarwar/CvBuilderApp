package com.example.cvbuilderapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnProfilePic, btnPersonalDetails, btnEducation, btnSummary, btnReferences, btnCertifications, btnExperience,btnPreview;
    ActivityResultLauncher<Intent> getImageLauncher,getPersonalDetails,getSummary,getEducation,getExperience,getCertificate,getReference,showPreview;

    String name,email,phone,summary,eduInstitute,eduDegree,eduYear,exp1,exp2,exp3;
    String issueDate,certificateName,issuingOrganization;
    String refPerson,refJob,refCompany,refEmail,imageString;
    ImageView iv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        btnProfilePic.setOnClickListener((v)->{
            Intent i = new Intent(this,ProfilePicture.class);
            i.putExtra("image",imageString);
            getImageLauncher.launch(i);
        });
        btnPersonalDetails.setOnClickListener((v)->{
            Intent i = new Intent(this,PersonalDetails.class);
            i.putExtra("name",name);
            i.putExtra("email",email);
            i.putExtra("phone",phone);
            getPersonalDetails.launch(i);

        });
        btnSummary.setOnClickListener((v)->{
            Intent i = new Intent(this,Summary.class);
            i.putExtra("summary",summary);
            getSummary.launch(i);

        });
        btnEducation.setOnClickListener((v)->{
            Intent i = new Intent(this,Education.class);
            i.putExtra("edu1",eduInstitute);
            i.putExtra("edu2",eduDegree);
            i.putExtra("edu3",eduYear);

            getEducation.launch(i);

        });
        btnExperience.setOnClickListener((v)->{
            Intent i = new Intent(this,Experience.class);
            i.putExtra("exp1",exp1);
            i.putExtra("exp2",exp2);
            i.putExtra("exp3",exp3);
            getExperience.launch(i);


        });
        btnCertifications.setOnClickListener((v)->{
            Intent i = new Intent(this,Certification.class);
            i.putExtra("cName",certificateName);
            i.putExtra("issOrg",issuingOrganization);
            i.putExtra("issDate",issueDate);
            getCertificate.launch(i);


        });
        btnReferences.setOnClickListener((v)->{
            Intent i = new Intent(this,References.class);
            i.putExtra("refCompany",refCompany);
            i.putExtra("refEmail",refEmail);
            i.putExtra("refPerson",refPerson);
            i.putExtra("refJob",refJob);
            getReference.launch(i);


        });
        btnPreview.setOnClickListener((v)->{
            Intent i = new Intent(this,Preview.class);
            i.putExtra("name",name);
            i.putExtra("email",email);
            i.putExtra("phone",phone);
            i.putExtra("summary",summary);
            i.putExtra("eduInstitute",eduInstitute);
            i.putExtra("eduDegree",eduDegree);
            i.putExtra("eduYear",eduYear);
            i.putExtra("exp1",exp1);
            i.putExtra("exp2",exp2);
            i.putExtra("exp3",exp3);
            i.putExtra("certificateName",certificateName);
            i.putExtra("issuingOrganization",issuingOrganization);
            i.putExtra("issueDate",issueDate);
            i.putExtra("refPerson",refPerson);
            i.putExtra("refEmail",refEmail);
            i.putExtra("refJob",refJob);
            i.putExtra("refCompany",refCompany);
            i.putExtra("imageString",imageString);
            i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            showPreview.launch(i);


        });
    }

    private void init() {
        iv = findViewById(R.id.ivImage);
        btnProfilePic = findViewById(R.id.btnProfilePic);
        btnPersonalDetails = findViewById(R.id.btnPersonalDetails);
        btnEducation = findViewById(R.id.btnEducation);
        btnSummary = findViewById(R.id.btnSummary);
        btnCertifications = findViewById(R.id.btnCertifications);
        btnReferences = findViewById(R.id.btnReferences);
        btnExperience = findViewById(R.id.btnExperience);
        btnPreview = findViewById(R.id.btnPreview);
        name = email = phone =summary= "";
        eduInstitute = eduDegree = eduYear = "";
        exp1 = exp2 = exp3 = "";
        certificateName = issuingOrganization = issueDate = "";
        refPerson = refJob = refCompany = refEmail = "";
        imageString = "";



        getImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result)->{
                    if(result.getResultCode() == RESULT_OK && result.getData()!=null)
                    {
                        Intent i = result.getData();
                        imageString = i.getStringExtra("image");
                    }
                    else
                    {
                        Toast.makeText(this, "Image Did not Selected", Toast.LENGTH_SHORT).show();
                    }
                });
        getPersonalDetails = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
            if(result.getResultCode() == RESULT_OK && result.getData()!=null)
            {
                Intent i = result.getData();
                name = i.getStringExtra("name");
                email = i.getStringExtra("email");
                phone = i.getStringExtra("phone");
                Toast.makeText(this,name+email+phone,Toast.LENGTH_SHORT).show();

            }
            else
            {
                if(name.isEmpty())
                    Toast.makeText(this, "Personal Details did not entered", Toast.LENGTH_SHORT).show();
            }


        });
        getSummary = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
            if(result.getResultCode() == RESULT_OK && result.getData()!=null)
            {
                Intent i = result.getData();
                summary = i.getStringExtra("summary");

                Toast.makeText(this,summary,Toast.LENGTH_SHORT).show();

            }
            else
            {
                if(summary.isEmpty())
                    Toast.makeText(this, "Summary did not entered", Toast.LENGTH_SHORT).show();
            }


        });
        getEducation = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
            if(result.getResultCode() == RESULT_OK && result.getData()!=null)
            {
                Intent i = result.getData();
                eduInstitute = i.getStringExtra("edu1");
                eduDegree = i.getStringExtra("edu2");
                eduYear = i.getStringExtra("edu3");
                Toast.makeText(this,eduInstitute+eduDegree+eduYear,Toast.LENGTH_SHORT).show();

            }
            else
            {
                if(eduDegree.isEmpty() && eduInstitute.isEmpty() && eduYear.isEmpty())
                    Toast.makeText(this, "Education Details did not entered", Toast.LENGTH_SHORT).show();
            }



        });
        getExperience = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
            if(result.getResultCode() == RESULT_OK && result.getData()!=null)
            {
                Intent i = result.getData();
                exp1 = i.getStringExtra("exp1");
                exp2 = i.getStringExtra("exp2");
                exp3 = i.getStringExtra("exp3");
                Toast.makeText(this,exp1+exp2+exp3,Toast.LENGTH_SHORT).show();

            }
            else
            {
                if(exp1.isEmpty() &&exp2.isEmpty() )
                    Toast.makeText(this, "Experience Details did not entered", Toast.LENGTH_SHORT).show();
            }



        });
        getCertificate = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
            if(result.getResultCode() == RESULT_OK && result.getData()!=null)
            {
                Intent i = result.getData();
                certificateName = i.getStringExtra("cName");
                issuingOrganization = i.getStringExtra("issOrg");
                issueDate = i.getStringExtra("issDate");
                Toast.makeText(this,certificateName+issuingOrganization+issueDate,Toast.LENGTH_SHORT).show();

            }
            else
            {
                if(certificateName.isEmpty() && issuingOrganization.isEmpty() && issueDate.isEmpty())
                    Toast.makeText(this, "Certification Details did not entered", Toast.LENGTH_SHORT).show();
            }



        });
        getReference = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
            if(result.getResultCode() == RESULT_OK && result.getData()!=null)
            {
                Intent i = result.getData();
                refPerson = i.getStringExtra("refPerson");
                refEmail = i.getStringExtra("refEmail");
                refCompany = i.getStringExtra("refCompany");
                refJob = i.getStringExtra("refJob");
                Toast.makeText(this,refPerson+refEmail+refJob+refCompany,Toast.LENGTH_SHORT).show();

            }
            else
            {
                if(refPerson.isEmpty() && refCompany.isEmpty() && refJob.isEmpty()&& refEmail.isEmpty())
                    Toast.makeText(this, "Reference Details did not entered", Toast.LENGTH_SHORT).show();
            }



        });
        showPreview = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
            if(result.getResultCode() == RESULT_OK )
            {
                name = email = phone =summary= "";
                eduInstitute = eduDegree = eduYear = "";
                exp1 = exp2 = exp3 = "";
                certificateName = issuingOrganization = issueDate = "";
                refPerson = refJob = refCompany = refEmail = "";
                imageString = "";

            }
        });
}


}