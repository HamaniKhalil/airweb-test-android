package fr.airweb.news.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import fr.airweb.news.config.POSTS_TABLE_NAME
import java.time.LocalDateTime

@Entity(
    tableName = POSTS_TABLE_NAME
)
data class Post(
    var type: String,
    var date: String,
    var title: String,
    var picture: String,
    var content: String,
    var dateformated: String
) {
    @PrimaryKey(autoGenerate = false)
    var nid: Int = 0
}