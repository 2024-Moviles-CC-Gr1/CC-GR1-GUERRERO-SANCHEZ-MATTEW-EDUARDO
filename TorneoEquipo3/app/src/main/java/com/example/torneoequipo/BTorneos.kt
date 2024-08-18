package com.example.torneoequipo;

import android.os.Parcel
import android.os.Parcelable

class BTorneos (
    var id_torneo: Int,
    var nombre: String,
    var anio: Int,
    var premio: Double,
    var patrocinador: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "$nombre $anio $premio $patrocinador"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_torneo)
        parcel.writeString(nombre)
        parcel.writeInt(anio)
        parcel.writeDouble(premio)
        parcel.writeString(patrocinador)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BTorneos> {
        override fun createFromParcel(parcel: Parcel): BTorneos {
            return BTorneos(parcel)
        }

        override fun newArray(size: Int): Array<BTorneos?> {
            return arrayOfNulls(size)
        }
    }

}