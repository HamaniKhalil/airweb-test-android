package fr.airweb.news.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import fr.airweb.news.PSGApplication
import fr.airweb.news.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dependency injection
        PSGApplication.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SplashActivity::class.java)
        }
    }
}
