package com.yamini.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.yamini.training.utils.AppUtils;

import org.w3c.dom.Text;

public class ActivityRealTime extends BaseActivity implements View.OnClickListener, ValueEventListener {


    private Button mBtnSend, mBtnRetrive;
    private EditText mEtMsg;
    private TextView mTvMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_real_time);
        mEtMsg=(EditText)findViewById(R.id.id_et_rl_msg);
        mTvMsg=(TextView)findViewById(R.id.id_tv_relattime_msg);
        mBtnRetrive=(Button)findViewById(R.id.id_btn_retrive);
        mBtnSend=(Button)findViewById(R.id.id_btn_send);
        mBtnRetrive.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
        mDataBase = getFirebaseDataBase();



    }

    @Override
    public void onClick(View view) {
        if(view==mBtnRetrive){


        } else if(view==mBtnSend){


            DatabaseReference ref = mDataBase.getReference("message");

            ref.setValue(mEtMsg.getText().toString().trim());

            ref.addValueEventListener(this);


        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        AppUtils.toast(getApplicationContext()," Data chnaged ");

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        AppUtils.toast(getApplicationContext()," Data Cancelled ");


    }
}
