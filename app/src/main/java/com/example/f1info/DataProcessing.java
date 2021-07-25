package com.example.f1info;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class DataProcessing implements Callable {

    private String key;
    private JSONArray arr;
    public DataProcessing(String key){
        this.key=key;

    }

    @Override
    public ArrayList call() throws Exception {
        ArrayList data = new ArrayList();
        String result;
        switch(key){
            case "driver":
                result=urlCall("https://ergast.com/api/f1/2021/driverStandings.json");
                try {
                    data=driverProcessing(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case "constructor":
                result=urlCall("https://ergast.com/api/f1/2021/constructorStandings.json");
                data=constructorProcessing(result);
                break;

            case "scheduling":
                result=urlCall("https://ergast.com/api/f1/current.json");
                data=schedulingProcessing(result);

        }
        return data;
    }


    private String urlCall(String urlString){
        Log.i("INFO_LOGGING","Making HttpURLConnection");
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

    private ArrayList driverProcessing(String result) throws JSONException {
        JSONObject json = new JSONObject(result);
        arr = json.getJSONObject("MRData").getJSONObject("StandingsTable").getJSONArray("StandingsLists").getJSONObject(0).getJSONArray("DriverStandings");
        Log.i("Parsed JSON", arr + "");
        ArrayList data=new ArrayList();
        for(int i=0; i<arr.length(); i++){
            JSONObject ob= (JSONObject) arr.get(i);
            String [] individualData=new String []{ob.getString("position"),ob.getJSONObject("Driver").getString("familyName"),ob.getString("points")};
            data.add(individualData);
        }
        return data;
    }

    private ArrayList constructorProcessing(String result) throws JSONException {

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
        return data;
    }
    private ArrayList schedulingProcessing(String result) throws JSONException {
        Log.d("qwe","schedulingProcess");
        JSONObject json= new JSONObject(result);
        arr=json.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
        Log.i("Parsed JSON", arr + "");
        ArrayList data=new ArrayList();
        for(int i=0; i<arr.length(); i++){
            JSONObject ob= (JSONObject) arr.get(i);
            String [] individualData=new String []{ob.getString("round"),ob.getString("raceName"),ob.getString("date"),ob.getJSONObject("Circuit").toString(),ob.getString("time")};
            data.add(individualData);
        }
        return data;
//        dataToUI_ExpandingListView(data);
    }
}