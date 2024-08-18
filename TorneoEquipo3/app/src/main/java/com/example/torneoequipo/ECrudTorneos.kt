package com.example.torneoequipo;

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class ECrudTorneos : AppCompatActivity() {

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_torneos),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_torneos)

        // BUSCAR TORNEO
        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val anio = findViewById<EditText>(R.id.input_anio)
            val premio = findViewById<EditText>(R.id.input_premio_torneo)
            val patrocinador = findViewById<EditText>(R.id.input_patrocinador)
            val torneo = EBaseDeDatosTorneos.tablaTorneos!!
                .consultarTorneoPorID(
                    id.text.toString().toInt()
                )
            if (torneo == null) {
                mostrarSnackbar("Torneo no encontrado")
                id.setText("")
                nombre.setText("")
                anio.setText("")
                premio.setText("")
                patrocinador.setText("")
            } else {
                id.setText(torneo.id_torneo.toString())
                nombre.setText(torneo.nombre)
                anio.setText(torneo.anio.toString())
                premio.setText(torneo.premio.toString())
                patrocinador.setText(torneo.patrocinador)
                mostrarSnackbar("Torneo encontrado")
            }
        }

        // FUNCION PARA CREAR UN TORNEO
        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd)
        botonCrearBDD.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val anio = findViewById<EditText>(R.id.input_anio)
            val premio = findViewById<EditText>(R.id.input_premio_torneo)
            val patrocinador = findViewById<EditText>(R.id.input_patrocinador)
            val respuesta = EBaseDeDatosTorneos.tablaTorneos!!
                .crearTorneo(
                    nombre.text.toString(),
                    anio.text.toString().toInt().toString(),
                    premio.text.toString().toDouble().toString(),
                    patrocinador.text.toString()
                )
            if (respuesta) mostrarSnackbar("Torneo Creado!")
        }

        // FUNCION PARA ACTUALIZAR UN TORNEO
        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
        botonActualizarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val anio = findViewById<EditText>(R.id.input_anio)
            val premio = findViewById<EditText>(R.id.input_premio_torneo)
            val patrocinador = findViewById<EditText>(R.id.input_patrocinador)
            val respuesta = EBaseDeDatosTorneos.tablaTorneos!!
                .actualizarTorneoFormulario(
                    id.text.toString().toInt(),
                    nombre.text.toString(),
                    anio.text.toString().toInt().toString(),
                    premio.text.toString().toDouble().toString(),
                    patrocinador.text.toString()
                )
            if (respuesta) mostrarSnackbar("Torneo Actualizado!")
        }

        // FUNCION PARA ELIMINAR UN TORNEO
        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)
        botonEliminarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id)
            val respuesta = EBaseDeDatosTorneos.tablaTorneos!!
                .eliminarTorneoFormulario(
                    id.text.toString().toInt()
                )
            if (respuesta) mostrarSnackbar("Torneo Eliminado!")
        }
    }
    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
