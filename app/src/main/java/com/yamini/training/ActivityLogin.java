package com.yamini.training;

import android.content.Intent;
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
import com.yamini.training.utils.AppUtils;

public class ActivityLogin extends BaseActivity implements View.OnClickListener,OnCompleteListener<AuthResult> {

private static final String TAG="ActivityLogin";
    private EditText mEtUserName,mEtPaswword;
    private Button mBtnSignUp,mBtnReset,mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        mEtPaswword=(EditText)findViewById(R.id.id_et_pwd);
        mEtUserName=(EditText)findViewById(R.id.id_et_unm);
        mBtnSignUp=(Button)findViewById(R.id.id_btn_show_signup);
        mBtnSignUp.setOnClickListener(this);
        mBtnReset=(Button)findViewById(R.id.id_btn_reset);
        mBtnLogin=(Button)findViewById(R.id.id_btn_login);
        mBtnReset.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mAuth = getFireBaseAuth();

    }
    // Bulk of data , Maniplations ,
    // No
    // 1000 studnt -> 10columns
    // User , pwd
    // Data base : SQLlite -> Sql Lite


    @Override
    protected void onRestart() {
        super.onRestart();
        if(mEtUserName!=null){
            mEtUserName.setText("");
        }
        if(mEtPaswword!=null){
            mEtPaswword.setText("");
        }


    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            Log.v(TAG," Already logged In ");

            startActivity(new Intent(ActivityLogin.this,ActivityRealTime.class));

            finish();
        }



    }


    @Override
    public void onClick(View view) {
        if(view==mBtnSignUp){
            startActivity(new Intent(this,ActivitySignup.class));
        } else if(view== mBtnReset){
            mEtPaswword.setText("");
            mEtUserName.setText("");
        } else if(view== mBtnLogin){
            login();
           // AppUtils.checkLogin(getApplication(), mEtUserName, mEtPaswword);

        }

    }


    private void login(){

        String email = mEtUserName.getText().toString().trim();
        String pwd = mEtPaswword.getText().toString().trim();
Task<AuthResult> resultTask = mAuth.signInWithEmailAndPassword(email,pwd);
resultTask.addOnCompleteListener(this);



    }


    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {



        if (task.isSuccessful()) {
            // Sign in success, update UI with the signed-in user's information
            Log.d(TAG, "User Logged In:success");
            FirebaseUser user = mAuth.getCurrentUser();
            startActivity(new Intent(ActivityLogin.this,ActivityHome.class));

        } else {
            // If sign in fails, display a message to the user.
            Log.w(TAG, "User Logged In :failure", task.getException());
            Toast.makeText(ActivityLogin.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }





    }
}
