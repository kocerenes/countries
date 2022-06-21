package com.example.countriesapp.presentation.country_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesapp.model.Country

class CountryListViewModel: ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    fun refreshData(){
        val country = Country("Turkey","Asia","Ankara","TRY","","Turkish")

        val countryList = arrayListOf<Country>(country)
        countries.value = countryList
        isLoading.value = false
        isError.value = false

    }

}