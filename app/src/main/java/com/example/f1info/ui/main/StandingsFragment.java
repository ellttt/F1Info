package com.example.f1info.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.f1info.MyListAdapter;
import com.example.f1info.R;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class StandingsFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private View root;

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
        pageViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);
        int index = 0;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        this.root = inflater.inflate(R.layout.fragment_main, container, false);
        ArrayList data=pageViewModel.loadDriverPage(this.root);
        dataToUI_ListView(data);
        pageViewModel.getIndex().observe(this, TabController.newInstance(pageViewModel,this.root));

        return this.root;
    }

    public void dataToUI_ListView(ArrayList data){
        Log.d("Tag","dataToUI");
        MyListAdapter adapter= new MyListAdapter(root.getContext(), R.layout.activity_list,data);
        ListView simpleList = (ListView) root.findViewById(R.id.id_listView);
        simpleList.setAdapter(adapter);
    }





}