package fr.airweb.news.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.airweb.news.data.models.Post
import fr.airweb.news.data.repositories.PostsRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val postsRepository: PostsRepository
) : ViewModel() {

    @Inject lateinit var disposable: CompositeDisposable

    val news = MutableLiveData<ArrayList<Post>>()

    fun loadNews() {
        Single.fromCallable {
            return@fromCallable postsRepository.fetchPosts()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { news -> this.news.value = (news ?: ArrayList<Post>()) as ArrayList<Post>? },
                { throwable ->
                    Log.e(
                        MAIN_VM_TAG,
                        LOAD_NEWS_LABEL + throwable.toString()
                    )
                }
            )
            .addTo(disposable)
    }



    companion object {
        private val MAIN_VM_TAG = "MainVM"
        private val LOAD_NEWS_LABEL = "In method loadNews(): "
        private val SUCCESS_MSG = "Operation was successful"
    }
}