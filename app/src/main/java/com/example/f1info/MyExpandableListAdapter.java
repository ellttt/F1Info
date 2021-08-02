package com.example.f1info;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.f1info.models.Race;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MyExpandableListAdapter implements ExpandableListAdapter {

    ArrayList<Race> raceArrayList;
    int resourceLayout;
    Context mContext;
    LayoutInflater mInflater;

    public MyExpandableListAdapter(Context context, int resource, ArrayList data){
        this.raceArrayList=data;
//        Log.d("Tag","MyExpandableListAdapter Constructor");
        this.resourceLayout = resource;
        this.mContext = context;
//        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return raceArrayList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 5;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return raceArrayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return raceArrayList.get(childPosition);
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
        Race individual=raceArrayList.get(groupPosition);
        p.setText(individual.getRound());
        competitor.setText(individual.getRaceNickName());
        points.setText(individual.getRaceDateTime().format(DateTimeFormatter.ofPattern("MM/dd")));
        Log.d("Data Added",individual.toString());

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        Log.d("Tag","OnView Group");
        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            view = vi.inflate(resourceLayout, null);
        }


        TextView p=view.findViewById(R.id.id_position);
        TextView competitor=view.findViewById(R.id.id_competitor);
        TextView points = view.findViewById(R.id.id_pointsPocket);
        Race individual=raceArrayList.get(groupPosition);


        p.setText(individual.getSessions().get(childPosition).getDate());
        competitor.setText(individual.getSessions().get(childPosition).getSessionType());
        points.setText(individual.getSessions().get(childPosition).getTime());
        Log.d("Data Added",individual.toString());

        return view;

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

    public ZonedDateTime parseDateTime(String date, String time){
        return ZonedDateTime.parse(date+"T"+time, DateTimeFormatter.ISO_INSTANT);
    }
}
