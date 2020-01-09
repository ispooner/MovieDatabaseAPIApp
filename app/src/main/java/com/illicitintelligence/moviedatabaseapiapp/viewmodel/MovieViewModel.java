package com.illicitintelligence.moviedatabaseapiapp.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.illicitintelligence.moviedatabaseapiapp.model.details.MovieDetails;
import com.illicitintelligence.moviedatabaseapiapp.model.search.Page;
import com.illicitintelligence.moviedatabaseapiapp.model.search.Result;
import com.illicitintelligence.moviedatabaseapiapp.network.MovieRetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends AndroidViewModel {

    private MovieRetrofitInstance retrofitInstance = new MovieRetrofitInstance();

    private MutableLiveData<List<Result>> resultLiveData = new MutableLiveData<>();

    private MutableLiveData<MovieDetails> detailsLiveData = new MutableLiveData<>();

    public MovieViewModel(@NonNull Application application) {
        super(application);
    }

    public void getResults(String query) {
        retrofitInstance.getResultsPage(query, 1).enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                if(response.body() == null) {
                    Toast.makeText(getApplication().getApplicationContext(), "Body is null: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG_R", call.request().url().toString());
                }
                else {
                    Log.d("TAG_R", "onResponse: Posting resultData");
                    resultLiveData.postValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                Toast.makeText(getApplication().getApplicationContext(), "Failed to retrieve page", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDetails(Integer id) {
        retrofitInstance.getMovieDetails(id).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                detailsLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Toast.makeText(getApplication().getApplicationContext(), "Failed to retrieve movie details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public MutableLiveData<List<Result>> getResultLiveData() {
        return resultLiveData;
    }

    public MutableLiveData<MovieDetails> getDetailsLiveData() {
        return detailsLiveData;
    }
}
