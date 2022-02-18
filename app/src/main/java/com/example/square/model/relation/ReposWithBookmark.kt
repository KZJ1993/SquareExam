package com.example.square.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.square.model.local.BookmarkEntity
import com.example.square.model.local.ReposEntity

data class ReposWithBookmark(
    @Embedded
    val repos: ReposEntity,
    @Relation(parentColumn = "id", entityColumn = "reposId")
    val bookmark: BookmarkEntity?
)