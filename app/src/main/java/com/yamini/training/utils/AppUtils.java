package com.yamini.training.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sravan on 10/03/18.
 */

public class AppUtils {

    private static final String TAG="AppUtils";

    public static boolean isEmpty(EditText editText){
        String data = editText.getText().toString().trim();

       // boolean isvlid = (data!=null&& data.length()>0);

        return TextUtils.isEmpty(data);

    }

    public static  void  checkLogin(Context ctx , EditText etUser, EditText etPwd){
        String username = etUser.getText().toString().trim();
        String pwd= etPwd.getText().toString().trim();


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ctx);
        String savedUserName = pref.getString("username","");
        String savedPwd= pref.getString("password","");

        if(savedUserName.equalsIgnoreCase(username)&& savedPwd.equals(pwd)){
            Toast.makeText(ctx," Success",Toast.LENGTH_LONG).show();
            Log.v(TAG," Success");
        }else{
            Toast.makeText(ctx," check unm and pwd ",Toast.LENGTH_LONG).show();
            Log.v(TAG," Error");
        }

    }


}
