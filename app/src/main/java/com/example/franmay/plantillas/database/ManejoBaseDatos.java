package com.example.franmay.plantillas.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.franmay.plantillas.proveedor_contenido.Contrato;

public class ManejoBaseDatos extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "plantillas.db";
    private static final int DATABASE_VERSION = 1;


    public ManejoBaseDatos(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sqlJugadores = "create table "
                            + Contrato.TABLA_JUGADORES
                            + " (_id INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, "
                            + Contrato.NOMBRE_JUGADOR + " TEXT , "
                            + Contrato.EQUIPO_JUGADOR + " TEXT, "
                            + Contrato.EDAD_JUGADOR + " INTEGER, "
                            + Contrato.PAIS_JUGADOR + " TEXT, "
                            + Contrato.POSICION_JUGADOR + " TEXT, "
                            + Contrato.INDICE_POSICION_JUGADOR + " INTEGER, "
                            + Contrato.FOTO_JUGADOR + " BLOB, "

                            + Contrato.JUGADOR_NACIONALIZADO + " INTEGER, "
                            + Contrato.JUGADOR_COMUNITARIO + " INTEGER, "
                            + Contrato.JUGADOR_EXTRACOMUNITARIO + " INTEGER, "

                            + Contrato.LIGA_NACIONAL + " INTEGER, "
                            + Contrato.LIGA_EXTRANJERA + " INTEGER, "
                            + Contrato.COPA_REY + " INTEGER, "
                            + Contrato.CHAMPIONS_LEAGUE+ " INTEGER, "
                            + Contrato.MUNDIAL + " INTEGER)";


        String sqlCuerpoTecnico = "create table "
                                + Contrato.TABLA_CUERPO_TECNICO
                                + " (_id INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, "
                                + Contrato.NOMBRE_CUERPO_TECNICO + " TEXT , "
                                + Contrato.EQUIPO_CUERPO_TECNICO + " TEXT, "
                                + Contrato.EDAD_CUERPO_TECNICO + " INTEGER, "
                                + Contrato.PAIS_CUERPO_TECNICO + " TEXT, "
                                + Contrato.CARGO_CUERPO_TECNICO + " TEXT, "
                                + Contrato.INDICE_CARGO + " INTEGER, "
                                + Contrato.FOTO_CUERPO_TECNICO + " BLOB)";


        db.execSQL(sqlJugadores);
        db.execSQL(sqlCuerpoTecnico);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String sqlJugadores = "DROP TABLE IF EXISTS " + Contrato.TABLA_JUGADORES;
        String sqlCuerpoTecnico = "DROP TABLE IF EXISTS " + Contrato.TABLA_CUERPO_TECNICO;

        db.execSQL(sqlJugadores);
        db.execSQL(sqlCuerpoTecnico);

        onCreate(db);
    }
}
