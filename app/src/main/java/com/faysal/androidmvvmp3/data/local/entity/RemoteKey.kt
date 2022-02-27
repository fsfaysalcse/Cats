package com.faysal.androidmvvmp3.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val nextKey : Int?,
    val isFinished : Boolean
)