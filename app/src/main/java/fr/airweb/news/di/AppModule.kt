package fr.airweb.news.di

import android.app.Application
import dagger.Module
import dagger.Provides
import fr.airweb.news.api.PSGNewsApi
import fr.airweb.news.config.PSG_BASE_URL
import fr.airweb.news.data.db.PSGDatabase
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(val application : Application) {

    @Provides
    fun getRetrofitWebService(): PSGNewsApi =
        Retrofit.Builder()
            .baseUrl(PSG_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(PSGNewsApi::class.java)

    @Provides
    fun getDatabase(): PSGDatabase = PSGDatabase(application)

    @Provides
    fun getDisposable(): CompositeDisposable = CompositeDisposable()
}