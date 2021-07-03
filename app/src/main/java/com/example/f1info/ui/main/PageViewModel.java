package com.example.f1info.ui.main;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.f1info.LongRunningGetIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PageViewModel extends ViewModel {

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

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Integer> getInt() {
        return mInt;
    }

    public void getJSON(String key,String url, View root, Activity activity) {
        LongRunningGetIO runner= new LongRunningGetIO(key,url,root, activity);
        runner.execute();

    }

    public void loadDriverPage(View root, Activity activity) throws JSONException {
        ArrayList<String> driverNames = new ArrayList<>();
        getJSON("driver","http://ergast.com/api/f1/2021/driverStandings.json", root, activity);

    }

    public void loadConstructorPage(View root, Activity activity) {

        getJSON("constructor","https://ergast.com/api/f1/2021/constructorStandings.json",root, activity);
    }

    public void loadSchedulingPage(View root, Activity activity){

    }

    public void getJSONNew() {
    }


}