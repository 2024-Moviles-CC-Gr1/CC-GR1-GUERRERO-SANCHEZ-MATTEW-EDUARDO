package com.example.torneoequipo;

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperTorneos(
    contexto: Context? // this
) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        // Crear una tabla
        val scriptSQLCrearTablaTorneo =
            """
                CREATE TABLE TORNEO(
                    id_torneo INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre VARCHAR(50),
                    anio INTEGER,
                    premio DOUBLE,
                    patrocinador VARCHAR(100)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaTorneo)
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun crearTorneo(
        nombre: String,
        anio: String,
        premio: String,
        patrocinador: String?
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("anio", anio)
        valoresAGuardar.put("premcio", premio)
        valoresAGuardar.put("patrocinador", patrocinador)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "TORNEO", // nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarTorneoFormulario(id_torneo: Int): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(id_torneo.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "TORNEO",
                "id_torneo=?",
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt()==-1) false else true
    }

    fun actualizarTorneoFormulario(
        id_torneo: Int,
        nombre: String,
        anio: String,
        premio: String,
        patrocinador: String
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("anio", anio)
        valoresAActualizar.put("premio", premio)
        valoresAActualizar.put("patrocinador", patrocinador)
        val parametrosConsultaActualizar = arrayOf(id_torneo.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "TORNEO",
                valoresAActualizar,
                "id_torneo=?",
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt()==-1) false else true
    }

    fun consultarTorneoPorID(id_torneo: Int): BTorneos? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
        SELECT * FROM TORNEO WHERE id_torneo = ?
    """.trimIndent()
        val arregloParametrosConsultaLectura = arrayOf(id_torneo.toString())
        val resultadoConsultaLectura = baseDatosLectura
            .rawQuery(scriptConsultaLectura, arregloParametrosConsultaLectura)

        val existeAlMenosUno = resultadoConsultaLectura.moveToFirst()
        val arregloRespuesta = arrayListOf<BTorneos>()
        if (existeAlMenosUno) {
            do {
                val torneo = BTorneos(
                    resultadoConsultaLectura.getInt(0),    // id_torneo (Int)
                    resultadoConsultaLectura.getString(1), // nombre (String)
                    resultadoConsultaLectura.getInt(2), // anio (String)
                    resultadoConsultaLectura.getDouble(3), // premio (String)
                    resultadoConsultaLectura.getString(4)  // patrocinador (String)
                )
                arregloRespuesta.add(torneo)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return if (arregloRespuesta.isNotEmpty()) arregloRespuesta[0] else null
    }


}
