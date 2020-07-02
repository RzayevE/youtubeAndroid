package com.reset.youtube

class HomeFeed(val items: List<Item>){}

class Item(val snippet: Snippet){}

class Snippet(val publishedAt: String, val title: String, val description: String,
              val channelTitle: String, val thumbnails: Thumbnail,val channelId: String){}

class Thumbnail(val maxres: Maxres, val standard: Standard){}

class Standard(val url: String){}

class Maxres(val url: String){}

class ChannelFeed(val items: List<ChannelItem>){}

class ChannelItem(val snippet: ChannelSnippet){
}
class ChannelSnippet(val publishedAt: String, val title: String, val thumbnails: ChannelThumbnail){}

class ChannelThumbnail(val high: High){}

class High(val url: String){}