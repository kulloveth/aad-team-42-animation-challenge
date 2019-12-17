package com.aad_team_42.travelmanticsrebranded.views.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aad_team_42.travelmanticsrebranded.R;
import com.aad_team_42.travelmanticsrebranded.model.Explore;
import com.aad_team_42.travelmanticsrebranded.utils.Constants;
import com.aad_team_42.travelmanticsrebranded.utils.FirebaseUtils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddEditPlaceActivity extends AppCompatActivity {

    ImageView choose;
    TextView imageUrl;
    TextInputEditText location, flavovour, price;
    Uri mImageUri;
    MaterialButton post;
    StorageReference mStrorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_place);

        choose = findViewById(R.id.choose_image);
        imageUrl = findViewById(R.id.image_url);
        location = findViewById(R.id.location_eT);
        flavovour = findViewById(R.id.flavour_eT);
        price = findViewById(R.id.price);
        post = findViewById(R.id.post);
        mStrorageRef = FirebaseStorage.getInstance().getReference().child("location");
        choose.setOnClickListener(v -> openFileChooser());
        post.setOnClickListener(v -> savePost());
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap info = MimeTypeMap.getSingleton();
        return info.getExtensionFromMimeType(cr.getType(uri));

    }

    private void savePost() {
        if (mImageUri != null) {

            StorageReference reference = mStrorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));
            reference.putFile(mImageUri)
                    .addOnSuccessListener(taskSnapshot ->
                            reference.getDownloadUrl().addOnCompleteListener(task -> {
                                Toast.makeText(AddEditPlaceActivity.this, "Upload Successfull", Toast.LENGTH_SHORT).show();
                                Explore explore = new Explore(location.getText().toString(), flavovour
                                        .getText().toString(), price.getText().toString(), task.getResult().toString());
                                String uploadId = FirebaseUtils.mRef.push().getKey();
                                FirebaseUtils.mRef.child("explore").child(uploadId).setValue(explore);
                                finish();
                            }))
                    .addOnFailureListener(taskSnapshot -> {

                    })
                    .addOnProgressListener(taskSnapshot -> {

                    });
        } else {
            Toast.makeText(this, "no file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void openFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, Constants.PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();

            choose.setImageURI(mImageUri);
        }
    }
}
