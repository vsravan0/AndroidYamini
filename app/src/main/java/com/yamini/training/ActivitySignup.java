package com.yamini.training;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.yamini.training.utils.DbUtils;
import com.yamini.training.utils.MyDatabase;

public class ActivitySignup extends BaseActivity implements View.OnClickListener, OnCompleteListener<AuthResult>{
    private EditText mEtUserName,mEtPaswword;



    private Button mBtnSignUp;
private static final String TAG="ActivitySignup";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);
        mEtPaswword=(EditText)findViewById(R.id.id_et_pwd_signup);
        mEtUserName=(EditText)findViewById(R.id.id_et_unm_signup);
        mBtnSignUp=(Button)findViewById(R.id.id_btn_signup);
        mBtnSignUp.setOnClickListener(this);
        mAuth=getFireBaseAuth();

    }
  //  yamini.lak@gmail.com
    //

    @Override
    public void onClick(View view) {
        if(view==mBtnSignUp){

            String username = mEtUserName.getText().toString().trim();
            String pwd= mEtPaswword.getText().toString().trim();



            if(username.length()>0&&pwd.length()>0){

                Task<AuthResult> resultTask =  mAuth.createUserWithEmailAndPassword(username, pwd);
                resultTask.addOnCompleteListener(this);


            }else{
                Toast.makeText(getApplicationContext()," pls check ",Toast.LENGTH_LONG).show();
            }

         /*   if(username.length()>0){

                if(pwd.length()>0){

                    saveData(username,pwd);
                    Toast.makeText(getApplicationContext()," Data Saved ",Toast.LENGTH_LONG).show();
                    Log.v("Signup"," Data saved");
                    finish();

                }else{
                    mEtPaswword.setError("Please Enter password");
                }

            }else{
                mEtUserName.setError("Please enter username ");
            }  */
        }
    }


    private void saveData(String userName, String password){


        MyDatabase db = new MyDatabase(getApplicationContext());

        ContentValues cv = new ContentValues();
        cv.put(DbUtils.TAB_USER_UNM,userName);
        cv.put(DbUtils.TAB_USER_PWD,password);
        long isInserved = db.saveData(DbUtils.TAB_USER,DbUtils.TAB_USER_UNM,cv);

        db.close();

        if(isInserved!=-1){
            Log.v(TAG," isInserved :"+isInserved);
            Toast.makeText(getApplicationContext()," success ",Toast.LENGTH_LONG).show();
            finish();

        }else{
            Toast.makeText(getApplicationContext()," Some error",Toast.LENGTH_LONG).show();
        }


// Key and values saved
        /*
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("username",userName);
        edit.putString("password",password);
        edit.apply(); */





    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

        if (task.isSuccessful()) {
            // Sign in success, update UI with the signed-in user's information
            Log.d(TAG, "createUserWithEmail:success");
            FirebaseUser user = mAuth.getCurrentUser();
            finish();

        } else {
            // If sign in fails, display a message to the user.
            Log.w(TAG, "createUserWithEmail:failure", task.getException());
            Toast.makeText(ActivitySignup.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
