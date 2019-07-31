package fr.airweb.news.di

import dagger.Component
import fr.airweb.news.activities.SplashActivity

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(splashActivity: SplashActivity)
}