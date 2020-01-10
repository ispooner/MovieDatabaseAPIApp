package com.illicitintelligence.moviedatabaseapiapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.illicitintelligence.moviedatabaseapiapp.R
import com.illicitintelligence.moviedatabaseapiapp.adapter.MovieItemAdapterK
import com.illicitintelligence.moviedatabaseapiapp.model.search.Result
import com.illicitintelligence.moviedatabaseapiapp.viewmodel.MovieViewModelK
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*


class SearchFragmentK : Fragment(), MovieItemAdapterK.MovieDelegate {

    internal lateinit var viewModelK: MovieViewModelK

    internal lateinit var resultObserver: Observer<List<Result>>

    internal var descriptionFragment = DescriptionFragmentK()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModelK = ViewModelProviders.of(this).get(MovieViewModelK::class.java)

        resultObserver = Observer { results -> setupRV(results) }

        viewModelK.resultLiveData.observe(this, resultObserver)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.search_search_button.setOnClickListener {
            view.search_title_textView.setVisibility(View.INVISIBLE)
            view.search_search_button.setVisibility(View.INVISIBLE)
            view.search_searchBar.setVisibility(View.VISIBLE)
            view.search_submit_button.setVisibility(View.VISIBLE)
            Toast.makeText(context, "Kotlin", Toast.LENGTH_SHORT).show()
        }

        view.search_submit_button.setOnClickListener{
            Log.d("TAG_S", "submit: clicked")
            viewModelK.getResults(view.search_searchBar.text.toString().trim())
        }

    }

    private fun setupRV(results: List<Result>) {
        Log.d("TAG_S", "setupRV: setting up")

        val adapter = MovieItemAdapterK(results, this)

        search_movie_recycler.adapter = adapter
        search_movie_recycler.layoutManager = LinearLayoutManager(context)
    }

    override fun movieCallback(result: Result) {

        val bundle = Bundle()
        bundle.putInt("movie_id", result.id!!.toInt())
        descriptionFragment.arguments = bundle

        fragmentManager!!
                .beginTransaction()
                .add(R.id.search_DetailFrame, descriptionFragment)
                .addToBackStack(descriptionFragment.tag)
                .commit()
    }
}