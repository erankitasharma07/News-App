package com.mx.demoapplication

import android.app.Application
import com.mx.demoapplication.Data.DataRepository
import com.mx.demoapplication.Data.source.Remote.APIClient
import com.mx.demoapplication.Data.source.Local.AppDatabase
import com.mx.demoapplication.Data.source.Remote.WebServices
import java.util.concurrent.Executors

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        AppDatabase.getInstance(this)
        REPOSITORY= DataRepository( webservice = APIClient.getClient().create(WebServices::class.java),
            articleDao = AppDatabase.getInstance(this).articleDao(),
            executor = Executors.newSingleThreadExecutor())
    }
    companion object{
        lateinit var REPOSITORY:DataRepository

    }

}