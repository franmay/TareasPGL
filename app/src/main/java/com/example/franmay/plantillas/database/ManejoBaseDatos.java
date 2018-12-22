package com.example.franmay.plantillas.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.franmay.plantillas.proveedor_contenido.Contrato;

public class ManejoBaseDatos extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "plantillas.db";
    private static final int DATABASE_VERSION = 18;


    public ManejoBaseDatos(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sqlJugadores = "create table if not exists "
                            + Contrato.TABLA_JUGADORES
                            //+ " (_id INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, "
                            + "("
                            + Contrato.ID_JUGADOR + " INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, "
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


        String sqlCuerpoTecnico = "create table if not exists "
                                + Contrato.TABLA_CUERPO_TECNICO
                                //+ " (_id INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, "
                                + "("
                                + Contrato.ID_CUERPO_TECNICO + " INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, "
                                + Contrato.NOMBRE_CUERPO_TECNICO + " TEXT , "
                                + Contrato.EQUIPO_CUERPO_TECNICO + " TEXT, "
                                + Contrato.EDAD_CUERPO_TECNICO + " INTEGER, "
                                + Contrato.PAIS_CUERPO_TECNICO + " TEXT, "
                                + Contrato.CARGO_CUERPO_TECNICO + " TEXT, "
                                + Contrato.INDICE_CARGO + " INTEGER, "
                                + Contrato.FOTO_CUERPO_TECNICO + " BLOB)";


        String sqlEquipos = "create table if not exists "
                          + Contrato.TABLA_EQUIPOS
                          //+ " (_id INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, "
                          + "("
                          + Contrato.ID_EQUIPO + " INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT, "
                          + Contrato.ID_AUXILIAR + " INTEGER, "
                          + Contrato.NOMBRE_EQUIPO + " TEXT , "
                          + Contrato.EQUIPO_PAIS + " TEXT, "
                          + Contrato.FOTO_EQUIPO + " BLOB, "
                          + "FOREIGN KEY (`" +  Contrato.ID_AUXILIAR + "`) REFERENCES jugadores(`" + Contrato.ID_JUGADOR + "`)"
                          + " ON DELETE CASCADE"
                          + " ON UPDATE CASCADE)";


        db.execSQL(sqlJugadores);
        db.execSQL(sqlCuerpoTecnico);
        db.execSQL(sqlEquipos);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.d("comol", "actualizar");
        System.out.println("=============================");
        System.out.println(oldVersion + " - " + newVersion);

        String sqlDeleteJugadores = "DROP TABLE IF EXISTS " + Contrato.TABLA_JUGADORES;
        String sqlDeleteCuerpoTecnico = "DROP TABLE IF EXISTS " + Contrato.TABLA_CUERPO_TECNICO;
        String sqlDeleteEquipos = "DROP TABLE IF EXISTS " + Contrato.TABLA_EQUIPOS;

        db.execSQL(sqlDeleteJugadores);
        db.execSQL(sqlDeleteCuerpoTecnico);
        db.execSQL(sqlDeleteEquipos);

        onCreate(db);
    }
}
