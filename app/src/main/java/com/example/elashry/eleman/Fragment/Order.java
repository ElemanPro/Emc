package com.example.elashry.eleman.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elashry.eleman.R;

/**
 * Created by Delta on 06/08/2017.
 */

public class Order extends Fragment {

    private RecyclerView mRecyclerView;
    Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order,container,false);
        init_View(view);
        return view;
    }

    private void init_View(View view) {
        mContext = view.getContext();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.order_RecyView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }
}
