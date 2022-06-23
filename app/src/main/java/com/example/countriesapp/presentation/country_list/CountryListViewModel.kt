package com.example.countriesapp.presentation.country_list

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesapp.base.BaseViewModel
import com.example.countriesapp.database.CountryDatabase
import com.example.countriesapp.model.Country
import com.example.countriesapp.service.NetworkModule
import com.example.countriesapp.utils.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CountryListViewModel(application: Application): BaseViewModel(application) {

    val countries = MutableLiveData<List<Country>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    private val networkModule = NetworkModule()
    private val disposable = CompositeDisposable()
    private val customPreferences = CustomSharedPreferences(getApplication())
    private val refreshTime = 10*60*1000*1000*1000L

    fun refreshData(){

        val updateTime = customPreferences.getTime()

        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){
            getDataFromSql()
        }else{
            getDataFromApi()
        }

    }

    private fun getDataFromSql(){
        isLoading.value = true
        launch {
            val countries = CountryDatabase(getApplication()).countryDAO().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(),"From Sqlite",Toast.LENGTH_LONG).show()
        }
    }

    private fun getDataFromApi(){
        isLoading.value = true

        disposable.add(
            networkModule.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        storeInSqlite(t)
                    }

                    override fun onError(e: Throwable) {
                        isLoading.value = false
                        isError.value = true
                        e.printStackTrace()
                    }

                })
        )
    }

    fun refreshFromApi(){
        getDataFromApi()
    }

    private fun showCountries(countryList : List<Country>){
        isLoading.value = false
        isError.value = false
        countries.value = countryList
    }

    private fun storeInSqlite(countryList : List<Country>){
        launch {
            val dao = CountryDatabase(getApplication()).countryDAO()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*countryList.toTypedArray()) // list -> individual
            var i = 0
            while (i<countryList.size){
                countryList[i].uuid = listLong[i].toInt()
                i += 1
            }
            showCountries(countryList)
        }

        customPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}