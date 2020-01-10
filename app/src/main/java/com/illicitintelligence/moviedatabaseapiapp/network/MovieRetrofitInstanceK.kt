package com.illicitintelligence.moviedatabaseapiapp.network

import com.illicitintelligence.moviedatabaseapiapp.global.API_KEY
import com.illicitintelligence.moviedatabaseapiapp.global.Constants
import com.illicitintelligence.moviedatabaseapiapp.model.details.MovieDetails
import com.illicitintelligence.moviedatabaseapiapp.model.search.Page
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRetrofitInstanceK {

    private val BASE_URL = "https://api.themoviedb.org/"

    private var movieRetriever: MovieDatabaseRetrieverK =
            createMovieDatabaseRetriever(getInstance())

    private fun getInstance(): Retrofit {
        val client = OkHttpClient.Builder()
                .build()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }

    private fun createMovieDatabaseRetriever(retrofit: Retrofit): MovieDatabaseRetrieverK {
        return retrofit.create(MovieDatabaseRetrieverK::class.java)
    }

    fun getMovieDetails(id: Int?): Call<MovieDetails> {
        return movieRetriever.getDetails(id, API_KEY)
    }

    fun getResultsPage(query: String, page: Int?): Call<Page> {
        return movieRetriever.getResults(API_KEY, "en-US", query, page, false)
    }

}