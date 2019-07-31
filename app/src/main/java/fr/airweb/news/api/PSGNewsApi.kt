package fr.airweb.news.api

import fr.airweb.news.config.NEWS_API_ROUTE
import fr.airweb.news.data.models.News
import io.reactivex.Single
import retrofit2.http.GET

interface PSGNewsApi {

    @GET(NEWS_API_ROUTE)
    fun getAllPosts(): Single<News>
}