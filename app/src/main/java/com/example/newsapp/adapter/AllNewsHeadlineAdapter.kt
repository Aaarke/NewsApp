package com.example.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.R
import com.example.newsapp.listener.OnNewsItemSelectedListener
import com.example.newsapp.model.Article
import kotlinx.android.synthetic.main.item_all_news_headline.view.*

class AllNewsHeadlineAdapter(
    private var listener: OnNewsItemSelectedListener, private var context: Context,
    private var listOfArticle: ArrayList<Article>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_all_news_headline, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listOfArticle.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val requestOptions =
            RequestOptions().placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
        Glide.with(context).setDefaultRequestOptions(requestOptions)
            .load(listOfArticle[position].urlToImage).into(holder.itemView.ivNewsMain)
        holder.itemView.apply {
            tvNewsHeadline.text = listOfArticle[position].title
            tvNewsPublishDate.text = listOfArticle[position].getPublishedAtDate()
            tvNewsSource.text = listOfArticle[position].source?.name
        }
        holder.itemView.setOnClickListener {
            listener.onNewsItemSelected(listOfArticle[position].url!!)
        }
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

}