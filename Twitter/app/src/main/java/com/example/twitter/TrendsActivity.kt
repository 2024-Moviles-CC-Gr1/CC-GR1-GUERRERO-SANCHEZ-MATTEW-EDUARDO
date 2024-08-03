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

        // Datos de ejemplo para Trends
        val trendList = listOf(
            Trend("Trend 1\n#Elecciones2024"),
            Trend("Trend 2\n#JuegosOlimpicos"),
            Trend("Trend 3\n#COVID19"),
            Trend("Trend 4\n#CambioClimatico"),
            Trend("Trend 5\n#Tecnologia2024"),
            Trend("Trend 6\n#EnergiasRenovables"),
            Trend("Trend 7\n#Deportes2024"),
            Trend("Trend 8\n#SaludPublica"),
            Trend("Trend 9\n#EducacionFutura"),
            Trend("Trend 10\n#Innovacion")

        )

        // Configura el RecyclerView para las Tendencias
        binding.recyclerViewTrends.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTrends.adapter = TrendsAdapter(trendList)

        // Configura el botón "Back" para volver a la actividad principal
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
