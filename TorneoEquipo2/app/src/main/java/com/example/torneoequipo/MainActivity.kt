package com.example.torneoequipo

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var baseDatos: BaseDatos
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var torneos: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        baseDatos = BaseDatos(this)
        torneos = baseDatos.getTournaments().toMutableList()

        val listaTorneos = findViewById<ListView>(R.id.listaTorneos)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, torneos)
        listaTorneos.adapter = adapter

        val btnCrearTorneo = findViewById<Button>(R.id.btnCrearTorneo)
        btnCrearTorneo.setOnClickListener {
            mostrarDialogoCrearTorneo()
        }

        listaTorneos.setOnItemClickListener { _, _, position, _ ->
            val torneoSeleccionado = torneos[position]
            val intent = Intent(this, TeamsActivity::class.java)
            intent.putExtra("TORNEO", torneoSeleccionado)
            startActivity(intent)
        }
    }

    private fun mostrarDialogoCrearTorneo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Crear Torneo")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            val nombreTorneo = input.text.toString()
            if (nombreTorneo.isNotEmpty()) {
                baseDatos.addTournament(nombreTorneo)
                torneos.add(nombreTorneo)
                adapter.notifyDataSetChanged()
            }
        })
        builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })

        builder.show()
    }
}
