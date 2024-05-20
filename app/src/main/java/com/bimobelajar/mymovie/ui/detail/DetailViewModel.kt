package com.bimobelajar.mymovie.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bimobelajar.mymovie.data.model.Movie
import com.bimobelajar.mymovie.data.network.RetrofitService
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    private val retrofitService = RetrofitService.getInstance()

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val response = retrofitService.getMovieDetails(movieId)
            if (response.isSuccessful) {
                _movie.postValue(response.body())
            }
        }
    }
}
