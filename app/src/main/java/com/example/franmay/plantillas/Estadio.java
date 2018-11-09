package com.example.franmay.plantillas;

import android.os.Parcel;
import android.os.Parcelable;

public class Estadio implements Parcelable {

    int foto;
    String nombre;
    int capacidad;
    int añoConstruccion;
    String dimension;;


    public Estadio()
    {
        foto = 0;
        nombre = "";
        capacidad = 0;
        añoConstruccion = 0;
        dimension = "";
    }

    public Estadio(int foto, String nombre, int capacidad, int añoConstruccion, String dimension)
    {
        this.foto = foto;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.añoConstruccion = añoConstruccion;
        this.dimension = dimension;
    }


    protected Estadio(Parcel in) {
        foto = in.readInt();
        nombre = in.readString();
        capacidad = in.readInt();
        añoConstruccion = in.readInt();
        dimension = in.readString();
    }

    public static final Creator<Estadio> CREATOR = new Creator<Estadio>() {
        @Override
        public Estadio createFromParcel(Parcel in) {
            return new Estadio(in);
        }

        @Override
        public Estadio[] newArray(int size) {
            return new Estadio[size];
        }
    };

    public int getFoto() {
        return foto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getAñoConstruccion() {
        return añoConstruccion;
    }

    public String getDimension() {
        return dimension;
    }


    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setAñoConstruccion(int añoConstruccion) {
        this.añoConstruccion = añoConstruccion;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(foto);
        dest.writeString(nombre);
        dest.writeInt(capacidad);
        dest.writeInt(añoConstruccion);
        dest.writeString(dimension);
    }
}
