package com.example.torneoequipo

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity

class TeamsActivity : AppCompatActivity() {
    private lateinit var dbManager: Cruds
    private var tournamentId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)

        dbManager = Cruds(this)
        tournamentId = intent.getLongExtra("tournament_id", 0)

        val buttonCreateTeam: Button = findViewById(R.id.button_create_team)
        val listViewTeams: ListView = findViewById(R.id.listview_teams)

        buttonCreateTeam.setOnClickListener {
            dbManager.insertTeam("New Team", tournamentId.toInt())
            loadTeams()
        }

        loadTeams()
    }

    private fun loadTeams() {
        val cursor = dbManager.getTeams(tournamentId.toInt())
        val from = arrayOf("name")
        val to = intArrayOf(android.R.id.text1)
        val adapter = SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, from, to, 0)
        findViewById<ListView>(R.id.listview_teams).adapter = adapter
    }
}