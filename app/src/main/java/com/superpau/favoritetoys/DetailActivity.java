package com.superpau.favoritetoys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private TextView tVtitle;
    private ImageView poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tVtitle = (TextView)findViewById(R.id.title);
        poster = (ImageView) findViewById(R.id.poster);

        Intent i = getIntent();
        tVtitle.setText(i.getStringExtra("text"));
        Picasso.with(this).load(i.getStringExtra("image")).resize(300, 480).error(R.mipmap.ic_launcher).into(poster);
    }
}
