package edu.bd.ewu.contactform;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

///    private static final  int SELECT_PICTURE = 200 ;
    String names,p_email,p_h,p_f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.email);
        EditText phone_home = findViewById(R.id.phone_home);
        EditText phone_office = findViewById(R.id.phone_office);
        Button cancel = findViewById(R.id.cancel);
        Button save = findViewById(R.id.save);
        ImageView imageGallery =findViewById(R.id.img_icon);
        Button btnGallary=findViewById(R.id.btnGallary);
        SharedPreferences sharedPref = this.getSharedPreferences("MySharedPref", MODE_PRIVATE);



//
//
//
//        btnGallary.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                pick_image();
//            }
//
//
//        });



        save.setOnClickListener(v -> {
            String error="";
            names= name.getText().toString();
            if(names.length()<5){
                error = "Invalid name\n";
            }

            p_email=email.getText().toString();
            if(p_email.length()<9){
                error += "Invalid Email\n";
            }

             p_h= phone_home.getText().toString();
            if(p_h.length()<11){
                error += "Invalid Phone number\n";
            }
            p_f= phone_office.getText().toString();
            if(p_f.length()<11){
                error += "Invalid Phone number\n";
            }

            if(error.length()!=0){

                showDialog(error, "Error", "Okay", "Back", sharedPref, false);

            }
            else{
                showDialog("Do you want to save this info?", "Info", "Yes", "No", sharedPref, true);
            }



//                String key = names+"-"+p_n;
//                String value= names+";"+places+";" +p_email+";"+p_n+"";
//                KeyValueDB kdb= new KeyValueDB(MainActivity.this);
//                boolean b =kdb.insertKeyValue(key,value);
//                System.out.println(b);

        });
        cancel.setOnClickListener(view -> finish());


    }
//    private void pick_image(){
//        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickPhoto, 1);
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode)
//        {
//            case 1:
//                if(resultCode == RESULT_OK){
//                    Uri selectedImageUri = data.getData();
//                    imgGelalry.setImageURI(selectedImageUri);
//                }
//                break;
//            case 2:
//                if(resultCode == RESULT_OK){
//                    Bundle bundle = data.getExtras();
//                    Bitmap bitmapImage = (Bitmap) bundle.get("data");
//                    imageGellary.setImageBitmap(bitmapImage);
//                }
//                break;
//        }
//    }


    private void showDialog(String message, String title, String bt1, String bt2, SharedPreferences sharedPref, boolean f){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setTitle(title);

        builder.setCancelable(false)
                .setPositiveButton(bt1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        System.out.println("Yes,it is working");
                        if(f){
                            SharedPreferences.Editor prefsEditor = sharedPref.edit();
                            prefsEditor.putString("name", names);
                            prefsEditor.putString("email", p_email);
                            prefsEditor.putString("phone_home", p_h);
                            prefsEditor.putString("phone_office", p_f);
                            prefsEditor.apply();

                            String names = sharedPref.getString("name", "");
                            System.out.println("names: "+names);

                            // Handler which will run after 2 seconds.
                            new Handler().postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,
                                            "Information has been stored.",
                                            Toast.LENGTH_LONG).show();
                                }
                            }, 2000);

                            //Toast.makeText(MainActivity.this, "Dear " + name_str_sp + ", Your information has been stored.", Toast.LENGTH_SHORT).show();
                        }
                        dialog.cancel();

                    }
                })
                .setNegativeButton(bt2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        System.out.println("No,it is not working");
                        dialog.cancel();

                    }
                });

        AlertDialog alert = builder.create();

        alert.show();
    }



    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
