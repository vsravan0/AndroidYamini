package com.yamini.training;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.yamini.training.utils.AppUtils;


public class ImplicitIntentActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEtPhoneNumber;
    private Button mBtnCall,mBtnSms;
    private int REQUEST_PHONE_CALL =10001;
    private int REQUEST_SEND_SMS =10003;

    private int REQUEST_PHONE_CALL_COMPLETED =10002;

    private final String TAG="Implicit";
    private ImageView mIvCam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_implicit_inyent_layout);
        mBtnCall=(Button)findViewById(R.id.id_btn_call);
        mEtPhoneNumber=(EditText)findViewById(R.id.editText_number);
        mBtnCall.setOnClickListener(this);
        mBtnSms=(Button)findViewById(R.id.id_btn_sms);
        mBtnSms.setOnClickListener(this);
        mIvCam=(ImageView)findViewById(R.id.id_iv_cam);





        /*
        Android Componenets :

        Activity : startActivity
        Service : startService , stopService
        Broadcast Receiver : sendBroadcast
        Content Provider :
        (currentClass, WhicClass.class)
        Explicit Intent

         ImplicitIntent :
         To open web browser


         */
    }

    String number;
    @Override
    public void onClick(View view) {
         number = mEtPhoneNumber.getText().toString().trim();
        if(view==mBtnCall){
            makeCall();
        } else if(view==mBtnSms){
            //sendSMS();
            sendSMSFromApp();
        }
    }


    public void sendSMSFromApp() {



        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {


            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("9912326989", null, "sample message", null, null);
                Toast.makeText(getApplicationContext(), "Message Sent",
                        Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
                        Toast.LENGTH_LONG).show();
                ex.printStackTrace();
            }
        }
        else{
            ActivityCompat.requestPermissions(ImplicitIntentActivity.this,
                    new String[]{Manifest.permission.SEND_SMS},REQUEST_SEND_SMS);
        }
    }


    private void sendSMS(){


        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + "9912326989"));
        intent.putExtra( "sms_body", "sample message" );
        startActivity(intent);


    }
    private void makeCall(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+number));
       if( ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CALL_PHONE)
        == PackageManager.PERMISSION_GRANTED) {
        startActivity(callIntent);
       } else{
            AppUtils.toast(getApplicationContext(),"Pls provide calpermission");
           requestPermission();
         }
    }


    private void requestPermission(){
        ActivityCompat.requestPermissions(ImplicitIntentActivity.this,
                new String[]{android.Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
         if(requestCode==REQUEST_PHONE_CALL){
           for(String per : permissions){
            Log.v(TAG," Per "+per);
           }

           for(int gr : grantResults){
                 Log.v(TAG," gr "+gr);
             }
             if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                 makeCall();

             }
         } else if(requestCode==REQUEST_SEND_SMS){
             if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                 sendSMSFromApp();

             }
         }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(requestCode==REQUEST_PHONE_CALL_COMPLETED ){
            AppUtils.toast(getApplicationContext()," Call completed ");
        } else if(requestCode==REQUEST_IMAGE_CAPTURE){
            AppUtils.toast(getApplicationContext()," back from camera screen  ");

            if(resultCode==RESULT_OK){


                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mIvCam.setImageBitmap(imageBitmap);

            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void startCamera(View view){

        dispatchTakePictureIntent();


    }
}
