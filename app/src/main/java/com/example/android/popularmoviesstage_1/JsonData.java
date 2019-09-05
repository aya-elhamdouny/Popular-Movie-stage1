package com.example.android.popularmoviesstage_1;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonData {


    public static Movies[] makeMoviesDataToArray(MainActivity mainActivity, String moviesJsonResults) throws JSONException
    {

        final String BASE_URL = "https://image.tmdb.org/t/p/";
        final String RESULTS = "results";
        final String TITLE = "original_title";
        final String POSTER = "poster_path";
        final String OVERVIEW = "overview";
        final String DATE = "release_date";

        JSONObject moviesJson = new JSONObject(moviesJsonResults);
        JSONArray resultsArray = moviesJson.getJSONArray(RESULTS);

        Movies[] movies;

        movies = new Movies[resultsArray.length()];

        for (int i = 0; i < resultsArray.length(); i++) {
            String poster_path , original_title , release_date , overview , result ;

            movies[i] = new Movies();

            original_title = resultsArray.getJSONObject(i).optString(TITLE);
            poster_path = resultsArray.getJSONObject(i).optString(POSTER);
            overview = resultsArray.getJSONObject(i).optString(OVERVIEW);
            release_date = resultsArray.getJSONObject(i).optString(DATE);



            JSONObject movieInfo = resultsArray.getJSONObject(i);


            movies[i].setTILTE(movieInfo.getString(TITLE));
            movies[i].setPOSTER(BASE_URL + poster_path);
            movies[i].setOVERVIEW(movieInfo.getString(OVERVIEW));
            movies[i].setDATE(movieInfo.getString(DATE));
        }
        return movies;
    }

}
