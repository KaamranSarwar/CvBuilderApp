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
    ActivityResultLauncher<Intent> getImageLauncher,getPersonalDetails,getSummary,getEducation;
    ImageView ivProfile;
    String name,email,phone,summary,edu1,edu2,edu3;

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
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
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
            getEducation.launch(i);

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
        ivProfile = findViewById(R.id.ivProfile);
        name = email = phone =summary= "";
        edu1 = edu2 = edu3 = "";



        getImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result)->{
                    if(result.getResultCode() == RESULT_OK && result.getData()!=null)
                    {
                        Uri image = result.getData().getData();
                        ivProfile.setImageURI(image);
                    }
                    else
                    {
                        Toast.makeText(this, "Please Select the image", Toast.LENGTH_SHORT).show();
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
                edu1 = i.getStringExtra("edu1");
                edu2 = i.getStringExtra("edu2");
                edu3 = i.getStringExtra("edu3");
                Toast.makeText(this,edu1+edu2+edu3,Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(this, "Education Details did not entered", Toast.LENGTH_SHORT).show();
            }


        });
}


}