package com.reset.youtube

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import android.webkit.WebChromeClient
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.video.*
import java.net.URI

class VideoActivity : AppCompatActivity() {

    var playbackPosition = 0
    private lateinit var mediaController: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video)
        val actionBar = supportActionBar
        actionBar!!.title = ""

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra("videoPath")
        val path = "http://www.youtube.com/embed/" + id

        web_view.settings.javaScriptEnabled = true
        web_view.webChromeClient = WebChromeClient()
        web_view.loadUrl(path)

//        mediaController = MediaController(this)

//        video_view.setOnPreparedListener{
//            mediaController.setAnchorView(fl)
//            video_view.setMediaController(mediaController)
//            video_view.seekTo(playbackPosition)
//            video_view.start()
//        }

    }

//    override fun onStart() {
//        super.onStart()
//
//        val id = intent.getStringExtra("videoPath")
//        val path = "http://www.youtube.com/embed/" + id
//        video_view.setVideoPath(path)
//        video_view.start()
//        progressBar.visibility = View.VISIBLE
//
//    }
//
//    override fun onPause() {
//        super.onPause()
//
//        video_view.pause()
//        playbackPosition = video_view.currentPosition
//    }
//
//    override fun onStop() {
//        video_view.stopPlayback()
//
//        super.onStop()
//    }
//

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}