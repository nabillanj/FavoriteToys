package com.superpau.favoritetoys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by paupau on 5/20/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<AndroidVersion> android;
    private Context context;
    private Picasso mPicasso;

    public DataAdapter(Context context,ArrayList<AndroidVersion> android) {
        this.android = android;
        this.context = context;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {
        final AndroidVersion ver = android.get(i);
        viewHolder.tv_android.setText(android.get(i).getAndroid_version_name());
        mPicasso.with(context).load(android.get(i).getAndroid_image_url()).resize(240, 300).error(R.mipmap.ic_launcher).into(viewHolder.img_android);

        viewHolder.setClickListener(new UlahDibuka() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("text",ver.getAndroid_version_name());
                i.putExtra("image",ver.getAndroid_image_url());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv_android;
        private ImageView img_android;
        UlahDibuka ulahpokonamah;

        public ViewHolder(View view) {
            super(view);

            tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (ImageView) view.findViewById(R.id.img_android);

            view.setOnClickListener(this);
        }

        public void setClickListener(UlahDibuka ulahatuh) {
            this.ulahpokonamah = ulahatuh;
        }
        @Override
        public void onClick(View v) {
            ulahpokonamah.onClick(v);
        }
    }
}
