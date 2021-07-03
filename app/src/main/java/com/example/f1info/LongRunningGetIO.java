package com.example.f1info;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.f1info.ui.main.StandingsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LongRunningGetIO extends AsyncTask<Void, Void, String> {
    private String urlString="";
    private JSONArray arr;
    private Activity activity;
    private View root;
    private String key;
    public LongRunningGetIO(String key,String url, View root, Activity activity){
        this.activity=activity;
        this.root=root;
        urlString=url;
        this.key=key;
    }



    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Log.i("Response Code: ",con.getResponseCode()+"");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            String data = "";
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                data += inputLine;
            }
            in.close();
            con.disconnect();
            Log.i("Data from API: ",data);
//            Log.i("JSON",new JSONObject(data)+"");
            return (data);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    protected void onPostExecute(String results) {
        switch(key) {
            case "driver":
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            driverProcessing(results);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;

            case "constructor":
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            constructorProcessing(results);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;


        }


    }

    private void constructorProcessing(String result) throws JSONException {
        Log.i("Tag","constructorProcessing");
        JSONObject json= new JSONObject(result);
        arr=json.getJSONObject("MRData").getJSONObject("StandingsTable").getJSONArray("StandingsLists").getJSONObject(0).getJSONArray("ConstructorStandings");
        Log.i("Parsed JSON Constructor", arr + "");
        ArrayList<String[]> data=new ArrayList();
        for(int i=0; i<arr.length(); i++){
            JSONObject ob= (JSONObject) arr.get(i);
            String [] individualData=new String[]{ob.getString("position"),ob.getJSONObject("Constructor").getString("name"),ob.getString("points")};
            data.add(individualData);
        }
        dataToUI(data);
    }

    private void driverProcessing(String result) throws JSONException {
        JSONObject json = new JSONObject(result);
        arr = json.getJSONObject("MRData").getJSONObject("StandingsTable").getJSONArray("StandingsLists").getJSONObject(0).getJSONArray("DriverStandings");
        Log.i("Parsed JSON", arr + "");
        ArrayList data=new ArrayList();
        for(int i=0; i<arr.length(); i++){
            JSONObject ob= (JSONObject) arr.get(i);
            String [] individualData=new String []{ob.getString("position"),ob.getJSONObject("Driver").getString("familyName"),ob.getString("points")};
            data.add(individualData);
        }
        dataToUI(data);

    }

    public void dataToUI(ArrayList data){
        Log.d("Tag","dataToUI");
        MyListAdapter adapter= new MyListAdapter(root.getContext(), R.layout.activity_list,data);
        ListView simpleList = (ListView) root.findViewById(R.id.id_listView);
        simpleList.setAdapter(adapter);
    }

    public void expandable(){
//        ExpandableListView expandableListView = (ExpandableListView) root.findViewById(R.id.id_listView);
    }
}