package com.illicitintelligence.moviedatabaseapiapp.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.illicitintelligence.moviedatabaseapiapp.R;
import com.illicitintelligence.moviedatabaseapiapp.adapter.MovieItemAdapter;
import com.illicitintelligence.moviedatabaseapiapp.model.search.Result;
import com.illicitintelligence.moviedatabaseapiapp.viewmodel.MovieViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements MovieItemAdapter.MovieDelegate {

    MovieViewModel viewModel;

    Observer<List<Result>> resultObserver;

    RecyclerView rv;

    DescriptionFragment descriptionFragment = new DescriptionFragment();

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        resultObserver = new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                setupRV(results);
            }
        };

        viewModel.getResultLiveData().observe(this, resultObserver);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.search_movie_recycler);

        final View v = view;
        final EditText query = view.findViewById(R.id.search_searchBar);
        final TextView title = view.findViewById(R.id.search_title_textView);

        final Button search = view.findViewById(R.id.search_search_button);
        final Button submit = view.findViewById(R.id.search_submit_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setVisibility(View.INVISIBLE);
                search.setVisibility(View.INVISIBLE);
                query.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG_S", "submit: clicked");
                viewModel.getResults(query.getText().toString().trim());
            }
        });

    }

    private void setupRV(List<Result> results) {
        Log.d("TAG_S", "setupRV: setting up");

        MovieItemAdapter adapter = new MovieItemAdapter(results, this);

        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void movieCallback(Result result) {

        Bundle bundle = new Bundle();
        bundle.putInt("movie_id", result.getId().intValue());
        descriptionFragment.setArguments(bundle);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.search_DetailFrame, descriptionFragment)
                .addToBackStack(descriptionFragment.getTag())
                .commit();
    }
}
