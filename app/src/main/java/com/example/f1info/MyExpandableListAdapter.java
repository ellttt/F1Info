package com.example.f1info;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyExpandableListAdapter implements ExpandableListAdapter {

    ArrayList<String []> data;
    int resourceLayout;
    Context mContext;
    LayoutInflater mInflater;

    public MyExpandableListAdapter(Context context, int resource, ArrayList data){
        this.data=data;
        Log.d("Tag","MyExpandableListAdapter Constructor");
        this.resourceLayout = resource;
        this.mContext = context;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        Log.d("Tag","OnView Group");
        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            view = vi.inflate(resourceLayout, null);
        }

        TextView p=view.findViewById(R.id.id_position);
        TextView competitor=view.findViewById(R.id.id_competitor);
        TextView points = view.findViewById(R.id.id_pointsPocket);
        String [] individual=data.get(groupPosition);
        p.setText(individual[0]);
        if(individual[1].contains("Grand Prix")){
            competitor.setText(individual[1].substring(0,individual[1].indexOf("Grand Prix"))+"GP");
        }

        points.setText(individual[2]);
        Log.d("Data Added",individual[1]);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {


        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
