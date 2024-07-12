package com.example.a2024accgr1megs

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Adrian", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Vicente","b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3,"Carolina","c@c.com")
                )


        }

        //Crea metodos como
        //Fun guardarEntrenador(){}
        //Fun busca, editar, eliminar, etc etc
        //Modelo vista compilador
        //no crear un arreglo en el listview
        //crear metodos usar en las actividades
        //se va a modificar para el sqlite
    }

}