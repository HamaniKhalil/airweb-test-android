package fr.airweb.news.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class PSGViewModelFactory @Inject constructor() : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        throw IllegalArgumentException("Wrong ViewModel class")
    }
}