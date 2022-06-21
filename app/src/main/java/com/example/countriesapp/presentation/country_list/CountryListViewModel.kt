package com.example.countriesapp.presentation.country_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesapp.model.Country
import com.example.countriesapp.service.NetworkModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CountryListViewModel: ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    private val networkModule = NetworkModule()
    private val disposable = CompositeDisposable()

    fun refreshData(){
        /*val country = Country("Turkey","Asia","Ankara","TRY","","Turkish")

        val countryList = arrayListOf<Country>(country)
        countries.value = countryList
        isLoading.value = false
        isError.value = false*/

        getDataFromApi()

    }

    private fun getDataFromApi(){
        isLoading.value = true

        disposable.add(
            networkModule.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        isLoading.value = false
                        isError.value = false
                        countries.value = t
                    }

                    override fun onError(e: Throwable) {
                        isLoading.value = false
                        isError.value = true
                        e.printStackTrace()
                    }

                })
        )
    }

}