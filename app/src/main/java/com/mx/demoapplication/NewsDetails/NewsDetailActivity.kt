package com.mx.demoapplication.NewsDetails
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.mx.demoapplication.R
import com.mx.demoapplication.databinding.ActivityNewsDetailsBinding
import com.squareup.picasso.Picasso

class NewsDetailActivity : AppCompatActivity(){
    lateinit var binding : ActivityNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_details)
        title = "Daily news"
        getData()

    }

    private fun getData() {
        val intent = intent
        binding.content.text = intent.getStringExtra("content")
        binding.publishedAt.text = intent.getStringExtra("publishedAt")
        binding.title.text = intent.getStringExtra("title")
        Picasso.get()
            .load(intent.getStringExtra("image"))
            .fit()
            .into(binding.imageView)
    }


}