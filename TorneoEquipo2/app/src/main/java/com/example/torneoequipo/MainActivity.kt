package com.example.torneoequipo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var dbManager: Cruds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbManager = Cruds(this)

        val buttonCreateTournament: Button = findViewById(R.id.button_create_tournament)
        val listViewTournaments: ListView = findViewById(R.id.listview_tournaments)

        buttonCreateTournament.setOnClickListener {
            dbManager.insertTournament("New Tournament")
            loadTournaments()
        }

        listViewTournaments.setOnItemClickListener { _, _, position, id ->
            val intent = Intent(this, TeamsActivity::class.java)
            intent.putExtra("tournament_id", id)
            startActivity(intent)
        }

        loadTournaments()
    }

    private fun loadTournaments() {
        val cursor = dbManager.getTournaments()
        val from = arrayOf("name")
        val to = intArrayOf(android.R.id.text1)
        val adapter = SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, from, to, 0)
        findViewById<ListView>(R.id.listview_tournaments).adapter = adapter
    }
}