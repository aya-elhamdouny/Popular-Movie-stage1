package com.example.android.popularmoviesstage_1;

import android.graphics.Movie;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtality   {

    final static String BASE_URL = "http://api.themoviedb.org/3/movie";
    final static String API_KEY = "bf70bd0d200feb5b6281cd46a3bb40d2";



    public static final String  TITLE = "title",
            OVERVIEW = "overview",
            RELEASE_DATE = "release_date",
            RATING = "vote_average",
            POSTER = "poster_path";



    public static URL buildUrl(String sortBy)  {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendEncodedPath(sortBy)
                .appendQueryParameter("api_key", API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }



}