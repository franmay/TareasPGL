package com.example.franmay.plantillas.proveedor_contenido;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.SparseArray;

import com.example.franmay.plantillas.database.ManejoBaseDatos;

import static com.example.franmay.plantillas.variables_globales.VariablesGlobales.AUTHORITY;


public class ProveedorContenido extends ContentProvider {

    public ManejoBaseDatos dbHelper;
    private SQLiteDatabase sqlDB;

    private static final int ONE_REG_PLAYER = 1;
    private static final int ALL_REGS_PLAYER = 2;

    private static final int ONE_REG_CUERPO_TECNICO = 3;
    private static final int ALL_REGS_CUERPO_TECNICO = 4;

    private static final int ONE_REG_EQUIPO = 5;
    private static final int ALL_REGS_EQUIPO = 6;

    private static UriMatcher uriMatcher;
    private static SparseArray<String> mimeTypes;


    static
    {
        uriMatcher = new UriMatcher(0);
        mimeTypes = new SparseArray<>();

        uriMatcher.addURI(AUTHORITY,
                Contrato.TABLA_JUGADORES + "/#",
                ONE_REG_PLAYER);

        uriMatcher.addURI(AUTHORITY,
                Contrato.TABLA_JUGADORES,
                ALL_REGS_PLAYER);

        uriMatcher.addURI(AUTHORITY,
                Contrato.TABLA_CUERPO_TECNICO + "/#",
                ONE_REG_CUERPO_TECNICO);

        uriMatcher.addURI(AUTHORITY,
                Contrato.TABLA_CUERPO_TECNICO,
                ALL_REGS_CUERPO_TECNICO);

        uriMatcher.addURI(AUTHORITY,
                Contrato.TABLA_EQUIPOS + "/#",
                ONE_REG_EQUIPO);

        uriMatcher.addURI(AUTHORITY,
                Contrato.TABLA_EQUIPOS,
                ALL_REGS_EQUIPO);


        mimeTypes.put(ONE_REG_PLAYER,
                "vnd.android.cursor.item/vnd." + AUTHORITY + "." + Contrato.TABLA_JUGADORES);

        mimeTypes.put(ALL_REGS_PLAYER,
                "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + Contrato.TABLA_JUGADORES);

        mimeTypes.put(ONE_REG_CUERPO_TECNICO,
                "vnd.android.cursor.item/vnd." + AUTHORITY + "." + Contrato.TABLA_CUERPO_TECNICO);

        mimeTypes.put(ALL_REGS_CUERPO_TECNICO,
                "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + Contrato.TABLA_CUERPO_TECNICO);

        mimeTypes.put(ONE_REG_EQUIPO,
                "vnd.android.cursor.item/vnd." + AUTHORITY + "." + Contrato.TABLA_EQUIPOS);

        mimeTypes.put(ALL_REGS_EQUIPO,
                "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + Contrato.TABLA_EQUIPOS);
    }


    public ProveedorContenido()
    {

    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        sqlDB = dbHelper.getWritableDatabase();

        int numeroRegistro=0;
        String tabla;
        String where=selection;
        String argumentos[] = selectionArgs;


        switch (uriMatcher.match(uri))
        {
            case ONE_REG_PLAYER:
                tabla=uri.getPathSegments().get(0);
                where = Contrato.ID_JUGADOR + "=?";
                argumentos=new String[] {uri.getLastPathSegment()};
            break;

            case ALL_REGS_PLAYER:
                tabla=uri.getLastPathSegment();
            break;

            case ONE_REG_CUERPO_TECNICO:
                tabla=uri.getPathSegments().get(0);
                where = Contrato.ID_CUERPO_TECNICO + "=?";
                argumentos=new String[] {uri.getLastPathSegment()};
            break;

            case ALL_REGS_CUERPO_TECNICO:
                tabla=uri.getLastPathSegment();
            break;

            case ONE_REG_EQUIPO:
                tabla=uri.getPathSegments().get(0);
                where = Contrato.ID_EQUIPO + "=?";
                argumentos=new String[] {uri.getLastPathSegment()};
            break;

            case ALL_REGS_EQUIPO:
                tabla=uri.getLastPathSegment();
            break;

            default:
                String mensajeError=String.valueOf(uri);
                throw new IllegalArgumentException(mensajeError);
        }



        numeroRegistro=sqlDB.delete(tabla, where, argumentos);

        if (numeroRegistro > 0)
        {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numeroRegistro;
    }


    @Override
    public String getType(Uri uri)
    {
        return mimeTypes.get(uriMatcher.match(uri));
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        sqlDB = dbHelper.getWritableDatabase();

        String tabla;

        switch (uriMatcher.match(uri))
        {
            case ONE_REG_PLAYER:
                tabla=uri.getPathSegments().get(0);
            break;

            case ALL_REGS_PLAYER:
                tabla=uri.getLastPathSegment();
            break;

            case ONE_REG_CUERPO_TECNICO:
                tabla=uri.getPathSegments().get(0);
            break;

            case ALL_REGS_CUERPO_TECNICO:
                tabla=uri.getLastPathSegment();
            break;

            case ONE_REG_EQUIPO:
                tabla=uri.getPathSegments().get(0);
            break;

            case ALL_REGS_EQUIPO:
                tabla=uri.getLastPathSegment();
            break;

            default:
                String mensajeError=String.valueOf(uri);
                throw new IllegalArgumentException(mensajeError);
        }





        //long rowId = sqlDB.insert("tabla", null, values);

        //long rowId=0;
        /*try
        {*/
        /*try
        {
            long rowId = sqlDB.insert("tabla", null, values);

            Uri rowUri = ContentUris.withAppendedId(uri, rowId);
            getContext().getContentResolver().notifyChange(rowUri, null);
            return rowUri;
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }*/



        long rowId = sqlDB.insert(tabla, null, values);
        //Uri rowUri = ContentUris.withAppendedId(uri, rowId);

        if (rowId > 0)
        {
            Uri rowUri = ContentUris.withAppendedId(uri, rowId);
            getContext().getContentResolver().notifyChange(rowUri, null);
            return rowUri;
        }
        else
        {
            /*Uri uriError = ContentUris.withAppendedId(uri, rowId);
            String mensajeError=String.valueOf(uriError);
            throw new SQLException(mensajeError);*/

            //return uri;
            Uri rowUri = ContentUris.withAppendedId(uri, rowId);
            throw new SQLException(String.valueOf(rowUri));
        }








        /*catch (ExecutionException e)
        {
            System.out.println("ffffffffffffffffffffffffffff = " + e.getMessage());
            throw new SQLException("xxxx = " + e.getMessage());
        }
        catch (IllegalArgumentException e)
        {
            throw new SQLException("xxxx = " + e.getMessage());
        }
        catch (SQLiteException e)
        {
            System.out.println("ffffffffffffffffffffffffffff = " + e.getMessage());
            throw new SQLException("xxxx = " + e.getMessage());
        }*/


            //System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz = " + rowId + " - " + tabla);

            /*if (rowId > 0) {
                Uri rowUri = ContentUris.withAppendedId(uri, rowId);
                getContext().getContentResolver().notifyChange(rowUri, null);
                return rowUri;
            }
            else
            {
                System.out.println("zzzzzzzzzfghfgjfjhgjhjjhkhjkhjkzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz = " + rowId + " - " + tabla);

                Uri rowUri = ContentUris.withAppendedId(uri, rowId);
                throw new SQLiteException(String.valueOf(rowUri));
            }
        /*}
        catch (IllegalArgumentException e)
        {
            throw new SQLException("xxxx = " + e.getMessage());
        }
        catch (SQLiteException e)
        {
            System.out.println("ffffffffffffffffffffffffffff = " + e.getMessage());
            throw new SQLException("xxxx = " + e.getMessage());
        }*/


        //long rowId = sqlDB.insert(tabla, null, values);

        /*if (rowId > 0)
        {
            Uri rowUri = ContentUris.withAppendedId(uri, rowId);
            getContext().getContentResolver().notifyChange(rowUri, null);
            return rowUri;
        }
        else
        {
            Uri rowUri = ContentUris.withAppendedId(uri, rowId);
            throw new SQLException(String.valueOf(rowUri));
        }*/
    }


    @Override
    public boolean onCreate()
    {
        dbHelper=new ManejoBaseDatos(getContext());

        if (dbHelper!=null)
            return true;
        else
           return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        sqlDB = dbHelper.getReadableDatabase();

        String tabla;
        String where=selection;


        switch (uriMatcher.match(uri))
        {
            case ONE_REG_PLAYER:
                tabla=uri.getPathSegments().get(0);
                where="id=" + uri.getLastPathSegment();
            break;

            case ALL_REGS_PLAYER:
                tabla=uri.getLastPathSegment();
            break;

            case ONE_REG_CUERPO_TECNICO:
                tabla=uri.getPathSegments().get(0);
                where="id=" + uri.getLastPathSegment();
            break;

            case ALL_REGS_CUERPO_TECNICO:
                tabla=uri.getLastPathSegment();
            break;

            case ONE_REG_EQUIPO:
                tabla=uri.getPathSegments().get(0);
                where="id=" + uri.getLastPathSegment();
            break;

            case ALL_REGS_EQUIPO:
                tabla=uri.getLastPathSegment();
            break;

            default:
                String mensajeError=String.valueOf(uri);
                throw new IllegalArgumentException(mensajeError);
        }


        Cursor cursor;

        try
        {
            cursor = sqlDB.query(tabla, projection, where, selectionArgs, null, null, sortOrder);
        }
        catch (IllegalArgumentException e)
        {
            String mensajeError=String.valueOf(uri);
            throw new SQLException(mensajeError);
        }

        return cursor;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) throws IllegalArgumentException {

        sqlDB = dbHelper.getWritableDatabase();

        int numeroRegistro=0;
        String tabla;
        String where=selection;
        String argumentos[] = selectionArgs;


        switch (uriMatcher.match(uri))
        {
            case ONE_REG_PLAYER:
                tabla=uri.getPathSegments().get(0);
                where = Contrato.ID_JUGADOR + "=?";
                argumentos=new String[] {uri.getLastPathSegment()};
            break;

            case ALL_REGS_PLAYER:
                tabla=uri.getLastPathSegment();
            break;

            case ONE_REG_CUERPO_TECNICO:
                tabla=uri.getPathSegments().get(0);
                where = Contrato.ID_CUERPO_TECNICO + "=?";
                argumentos=new String[] {uri.getLastPathSegment()};
            break;

            case ALL_REGS_CUERPO_TECNICO:
                tabla=uri.getLastPathSegment();
            break;

            case ONE_REG_EQUIPO:
                tabla=uri.getPathSegments().get(0);
                where = Contrato.ID_EQUIPO + "=?";
                argumentos=new String[] {uri.getLastPathSegment()};
            break;

            case ALL_REGS_EQUIPO:
                tabla=uri.getLastPathSegment();
            break;

            default:
                String mensajeError=String.valueOf(uri);
                throw new IllegalArgumentException(mensajeError);
        }


        numeroRegistro=sqlDB.update(tabla, values, where, argumentos);

        if (numeroRegistro > 0)
        {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numeroRegistro;
    }
}
