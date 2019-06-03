package com.mx.demoapplication.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.mx.demoapplication.App
import com.mx.demoapplication.Data.ArticleModel
import com.mx.demoapplication.Data.DataRepository


class CommonViewModel internal constructor() : ViewModel(){
    val userRepository: DataRepository
    init {
        userRepository = App.REPOSITORY
    }
    val articles : LiveData<ArticleModel> = userRepository.getNewsArticles()
//    val article : LiveData<Response.Article> = userRepository.getDataArticles()
}
