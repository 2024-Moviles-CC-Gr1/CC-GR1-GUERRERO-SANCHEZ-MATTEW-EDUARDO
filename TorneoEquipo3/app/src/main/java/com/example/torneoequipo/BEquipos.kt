package com.example.torneoequipo;

import android.os.Parcel
import android.os.Parcelable

class BEquipos (
    var id_equipo: Int,
    var id_torneo: Int,
    var nombre: String,
    var codigo: String,
    var fecha_fundacion: String,
    var presupuesto: Double,
    var numero_jugadores: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun toString(): String {
        return "$id_torneo $nombre $codigo $fecha_fundacion $presupuesto $numero_jugadores"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_equipo)
        parcel.writeInt(id_torneo)
        parcel.writeString(nombre)
        parcel.writeString(codigo)
        parcel.writeString(fecha_fundacion)
        parcel.writeDouble(presupuesto)
        parcel.writeInt(numero_jugadores)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BEquipos> {
        override fun createFromParcel(parcel: Parcel): BEquipos {
            return BEquipos(parcel)
        }

        override fun newArray(size: Int): Array<BEquipos?> {
            return arrayOfNulls(size)
        }
    }

}