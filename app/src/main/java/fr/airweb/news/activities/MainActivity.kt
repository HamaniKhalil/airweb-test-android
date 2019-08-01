package fr.airweb.news.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import fr.airweb.news.PSGApplication
import fr.airweb.news.R
import fr.airweb.news.data.models.News
import fr.airweb.news.data.models.Post
import fr.airweb.news.data.repositories.PostsRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        PSGApplication.appComponent?.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)


        }
    }
}
