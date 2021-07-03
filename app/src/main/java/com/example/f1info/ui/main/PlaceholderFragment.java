package com.example.f1info.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
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
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    ArrayList<String> listString = new ArrayList<String>();

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
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
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        pageViewModel.getInt().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer tabNum) {
                Log.i("TabNum",tabNum+"");
                try {
                    switch (tabNum) {
                        case 1:
                            pageViewModel.loadDriverPage(root,getActivity());
                            break;

                        case 2:
                            Log.i("TabNumm",tabNum+"");
                            pageViewModel.loadConstructorPage(root, getActivity());
                            break;

                        case 3:
                            pageViewModel.loadSchedulingPage(root,getActivity());
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });




        return root;
    }


}