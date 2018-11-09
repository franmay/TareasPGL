package com.example.franmay.plantillas;

import android.os.Parcel;
import android.os.Parcelable;

public class InformacionGeneral implements Parcelable {

    int foto;
    String nombreEquipo;
    String presidente;
    String ciudad;
    String lugarEntrenamiento;
    int añoFundacion;


    public InformacionGeneral()
    {
        foto=0;
        nombreEquipo = "";
        presidente = "";
        ciudad = "";
        lugarEntrenamiento = "";
        añoFundacion = 0;
    }


    public InformacionGeneral(int foto, String nombreEquipo, String presidente, String ciudad, String lugarEntrenamiento, int añoFundacion)
    {
        this.foto = foto;
        this.nombreEquipo = nombreEquipo;
        this.presidente = presidente;
        this.ciudad = ciudad;
        this.lugarEntrenamiento = lugarEntrenamiento;
        this.añoFundacion = añoFundacion;
    }


    protected InformacionGeneral(Parcel in)
    {
        foto = in.readInt();
        nombreEquipo = in.readString();
        presidente = in.readString();
        ciudad = in.readString();
        lugarEntrenamiento = in.readString();
        añoFundacion = in.readInt();
    }

    public static final Creator<InformacionGeneral> CREATOR = new Creator<InformacionGeneral>() {
        @Override
        public InformacionGeneral createFromParcel(Parcel in) {
            return new InformacionGeneral(in);
        }

        @Override
        public InformacionGeneral[] newArray(int size) {
            return new InformacionGeneral[size];
        }
    };


    public int getFoto() {
        return foto;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public String getPresidente() {
        return presidente;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getLugarEntrenamiento() {
        return lugarEntrenamiento;
    }

    public int getAñoFundacion() {
        return añoFundacion;
    }


    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setLugarEntrenamiento(String lugarEntrenamiento) {
        this.lugarEntrenamiento = lugarEntrenamiento;
    }

    public void setAñoFundacion(int añoFundacion) {
        this.añoFundacion = añoFundacion;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(foto);
        dest.writeString(nombreEquipo);
        dest.writeString(presidente);
        dest.writeString(ciudad);
        dest.writeString(lugarEntrenamiento);
        dest.writeInt(añoFundacion);
    }
}
