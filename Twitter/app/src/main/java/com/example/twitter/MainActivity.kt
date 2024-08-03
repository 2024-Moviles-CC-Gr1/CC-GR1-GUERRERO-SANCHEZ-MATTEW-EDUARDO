package com.example.twitter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twitter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Datos de ejemplo
        val tweetList = listOf(
            Tweet("User1", "This is a tweet"),
            Tweet("User2", "Another tweet")
        )

        val trendList = listOf(
            Trend("Trend1"),
            Trend("Trend2")
        )

        // Configura el RecyclerView para el Feed
        binding.recyclerViewFeed.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewFeed.adapter = FeedAdapter(tweetList)

        // Configura el RecyclerView para los Trends
        binding.recyclerViewTrends.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTrends.adapter = TrendsAdapter(trendList)
    }
}
