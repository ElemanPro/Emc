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
public class Washer extends Fragment {


    public Washer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        Button b1=(Button) getActivity().findViewById(R.id.b1);
//
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i =new Intent(getContext(),Detail.class);
//                startActivity(i);
//            }
//        });
        return inflater.inflate(R.layout.washer, container, false);


    }

}
