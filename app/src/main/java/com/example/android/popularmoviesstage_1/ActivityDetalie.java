package com.example.android.popularmoviesstage_1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityDetalie extends AppCompatActivity {
    @BindView(R.id.img_view)
    ImageView POSTER;

    @BindView(R.id.overview)
    TextView OVERVIEW ;

    @BindView(R.id.movie_name)
    TextView NAME;

    @BindView(R.id.release_data)
    TextView DATE ;

    @BindView(R.id.tv_detail_rate)
    TextView RATE;


    protected void onCreate(Bundle savedInstanceState)
    {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.dateils);

        ButterKnife.bind(this);


        String MovieImage = getIntent().getStringExtra("poster");
        String title = getIntent().getStringExtra("title");
        String Movierate = getIntent().getStringExtra("rate");
        String release = getIntent().getStringExtra("release");
        String overview = getIntent().getStringExtra("overview");




        NAME.setText(title);
        OVERVIEW.setText(overview);
        RATE.setText(Movierate);
        DATE.setText(release);
        Picasso.with(this)
                .load(MovieImage)
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(POSTER);

    }







}
