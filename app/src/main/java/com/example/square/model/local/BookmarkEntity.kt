package com.example.square.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "bookmark",
    foreignKeys = [ForeignKey(
        entity = ReposEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("reposId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class BookmarkEntity(

    @PrimaryKey
    @ColumnInfo(name = "reposId", index = true)
    val reposId: Int
)

