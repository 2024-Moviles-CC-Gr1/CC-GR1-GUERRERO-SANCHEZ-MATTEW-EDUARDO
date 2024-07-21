package com.example.torneoequipo

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class TeamsActivity : AppCompatActivity() {

    private lateinit var baseDatos: BaseDatos
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var equipos: MutableList<String>
    private var torneoId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)

        val torneoNombre = intent.getStringExtra("TORNEO")
        // Obtener el ID del torneo (a implementar)
        torneoId = obtenerIdTorneo(torneoNombre)

        baseDatos = BaseDatos(this)
        equipos = baseDatos.getTeams(torneoId).toMutableList()

        val listaEquipos = findViewById<ListView>(R.id.listaEquipos)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, equipos)
        listaEquipos.adapter = adapter

        val btnCrearEquipo = findViewById<Button>(R.id.btnCrearEquipo)
        btnCrearEquipo.setOnClickListener {
            mostrarDialogoCrearEquipo()
        }
    }

    private fun mostrarDialogoCrearEquipo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Crear Equipo")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            val nombreEquipo = input.text.toString()
            if (nombreEquipo.isNotEmpty()) {
                baseDatos.addTeam(nombreEquipo, torneoId)
                equipos.add(nombreEquipo)
                adapter.notifyDataSetChanged()
            }
        })
        builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })

        builder.show()
    }

    private fun obtenerIdTorneo(nombre: String?): Int {
        // Implementar método para obtener el ID del torneo basado en el nombre
        return 0 // Sustituir con el ID real
    }
}
