package com.example.kemetnews

import android.content.Context
import com.example.kemetnews.Models.Results
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.content.Intent

import com.squareup.picasso.Picasso
import java.util.ArrayList

class Adapter(var context: Context, var articles: ArrayList<Results>) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardView.setOnClickListener {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra("title", articles[position].title)
            intent.putExtra("source", articles[position].source_id)
            intent.putExtra("summary", articles[position].description)
            intent.putExtra("image", articles[position].image_url)
            intent.putExtra("image_url", articles[position].link)
            intent.putExtra("video_url", articles[position].video_url)
            intent.putExtra("content", articles[position].content)
            context.startActivity(intent)
        }
        holder.textTitle.text = articles[position].title
        holder.textSource.text = articles[position].source_id
        holder.textSummary.text = articles[position].description
        if (articles[position].image_url != null) {
            Picasso.get().load(articles[position].image_url).into(holder.imgHeadline)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}