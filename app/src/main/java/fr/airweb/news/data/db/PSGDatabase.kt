package fr.airweb.news.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.airweb.news.config.DATABASE_NAME
import fr.airweb.news.data.dao.PostDao
import fr.airweb.news.data.models.Post

@Database(
    entities = [Post::class],
    version = 1,
    exportSchema = false
)
abstract class PSGDatabase : RoomDatabase() {

    abstract fun getPostDao() : PostDao

    companion object {
        @Volatile private var instance : PSGDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PSGDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}