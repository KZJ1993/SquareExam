package com.example.square.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class ReposEntity(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "stars")
    val stars: String,

    @ColumnInfo(name = "watcher")
    val watcher: String,

    @ColumnInfo(name = "fork")
    val fork: String
) {
    fun isDescriptionNull() = description.isNullOrEmpty()
}