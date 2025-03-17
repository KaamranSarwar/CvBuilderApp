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
    Button btnProfilePic, btnPersonalDetails, btnEducation, btnSummary, btnReferences, btnCertifications, btnExperience;
    ActivityResultLauncher<Intent> getImageLauncher,getPersonalDetails,getSummary,getEducation,getExperience,getCertificate;

    String name,email,phone,summary,eduInstitute,eduDegree,eduYear,exp1,exp2,exp3;
    String issueDate,certificateName,issuingOrganization;

    Uri image;

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
            if(image != null)
            {
                i.putExtra("image",image.toString());

            }
            else
            {
                i.putExtra("image","");
            }

            getImageLauncher.launch(i);
        });
        btnPersonalDetails.setOnClickListener((v)->{
            Intent i = new Intent(this,PersonalDetails.class);
            getPersonalDetails.launch(i);

        });
        btnSummary.setOnClickListener((v)->{
            Intent i = new Intent(this,Summary.class);
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
    }

    private void init() {
        btnProfilePic = findViewById(R.id.btnProfilePic);
        btnPersonalDetails = findViewById(R.id.btnPersonalDetails);
        btnEducation = findViewById(R.id.btnEducation);
        btnSummary = findViewById(R.id.btnSummary);
        btnCertifications = findViewById(R.id.btnCertifications);
        btnReferences = findViewById(R.id.btnReferences);
        btnExperience = findViewById(R.id.btnExperience);
        name = email = phone =summary= "";
        eduInstitute = eduDegree = eduYear = "";
        exp1 = exp2 = exp3 = "";
        certificateName = issuingOrganization = issueDate = "";
        image = null;



        getImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result)->{
                    if(result.getResultCode() == RESULT_OK && result.getData()!=null)
                    {
                        Intent i = result.getData();
                        String imageString = i.getStringExtra("image");
                        image = Uri.parse(imageString);

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
}


}