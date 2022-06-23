package com.example.countriesapp.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    private val job  = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main //önce işini yap sonra main threada dön

    //bir şey destroy olursa mesela app kapanırsa işi de kapat
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}