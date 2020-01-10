package com.illicitintelligence.moviedatabaseapiapp.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.illicitintelligence.moviedatabaseapiapp.model.details.MovieDetails
import com.illicitintelligence.moviedatabaseapiapp.model.search.Page
import com.illicitintelligence.moviedatabaseapiapp.model.search.Result
import com.illicitintelligence.moviedatabaseapiapp.network.MovieRetrofitInstanceK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModelK(application: Application) : AndroidViewModel(application) {

    private val retrofitInstance = MovieRetrofitInstanceK()

    val resultLiveData = MutableLiveData<List<Result>>()

    val detailsLiveData = MutableLiveData<MovieDetails>()

    fun getResults(query: String) {
        retrofitInstance.getResultsPage(query, 1).enqueue(object : Callback<Page> {
            override fun onResponse(call: Call<Page>, response: Response<Page>) {
                if (response.body() == null) {
                    Toast.makeText(getApplication<Application>().applicationContext, "Body is null: " + response.code(), Toast.LENGTH_SHORT).show()
                    Log.d("TAG_R", call.request().url().toString())
                } else {
                    Log.d("TAG_R", "onResponse: Posting resultData")
                    resultLiveData.postValue(response.body()!!.results)
                }
            }

            override fun onFailure(call: Call<Page>, t: Throwable) {
                Toast.makeText(getApplication<Application>().applicationContext, "Failed to retrieve page", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getDetails(id: Int?) {
        retrofitInstance.getMovieDetails(id).enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                detailsLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Toast.makeText(getApplication<Application>().applicationContext, "Failed to retrieve movie details", Toast.LENGTH_SHORT).show()
            }
        })
    }
}