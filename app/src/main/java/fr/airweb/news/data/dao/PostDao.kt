package fr.airweb.news.data.dao

import androidx.room.*
import fr.airweb.news.config.POSTS_TABLE_NAME
import fr.airweb.news.data.models.Post

@Dao
interface PostDao {

    @Insert
    fun addPost(post: Post)

    @Insert
    fun addPosts(posts: List<Post>)

    @Query("SELECT * FROM $POSTS_TABLE_NAME ORDER BY nid DESC")
    fun getAllPosts() : List<Post>

    @Query("SELECT * FROM $POSTS_TABLE_NAME WHERE type=:type ORDER BY nid DESC")
    fun getPostsByType(type: String) : List<Post>

    @Query("SELECT * FROM $POSTS_TABLE_NAME WHERE date=:date ORDER BY nid DESC")
    fun getPostsByDate(date: String) : List<Post>

    @Query("SELECT * FROM $POSTS_TABLE_NAME WHERE title=:title ORDER BY nid DESC")
    fun getPostsByTitle(title: String) : List<Post>

    /**
     * Better to use @Delete annotation next time
     */
    @Query("DELETE FROM $POSTS_TABLE_NAME")
    fun truncate()


}