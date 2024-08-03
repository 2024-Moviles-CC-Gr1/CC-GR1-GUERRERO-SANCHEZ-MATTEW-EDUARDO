package com.example.twitter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twitter.databinding.ActivityTrendsBinding

class TrendsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrendsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrendsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Datos de ejemplo
        val trendList = listOf(
            Trend("Trend 1"),
            Trend("Trend 2")
        )

        // Configura el RecyclerView para las Tendencias
        binding.recyclerViewTrends.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTrends.adapter = TrendsAdapter(trendList)

        // Configura el botón "Regresar" para volver a la actividad principal
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
