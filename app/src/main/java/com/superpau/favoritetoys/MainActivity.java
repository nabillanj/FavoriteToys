package com.superpau.favoritetoys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.superpau.favoritetoys.Model.Movie;
import com.superpau.favoritetoys.Model.Result;
import com.superpau.favoritetoys.REST.RestApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String android_version_names[] = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"
    };

    private final String android_image_urls[] = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png",
            "http://api.learn2crack.com/android/images/honey.png",
            "http://api.learn2crack.com/android/images/icecream.png",
            "http://api.learn2crack.com/android/images/jellybean.png",
            "http://api.learn2crack.com/android/images/kitkat.png",
            "http://api.learn2crack.com/android/images/lollipop.png",
            "http://api.learn2crack.com/android/images/marshmallow.png"
    };

    private RecyclerView recyclerView;
    private List<Movie> movieList = new ArrayList<>();
    private final static String BASE_URL = "https://api.themoviedb.org/3/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<AndroidVersion> androidVersions = prepareData();

        getNowPlaying();

    }

    private void getNowPlaying(){
        movieList.clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestApi restApi = retrofit.create(RestApi.class);
        final Call<Result> resultCall = restApi.getNowPlaying(getResources().getString(R.string.API_KEY));
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();

                Movie movie;
                for (int i=0; i< result.getResults().size(); i++){
                    movie = new Movie();

                    String title = result.getResults().get(i).getTitle();
                    String poster = result.getResults().get(i).getPosterPath();
                    Double vote = result.getResults().get(i).getVoteAverage();

                    movie.setTitle(title);
                    movie.setPosterPath("http://image.tmdb.org/t/p/w185"+poster);
                    movie.setVoteAverage(vote);


                    movieList.add(movie);
                }

                if (movieList.size() > 0){
                    DataAdapter adapter = new DataAdapter(getApplicationContext(), movieList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    private void getPopularMovie(){
        movieList.clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestApi restApi = retrofit.create(RestApi.class);
        final Call<Result> resultCall = restApi.getPopular(getResources().getString(R.string.API_KEY));
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();

                Movie movie;
                for (int i=0; i< result.getResults().size(); i++){
                    movie = new Movie();

                    String title = result.getResults().get(i).getTitle();
                    String poster = result.getResults().get(i).getPosterPath();
                    Double vote = result.getResults().get(i).getVoteAverage();

                    movie.setTitle(title);
                    movie.setPosterPath("http://image.tmdb.org/t/p/w185"+poster);
                    movie.setVoteAverage(vote);


                    movieList.add(movie);
                }

                if (movieList.size() > 0){
                    DataAdapter adapter = new DataAdapter(getApplicationContext(), movieList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
    private void getUpComing(){
        movieList.clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestApi restApi = retrofit.create(RestApi.class);
        final Call<Result> resultCall = restApi.getUpComing(getResources().getString(R.string.API_KEY));
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();

                Movie movie;
                for (int i=0; i< result.getResults().size(); i++){
                    movie = new Movie();

                    String title = result.getResults().get(i).getTitle();
                    String poster = result.getResults().get(i).getPosterPath();
                    Double vote = result.getResults().get(i).getVoteAverage();

                    movie.setTitle(title);
                    movie.setPosterPath("http://image.tmdb.org/t/p/w185"+poster);
                    movie.setVoteAverage(vote);


                    movieList.add(movie);
                }

                if (movieList.size() > 0){
                    DataAdapter adapter = new DataAdapter(getApplicationContext(), movieList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_now_playing){
            getNowPlaying();
        }else if (id == R.id.menu_coming_soon){
        }else if(id == R.id.menu_popular){
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<AndroidVersion> prepareData(){

        ArrayList<AndroidVersion> android_version = new ArrayList<>();
        for(int i=0;i<android_version_names.length;i++){
            AndroidVersion androidVersion = new AndroidVersion();
            androidVersion.setAndroid_version_name(android_version_names[i]);
            androidVersion.setAndroid_image_url(android_image_urls[i]);
            android_version.add(androidVersion);
        }
        return android_version;
    }

}
