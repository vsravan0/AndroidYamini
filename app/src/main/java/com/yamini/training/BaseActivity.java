package com.yamini.training;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by sravan on 24/03/18.
 */

public class BaseActivity extends AppCompatActivity {


    protected static  FirebaseAuth mAuth;


    protected  static FirebaseDatabase mDataBase;



    public FirebaseAuth getFireBaseAuth(){
        if(mAuth==null){
            mAuth= FirebaseAuth.getInstance();
        }
        return mAuth;

    }

    public FirebaseDatabase getFirebaseDataBase(){
        if(mDataBase==null){
              mDataBase  = FirebaseDatabase.getInstance();

        }
        return mDataBase;

    }
}
