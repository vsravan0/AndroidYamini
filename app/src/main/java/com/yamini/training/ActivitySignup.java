package com.yamini.training;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivitySignup extends AppCompatActivity implements View.OnClickListener{
    private EditText mEtUserName,mEtPaswword;
    private Button mBtnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);
        mEtPaswword=(EditText)findViewById(R.id.id_et_pwd_signup);
        mEtUserName=(EditText)findViewById(R.id.id_et_unm_signup);
        mBtnSignUp=(Button)findViewById(R.id.id_btn_signup);
        mBtnSignUp.setOnClickListener(this);
    }
  //  yamini.lak@gmail.com
    //

    @Override
    public void onClick(View view) {
        if(view==mBtnSignUp){

            String username = mEtUserName.getText().toString().trim();
            String pwd= mEtPaswword.getText().toString().trim();

            if(username.length()>0){

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
            }
        }
    }


    private void saveData(String userName, String password){

// Key and values saved
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("username",userName);
        edit.putString("password",password);
        edit.apply();





    }
}
