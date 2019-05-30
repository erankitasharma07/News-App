package com.mx.demoapplication.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.mx.demoapplication.Data.Response
import com.mx.demoapplication.Data.DataRepository


class CommonViewModel internal constructor() : ViewModel(){
    val userRepository: DataRepository
    init {
        userRepository = DataRepository()
    }
    val articles : LiveData<Response.ArticleModel> = userRepository.getNewsArticles()
}
