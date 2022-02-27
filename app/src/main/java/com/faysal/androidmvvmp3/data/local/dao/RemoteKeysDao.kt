package com.faysal.androidmvvmp3.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.faysal.androidmvvmp3.data.local.entity.RemoteKey

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllKeys(remoteKeys: List<RemoteKey>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(remoteKey: RemoteKey)

    @Query("SELECT * FROM remote_keys WHERE id =:repoId")
    suspend fun remoteKeyByRepoId(repoId : Int) : RemoteKey

    @Query("DELETE FROM remote_keys")
    suspend fun clearAllRemoteKeysDao()

    @Query("SELECT * FROM remote_keys")
    suspend fun getRemoteKeys() : List<RemoteKey>
}