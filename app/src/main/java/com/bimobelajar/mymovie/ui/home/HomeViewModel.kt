package com.bimobelajar.mymovie.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bimobelajar.mymovie.data.model.Movie
import com.bimobelajar.mymovie.data.network.RetrofitService
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val retrofitService = RetrofitService.getInstance()

    init {
        fetchNowPlayingMovies()
    }

    private fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            val response = retrofitService.getNowPlayingMovies()
            if (response.isSuccessful) {
                _movies.postValue(response.body()?.results)
            }
        }
    }
}
