package com.example.f1info.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.f1info.MyExpandableListAdapter;
import com.example.f1info.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ExpandingListViewFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private View root;
    private ArrayList<String> listString = new ArrayList<String>();

    public static ExpandingListViewFragment newInstance(int index) {
        ExpandingListViewFragment fragment = new ExpandingListViewFragment();
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

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        this.root= inflater.inflate(R.layout.fragment_expandlistview, container, false);
        ArrayList data=pageViewModel.loadSchedulingPage(root);
        dataToUI_ExpandingListView(data);
        return this.root;
    }

    public void dataToUI_ExpandingListView(ArrayList data){
        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this.root.getContext(), R.layout.activity_list,data);
        ExpandableListView expandableListView = (ExpandableListView) this.root.findViewById(R.id.id_expandinglistView);
        expandableListView.setAdapter(adapter);
    }
}



