package com.illicitintelligence.moviedatabaseapiapp.network

import com.illicitintelligence.moviedatabaseapiapp.model.details.MovieDetails
import com.illicitintelligence.moviedatabaseapiapp.model.search.Page
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDatabaseRetrieverK {

    @GET("/3/search/movie")
    abstract fun getResults(
            @Query("api_key") Key: String,
            @Query("language") language: String,
            @Query("query") query: String,
            @Query("page") page: Int?,
            @Query("include_adult") include_adult: Boolean?
    ): Call<Page>

    @GET("/3/movie/{movie_id}")
    abstract fun getDetails(
            @Path("movie_id") movie_id: Int?,
            @Query("api_key") key: String
    ): Call<MovieDetails>

}