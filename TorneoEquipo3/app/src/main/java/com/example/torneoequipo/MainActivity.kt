package com.example.torneoequipo;

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.id_layout_main),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicializar Base de Datos
        EBaseDeDatosTorneos.tablaTorneos = ESqliteHelperTorneos(
            this
        )
        EBaseDeDatosEquipos.tablaEquipos = ESqliteHelperEquipos(
            this
        )

        val botonAutos = findViewById<Button>(R.id.btn_torneos)
        botonAutos.setOnClickListener {
            irActividad((ECrudTorneos::class.java))
        }

        val botonPartes = findViewById<Button>(R.id.btn_equipos)
        botonPartes.setOnClickListener {
            irActividad((ECrudEquipos::class.java))
        }


    }

    // Mensaje de prueba
    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}