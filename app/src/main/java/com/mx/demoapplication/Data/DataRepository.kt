package com.mx.demoapplication.Data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.widget.Toast
import com.mx.demoapplication.Data.source.Remote.WebServices
import com.mx.demoapplication.Data.source.Local.ArticleDao
import com.mx.demoapplication.utils.Constants
import retrofit2.Call
import java.util.*
import java.util.concurrent.Executor


class DataRepository constructor(private var webservice: WebServices,
                                 val articleDao: ArticleDao,
                                 private val executor: Executor)
{
    fun getNewsArticles(): LiveData<ArticleModel> {
        val data = MutableLiveData<ArticleModel>()
        executor.execute {
                val articles = articleDao.getAll()
                if (!articles.isNullOrEmpty()) {
                    val articleModel = ArticleModel(articles = articles)
                    data.postValue(articleModel)
                } else {
//                webservice = APIClient.getClient().create(WebServices::class.java)
                    webservice.getArticle(Constants.countryCode, Constants.api_key)
                        .enqueue(object : retrofit2.Callback<ArticleModel> {
                            override fun onResponse(
                                call: Call<ArticleModel>,
                                response: retrofit2.Response<ArticleModel>
                            ) {
                                val article = response.body()
                                data.postValue(article)
                                executor.execute {
                                    articleDao.insertAll(article?.articles!!)
                                }
                            }

                            // Error case is left out for brevity.
                            override fun onFailure(call: Call<ArticleModel>, t: Throwable) {
                                Log.d("Article", t.message)
                            }
                        })
                }


        }
        return data
    }

   fun markFavorite(article: Article):LiveData<Article>{
       val data = MutableLiveData<Article>()
       executor.execute {
           articleDao.addFavorite(article = article)
           data.postValue(article)
       }
      return data
   }

    fun forceRefreshed() : LiveData<ArticleModel>{
        val data = MutableLiveData<ArticleModel>()
        executor.execute {
            webservice.getArticle(Constants.countryCode, Constants.api_key)
                .enqueue(object : retrofit2.Callback<ArticleModel> {
                    override fun onResponse(
                        call: Call<ArticleModel>,
                        response: retrofit2.Response<ArticleModel>
                    ) {
                        val article = response.body()
                        data.postValue(article)
                        executor.execute {
                            articleDao.updateAll(article?.articles!!)
                        }
                    }

                    // Error case is left out for brevity.
                    override fun onFailure(call: Call<ArticleModel>, t: Throwable) {
                        Log.d("Article", t.message)
                    }
                })
        }
        return data
    }
}