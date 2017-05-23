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
import com.superpau.favoritetoys.Model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<Movie> movieList;
    private Context context;
    int selectedPos;
    private Picasso mPicasso;

    public DataAdapter(Context context, List<Movie> movies) {
        this.movieList = movies;
        this.context = context;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {
        final Movie ver = movieList.get(i);
        viewHolder.tv_android.setText(movieList.get(i).getTitle());
        viewHolder.tv_android2.setText(String.valueOf(movieList.get(i).getVoteAverage()));
        mPicasso.with(context)
                .load(movieList.get(i).getPosterPath())
                .resize(240, 300)
                .error(R.mipmap.ic_launcher)
                .into(viewHolder.img_android);


        viewHolder.setClickListener(new UlahDibuka() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("text",ver.getTitle());
                i.putExtra("image",ver.getPosterPath());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv_android, tv_android2;
        private ImageView img_android;
        UlahDibuka ulahpokonamah;

        public ViewHolder(View view) {
            super(view);

            tv_android = (TextView)view.findViewById(R.id.tv_android);
            tv_android2 = (TextView)view.findViewById(R.id.tv_android2);
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
