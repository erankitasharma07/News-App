package com.mx.demoapplication.Data.source.Local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.mx.demoapplication.Data.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM Article")
    fun getAll(): List<Article>

    @Query("SELECT COUNT(title)>0 FROM Article")
    fun isArticlesAvailable():Boolean


    @Query("SELECT * FROM Article WHERE author LIKE :first")
    fun findByName(first: String): List<Article>

    @Insert
    fun insertAll(articles: List<Article>)

//    @Query("Delete from Article where pid like :pid")
//    fun deletePos(pid: Int)

    @Delete
    fun delete(article: List<Article>)

    @Query("Select * From Article where isFavorite = 0 ")
    fun selectUnFav():List<Article>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun updateAll(article: List<Article>)

    @Update
    fun addFavorite(article: Article)

//    @Query("SELECT * FROM Article Where pid")
//    fun getFavorite(): List<Article>

}