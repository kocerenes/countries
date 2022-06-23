package com.example.countriesapp.presentation.country_detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesapp.base.BaseViewModel
import com.example.countriesapp.database.CountryDAO
import com.example.countriesapp.database.CountryDatabase
import com.example.countriesapp.model.Country
import kotlinx.coroutines.launch

class CountryDetailViewModel(application: Application) : BaseViewModel(application) {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(uuid : Int){
        launch {
            val dao = CountryDatabase(getApplication()).countryDAO()
            val country = dao.getCountryFromId(uuid)
            countryLiveData.value = country
        }
    }

}