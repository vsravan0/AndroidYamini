package com.yamini.training;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by sravan on 24/03/18.
 */

public class BaseActivity extends AppCompatActivity {


    protected static  FirebaseAuth mAuth;



    public FirebaseAuth getFireBaseAuth(){
        if(mAuth==null){
            mAuth= FirebaseAuth.getInstance();
        }
        return mAuth;

    }
}
