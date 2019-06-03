package com.mx.demoapplication.NewsHome

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.provider.SyncStateContract
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.mx.demoapplication.Data.Article
import com.mx.demoapplication.databinding.ActivityHomeBinding

import com.mx.demoapplication.ViewModel.CommonViewModel

import com.mx.demoapplication.R
import com.mx.demoapplication.utils.Constants
import okhttp3.internal.notifyAll


class HomeActivity : AppCompatActivity(){

    lateinit var binding : ActivityHomeBinding
    lateinit var viewModel: CommonViewModel
    lateinit var  adapter:HomeViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Daily News"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager

//        viewModel = ViewModelProviders.of(this).get(CommonViewModel::class.java)
        showProgressbar()
        callObserver()

        binding.swipeContainer.setOnRefreshListener {
            Constants.isForceRefresh = true
            callObserver()
        }

    }
    fun callObserver(){
        viewModel = ViewModelProviders.of(this).get(CommonViewModel::class.java)
        viewModel.articles.observe(this, Observer { userResource ->

            val article = userResource?.articles
            adapter = HomeViewAdapter(
                this@HomeActivity,
                article!!
            ) { view: View, i: Int, article: Article ->
                article.isFavorite=true
                viewModel.markFavorite(article)

                //update recycler adapter item at position
                 adapter.notifyItemChanged(i,article)
            }
            binding.recyclerView.adapter = adapter
            Log.d("Articles", article.toString())
            binding.swipeContainer.isRefreshing = false
            hideProgressbar()
        })
    }
    fun showToast(msg: String, context: Context){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
    fun showProgressbar(){
        binding.progressbar.visibility = View.VISIBLE
    }
    fun hideProgressbar(){
        binding.progressbar.visibility = View.GONE
    }


}