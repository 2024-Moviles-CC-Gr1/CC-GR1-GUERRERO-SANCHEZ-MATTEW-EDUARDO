package com.example.twitter

import android.content.Intent
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

        // Datos de ejemplo para News
        val newsList = listOf(
            News("@AstroDiscoveries", "Científicos descubren un nuevo exoplaneta en la zona habitable."),
            News("@TechGuru", "El último iPhone ahora incluye tecnología de carga solar."),
            News("@GlobalUpdates", "Aumento en las tensiones entre países de Medio Oriente."),
            News("@SportsFanatic", "El Real Madrid gana el clásico contra el Barcelona."),
            News("@MovieMania", "Lanzan el tráiler de la nueva película de Marvel."),
            News("@MarketWatch", "El mercado de valores muestra signos de recuperación."),
            News("@HealthFirst", "Nuevas recomendaciones sobre la vacuna contra la gripe."),
            News("@ArtLovers", "Exposición de arte moderno abre sus puertas en Nueva York."),
            News("@PoliticalNews", "Debate presidencial atrae a millones de espectadores."),
            News("@EduTech", "Universidades adoptan nuevas tecnologías para el aprendizaje remoto."),
            News("@EcoWarrior", "Campaña global para reducir el uso de plásticos."),
            News("@ScienceDaily", "Investigación revela los secretos del ADN antiguo."),
            News("@TravelGuide", "Destinos turísticos reabren tras la pandemia."),
            News("@FashionTrends", "Tendencias de moda para la próxima temporada."),
            News("@MusicVibes", "Artista famoso lanza nuevo álbum con colaboraciones sorprendentes."),
            News("@TechInnovations", "La inteligencia artificial se integra en aplicaciones de uso diario."),
            News("@SoccerUpdates", "Resultados de la última jornada de la liga de fútbol."),
            News("@LocalNews", "Nueva infraestructura vial mejora el tráfico en la ciudad."),
            News("@CineWorld", "Premios de la Academia anuncian sus nominados."),
            News("@MentalHealthMatters", "Técnicas efectivas para manejar el estrés en tiempos modernos.")
        )


        // Configura el RecyclerView para las Noticias
        binding.recyclerViewNews.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewNews.adapter = NewsAdapter(newsList)

        // Configura el botón "Search" para ir a la actividad de tendencias
        binding.btnBuscar.setOnClickListener {
            val intent = Intent(this, TrendsActivity::class.java)
            startActivity(intent)
        }
    }
}
