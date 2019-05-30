package com.mx.demoapplication.Data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.mx.demoapplication.Data.source.Remote.WebServices
import com.mx.demoapplication.Data.source.Remote.APIClient
import com.mx.demoapplication.utils.Constants
import retrofit2.Call


class DataRepository{
    private val webservice: WebServices = APIClient.getClient().create(WebServices::class.java)
    fun getNewsArticles(): LiveData<Response.ArticleModel> {
        // This isn't an optimal implementation. We'll fix it later.
        val data = MutableLiveData<Response.ArticleModel>()
        webservice.getArticle(Constants.countryCode,Constants.api_key)
            .enqueue(object : retrofit2.Callback<Response.ArticleModel> {
            override fun onResponse(call: Call<Response.ArticleModel>, response: retrofit2.Response<Response.ArticleModel>) {
                data.value = response.body()
            }
            // Error case is left out for brevity.
            override fun onFailure(call: Call<Response.ArticleModel>, t: Throwable) {
               Log.d("Article", t.message)
            }
        })
        return data
    }

}