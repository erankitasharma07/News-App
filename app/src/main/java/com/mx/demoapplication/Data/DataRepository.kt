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
import kotlin.collections.ArrayList


class DataRepository constructor(private var webservice: WebServices,
                                 val articleDao: ArticleDao,
                                 private val executor: Executor)
{
    val data = MutableLiveData<ArticleModel>()
    lateinit var articles : List<Article>
    fun getNewsArticles(): LiveData<ArticleModel> {
        executor.execute {
                articles = articleDao.getAll()
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
//        val data = MutableLiveData<ArticleModel>()
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
                                articles = articleDao.getAll()
                                if (article?.articles?.get(0)?.title != articles[0].title) {
                                    Log.d("ArticleMatch","Not Matched")
                                articleDao.updateAll(article?.articles!!)
                            }
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

    fun getFavoriteData():LiveData<ArticleModel>{
        executor.execute {
            val favArticles = ArrayList<Article>()
            var i = 0
            while (i < articles.size) {
                if (articles[i].isFavorite) {
                    favArticles.add(articles[i])
                }
                i++
            }
            val articleModel = ArticleModel(articles = favArticles)
            data.postValue(articleModel)
        }
        return data
    }


}