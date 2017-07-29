package com.example.elashry.eleman.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.elashry.eleman.Activities.Category;
import com.example.elashry.eleman.Activities.Detail;
import com.example.elashry.eleman.Activities.ItemCategory;
import com.example.elashry.eleman.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CallsFragment extends Fragment {


    public CallsFragment() {
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
        return inflater.inflate(R.layout.fragment_calls, container, false);


    }

}
