package fr.airweb.news

import android.app.Application
import fr.airweb.news.di.AppComponent
import fr.airweb.news.di.AppModule
import fr.airweb.news.di.DaggerAppComponent

class PSGApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        var appComponent: AppComponent? = null
    }
}