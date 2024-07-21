package com.example.torneoequipo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class Cruds(context: Context) {
    private val dbHelper = BaseDatos(context)
    private val db = dbHelper.writableDatabase

    fun insertTournament(name: String) {
        val values = ContentValues().apply {
            put("name", name)
        }
        db.insert("tournaments", null, values)
    }

    fun getTournaments(): Cursor {
        return db.query("tournaments", arrayOf("_id", "name"), null, null, null, null, null)
    }

    fun insertTeam(name: String, tournamentId: Int) {
        val values = ContentValues().apply {
            put("name", name)
            put("tournament_id", tournamentId)
        }
        db.insert("teams", null, values)
    }

    fun getTeams(tournamentId: Int): Cursor {
        return db.query("teams", arrayOf("_id", "name"), "tournament_id=?", arrayOf(tournamentId.toString()), null, null, null)
    }
}