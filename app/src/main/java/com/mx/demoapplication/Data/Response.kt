package com.mx.demoapplication.Data

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*


data class ArticleModel(
        var articles: List<Article>,
        var status: String="",
        var totalResults: Int=0
    )

@Entity
data class Article (
    @PrimaryKey
    var pid: String = UUID.randomUUID().toString(),
    var author: String?= null,
    var content: String?=null,
    var description: String="",
    var publishedAt: String?=null,
    @Embedded(prefix = "source") var source: Source= Source(),
    var title: String="",
    var url: String?=null,
    var urlToImage: String?=null,
    var isFavorite:Boolean = false
)

data class Source(
    var id: String?=null,
    var name: String=""
)