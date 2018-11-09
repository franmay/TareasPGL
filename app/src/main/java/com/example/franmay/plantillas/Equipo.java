package com.example.franmay.plantillas;

import android.os.Parcel;
import android.os.Parcelable;

public class Equipo implements Parcelable {

    int foto;
    String equipo;
    int indiceEquipo;


    public Equipo()
    {
        foto=0;
        equipo="";
        indiceEquipo=0;
    }


    public Equipo(int foto, String equipo, int indiceEquipo)
    {
        this.foto = foto;
        this.equipo = equipo;
        this.indiceEquipo = indiceEquipo;
    }


    protected Equipo(Parcel in) {
        foto = in.readInt();
        equipo = in.readString();
        indiceEquipo = in.readInt();
    }

    public static final Creator<Equipo> CREATOR = new Creator<Equipo>() {
        @Override
        public Equipo createFromParcel(Parcel in) {
            return new Equipo(in);
        }

        @Override
        public Equipo[] newArray(int size) {
            return new Equipo[size];
        }
    };

    public int getFoto() {
        return foto;
    }

    public String getEquipo() {
        return equipo;
    }

    public int getIndiceEquipo() {
        return indiceEquipo;
    }


    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public void setIndiceEquipo(int indiceEquipo) {
        this.indiceEquipo = indiceEquipo;
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
        dest.writeString(equipo);
        dest.writeInt(indiceEquipo);
    }
}
