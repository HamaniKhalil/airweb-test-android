package fr.airweb.news.data.repositories

import fr.airweb.news.api.PSGNewsApi
import javax.inject.Inject

class NewsRepository @Inject constructor(val psgNewsApi: PSGNewsApi) {

    fun fetchNews() = psgNewsApi.getAllPosts()
}