package com.illicitintelligence.moviedatabaseapiapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.illicitintelligence.moviedatabaseapiapp.R;
import com.illicitintelligence.moviedatabaseapiapp.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {

    SearchFragmentK searchFragment = new SearchFragmentK();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragmentFrame, searchFragment)
                .commit();

    }
}
