package com.semicolon.emcmisir.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.semicolon.emcmisir.Activities.Matgar_product_details;
import com.semicolon.emcmisir.Activities.Order;
import com.semicolon.emcmisir.Activities.Zooming_Image;
import com.semicolon.emcmisir.App_URL;
import com.semicolon.emcmisir.Model.Image_details_Model;
import com.semicolon.emcmisir.Model.MatgarModel;
import com.semicolon.emcmisir.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class MatgarAdapter extends RecyclerView.Adapter<MatgarAdapter.MyViewHolder> {

    private Context mContext;
    private List<MatgarModel> matgarModelList;
    private LayoutInflater inflater;




    public MatgarAdapter(Context mContext, List<MatgarModel> matgarModelList) {
        this.mContext = mContext;
        this.matgarModelList = matgarModelList;
        inflater =LayoutInflater.from(mContext);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =inflater.inflate(R.layout.matgar_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.pro_name.setText(matgarModelList.get(position).getProduct_name().toString());
        if(!matgarModelList.get(position).getProduct_price().isEmpty())
        {
            holder.pro_price.setText(matgarModelList.get(position).getProduct_price().toString()+" "+"جنيه");
        }
        else
            {

            }

        holder.date.setText("منذ"+" "+matgarModelList.get(position).getDate().toString());
        Picasso.with(mContext).load(App_URL.image_url+matgarModelList.get(position).getProduct_image().toString()). fit().into(holder.pro_image);
        holder.pro_image.setVisibility(View.VISIBLE);
        holder.mBar.setVisibility(View.GONE);
        holder.pro_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Zooming_Image.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Image_details_Model model = new Image_details_Model(matgarModelList.get(position).getProduct_name()+" "+matgarModelList.get(position).getProduct_price().toString(),matgarModelList.get(position).getProduct_image().toString());
                intent.putExtra("image_details",model);
                intent.putExtra("flag","1");
                mContext.startActivity(intent);
            }
        });
       // new asyn_task(holder).execute(matgarModelList.get(position).getProduct_image().toString());
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow,holder);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view,MyViewHolder holder) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(holder));
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        MyViewHolder holder;
        public MyMenuItemClickListener(MyViewHolder holder) {
            this.holder = holder;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_regiter:

                    Intent i =new Intent(mContext, Order.class);
                    MatgarModel matgarModel1 = matgarModelList.get(holder.getLayoutPosition());
                    i.putExtra("matgar_item_data",matgarModel1);
                    i.putExtra("flag","1");
                    mContext.startActivity(i);
                    return true;
                case R.id.action_detail:
                    Intent intent = new Intent(mContext, Matgar_product_details.class);
                    MatgarModel matgarModel = matgarModelList.get(holder.getLayoutPosition());
                    intent.putExtra("dev_data",matgarModel);
                    mContext.startActivity(intent);
                    return true;
                default:
            }
            return false;
        }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  pro_name, pro_price,date;
        public ImageView overflow,pro_image;
        public ProgressBar mBar;
        public MyViewHolder(View view) {
            super(view);
            pro_name  = (TextView) view.findViewById(R.id.matgar_product_name);
            pro_price = (TextView) view.findViewById(R.id.matgar_product_price);
            pro_image = (ImageView) view.findViewById(R.id.matgar_product_image);
            date      = (TextView) view.findViewById(R.id.matgar_date);
            mBar      = (ProgressBar) view.findViewById(R.id.matgar_progBar);
            overflow  = (ImageView) view.findViewById(R.id.overflow);
            mBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(mContext,R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);


        }
    }

    @Override
    public int getItemCount() {
        return matgarModelList.size();
    }

}
