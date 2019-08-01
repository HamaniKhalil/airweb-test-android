package fr.airweb.news.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import fr.airweb.news.PSGApplication
import fr.airweb.news.R
import fr.airweb.news.viewmodels.PSGViewModelFactory
import fr.airweb.news.viewmodels.SplashViewModel
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject lateinit var splashViewModel: SplashViewModel
    @Inject lateinit var psgViewModelFactory: PSGViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dependency injection
        PSGApplication.appComponent?.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        splashViewModel = ViewModelProviders.of(this, psgViewModelFactory).get(SplashViewModel::class.java)

        splashViewModel.news.observe(this, Observer { news ->
            splashViewModel.populateDbWithPosts(news)
            startActivity(MainActivity.getIntent(this))
        })

        splashViewModel.isOffline.observe(this, Observer { isOffline ->
            if (isOffline) {
                startActivity(MainActivity.getIntent(this))
            }
        })

        splashViewModel.loadNews()
    }

    companion object {
        /**
         * Not sure that one is useful since there's no need to access it
         * from anywhere. But it is left here in case.
         */
        fun getIntent(context: Context): Intent {
            return Intent(context, SplashActivity::class.java)
        }
    }
}
