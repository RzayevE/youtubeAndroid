package com.reset.youtube

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*
import kotlin.coroutines.coroutineContext

class MainAdapter(val homeFeed: HomeFeed, val listOfChannels: ArrayList<ChannelFeed>) : RecyclerView.Adapter<CustomViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.item, parent, false)

        return CustomViewHolder(cellForRow)

    }

    override fun getItemCount(): Int {
        return homeFeed.items.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val video = homeFeed.items.get(position)
        holder.video = video
        if (listOfChannels.isEmpty()) {

        } else {
            val channel = listOfChannels[position].items.get(0)

            if (video.snippet.thumbnails.maxres != null) {
                val videoImageView = holder.itemView.video_image
                Picasso.get()
                    .load(video.snippet.thumbnails.maxres.url)
                    .into(videoImageView)
            }else{
                val videoImageView = holder.itemView.video_image
                Picasso.get()
                    .load(video.snippet.thumbnails.standard.url)
                    .into(videoImageView)
            }

            holder.itemView.video_title.text = video.snippet.title

            holder.itemView.channel_title.text = video.snippet.channelTitle


            val channelImageView = holder.itemView.channel_image



            Picasso.get()
                .load(channel.snippet.thumbnails.high.url)
                .into(channelImageView)

            Picasso.get().isLoggingEnabled = true
        }
    }
}

class CustomViewHolder(v: View, var video: Item? = null) : RecyclerView.ViewHolder(v) {



    init {
        v.setOnClickListener {
            val intent = Intent(it.context, VideoActivity::class.java)
            intent.putExtra("videoPath", video?.id)
            it.context.startActivity(intent)
        }
    }
}