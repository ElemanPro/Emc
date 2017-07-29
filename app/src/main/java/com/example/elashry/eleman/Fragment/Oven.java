package com.example.elashry.eleman.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elashry.eleman.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Oven extends Fragment {


    public Oven() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.oven, container, false);
    }

}
