package com.example.f1info.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.f1info.R;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class StandingsFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    ArrayList<String> listString = new ArrayList<String>();

    public static StandingsFragment newInstance(int index) {
        StandingsFragment fragment = new StandingsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
        if(pageViewModel==null)
            Log.i("Tag","Fail");
        else
            pageViewModel.getJSONNew();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        TabController tabController=new TabController(inflater,container);
        pageViewModel.getInt().observe(this, tabController);
        Log.d("Tag","OnViewTabs");

        return tabController.root;
    }
    class TabController implements Observer<Integer> {
        View root;
        private LayoutInflater inflater;
        private ViewGroup container;
        public TabController(LayoutInflater inflater, ViewGroup container) {
            this.inflater=inflater;
            this.container=container;
            root = inflater.inflate(R.layout.fragment_main, container, false);

        }

        @Override
        public void onChanged(Integer tabNum) {
            Log.i("TabNum",tabNum+"");
            try {
                switch (tabNum) {
                    case 1:
                        pageViewModel.loadDriverPage(root,getActivity());
                        break;

                    case 2:
                        pageViewModel.loadConstructorPage(root, getActivity());
                        break;
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
        }

        }



}