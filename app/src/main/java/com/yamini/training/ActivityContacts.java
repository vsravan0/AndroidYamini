package com.yamini.training;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Profile;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class ActivityContacts extends AppCompatActivity {
    private final String TAG="ActivityContacts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contacts);
        Log.v(TAG," on Create ");

       String mProjection[] = new String[]
                {

                        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,


                };

// Retrieves the profile from the Contacts Provider
       Cursor contacts =
                getContentResolver().query(
                        ContactsContract.Contacts.CONTENT_URI,
                        mProjection ,
                        null,
                        null,
                        null);

       if(contacts!=null&& contacts.getCount()>0){

           Log.v(TAG," contact count :"+contacts.getCount() +" Column Count "+contacts.getColumnCount());



           for(int i=0;i<contacts.getColumnCount();i++){

               Log.v(TAG," COntact Col Name "+contacts.getColumnName(i));
           }

           for(;contacts.moveToNext();){

               for(int i=0;i<contacts.getColumnCount();i++){

                   Log.v(TAG," COntact Col Name "+contacts.getColumnName(i) +" Col val "+contacts.getString(i));
               }
               Log.v(TAG," ---------------->>>>>>>>>");

           }


       }else{
           Log.v(TAG," mProfileCursor count O  ");

       }

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG," on onResume ");

    }
}
