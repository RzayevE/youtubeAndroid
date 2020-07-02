package com.reset.youtube

import android.hardware.SensorManager.getOrientation
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    var id = ""

    lateinit var listOfChannels: ArrayList<ChannelFeed>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView_main.context,
            (recyclerView_main.layoutManager as LinearLayoutManager).orientation
        )
        recyclerView_main.addItemDecoration(dividerItemDecoration)

        listOfChannels = ArrayList<ChannelFeed>()

        fetchJson()
    }


    fun fetchJson(){
        val api_key = "AIzaSyDGQ-Jkha2uR-RFyjZz-PNYr4JWAwzkWHE"
        val url = "https://www.googleapis.com/youtube/v3/videos?part=snippet&chart=mostPopular&key=" + api_key

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)
                for (item in homeFeed.items){
                    id = item.snippet.channelId
                    val URL = "https://www.googleapis.com/youtube/v3/channels?part=snippet&id="+ id +"&key=AIzaSyDGQ-Jkha2uR-RFyjZz-PNYr4JWAwzkWHE"
                    val r = Request.Builder().url(URL).build()
                    client.newCall(r).enqueue(object : Callback{
                        override fun onFailure(call: Call, e: IOException) {
                            println("Failed to execute second request")
                        }

                        override fun onResponse(call: Call, response: Response) {
                            val b = response.body?.string()
                            println(b)

                            val channnelFeed = gson.fromJson(b, ChannelFeed::class.java)


                            listOfChannels.add(channnelFeed)

                            runOnUiThread {
                                recyclerView_main.adapter = MainAdapter(homeFeed, listOfChannels)
                            }
                        }
                    })
                }
            }
        })
        println("Attempting to fetch JSON")
    }
}


