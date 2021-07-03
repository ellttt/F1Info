package com.example.f1info;

import android.util.Log;

import com.example.f1info.ui.main.PageViewModel;
import com.example.f1info.ui.main.SectionsPagerAdapter;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void urlConnection() throws JSONException {
        PageViewModel pageViewModel= new PageViewModel();
//        String json=pageViewModel.getJSON("http://ergast.com/api/f1/2021/driverStandings.json");
//        System.out.println(json);

    }
}