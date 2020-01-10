package com.illicitintelligence.moviedatabaseapiapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.illicitintelligence.moviedatabaseapiapp.R

class MainActivityK : AppCompatActivity(){
    internal var searchFragment = SearchFragmentK()

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.main_fragmentFrame, searchFragment)
                .commit()

    }
}