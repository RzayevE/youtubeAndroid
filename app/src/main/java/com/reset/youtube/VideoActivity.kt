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
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.video.*
import java.lang.Exception
import java.net.URI

class VideoActivity : AppCompatActivity() {

    var playbackPosition = 0
    var mediaController: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video)
        val actionBar = supportActionBar
        actionBar!!.title = ""

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra("videoPath")
        val path = "https://www.youtube.com/watch?v=" + id
//
//        web_view.settings.javaScriptEnabled = true
//        web_view.webChromeClient = WebChromeClient()
//        web_view.loadUrl(path)

        if (mediaController == null) {
            mediaController = MediaController(this)
        }
        try {
            video_view.setMediaController(mediaController)
            video_view.setVideoPath(path)
        } catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
        video_view.requestFocus()
        video_view.setOnCompletionListener {
            video_view.seekTo(playbackPosition)
            if (playbackPosition == 0) {
                video_view.start()
            }
        }



    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}