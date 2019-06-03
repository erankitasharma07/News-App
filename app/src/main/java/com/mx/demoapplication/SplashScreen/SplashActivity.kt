package com.mx.demoapplication.SplashScreen

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.mx.demoapplication.NewsHome.HomeActivity
import com.mx.demoapplication.R
import com.mx.demoapplication.databinding.ActivitySplashBinding

class SplashActivity:AppCompatActivity() {
    private val SPLASH_TIME_OUT = 2000
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        initializeHandler()

    }
    private fun initializeHandler() {
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, HomeActivity::class.java )
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}