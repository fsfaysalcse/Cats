package com.faysal.androidmvvmp3.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faysal.androidmvvmp3.models.Cat

@Dao
interface CatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCats(list: List<Cat>)

    @Query("SELECT * FROM cat_tables")
    fun getCats() : PagingSource<Int,Cat>

    @Query("DELETE FROM cat_tables")
    suspend fun clearAllDataset()

    @Query("SELECT COUNT(id) from cat_tables")
    suspend fun totalAllCats() : Int

}