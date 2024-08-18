package com.example.torneoequipo;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class ECrudEquipos : AppCompatActivity() {

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_equipos),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_equipos)

        // BUSCAR EQUIPO
        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_equipo)
            val idTorneo = findViewById<EditText>(R.id.input_id_torneo)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val numeroJugadores = findViewById<EditText>(R.id.input_numero_jugadores)
            val fechaFundacion = findViewById<EditText>(R.id.input_fecha_fundacion)
            val presupuesto = findViewById<EditText>(R.id.input_presupuesto_equipo)
            val equipo = EBaseDeDatosEquipos.tablaEquipos!!
                .consultarEquipoPorID(
                    id.text.toString().toInt()
                )
            if (equipo == null) {
                mostrarSnackbar("Equipo no encontrado")
                id.setText("")
                idTorneo.setText("")
                nombre.setText("")
                numeroJugadores.setText("")
                fechaFundacion.setText("")
                presupuesto.setText("")
            } else {
                id.setText(equipo.id_equipo.toString())
                idTorneo.setText(equipo.id_torneo.toString())
                nombre.setText(equipo.nombre)
                numeroJugadores.setText(equipo.numero_jugadores.toString())
                fechaFundacion.setText(equipo.fecha_fundacion)
                presupuesto.setText(equipo.presupuesto.toString())
                mostrarSnackbar("Equipo encontrado")
            }
        }

        // FUNCION PARA CREAR UN EQUIPO
        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd)
        botonCrearBDD.setOnClickListener {
            val idTorneo = findViewById<EditText>(R.id.input_id_torneo)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val numeroJugadores = findViewById<EditText>(R.id.input_numero_jugadores)
            val fechaFundacion = findViewById<EditText>(R.id.input_fecha_fundacion)
            val presupuesto = findViewById<EditText>(R.id.input_presupuesto_equipo)
            val respuesta = EBaseDeDatosEquipos.tablaEquipos!!
                .crearEquipo(
                    idTorneo.text.toString().toInt(),
                    nombre.text.toString(),
                    numeroJugadores.text.toString().toInt(),
                    fechaFundacion.text.toString(),
                    presupuesto.text.toString().toDouble()
                )
            if (respuesta) mostrarSnackbar("Equipo Creado!")
        }

        // FUNCION PARA ACTUALIZAR UN EQUIPO
        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
        botonActualizarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_equipo)
            val idTorneo = findViewById<EditText>(R.id.input_id_torneo)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val numeroJugadores = findViewById<EditText>(R.id.input_numero_jugadores)
            val fechaFundacion = findViewById<EditText>(R.id.input_fecha_fundacion)
            val presupuesto = findViewById<EditText>(R.id.input_presupuesto_equipo)
            val respuesta = EBaseDeDatosEquipos.tablaEquipos!!
                .actualizarEquipoFormulario(
                    id.text.toString().toInt(),
                    idTorneo.text.toString().toInt(),
                    nombre.text.toString(),
                    numeroJugadores.text.toString().toInt(),
                    fechaFundacion.text.toString(),
                    presupuesto.text.toString().toDouble()
                )
            if (respuesta) mostrarSnackbar("Equipo Actualizado!")
        }

        // FUNCION PARA ELIMINAR UN EQUIPO
        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)
        botonEliminarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_equipo)
            val respuesta = EBaseDeDatosEquipos.tablaEquipos!!
                .eliminarEquipoFormulario(
                    id.text.toString().toInt()
                )
            if (respuesta) mostrarSnackbar("Equipo Eliminado!")
        }
    }
}
