package com.mx.demoapplication.NewsDetails

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.mx.demoapplication.databinding.ActivityNewsDetailsBinding
import com.squareup.picasso.Picasso
import java.util.*
import com.mx.demoapplication.R
import java.text.SimpleDateFormat


class NewsDetailActivity : AppCompatActivity(){
    lateinit var binding : ActivityNewsDetailsBinding
    lateinit var pulishedDate : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_details)
        title = "Daily news"
        getData()

    }

    private fun getData() {
        val intent = intent
        binding.content.text = intent.getStringExtra("content")
        pulishedDate = intent.getStringExtra("publishedAt")
        binding.title.text = intent.getStringExtra("title")
        Picasso.get()
            .load(intent.getStringExtra("image"))
            .fit()
            .into(binding.imageView)
        getDateFormat()
    }

    private fun getDateFormat(){
        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        parser.timeZone = TimeZone.getTimeZone("IST")
        val parsed = parser.parse(pulishedDate)
        binding.publishedAt.text = SimpleDateFormat("dd-MMM-yyyy HH:mm a").format(parsed)

    }

}