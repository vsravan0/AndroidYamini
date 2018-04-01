package com.yamini.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ViewFlipper;

import com.yamini.training.utils.AppUtils;
import com.yamini.training.utils.ExpandleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityExpandable extends AppCompatActivity {

    private ExpandableListView mExLv;
    private ExpandleAdapter mExAdapter;
    HashMap<String, ArrayList<String>> mMapData;
    private ViewFlipper mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_expandable);
        mExLv=(ExpandableListView)findViewById(R.id.id_exlistview);
        mExAdapter=new ExpandleAdapter(AppUtils.getExListviewData(),getLayoutInflater());
        mExLv.setAdapter(mExAdapter);
mVp=(ViewFlipper)findViewById(R.id.id_vp);

    }


    public void showPrevious(View v){

        mVp.showPrevious();
    }
    public void showNext(View v){
        mVp.showNext();

    }
    public void showViewThree(View v){
        mVp.setDisplayedChild(2);

    }
    public void showViewTwo(View v){
        mVp.setDisplayedChild(1);


    }
    public void showViewOne(View v){
        mVp.setDisplayedChild(0);


    }

}
