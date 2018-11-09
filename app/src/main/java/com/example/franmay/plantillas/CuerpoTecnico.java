package com.example.franmay.plantillas;

import android.os.Parcel;
import android.os.Parcelable;

public class CuerpoTecnico implements Parcelable {

    int foto;
    String nombre;
    String equipo;
    String pais;
    String cargo;
    int indiceCargo;
    int edad;


    public CuerpoTecnico()
    {
        foto = 0;
        nombre = "";
        equipo = "";
        pais = "";
        cargo = "";
        indiceCargo=0;
        edad = 0;
    }


    public CuerpoTecnico(int foto, String nombre, String equipo, String pais, String cargo, int indiceCargo, int edad)
    {
        this.foto = foto;
        this.nombre = nombre;
        this.equipo = equipo;
        this.pais = pais;
        this.cargo = cargo;
        this.indiceCargo = indiceCargo;
        this.edad = edad;
    }


    protected CuerpoTecnico(Parcel in)
    {
        foto = in.readInt();
        nombre = in.readString();
        equipo = in.readString();
        pais = in.readString();
        cargo = in.readString();
        indiceCargo = in.readInt();
        edad = in.readInt();
    }


    public static final Creator<CuerpoTecnico> CREATOR = new Creator<CuerpoTecnico>() {
        @Override
        public CuerpoTecnico createFromParcel(Parcel in) {
            return new CuerpoTecnico(in);
        }

        @Override
        public CuerpoTecnico[] newArray(int size) {
            return new CuerpoTecnico[size];
        }
    };


    public int getFoto() {
        return foto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEquipo() {
        return equipo;
    }

    public String getPais() {
        return pais;
    }

    public String getCargo() {
        return cargo;
    }

    public int getIndiceCargo() {
        return indiceCargo;
    }

    public int getEdad() {
        return edad;
    }


    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setIndiceCargo(int indiceCargo) {
        this.indiceCargo = indiceCargo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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
        dest.writeString(equipo);
        dest.writeString(pais);
        dest.writeString(cargo);
        dest.writeInt(indiceCargo);
        dest.writeInt(edad);
    }
}
