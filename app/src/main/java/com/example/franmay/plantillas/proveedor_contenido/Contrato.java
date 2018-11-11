package com.example.franmay.plantillas.proveedor_contenido;

import android.net.Uri;
import android.provider.BaseColumns;

import static com.example.franmay.plantillas.variables_globales.VariablesGlobales.AUTHORITY;

public class Contrato implements BaseColumns {

    public static String TABLA_JUGADORES = "jugadores";
    public static String TABLA_CUERPO_TECNICO = "cuerpo_tecnico";

    public static final Uri CONTENT_URI_JUGADORES = Uri.parse("content://" + AUTHORITY + "/" + TABLA_JUGADORES);
    public static final Uri CONTENT_URI_CUERPO_TECNICO = Uri.parse("content://" + AUTHORITY + "/" + TABLA_CUERPO_TECNICO);


    // Columnas tabla "jugadores"
    public static final String NOMBRE_JUGADOR = "nombre";
    public static final String EQUIPO_JUGADOR = "equipo";
    public static final String EDAD_JUGADOR = "edad";
    public static final String PAIS_JUGADOR = "pais";
    public static final String POSICION_JUGADOR = "puesto";
    public static final String INDICE_POSICION_JUGADOR = "indice_posicion";
    public static final String FOTO_JUGADOR = "foto_jugador";

    public static final String JUGADOR_NACIONALIZADO = "nacionalizado";
    public static final String JUGADOR_COMUNITARIO = "comunitario";
    public static final String JUGADOR_EXTRACOMUNITARIO = "extracomunitario";

    public static final String LIGA_NACIONAL = "liga";
    public static final String LIGA_EXTRANJERA = "liga_extranjera";
    public static final String COPA_REY = "copa_rey";
    public static final String CHAMPIONS_LEAGUE = "champions_league";
    public static final String MUNDIAL = "mundial";


    // Columnas tabla "cuerpo_tecnico"
    public static final String NOMBRE_CUERPO_TECNICO = "nombre";
    public static final String EQUIPO_CUERPO_TECNICO = "equipo";
    public static final String EDAD_CUERPO_TECNICO = "edad";
    public static final String PAIS_CUERPO_TECNICO = "pais";
    public static final String CARGO_CUERPO_TECNICO = "cargo";
    public static final String INDICE_CARGO = "indice_cargo";
    public static final String FOTO_CUERPO_TECNICO = "foto_cuerpo_tecnico";
}
