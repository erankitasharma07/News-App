package com.mx.demoapplication.Data.source.Local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

import com.mx.demoapplication.Data.Source

@Entity
data class ArticleEntity(
//    @PrimaryKey val id: Int,
//    @ColumnInfo(name = "ArticleModel")val articles: List<Response.Article>,
//    @Ignore val status: String,
//    @Ignore val totalResults: Int
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "authorName") val author: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "publishedDate") val publishedAt: String,
    @ColumnInfo(name = "source") val source: Source,
    @ColumnInfo(name = "title") val title: String,
    @Ignore val url: String,
    @Ignore val urlToImage: String
)
@Entity
data class ArticlEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "authorName") val author: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "publishedDate") val publishedAt: String,
    @ColumnInfo(name = "source") val source: Source,
    @ColumnInfo(name = "title") val title: String,
    @Ignore val url: String,
    @Ignore val urlToImage: String
    )