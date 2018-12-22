package com.example.franmay.plantillas.formularios;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.franmay.plantillas.MainActivity;
import com.example.franmay.plantillas.R;
import com.example.franmay.plantillas.VentanaEmergente;
import com.example.franmay.plantillas.adaptadores.AdaptadorEquipoJugadores;
import com.example.franmay.plantillas.pojos.EquipoJugador;
import com.example.franmay.plantillas.pojos.Jugador;
import com.example.franmay.plantillas.proveedor_contenido.Contrato;

import java.util.ArrayList;


public class FormularioEquiposJugador extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ImageView imagen;

    int id, indiceEquipo;
    String equipo;
    byte[] foto;

    boolean insertar;

    Jugador jugadorActual = new Jugador();

    ArrayList<EquipoJugador> listaEquipos;
    ArrayList<String> nombreEquipos;

    AdaptadorEquipoJugadores adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_equipos_jugador);

        // habilitar botón de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imagen = (ImageView) findViewById(R.id.imagenJugador);

        listaEquipos = new ArrayList<>();
        nombreEquipos = new ArrayList<>();

        Bundle datos = getIntent().getExtras();

        insertar=datos.getBoolean("insertar");
        equipo=datos.getString("nombreEquipo");
        jugadorActual=datos.getParcelable("objeto");


        id=jugadorActual.getId();
        foto=jugadorActual.getFoto();

        Bitmap bitmap = BitmapFactory.decodeByteArray(foto, 0, foto.length);
        imagen.setImageBitmap(bitmap);

        leerEquipos(id);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_equipos, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuEquipoHome:
                Intent accion = new Intent(this, MainActivity.class);
                startActivity(accion);
            break;

            case R.id.menuInsertarEquipo:
                Intent accionInsertar = new Intent(this, FormularioEquipos.class);

                accionInsertar.putExtra("insertar", false);
                accionInsertar.putExtra("nombreEquipo", equipo);
                accionInsertar.putExtra("objeto", (Parcelable) jugadorActual);
                accionInsertar.putExtra("lista", nombreEquipos);
                startActivity(accionInsertar);
            break;

            case android.R.id.home:
                Intent accionVolver = new Intent(this,FormularioJugador.class);

                accionVolver.putExtra("insertar", false);
                accionVolver.putExtra("nombreEquipo", equipo);
                accionVolver.putExtra("objeto", (Parcelable) jugadorActual);
                startActivity(accionVolver);
        }

        return true;
    }


    public void leerEquipos(int id)
    {
        Cursor cursor=null;

        String campos[] = { Contrato.ID_EQUIPO,
                            Contrato.ID_AUXILIAR,
                            Contrato.NOMBRE_EQUIPO,
                            Contrato.EQUIPO_PAIS,
                            Contrato.FOTO_EQUIPO};


        String idBuscar=String.valueOf(id);
        String[] args = new String[] {idBuscar};
        String seleccion= Contrato.ID_AUXILIAR + "=?";


        try
        {
            cursor=getApplicationContext().getContentResolver().query(Contrato.CONTENT_URI_EQUIPOS, campos, seleccion, args, null);

            EquipoJugador equipo;

            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
            {
                equipo = new EquipoJugador();

                equipo.setId(cursor.getInt(cursor.getColumnIndex(Contrato.ID_EQUIPO)));
                equipo.setId_jugador(cursor.getInt(cursor.getColumnIndex(Contrato.ID_AUXILIAR)));
                equipo.setNombre(cursor.getString(cursor.getColumnIndex(Contrato.NOMBRE_EQUIPO)));
                equipo.setPais(cursor.getString(cursor.getColumnIndex(Contrato.EQUIPO_PAIS)));
                equipo.setFoto(cursor.getBlob(cursor.getColumnIndex(Contrato.FOTO_EQUIPO)));

                listaEquipos.add(equipo);
                nombreEquipos.add(equipo.getNombre());
            }


            ListView listaImagenes;
            listaImagenes = findViewById(R.id.listadoEquipos);

            //AdaptadorEquipoJugadores adaptador;
            adaptador = new AdaptadorEquipoJugadores(this, listaEquipos);
            listaImagenes.setAdapter(adaptador);
            listaImagenes.setOnItemClickListener(this);


        }
        catch (IllegalArgumentException e)
        {
            VentanaEmergente vv = new VentanaEmergente("URI incorrecta.", e.getMessage(), this);
            vv.ventana();
        }
        catch (SQLException e)
        {
            String titulo="Consulta SQL incorrecta en la tabla: " + Contrato.TABLA_JUGADORES;

            VentanaEmergente vv = new VentanaEmergente(titulo, e.getMessage(), this);
            vv.ventana();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        indiceEquipo=position;

        String equipo=listaEquipos.get(position).getNombre();
        String mensaje="¿Quiere eliminar este equipo?" + "\n--> " + equipo;

        AlertDialog.Builder ventana = new AlertDialog.Builder(this);

        ventana.setTitle("Advertencia.");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Eliminar", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
                eliminarEquipo();
            }
        });

        ventana.setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = ventana.create();
        alert.show();
    }


    public void eliminarEquipo()
    {
        int idEliminar=listaEquipos.get(indiceEquipo).getId();

        Uri uri = Uri.parse(Contrato.CONTENT_URI_EQUIPOS + "/" + idEliminar);


        try
        {
            int numeroRegistros = getApplicationContext().getContentResolver().delete(uri, null, null);

            if (numeroRegistros > 0)
            {
                listaEquipos.remove(indiceEquipo);
                nombreEquipos.remove(indiceEquipo);
                adaptador.notifyDataSetChanged();
            }
            else
            {
                VentanaEmergente alerta = new VentanaEmergente("Advertencia.",
                                                             "El equipo no se pudo eliminar\n" +
                                                                      "o no se encuentra registrado...",
                                                             this);
                alerta.ventana();
            }
        }
        catch (IllegalArgumentException e)
        {
            VentanaEmergente vv = new VentanaEmergente("Uri incorrecta.\n", e.getMessage(), this);
            vv.ventana();
        }
        catch (SQLException e)
        {
            VentanaEmergente vv = new VentanaEmergente("Consulta SQL incorrecta.\n", e.getMessage(), this);
            vv.ventana();
        }
    }
}
