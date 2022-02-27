package com.faysal.androidmvvmp3.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_tables")
data class Cat(
    val breed_id: String?,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val url: String
)