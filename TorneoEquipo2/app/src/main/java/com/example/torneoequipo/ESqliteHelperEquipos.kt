package com.example.torneoequipo;

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEquipos(
    contexto: Context? // this
) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        // Crear una tabla
        val scriptSQLCrearTablaEquipos =
            """
                CREATE TABLE EQUIPO(
                    id_equipo INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_torneo INTEGER,
                    nombre VARCHAR(50),
                    numero_jugadores INTEGER,
                    fecha_creacion VARCHAR(50),
                    presupuesto DOUBLE
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEquipos)
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun crearEquipo(
        id_torneo: Int,
        nombre: String,
        numero_jugadores: Int,
        fecha_creacion: String,
        presupuesto: Double
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id_torneo", id_torneo)
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("numero_jugadores", numero_jugadores)
        valoresAGuardar.put("fecha_creacion", fecha_creacion)
        valoresAGuardar.put("presupuesto", presupuesto)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "EQUIPO", // nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return resultadoGuardar != -1L
    }

    fun eliminarEquipoFormulario(id_equipo: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id_equipo.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "EQUIPO",
                "id_equipo=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return resultadoEliminacion != -1
    }

    fun actualizarEquipoFormulario(
        id_equipo: Int,
        id_torneo: Int,
        nombre: String,
        numero_jugadores: Int,
        fecha_creacion: String,
        presupuesto: Double
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("id_torneo", id_torneo)
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("numero_jugadores", numero_jugadores)
        valoresAActualizar.put("fecha_creacion", fecha_creacion)
        valoresAActualizar.put("presupuesto", presupuesto)
        val parametrosConsultaActualizar = arrayOf(id_equipo.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "EQUIPO",
                valoresAActualizar,
                "id_equipo=?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun consultarEquipoPorID(id_equipo: Int): BEquipos? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
        SELECT * FROM EQUIPO WHERE id_equipo = ?
    """.trimIndent()
        val arregloParametrosConsultaLectura = arrayOf(
            id_equipo.toString()
        )
        val resultadoConsultaLectura = baseDatosLectura
            .rawQuery(
                scriptConsultaLectura,
                arregloParametrosConsultaLectura
            )

        val existeAlMenosUno = resultadoConsultaLectura.moveToFirst()
        val arregloRespuesta = arrayListOf<BEquipos>()
        if (existeAlMenosUno) {
            do {
                val equipos = BEquipos(
                    resultadoConsultaLectura.getInt(0),  // id_equipo
                    resultadoConsultaLectura.getInt(1),  // id_torneo
                    resultadoConsultaLectura.getString(2), // nombre
                    "", // código (si no se usa, puedes pasar un string vacío o ajustar tu clase)
                    resultadoConsultaLectura.getString(4), // fecha_creacion
                    resultadoConsultaLectura.getDouble(5), // presupuesto
                    resultadoConsultaLectura.getInt(3)  // numero_jugadores
                )
                arregloRespuesta.add(equipos)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return if (arregloRespuesta.isNotEmpty()) arregloRespuesta[0] else null
    }



}