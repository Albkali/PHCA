package com.example.masood.phca;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class Register extends AppCompatActivity {

    EditText txtEmail1;
    EditText txtPassword1;
    EditText txtLastName;
    EditText txtMotherName;
    EditText txtPhone;
    EditText txtFirstName;
    Button btn_next;
    Child child;
    ImageView ImgUserPhoto;
    Uri pickedImgUri ;
    FirebaseUser currentUser ;

    private FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
   StorageTask uploadTask ;
   public  Uri imguri ;


    private static final int PReqCode = 2 ;
    private static final int REQUESCODE = 2 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        btn_next = (Button) findViewById(R.id.nextregister2);
        txtEmail1 = (EditText) findViewById(R.id.editTextEmail);
        txtPassword1 = (EditText) findViewById(R.id.editTextPassword);
        txtFirstName = (EditText) findViewById(R.id.editTextFirsttName);
        txtLastName = (EditText) findViewById(R.id.editTextLastName);
        txtMotherName = (EditText) findViewById(R.id.editTextMatherName);
        txtPhone = (EditText) findViewById(R.id.editTextPhoneNum);
        child = new Child();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Register.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                try {

                    String email = txtEmail1.getText().toString().trim();
                    String password = txtPassword1.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                    } else {
                                        Toast.makeText(Register.this, "Email or password not correct", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                    StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
                    final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
                    imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // image uploaded succesfully
                            // now we can get our image url

                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    // uri contain user image url


                                    UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(txtFirstName +""+ txtLastName)
                                            .setPhotoUri(uri)
                                            .build();


                                    currentUser.updateProfile(profleUpdate)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful()) {
                                                        // user info updated successfully
//                                            showMessage("Register Complete");
//                                            updateUI();
                                                    }

                                                }
                                            });

                                }
                            });





                        }
                    });








                    Toast.makeText(Register.this, "data inserted", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Register.this, activity_Register1.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    intent.putExtra("ChildFirstName", txtFirstName.getText().toString());
                    intent.putExtra("ChildLastName", txtLastName.getText().toString());
                    intent.putExtra("ChildMotherName", txtMotherName.getText().toString());
                    intent.putExtra("Phone", txtPhone.getText().toString());
                    startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(Register.this, "Wrong input, please retry", Toast.LENGTH_SHORT).show();

                }
            }
        });


        ImgUserPhoto = findViewById(R.id.user_profile_photo) ;

        ImgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 22) {

                    checkAndRequestForPermission();


                }
                else
                {
                   openGallery();
                }



            }
        });

    }
    private void updateUserInfo( Uri pickedImgUri, final FirebaseUser currentUser) {

        // first we need to upload user photo to firebase storage and get url





    }

    private void openGallery() {
        //TODO: open gallery intent and wait for user to pick an image !

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }

    private void checkAndRequestForPermission() {


        if (ContextCompat.checkSelfPermission(Register.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Register.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(Register.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            }

            else
            {
                ActivityCompat.requestPermissions(Register.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }

        }
        else
            openGallery();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null ) {

            // the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData() ;
            ImgUserPhoto.setImageURI(pickedImgUri);


        }


    }

    public void ClickBackToLogin(View view)
    {
        Intent intent = new Intent( this, Login_form.class);
        startActivity(intent);
    }
}
