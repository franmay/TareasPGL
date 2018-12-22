package com.example.franmay.plantillas.formularios;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.franmay.plantillas.R;
import com.example.franmay.plantillas.VentanaEmergente;
import com.example.franmay.plantillas.adaptadores.AdaptadorRecyclerViewEquipos;
import com.example.franmay.plantillas.pojos.Equipo;
import com.example.franmay.plantillas.pojos.Jugador;
import com.example.franmay.plantillas.proveedor_contenido.Contrato;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class FormularioEquipos extends AppCompatActivity {

    int id, posicion, recursoImagenEquipo;
    String nombreEquipo;
    byte[] foto;

    String equipo;

    boolean insertar;

    Jugador jugadorActual = new Jugador();

    ArrayList<Equipo> lista = new ArrayList<>();
    ArrayList<String> listaEquipos;

    RecyclerView recyclerView;
    AdaptadorRecyclerViewEquipos adaptador;

    Context contexto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_equipos);


        // habilitar botón de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contexto=this;

        Bundle datos = getIntent().getExtras();

        listaEquipos = new ArrayList<>();

        insertar=datos.getBoolean("insertar");
        equipo=datos.getString("nombreEquipo");
        jugadorActual=datos.getParcelable("objeto");
        listaEquipos=datos.getStringArrayList("lista");


        id=jugadorActual.getId();
        foto=jugadorActual.getFoto();

        System.out.println(equipo);
        System.out.println(id);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerPaises);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        lista.add(new Equipo(R.drawable.alaves, "Alavés", 0));
        lista.add(new Equipo(R.drawable.at_madrid, "At. Madrid", 0));
        lista.add(new Equipo(R.drawable.bilbao, "Ath. Bilbao", 0));
        lista.add(new Equipo(R.drawable.barcelona, "Barcelona", 0));
        lista.add(new Equipo(R.drawable.betis, "Betis", 0));
        lista.add(new Equipo(R.drawable.celta, "Celta", 0));
        lista.add(new Equipo(R.drawable.eibar, "Eibar", 0));
        lista.add(new Equipo(R.drawable.espanyol, "Español", 0));
        lista.add(new Equipo(R.drawable.getafe, "Getafe", 0));
        lista.add(new Equipo(R.drawable.girona, "Girona", 0));
        lista.add(new Equipo(R.drawable.huesca, "Huesca", 0));
        lista.add(new Equipo(R.drawable.leganes, "Leganes", 0));
        lista.add(new Equipo(R.drawable.levante, "Levante", 0));
        lista.add(new Equipo(R.drawable.rayo_vallecano, "Rayo Vallecano", 0));
        lista.add(new Equipo(R.drawable.real_madrid, "Real Madrid", 0));
        lista.add(new Equipo(R.drawable.real_sociedad, "Real Sociedad", 0));
        lista.add(new Equipo(R.drawable.sevilla, "Sevilla", 0));
        lista.add(new Equipo(R.drawable.valencia, "Valencia", 0));
        lista.add(new Equipo(R.drawable.valladolid, "Valladolid", 0));
        lista.add(new Equipo(R.drawable.villareal, "Villareal", 0));


        adaptador = new AdaptadorRecyclerViewEquipos(lista);
        recyclerView.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int i=0;
                boolean estado=false;

                nombreEquipo=lista.get(recyclerView.getChildAdapterPosition(v)).getEquipo();
                posicion=lista.get(recyclerView.getChildAdapterPosition(v)).getIndiceEquipo();
                recursoImagenEquipo=lista.get(recyclerView.getChildAdapterPosition(v)).getFoto();


                while ((i<listaEquipos.size())   &&   (!estado))
                {
                    if (listaEquipos.get(i).equals(nombreEquipo))
                        estado=true;
                    else
                       i++;
                }

                if (estado)
                {
                    VentanaEmergente vv5 = new VentanaEmergente("Advertencia.",
                                                              "El equipo ha sido añadido anteriormente...",
                                                                      contexto);
                    vv5.ventana();
                }
                else
                   ventanaConfirmar();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent accionVolver = new Intent(this,FormularioEquiposJugador.class);
                accionVolver.putExtra("insertar", false);
                accionVolver.putExtra("nombreEquipo", equipo);
                accionVolver.putExtra("objeto", (Parcelable) jugadorActual);
                startActivity(accionVolver);
        }

        return true;
    }


    public void ventanaConfirmar()
    {
        String mensaje="¿Quiere agregar este equipo?" + "\n--> " + nombreEquipo;

        AlertDialog.Builder ventana = new AlertDialog.Builder(this);

        ventana.setTitle("Mensaje.");
        ventana.setMessage(mensaje);

        ventana.setPositiveButton("Continuar", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
                grabarEquipo();
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


    public void grabarEquipo()
    {
        ContentValues valores = new ContentValues();

        valores.put(Contrato.ID_AUXILIAR, id);
        valores.put(Contrato.NOMBRE_EQUIPO, nombreEquipo);
        valores.put(Contrato.EQUIPO_PAIS, "España");

        Drawable drawable = getResources().getDrawable(recursoImagenEquipo);

        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        byte[] byteArray = stream.toByteArray();
        valores.put(Contrato.FOTO_EQUIPO, byteArray);


        try
        {
            Uri uri = getApplicationContext().getContentResolver().insert(Contrato.CONTENT_URI_EQUIPOS, valores);

            int idDevuelto=Integer.parseInt(uri.getLastPathSegment());

            AlertDialog.Builder ventana = new AlertDialog.Builder(contexto);

            ventana.setTitle("Mensaje.");
            ventana.setMessage("Equipo grabado correctamente...");

            // solo mostramos el botón "Aceptar"
            ventana.setPositiveButton("Continuar", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();

                    Intent accion = new Intent(getApplicationContext(), FormularioEquiposJugador.class);
                    accion.putExtra("insertar", false);
                    accion.putExtra("nombreEquipo", equipo);
                    accion.putExtra("objeto", (Parcelable) jugadorActual);
                    startActivity(accion);
                }
            });

                AlertDialog alert = ventana.create();
                alert.show();
        }
        catch (IllegalArgumentException e)
        {
            VentanaEmergente vv = new VentanaEmergente("Uri incorrecta.", e.getMessage(), this);
            vv.ventana();
        }
        catch (SQLException e)
        {
            VentanaEmergente vv = new VentanaEmergente("Consulta SQL incorrecta.", e.getMessage(), this);
            vv.ventana();
        }
    }
}
