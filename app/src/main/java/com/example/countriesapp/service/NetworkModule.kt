package com.example.countriesapp.service

import com.example.countriesapp.model.Country
import com.example.countriesapp.utils.Constants.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NetworkModule {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ApiFactory::class.java)

    fun getData():Single<List<Country>>{
        return api.getCountries()
    }

}