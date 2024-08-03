package com.example.twitter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter.databinding.ItemFeedBinding

data class Tweet(val user: String, val content: String)

class FeedAdapter(private val tweetList: List<Tweet>) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    class FeedViewHolder(val binding: ItemFeedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding = ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val tweet = tweetList[position]
        holder.binding.tweetUser.text = tweet.user
        holder.binding.tweetContent.text = tweet.content
    }

    override fun getItemCount() = tweetList.size
}
