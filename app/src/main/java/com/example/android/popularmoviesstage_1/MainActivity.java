package com.example.android.popularmoviesstage_1;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.android.popularmoviesstage_1.NetworkUtality;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements MovieAdapter.ONCLICK{


    private RecyclerView mRecyclerView;
    private Movies[]  moviedata;
    private  MovieAdapter mAdapter;
    String x = "popular";
    @BindView(R.id.tv_connection_error) TextView mConnctionError ;
    @BindView(R.id.loading_page) ProgressBar mLoadingPage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById (R.id.my_recycler_view);


        ButterKnife.bind(this);

        int num = 2;
        GridLayoutManager layoutManager;
        layoutManager = new GridLayoutManager(this,num);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);

        loadData();

    }

    private void loadData() {
        String theMovieDbQueryType = x;
        showJsonDataResults();
        new FetchMovies().execute(theMovieDbQueryType);
    }


    @Override
    public void onClick(int adapterPosition) {
        Context context = this;
        Class destinationClass = ActivityDetalie.class;

        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, adapterPosition);
        intentToStartDetailActivity.putExtra("title", moviedata[adapterPosition].getTILTE());
        intentToStartDetailActivity.putExtra("poster", moviedata[adapterPosition].getPOSTER());
        intentToStartDetailActivity.putExtra("rate", moviedata[adapterPosition].getRATE());
        intentToStartDetailActivity.putExtra("release", moviedata[adapterPosition].getDATE());
        intentToStartDetailActivity.putExtra("overview", moviedata[adapterPosition].getOVERVIEW());

        startActivity(intentToStartDetailActivity);
    }


    private void showJsonDataResults() {
        mConnctionError = (TextView) findViewById(R.id.tv_connection_error);
        mConnctionError.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }


    private void showErrorMessage() {
        mConnctionError = (TextView) findViewById(R.id.tv_connection_error);
        mRecyclerView.setVisibility(View.INVISIBLE);
        mConnctionError.setVisibility(View.VISIBLE);
    }


    public class FetchMovies extends AsyncTask<String,Void,Movies[]> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingPage = (ProgressBar) findViewById(R.id.loading_page);
            mLoadingPage.setVisibility(View.VISIBLE);

        }



        @Override
        protected Movies[] doInBackground(String... params) {
            if (params.length == 0){
                return null;
            }

            String sortBy = params[0];
            URL movieRequestUrl;
            movieRequestUrl = NetworkUtality.buildUrl(sortBy);


            try {
                String jsonMovieResponse = NetworkUtality.getResponseFromHttpUrl(movieRequestUrl);

                moviedata = JsonData.makeMoviesDataToArray(MainActivity.this, jsonMovieResponse);
                return moviedata;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }


        @Override
        protected void onPostExecute(Movies[] movieData) {
            mLoadingPage.setVisibility(View.INVISIBLE);
            if (movieData != null) {
                showJsonDataResults();
                mAdapter = new MovieAdapter(movieData,MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }
            else  showErrorMessage();

            }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelected = item.getItemId();

        if (menuItemSelected == R.id.action_popular) {
            x = "popular";
            loadData();
            return true;
        }

        if (menuItemSelected == R.id.action_top_rated) {
            x = "top_rated";
            loadData();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }






}
