package com.yamini.training;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yamini.training.utils.AppUtils;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener{

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
    public void onClick(View view) {
        if(view==mBtnSignUp){
            startActivity(new Intent(this,ActivitySignup.class));
        } else if(view== mBtnReset){
            mEtPaswword.setText("");
            mEtUserName.setText("");
        } else if(view== mBtnLogin){
            AppUtils.checkLogin(getApplication(), mEtUserName, mEtPaswword);

        }

    }


}
