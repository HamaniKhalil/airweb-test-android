package fr.airweb.news.data.repositories

import fr.airweb.news.data.db.PSGDatabase
import fr.airweb.news.data.models.Post
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsRepository @Inject constructor(var psgDatabase: PSGDatabase) {

    @Inject lateinit var disposable: CompositeDisposable

    fun fetchPosts() = psgDatabase.getPostDao().getAllPosts()

    fun insertPosts(posts: ArrayList<Post>) {
        psgDatabase.getPostDao().addPosts(posts)
    }

    fun init() {
        Single.fromCallable{
            psgDatabase.getPostDao().truncate()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(disposable)
    }
}