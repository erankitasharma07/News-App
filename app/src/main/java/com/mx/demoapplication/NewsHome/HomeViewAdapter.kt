package com.mx.demoapplication.NewsHome

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mx.demoapplication.Data.Article
import com.mx.demoapplication.Data.source.Local.ArticleDao
import com.mx.demoapplication.NewsDetails.NewsDetailActivity
import com.mx.demoapplication.R
import com.mx.demoapplication.databinding.ItemHomeViewBinding
import com.squareup.picasso.Picasso

class HomeViewAdapter(
    private var mcontext: Context,
    var articleModel: List<Article>,
    private val listener:(View, Int, Article, Boolean)->Unit
) :
    RecyclerView.Adapter<HomeViewAdapter.ItemViewHolder>() {
    private lateinit var binding: ItemHomeViewBinding
    private val articleDao: ArticleDao? = null

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItems(articleModel[position])
        binding.favorite.setOnClickListener {
            if (!articleModel[position].isFavorite){
                listener(it,holder.adapterPosition, articleModel[holder.adapterPosition], true)
            }else{
                listener(it,holder.adapterPosition, articleModel[holder.adapterPosition], false)
            }

        }
    }

    override fun getItemCount(): Int = articleModel.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_home_view, parent, false)
        return ItemViewHolder(binding, mcontext)
    }

    inner class ItemViewHolder(val binding: ItemHomeViewBinding, val c: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(article: Article) {
            binding.description.text = article.description
            binding.title.text = article.title
            Picasso.get()
                .load(article.urlToImage)
                .into(binding.imageView)
            mcontext = c


            if (article.isFavorite) {
                binding.favorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        mcontext,
                        R.drawable.ic_favorite_red_700_24dp
                    )
                )
            } else {
                binding.favorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        mcontext,
                        R.drawable.ic_favorite_border_red_700_24dp
                    )
                )
            }




            binding.itemHome.setOnClickListener {
                val intent = Intent(mcontext, NewsDetailActivity::class.java)
                intent.putExtra("content", article.description)
                intent.putExtra("image", article.urlToImage)
                intent.putExtra("publishedAt", article.publishedAt)
                intent.putExtra("source", article.author)
                intent.putExtra("title", article.title)
                mcontext.startActivity(intent)
            }

        }
    }
}