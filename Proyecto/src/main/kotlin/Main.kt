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
        return "Nombre: $nombre, Primera Participación: $year, Veces Participado: $vecesParticipado, Participando: $participando, Partidos Ganados: $partidosGanados, Partidos Empatados: $partidosEmpatados, Partidos Perdidos: $partidosPerdidos"
    }
}

data class Competencia(
    val nombre: String,
    var equipos: MutableList<Equipo>
)

class FutbolManager {
    private val archivoCompetencia = "competencia.txt"
    private val dateFormat = SimpleDateFormat("yyyy")

    // Crear un equipo y añadirlo a la competencia
    fun crearEquipo(competencia: Competencia, equipo: Equipo) {
        competencia.equipos.add(equipo)
        guardarCompetencia(competencia)
    }

    // Leer equipos desde el archivo
    private fun leerEquipos(lines: List<String>): MutableList<Equipo> {
        val equipos = mutableListOf<Equipo>()
        for (line in lines) {
            val data = line.split(",")
            val date = dateFormat.parse(data[1])
            val participando = data[3].toBoolean()
            equipos.add(Equipo(data[0], date, data[2].toInt(), participando, data[4].toInt(), data[5].toInt(), data[6].toInt()))
        }
        return equipos
    }

    // Actualizar un equipo
    fun actualizarEquipo(competencia: Competencia, equipoActualizado: Equipo) {
        val index = competencia.equipos.indexOfFirst { it.nombre == equipoActualizado.nombre }
        if (index != -1) {
            competencia.equipos[index] = equipoActualizado
            guardarCompetencia(competencia)
        }
    }

    // Eliminar un equipo
    fun eliminarEquipo(competencia: Competencia, nombreEquipo: String) {
        competencia.equipos.removeAll { it.nombre == nombreEquipo }
        guardarCompetencia(competencia)
    }

    // Guardar la competencia y equipos en el archivo
    fun guardarCompetencia(competencia: Competencia) {
        File(archivoCompetencia).printWriter().use { out ->
            out.println(competencia.nombre)
            competencia.equipos.forEach { equipo ->
                val date = dateFormat.format(equipo.primeraParticipacion)
                out.println("${equipo.nombre},$date,${equipo.vecesParticipado},${equipo.participando},${equipo.partidosGanados},${equipo.partidosEmpatados},${equipo.partidosPerdidos}")
            }
        }
    }

    // Leer la competencia y equipos desde el archivo
    fun leerCompetencia(): Competencia {
        val file = File(archivoCompetencia)
        return if (file.exists()) {
            val lines = file.readLines()
            val nombre = lines.first().trim()
            val equipos = leerEquipos(lines.drop(1))
            Competencia(nombre, equipos)
        } else {
            Competencia("Competencia de Fútbol", mutableListOf())
        }
    }
}

fun main() {
    val futbolManager = FutbolManager()
    var competencia = futbolManager.leerCompetencia()
    val dateFormat = SimpleDateFormat("yyyy")

    while (true) {
        println("\nGestión de Competencia de Fútbol")
        println("1. Crear equipo")
        println("2. Leer equipos")
        println("3. Actualizar equipo")
        println("4. Eliminar equipo")
        println("5. Guardar competencia")
        println("6. Salir")
        print("Seleccione una opción: ")

        when (readLine()?.toInt()) {
            1 -> {
                println("Crear nuevo equipo")
                print("Nombre: ")
                val nombre = readLine()!!
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

                val equipo = Equipo(nombre, fecha, veces, participando, ganados, empatados, perdidos)
                futbolManager.crearEquipo(competencia, equipo)
            }
            2 -> {
                println("Equipos en la competencia:")
                competencia.equipos.forEach {
                    println(it)
                    println() // Agrega un salto de línea entre cada equipo
                }
            }
            3 -> {
                println("Actualizar equipo")
                print("Nombre del equipo a actualizar: ")
                val nombre = readLine()!!
                val equipo = competencia.equipos.find { it.nombre == nombre }
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
                    println("Equipo no encontrado.")
                }
            }
            4 -> {
                println("Eliminar equipo")
                print("Nombre del equipo a eliminar: ")
                val nombre = readLine()!!
                futbolManager.eliminarEquipo(competencia, nombre)
            }
            5 -> {
                futbolManager.guardarCompetencia(competencia)
                println("Competencia guardada.")
            }
            6 -> {
                println("Saliendo...")
                break
            }
            else -> println("Opción no válida.")
        }
    }
}
