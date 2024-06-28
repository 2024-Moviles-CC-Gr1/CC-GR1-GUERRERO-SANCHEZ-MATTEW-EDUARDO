import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

data class Equipo(
    val nombre: String,
    val primeraParticipacion: Date,
    var vecesParticipado: Int,
    var participando: Boolean,
    var partidosGanados: Int,
    var partidosEmpatados: Int,
    var partidosPerdidos: Int
) {
    private val dateFormat = SimpleDateFormat("yyyy")

    override fun toString(): String {
        val year = dateFormat.format(primeraParticipacion)
        return "Nombre: $nombre, Primera Participación: $year, Veces Participado: $vecesParticipado," +
                " Participando: $participando, Partidos Ganados: $partidosGanados, Partidos Empatados:" +
                " $partidosEmpatados, Partidos Perdidos: $partidosPerdidos"
    }
}

data class Competencia(
    val nombre: String
)

class FutbolManager {
    private val dateFormat = SimpleDateFormat("yyyy")

    // Crear un equipo y añadirlo a la competencia
    fun crearEquipo(competencia: Competencia, equipo: Equipo) {
        val archivoCompetencia = getArchivoCompetencia(competencia)
        archivoCompetencia.appendText("${equipo.nombre},${dateFormat.format(equipo.primeraParticipacion)},${equipo.vecesParticipado},${equipo.participando},${equipo.partidosGanados},${equipo.partidosEmpatados},${equipo.partidosPerdidos}\n")
    }

    // Ver equipos de una competencia
    fun verEquipos(competencia: Competencia): List<Equipo> {
        val archivoCompetencia = getArchivoCompetencia(competencia)
        return if (archivoCompetencia.exists()) {
            archivoCompetencia.readLines().map { line ->
                val data = line.split(",")
                Equipo(
                    data[0],
                    dateFormat.parse(data[1]),
                    data[2].toInt(),
                    data[3].toBoolean(),
                    data[4].toInt(),
                    data[5].toInt(),
                    data[6].toInt()
                )
            }
        } else {
            emptyList()
        }
    }

    // Actualizar un equipo en una competencia
    fun actualizarEquipo(competencia: Competencia, equipoActualizado: Equipo) {
        val equipos = verEquipos(competencia).toMutableList()
        val index = equipos.indexOfFirst { it.nombre == equipoActualizado.nombre }
        if (index != -1) {
            equipos[index] = equipoActualizado
            guardarCompetencia(competencia, equipos)
        }
    }

    // Eliminar un equipo de una competencia
    fun eliminarEquipo(competencia: Competencia, nombreEquipo: String) {
        val equipos = verEquipos(competencia).filter { it.nombre != nombreEquipo }
        guardarCompetencia(competencia, equipos)
    }

    // Guardar la competencia y equipos en el archivo
    private fun guardarCompetencia(competencia: Competencia, equipos: List<Equipo>) {
        val archivoCompetencia = getArchivoCompetencia(competencia)
        archivoCompetencia.printWriter().use { out ->
            equipos.forEach { equipo ->
                out.println("${equipo.nombre},${dateFormat.format(equipo.primeraParticipacion)},${equipo.vecesParticipado},${equipo.participando},${equipo.partidosGanados},${equipo.partidosEmpatados},${equipo.partidosPerdidos}")
            }
        }
    }

    // Obtener el archivo de la competencia
    private fun getArchivoCompetencia(competencia: Competencia): File {
        return File("${competencia.nombre}.txt")
    }
}

fun main() {
    val futbolManager = FutbolManager()
    val dateFormat = SimpleDateFormat("yyyy")

    while (true) {
        println("\nGestión de Competencias de Fútbol")
        println("1. Seleccionar competencia")
        println("2. Crear nueva competencia")
        println("3. Salir")
        print("Seleccione una opción: ")

        when (readLine()?.toInt()) {
            1 -> {
                println("Seleccione una competencia existente:")
                val competencias = File(".").listFiles { _, name -> name.endsWith(".txt") }?.map { it.nameWithoutExtension } ?: emptyList()
                competencias.forEachIndexed { index, nombre -> println("${index + 1}. $nombre") }

                val opcion = readLine()?.toIntOrNull()
                if (opcion != null && opcion in 1..competencias.size) {
                    val nombreCompetencia = competencias[opcion - 1]
                    val competencia = Competencia(nombreCompetencia)
                    gestionarCompetencia(futbolManager, competencia, dateFormat)
                } else {
                    println("Opción no válida.")
                }
            }
            2 -> {
                print("Ingrese el nombre de la nueva competencia: ")
                val nombreCompetencia = readLine()!!
                val competencia = Competencia(nombreCompetencia)
                gestionarCompetencia(futbolManager, competencia, dateFormat)
            }
            3 -> {
                println("Saliendo...")
                break
            }
            else -> println("Opción no válida.")
        }
    }
}

fun gestionarCompetencia(futbolManager: FutbolManager, competencia: Competencia, dateFormat: SimpleDateFormat) {
    while (true) {
        println("\nGestión de Competencia: ${competencia.nombre}")
        println("1. Crear equipo")
        println("2. Ver equipos")
        println("3. Actualizar equipo")
        println("4. Eliminar equipo")
        println("5. Volver al menú principal")
        print("Seleccione una opción: ")

        when (readLine()?.toInt()) {
            1 -> {
                println("Crear nuevo equipo en ${competencia.nombre}")
                print("Nombre del equipo: ")
                val nombreEquipo = readLine()!!
                print("Fecha de Primera Participación (yyyy): ")
                val fecha = dateFormat.parse(readLine()!!)
                print("Veces Participado: ")
                val veces = readLine()!!.toInt()
                print("Participando (esta temporada) (true/false): ")
                val participando = readLine()!!.toBoolean()
                print("Partidos Ganados: ")
                val ganados = readLine()!!.toInt()
                print("Partidos Empatados: ")
                val empatados = readLine()!!.toInt()
                print("Partidos Perdidos: ")
                val perdidos = readLine()!!.toInt()

                val equipo = Equipo(nombreEquipo, fecha, veces, participando, ganados, empatados, perdidos)
                futbolManager.crearEquipo(competencia, equipo)
            }
            2 -> {
                println("Equipos en ${competencia.nombre}:")
                val equipos = futbolManager.verEquipos(competencia)
                equipos.forEach {
                    println(it)
                    println() // Agrega un salto de línea entre cada equipo
                }
            }
            3 -> {
                println("Actualizar equipo en ${competencia.nombre}")
                print("Nombre del equipo a actualizar: ")
                val nombreEquipo = readLine()!!
                val equipo = futbolManager.verEquipos(competencia).find { it.nombre == nombreEquipo }
                if (equipo != null) {
                    print("Nueva Fecha de Participación (yyyy): ")
                    val fecha = dateFormat.parse(readLine()!!)
                    print("Nuevas Veces Participado: ")
                    val veces = readLine()!!.toInt()
                    print("Participando (esta temporada) (true/false): ")
                    val participando = readLine()!!.toBoolean()
                    print("Nuevos Partidos Ganados: ")
                    val ganados = readLine()!!.toInt()
                    print("Nuevos Partidos Empatados: ")
                    val empatados = readLine()!!.toInt()
                    print("Nuevos Partidos Perdidos: ")
                    val perdidos = readLine()!!.toInt()

                    val equipoActualizado = equipo.copy(primeraParticipacion = fecha, vecesParticipado = veces, participando = participando, partidosGanados = ganados, partidosEmpatados = empatados, partidosPerdidos = perdidos)
                    futbolManager.actualizarEquipo(competencia, equipoActualizado)
                } else {
                    println("Equipo no encontrado en ${competencia.nombre}.")
                }
            }
            4 -> {
                println("Eliminar equipo de ${competencia.nombre}")
                print("Nombre del equipo a eliminar: ")
                val nombreEquipo = readLine()!!
                futbolManager.eliminarEquipo(competencia, nombreEquipo)
            }
            5 -> {
                println("Volviendo al menú principal...")
                break
            }
            else -> println("Opción no válida.")
        }
    }
}
