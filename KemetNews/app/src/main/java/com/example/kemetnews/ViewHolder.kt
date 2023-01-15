package com.example.kemetnews

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.cardview.widget.CardView


class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textTitle: TextView
    var textSource: TextView
    var textSummary: TextView
    var imgHeadline: ImageView
    var cardView: CardView

    init {
        textTitle = itemView.findViewById(R.id.text_title)
        textSource = itemView.findViewById(R.id.text_source)
        textSummary = itemView.findViewById(R.id.text_summary)
        imgHeadline = itemView.findViewById(R.id.img_headline)
        cardView = itemView.findViewById(R.id.main_container)
    }
}