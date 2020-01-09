package com.illicitintelligence.moviedatabaseapiapp.network;

import com.google.gson.Gson;
import com.illicitintelligence.moviedatabaseapiapp.global.Constants;
import com.illicitintelligence.moviedatabaseapiapp.model.details.MovieDetails;
import com.illicitintelligence.moviedatabaseapiapp.model.search.Page;
import com.illicitintelligence.moviedatabaseapiapp.model.search.Result;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRetrofitInstance {

    private final String BASE_URL = "https://api.themoviedb.org/";

    private MovieDatabaseRetriever movieRetriever;

    public MovieRetrofitInstance() {
        movieRetriever = createMovieDatabaseRetriever(getInstance());
    }

    private Retrofit getInstance() {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        return new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    private MovieDatabaseRetriever createMovieDatabaseRetriever(Retrofit retrofit) {
        return retrofit.create(MovieDatabaseRetriever.class);
    }

    public Call<MovieDetails> getMovieDetails(Integer id) {
        return movieRetriever.getDetails(id, Constants.API_KEY);
    }

    public Call<Page> getResultsPage(String query, Integer page) {
        return movieRetriever.getResults(Constants.API_KEY, "en-US", query, page, false);
    }

}
