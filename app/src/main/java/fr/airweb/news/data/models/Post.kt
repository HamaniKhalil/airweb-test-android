package fr.airweb.news.data.models

import androidx.room.PrimaryKey
import java.time.LocalDateTime

class Post(
    var type: String,
    var date: String,
    var title: String,
    var picture: String,
    var content: String,
    var dateformated: LocalDateTime
) {
    @PrimaryKey(autoGenerate = false)
    var nid: Int = 0
}