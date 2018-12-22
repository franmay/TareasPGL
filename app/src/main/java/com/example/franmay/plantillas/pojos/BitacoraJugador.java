package com.example.franmay.plantillas.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import static com.example.franmay.plantillas.variables_globales.VariablesGlobales.SIN_VALOR_INT;

public class BitacoraJugador implements Parcelable {

    int id;
    int id_Jugador;
    int operacion;

    public BitacoraJugador()
    {
        id = SIN_VALOR_INT;
        id_Jugador = SIN_VALOR_INT;
        operacion = SIN_VALOR_INT;
    }


    public BitacoraJugador(int id, int id_Jugador, int operacion)
    {
        this.id = id;
        this.id_Jugador = id_Jugador;
        this.operacion = operacion;
    }


    protected BitacoraJugador(Parcel in) {
        id = in.readInt();
        id_Jugador = in.readInt();
        operacion = in.readInt();
    }

    public static final Creator<BitacoraJugador> CREATOR = new Creator<BitacoraJugador>() {
        @Override
        public BitacoraJugador createFromParcel(Parcel in) {
            return new BitacoraJugador(in);
        }

        @Override
        public BitacoraJugador[] newArray(int size) {
            return new BitacoraJugador[size];
        }
    };

    public int getId()
    {
        return id;
    }

    public int getId_Jugador()
    {
        return id_Jugador;
    }

    public int getOperacion()
    {
        return operacion;
    }


    public void setId(int id)
    {
        this.id = id;
    }

    public void setId_Jugador(int id_Jugador)
    {
        this.id_Jugador = id_Jugador;
    }

    public void setOperacion(int operacion)
    {
        this.operacion = operacion;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeInt(id_Jugador);
        dest.writeInt(operacion);
    }
}
