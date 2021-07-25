package com.example.f1info.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.lifecycle.Observer;

import com.example.f1info.MyListAdapter;
import com.example.f1info.R;

import java.util.ArrayList;

public class TabController implements Observer<Integer> {
    static int previousTab=-1;
    static PageViewModel pageViewModel;
    static View root;
    public static TabController newInstance(PageViewModel pVM, View view) {
        TabController controller=new TabController();
        pageViewModel=pVM;
        root=view;
        return controller;

    }

    @Override
    public void onChanged(Integer tabNum) {
        ArrayList data= new ArrayList();
        if(previousTab!=tabNum) {
            Log.i("INFO_LOGGING", "Tab Selected confirm: "+tabNum);
            switch (tabNum){
                case 0:
                    Log.i("INFO_LOGGING","loading Drivers");
                    data=pageViewModel.loadDriverPage(this.root);
                    break;

                case 1:
                    Log.i("INFO_LOGGING","loading Constructors");
                    data=pageViewModel.loadConstructorPage(this.root);
                    break;
            }
            Log.i("INFO_LOGGING", "data refresh");
            dataToUI_ListView(data);
        }
        previousTab=tabNum;


    }
    public void dataToUI_ListView(ArrayList data){
        Log.i("INFO_LOGGING","Posting Data to UI: "+data);
        MyListAdapter adapter= new MyListAdapter(root.getContext(), R.layout.activity_list,data);
        ListView simpleList = (ListView) root.findViewById(R.id.id_listView);
        simpleList.setAdapter(adapter);
    }


}
