package com.example.f1info;

import android.util.Log;

import com.example.f1info.ui.main.PageViewModel;
import com.example.f1info.ui.main.SectionsPagerAdapter;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @InjectMocks
    PageViewModel pageViewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void urlConnection() throws JSONException {
//        PageViewModel pageViewModel= new PageViewModel();
//        String json=pageViewModel.getJSON("http://ergast.com/api/f1/2022/driverStandings.json");
//        System.out.println(json);

    }

    @Test
    public void testDate() {
        ZonedDateTime dateTime = ZonedDateTime.parse("2011-12-03T10:15:30Z");
        StringBuilder str =new StringBuilder();
        str.append("test");
        str.append("test2");
        System.out.println(str);
    }

    @Test
    public void testExecutorService(){
//        Mockito.doNothing().when(Log).d(Mockito.anyString(),Mockito.anyString());
        pageViewModel.loadDriverPage(Mockito.anyObject());
    }

}