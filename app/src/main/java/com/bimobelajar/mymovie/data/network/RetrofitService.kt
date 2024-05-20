package com.bimobelajar.mymovie.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private var retrofitService: TMDBApi? = null

        fun getInstance(): TMDBApi {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(TMDBApi::class.java)
            }
            return retrofitService!!
        }
    }
}
