package fr.airweb.news.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class PSGViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    @Inject lateinit var splashViewModel: SplashViewModel

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return splashViewModel as T
        }
        throw IllegalArgumentException("Wrong ViewModel class")
    }
}