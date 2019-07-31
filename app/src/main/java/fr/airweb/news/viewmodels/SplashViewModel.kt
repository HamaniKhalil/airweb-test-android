package fr.airweb.news.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var disposable: CompositeDisposable


}