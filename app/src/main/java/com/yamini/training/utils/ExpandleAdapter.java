package com.yamini.training.utils;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.yamini.training.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sravan on 01/04/18.
 */

public class ExpandleAdapter extends BaseExpandableListAdapter {


    private HashMap<String, ArrayList<String>>  mMapData;
    private LayoutInflater mInflater;

    String cheildText;
    String hederText;
    ArrayList<String> mHeaders;
private String TAG="ExAdapter";
    public ExpandleAdapter(HashMap<String, ArrayList<String>>  data, LayoutInflater inflater){
        mMapData= data;
        mInflater= inflater;



        mHeaders= new ArrayList<>(mMapData.keySet());


    }


    @Override
    public int getGroupCount() {
        return mHeaders.size();
    }

    @Override
    public int getChildrenCount(int gropuPosition) {

        String headerKey= mHeaders.get(gropuPosition);
        Log.v(TAG," Header key "+headerKey);


        return mMapData.get(mHeaders.get(gropuPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mHeaders.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

       /*
       String headerkey= mHeaders.get(groupPosition);

      ArrayList<String> childs = mMapData.get(headerkey);
      childs.get(childPosition);
       */


        return mMapData.get(mHeaders.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {


            view=mInflater.inflate(R.layout.layout_ex_header,null);
            TextView textViewHeade = (TextView)view.findViewById(R.id.id_tv_header);

            hederText = mHeaders.get(groupPosition);
            textViewHeade.setText(hederText);



        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        view=mInflater.inflate(R.layout.layout_ex_child,null);
        TextView textViewHeade = (TextView)view.findViewById(R.id.id_tv_child);

        hederText = mHeaders.get(groupPosition);
        cheildText = mMapData.get(hederText).get(childPosition);
        textViewHeade.setText(cheildText);
        return  view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
