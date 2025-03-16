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

public class ProfilePicture extends AppCompatActivity {
    ImageView ivProfile;
    Button btnSave,btnCancel,btnImage;
    Intent imageIntent;
    ActivityResultLauncher<Intent> getImageLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_picture);
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
        btnImage.setOnClickListener((v)->{
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            getImageLauncher.launch(i);
        });
    }
    private void init()
    {
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        btnImage = findViewById(R.id.btnImage);
        ivProfile = findViewById(R.id.ivImage);
        imageIntent = null;
        getImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                (result)->{
                    if(result.getResultCode() == RESULT_OK && result.getData()!=null)
                    {
                        imageIntent = result.getData();
                        Uri image = imageIntent.getData();
                        ivProfile.setImageURI(image);
                    }
                    else
                    {
                        Toast.makeText(this, "Select the image", Toast.LENGTH_SHORT).show();
                    }
                });


    }
    private void handleSave()
    {


        if(imageIntent == null)
        {
            Toast.makeText(this,"Please Select image First",Toast.LENGTH_SHORT).show();
            return;
        }

        setResult(RESULT_OK,imageIntent);
        finish();
    }
}