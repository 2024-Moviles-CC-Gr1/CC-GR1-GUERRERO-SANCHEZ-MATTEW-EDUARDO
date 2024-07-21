package com.example.torneoequipo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTournamentsTable = """
            CREATE TABLE $TABLE_TOURNAMENTS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL
            )
        """.trimIndent()

        val createTeamsTable = """
            CREATE TABLE $TABLE_TEAMS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_TOURNAMENT_ID INTEGER,
                FOREIGN KEY($COLUMN_TOURNAMENT_ID) REFERENCES $TABLE_TOURNAMENTS($COLUMN_ID)
            )
        """.trimIndent()

        db.execSQL(createTournamentsTable)
        db.execSQL(createTeamsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TEAMS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TOURNAMENTS")
        onCreate(db)
    }

    fun addTournament(name: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
        }
        db.insert(TABLE_TOURNAMENTS, null, values)
        db.close()
    }

    fun getTournaments(): List<String> {
        val tournaments = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_TOURNAMENTS", null)
        if (cursor.moveToFirst()) {
            do {
                tournaments.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return tournaments
    }

    fun addTeam(name: String, tournamentId: Int) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_TOURNAMENT_ID, tournamentId)
        }
        db.insert(TABLE_TEAMS, null, values)
        db.close()
    }

    fun getTeams(tournamentId: Int): List<String> {
        val teams = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_TEAMS WHERE $COLUMN_TOURNAMENT_ID = $tournamentId", null)
        if (cursor.moveToFirst()) {
            do {
                teams.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return teams
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "TorneoEquipo.db"
        const val TABLE_TOURNAMENTS = "Tournaments"
        const val TABLE_TEAMS = "Teams"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_TOURNAMENT_ID = "tournament_id"
    }
}
