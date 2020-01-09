package com.illicitintelligence.moviedatabaseapiapp.network;

import com.illicitintelligence.moviedatabaseapiapp.model.details.MovieDetails;
import com.illicitintelligence.moviedatabaseapiapp.model.search.Page;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDatabaseRetriever {

    @GET("/3/search/movie")
    Call<Page> getResults(
            @Query("api_key") String Key,
            @Query("language") String language,
            @Query("query") String query,
            @Query("page") Integer page,
            @Query("include_adult") Boolean include_adult
    );

    @GET("/3/movie/{movie_id}")
    Call<MovieDetails> getDetails(
            @Path("movie_id") Integer movie_id,
            @Query("api_key") String key
    );
}



 // https://api.themoviedb.org/3/search/movie?api_key=3bdf73c4d7148be151eed040c0aef053&language=en-US&query=hell&page=1&include_adult=false