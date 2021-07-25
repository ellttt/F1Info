package com.example.f1info;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.f1info.ui.main.PageViewModel;
import com.example.f1info.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    public int tabSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                1);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // Permission Granted
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Internet Connected", Toast.LENGTH_SHORT).show();

                    SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
                    ViewPager viewPager = findViewById(R.id.id_fragment_container);
                    viewPager.setAdapter(sectionsPagerAdapter);

                    TabLayout tabs = findViewById(R.id.tabs);
                    tabs.setupWithViewPager(viewPager);
                    Log.d("TabDebug","Tab selected on open: "+tabs.getSelectedTabPosition());

                    PageViewModel pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);

                    MyTabListener tabListener = new MyTabListener(pageViewModel);
                    tabs.addOnTabSelectedListener(tabListener);

                } else {
                    // Permission Denied!
                    // Disable the functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }


    class MyTabListener implements TabLayout.OnTabSelectedListener {
        private PageViewModel pageViewModel;

        public MyTabListener(PageViewModel pageViewModel) {

            this.pageViewModel = pageViewModel;

        }

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            Log.i("INFO_LOGGING", "The tab selected: " + tab.getPosition());

            tabSelected = tab.getPosition();
            pageViewModel.setIndex(tabSelected);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }
}