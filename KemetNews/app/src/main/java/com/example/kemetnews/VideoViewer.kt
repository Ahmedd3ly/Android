package com.example.kemetnews

import androidx.appcompat.app.AppCompatActivity
import android.widget.VideoView
import android.os.Bundle

import android.net.Uri
import android.widget.MediaController

class VideoViewer : AppCompatActivity() {
    private var videoUrl: String? = null
    private var videoView: VideoView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view)
        val intent = intent
        videoUrl = intent.getStringExtra("video_url")

        // on below line we are initializing our variables.
        videoView = findViewById(R.id.video_view)

        // Uri object to refer the
        // resource from the videoUrl
        val uri = Uri.parse(videoUrl)

        // sets the resource from the
        // videoUrl to the videoView
        videoView?.setVideoURI(uri)

        // creating object of
        // media controller class
        val mediaController = MediaController(this@VideoViewer)

        // sets the anchor view
        // anchor view for the videoView
        mediaController.setAnchorView(videoView)

        // sets the media player to the videoView
        mediaController.setMediaPlayer(videoView)

        // sets the media controller to the videoView
        videoView?.setMediaController(mediaController)

        // starts the video
        videoView?.start()
    }
}