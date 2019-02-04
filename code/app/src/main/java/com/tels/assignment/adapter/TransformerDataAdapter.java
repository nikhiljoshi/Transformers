package com.tels.assignment.adapter;

/**
 * Created by Nikhil Joshi
 *
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tels.assignment.R;
import com.tels.assignment.model.Transformer;

import java.util.List;


/**
 */
public class TransformerDataAdapter extends RecyclerView.Adapter<TransformerDataAdapter.ViewHolder> {
    private List<Transformer> mTransformerList;
    private Context mContext;


    /* you provide access to all the views for a data item in a view holder*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtRating;
        public TextView txtStrength;
        public TextView txtCourage;
        public  TextView txtSkill;
        public  ImageView imgTeam;

        public ViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txt_name);
            txtRating = (TextView) view.findViewById(R.id.txt_rating);
            txtStrength = (TextView) view.findViewById(R.id.txt_strength);
            txtCourage = (TextView) view.findViewById(R.id.txt_courage);
            txtSkill = (TextView) view.findViewById(R.id.txt_skill);
            imgTeam = (ImageView) view.findViewById(R.id.img_team);

        }
    }


    public TransformerDataAdapter(List<Transformer> listData, Context context) {
        mTransformerList = listData;
        mContext=context;

    }

    /***
     *  Create new views (invoked by the layout manager)*/
    @Override
    public TransformerDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_transformer_stat, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    /**
     *  Replace the contents of a view (invoked by the layout manager)*/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        /***
         *  get element from dataset at this position
         replace the contents of the view with that element*/

        holder.txtName.setText(String.valueOf(mTransformerList.get(position).getName()));
        holder.txtRating.setText(String.valueOf(mTransformerList.get(position).getRank()));
        holder.txtStrength.setText(String.valueOf(mTransformerList.get(position).getStrength()));
        holder.txtSkill.setText(String.valueOf(mTransformerList.get(position).getSkill()));
        holder.txtCourage.setText(String.valueOf(mTransformerList.get(position).getCourage()));

        Glide.with(mContext).load(mTransformerList.get(position).getTeamIcon()).into(holder.imgTeam);

    }

    /***
     *  Return the size of your dataset */
    @Override
    public int getItemCount() {
        return mTransformerList.size();
    }

}