package fr.airweb.news.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.airweb.news.data.models.Post
import fr.airweb.news.data.repositories.NewsRepository
import fr.airweb.news.data.repositories.PostsRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    val newsRepository: NewsRepository,
    val postsRepository: PostsRepository
) : ViewModel() {

    @Inject lateinit var disposable: CompositeDisposable

    val posts = MutableLiveData<ArrayList<Post>>()

    fun loadNews() {
        newsRepository.fetchNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { news ->
                    this.posts.value = news.posts
                },
                { throwable ->
                    Log.e(
                        SPLASH_VM_TAG,
                        LOAD_NEWS_LABEL + throwable.toString()
                    )
                }
            )
            .addTo(disposable)
    }

    fun populateDbWithPosts(posts: ArrayList<Post>) {
        Single.fromCallable {
            postsRepository.insertPosts(posts)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.i(
                        SPLASH_VM_TAG,
                        SUCCESS_MSG
                    )
                },
                { throwable ->
                    Log.e(
                        SPLASH_VM_TAG,
                        POPULATE_POSTS_LABEL + throwable.toString()
                    )
                }
            )
            .addTo(disposable)
    }

    companion object {
        // Tags
        val SPLASH_VM_TAG = "SplashVM"

        // Message suffixes
        val LOAD_NEWS_LABEL = "In method loadNews(): "
        val POPULATE_POSTS_LABEL = "In method populateDbWithPosts(): "

        // Messages
        val SUCCESS_MSG = "Operation was successful"
    }
}