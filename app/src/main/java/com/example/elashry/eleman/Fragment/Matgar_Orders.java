package com.example.elashry.eleman.Fragment;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.elashry.eleman.R;

/**
 * Created by Delta on 22/08/2017.
 */

public class Matgar_Orders extends Fragment {
    private LinearLayout pbc;
    private TextView no_order_txt;
    private RecyclerView mRecyclerView;
    Context mContext;
    private ProgressBar prog_bar;
    private SwipeRefreshLayout mRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matgar_orders,container,false);
        init_View(view);
        return view;
    }

    private void init_View(View view) {
        mContext       = view.getContext();
        pbc = (LinearLayout) view.findViewById(R.id.progressBar_container);
        pbc.setVisibility(View.VISIBLE);
        prog_bar       = (ProgressBar) view.findViewById(R.id.o_progBar);
        prog_bar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(mContext,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        no_order_txt   = (TextView) view.findViewById(R.id.no_order_txt);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swap_refresh);
        mRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext,R.color.colorPrimary));
        mRecyclerView  = (RecyclerView) view.findViewById(R.id.order_RecyView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

    }
}
