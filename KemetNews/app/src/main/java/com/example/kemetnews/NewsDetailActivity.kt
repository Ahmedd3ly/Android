package com.example.kemetnews

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.squareup.picasso.Picasso
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar

class NewsDetailActivity : AppCompatActivity() {
    private var title: String? = null
    private var source: String? = null
    private var summary: String? = null
    private var image: String? = null
    private var imageUrl: String? = null
    private var content: String? = null
    private var videoUrl: String? = null

    private var newsTitle: TextView? = null
    private var newsSource: TextView? = null
    private var newsSummary: TextView? = null
    private var newsContent: TextView? = null
    private var newsImg: ImageView? = null
    private var newsButton: Button? = null
    private var videoButton: Button? = null
    private var shareButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        title = intent.getStringExtra("title")
        source = intent.getStringExtra("source")
        summary = intent.getStringExtra("summary")
        image = intent.getStringExtra("image")
        imageUrl = intent.getStringExtra("image_url")
        videoUrl = intent.getStringExtra("video_url")
        content = intent.getStringExtra("content")

        newsTitle = findViewById(R.id.news_title)
        newsSource = findViewById(R.id.news_source)
        newsSummary = findViewById(R.id.news_summary)
        newsImg = findViewById(R.id.news_img)
        newsContent = findViewById(R.id.news_content)
        newsButton = findViewById(R.id.news_button)
        videoButton = findViewById(R.id.video_button)
        shareButton = findViewById(R.id.share_button)

        newsTitle?.text = title
        newsSource?.text = source
        newsSummary?.text = summary
        newsContent?.text = content
        Picasso.get().load(image).into(newsImg)

        newsButton?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(imageUrl)
            startActivity(intent)

            videoButton?.setOnClickListener { view: View? ->
                if (videoUrl != null) {
                    val intent = Intent(this, VideoViewer::class.java)
                    intent.putExtra("url", videoUrl)
                    startActivity(intent)
                } else {
                    Snackbar.make(
                        view!!,
                        "This Article doesn't have a video.",
                        Snackbar.LENGTH_LONG
                    )
                        .show()
                }
            }
        }

        shareButton?.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, "Check out this awesome article!")
            intent.putExtra(Intent.EXTRA_TEXT, imageUrl)
            startActivity(Intent.createChooser(intent, "Share Via"))
        }
    }
}