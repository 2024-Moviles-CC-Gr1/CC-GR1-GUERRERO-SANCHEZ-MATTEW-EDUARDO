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
        val newsList = listOf(
            News("News Title 1", "This is the content of the news item 1."),
            News("News Title 2", "This is the content of the news item 2.")
        )

        val trendList = listOf(
            Trend("Trend 1"),
            Trend("Trend 2")
        )

        // Configura el RecyclerView para las Noticias
        binding.recyclerViewNews.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewNews.adapter = NewsAdapter(newsList)

        // Configura el RecyclerView para los Trends
        binding.recyclerViewTrends.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTrends.adapter = TrendsAdapter(trendList)
    }
}
