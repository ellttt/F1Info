package com.example.f1info.ui.main;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.f1info.DataProcessing;
import com.example.f1info.MyExpandableListAdapter;
import com.example.f1info.MyListAdapter;
import com.example.f1info.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PageViewModel extends ViewModel {

    private View root;
    private Activity activity;
    private ArrayList driverData;
    private ArrayList constructorData;
    private ArrayList schedulingData;

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });
    private LiveData<Integer> mInt = Transformations.map(mIndex, new Function<Integer, Integer>() {
        @Override
        public Integer apply(Integer input) {
            return input;
        }
    });



    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public MutableLiveData<Integer> getIndex(){
        return mIndex;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public ArrayList loadDriverPage(View root){
        Log.i("INFO_LOGGING","loadDriverPage");

        this.root=root;

        if (driverData == null) {
            Log.d("ConcurrencySwitch","driverData null");
            driverData=getAndProcessData("driver");
        }

        Log.d("ConcurrencySwitch","Data: "+driverData);
//        dataToUI_ListView(driverData);
        Log.d("ConcurrencySwitch","Data sent to UI");
        return driverData;


    }

    public ArrayList loadConstructorPage(View root) {
        Log.i("INFO_LOGGING","loadConstructorPage");

        this.root=root;

        if (constructorData == null) {
            Log.d("ConcurrencySwitch","constructorData null");
            constructorData=getAndProcessData("constructor");
        }

        Log.d("ConcurrencySwitch","Data: "+constructorData);
//        dataToUI_ListView(constructorData);
        Log.d("ConcurrencySwitch","Data sent to UI");
        return constructorData;
    }

    public ArrayList loadSchedulingPage(View root){
        this.root=root;
        if(schedulingData==null){
            schedulingData=getAndProcessData("scheduling");
        }
        return schedulingData;


    }

    public void dataToUI_ListView(ArrayList data){
        Log.i("INFO_LOGGING","Posting Data to UI: "+data);
        MyListAdapter adapter= new MyListAdapter(root.getContext(), R.layout.activity_list,data);
        ListView simpleList = (ListView) root.findViewById(R.id.id_listView);
        simpleList.setAdapter(adapter);
    }

    public void dataToUI_ExpandingListView(ArrayList data){
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(root.getContext(), R.layout.activity_list,data);
        ExpandableListView expandableListView = (ExpandableListView) root.findViewById(R.id.id_expandinglistView);
        expandableListView.setAdapter(adapter);
    }

    private ArrayList getAndProcessData(String key){
        ArrayList processedData = new ArrayList();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<ArrayList> future=executorService.submit(new DataProcessing(key));
        try {
            processedData=future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        Log.d("ConcurrencySwitch","Future is done: "+future.isDone());
        return processedData;
    }




}