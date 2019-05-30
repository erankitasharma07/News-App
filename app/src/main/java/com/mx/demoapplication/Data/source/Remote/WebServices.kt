package com.mx.demoapplication.Data.source.Remote

import com.mx.demoapplication.Data.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WebServices {
    @GET("top-headlines")
    fun getArticle(@Query("country") country: String ,@Query("apiKey") apiKey: String): Call<Response.ArticleModel>
}