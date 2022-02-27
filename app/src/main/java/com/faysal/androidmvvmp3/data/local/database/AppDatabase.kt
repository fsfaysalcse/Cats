package com.faysal.androidmvvmp3.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.faysal.androidmvvmp3.data.local.dao.CatDao
import com.faysal.androidmvvmp3.data.local.dao.RemoteKeysDao
import com.faysal.androidmvvmp3.data.local.entity.RemoteKey
import com.faysal.androidmvvmp3.models.Cat

@Database(
    entities = [Cat::class, RemoteKey::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val catDao: CatDao
    abstract val remoteKey: RemoteKeysDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
            }


        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_db"
            ).fallbackToDestructiveMigration().build()
    }
}