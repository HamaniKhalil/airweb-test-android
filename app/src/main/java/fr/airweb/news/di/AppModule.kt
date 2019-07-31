package fr.airweb.news.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class AppModule(context: Context) {


    @Provides
    fun getDisposable(): CompositeDisposable = CompositeDisposable()
}