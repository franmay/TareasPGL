package com.example.franmay.plantillas.variables_globales;

import android.app.Application;

public class VariablesGlobales extends Application {

    public static String AUTHORITY = "com.example.franmay.plantillas.proveedor_contenido.ProveedorContenido";

    public static final boolean VERSION_ADMINISTRADOR = false;

    public static final int SIN_VALOR_INT = 1;
    public static final String SIN_VALOR_STRING = "";

    public static final int INSERTAR = 1;
    public static final int GUARDAR = 2;

    public static final int OPERACION_INSERTAR = 1;
    public static final int OPERACION_MODIFICAR = 2;
    public static final int OPERACION_ELIMINAR = 3;

    public static final int SYNC_INTERVAL = 60;
}
