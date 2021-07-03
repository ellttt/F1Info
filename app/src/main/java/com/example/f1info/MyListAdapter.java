package com.example.f1info;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter {

    ArrayList<String []> data;
    int resourceLayout;
    Context mContext;
    LayoutInflater mInflater;
    int counter=0;

    public MyListAdapter(Context context, int resource, ArrayList data){
        super(context, resource, data);
        this.data=data;
        Log.d("Tag","MyListAdapter Constructor");
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
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        counter++;
        Log.d("Tag","MyListAdapter onView "+counter);
        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            view = vi.inflate(resourceLayout, null);
        }

        TextView p=view.findViewById(R.id.id_position);
        TextView competitor=view.findViewById(R.id.id_competitor);
        TextView points = view.findViewById(R.id.id_pointsPocket);
        String [] individual=data.get(position);
        p.setText("P"+individual[0]);
        competitor.setText(individual[1]);
        points.setText(individual[2]);
        Log.d("Data Added",individual[1]);


        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
