package com.example.franmay.plantillas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;


public class FormularioEstadio extends AppCompatActivity {

    String equipo;
    int indice;

    EditText editarNombre, editarCapacidad, editarAñoConstruccion, editarDimension;

    ImageView imagen;


    ArrayList<Estadio> listaEstadio = new ArrayList<>();
    ArrayList<InformacionGeneral> listaInformacion = new ArrayList<>();

    ArrayList<Jugador> listaJugadoresAlaves = new ArrayList<>();
    ArrayList<CuerpoTecnico> listaCuerpoTecnicoAlaves = new ArrayList<>();

    ArrayList<Jugador> listaJugadoresAtMadrid = new ArrayList<>();
    ArrayList<CuerpoTecnico> listaCuerpoTecnicoAtMadrid = new ArrayList<>();

    Estadio auxiliar = new Estadio();

    int indiceEquipo;

    Context contexto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_estadio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        contexto=this;

        editarNombre = (EditText) findViewById(R.id.editTextName);
        editarCapacidad = (EditText) findViewById(R.id.editTextCapacity);
        editarAñoConstruccion = (EditText) findViewById(R.id.editTextYear);
        editarDimension = (EditText) findViewById(R.id.editTextDimension);

        imagen = (ImageView) findViewById(R.id.imagenEstadio);


        // habilitar botón de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle informacionRecibida = getIntent().getExtras();
        equipo=informacionRecibida.getString("nombreEquipo");
        this.setTitle(equipo);

        indiceEquipo=informacionRecibida.getInt("indiceEquipo");


        listaJugadoresAlaves=informacionRecibida.getParcelableArrayList("listaAlaves1");
        listaCuerpoTecnicoAlaves=informacionRecibida.getParcelableArrayList("listaAlaves2");

        listaJugadoresAtMadrid=informacionRecibida.getParcelableArrayList("listaAtMadrid1");
        listaCuerpoTecnicoAtMadrid=informacionRecibida.getParcelableArrayList("listaAtMadrid2");

        listaEstadio=informacionRecibida.getParcelableArrayList("listaEstadio");
        listaInformacion=informacionRecibida.getParcelableArrayList("listaInformacion");

        auxiliar.setFoto(listaEstadio.get(indiceEquipo).getFoto());
        auxiliar.setNombre(listaEstadio.get(indiceEquipo).getNombre());
        auxiliar.setCapacidad(listaEstadio.get(indiceEquipo).getCapacidad());
        auxiliar.setAñoConstruccion(listaEstadio.get(indiceEquipo).getAñoConstruccion());
        auxiliar.setDimension(listaEstadio.get(indiceEquipo).getDimension());

        imagen.setImageResource(auxiliar.getFoto());

        editarNombre.setText(listaEstadio.get(indiceEquipo).getNombre());
        editarCapacidad.setText(String.valueOf(listaEstadio.get(indiceEquipo).getCapacidad()));
        editarAñoConstruccion.setText(String.valueOf(listaEstadio.get(indiceEquipo).getAñoConstruccion()));
        editarDimension.setText(listaEstadio.get(indiceEquipo).getDimension());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_jugadores, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuJugadorHome:
                Intent accion = new Intent(this, MainActivity.class);

                accion.putExtra("listaAlaves1", listaJugadoresAlaves);
                accion.putExtra("listaAlaves2", listaCuerpoTecnicoAlaves);

                accion.putExtra("listaAtMadrid1", listaJugadoresAtMadrid);
                accion.putExtra("listaAtMadrid2", listaCuerpoTecnicoAtMadrid);

                accion.putExtra("listaEstadio", listaEstadio);
                accion.putExtra("listaInformacion", listaInformacion);

                startActivity(accion);
                break;

            case R.id.menuJugadorValidar: validarCampos();
                break;

            case R.id.menuJugadorActualizar: ActualizarFormulario();
                break;

            case android.R.id.home:
                Intent accion2 = new Intent(this, SeccionesInformacion.class);

                accion2.putExtra("listaAlaves1", listaJugadoresAlaves);
                accion2.putExtra("listaAlaves2", listaCuerpoTecnicoAlaves);

                accion2.putExtra("listaAtMadrid1", listaJugadoresAtMadrid);
                accion2.putExtra("listaAtMadrid2", listaCuerpoTecnicoAtMadrid);

                accion2.putExtra("listaEstadio", listaEstadio);
                accion2.putExtra("listaInformacion", listaInformacion);

                startActivity(accion2);
        }

        return true;
    }


    public void validarCampos()
    {
        String nombre = editarNombre.getText().toString();
        String capacidad = editarCapacidad.getText().toString();
        String año = editarAñoConstruccion.getText().toString();
        String dimension  = editarDimension.getText().toString();


        editarNombre.setError(null);
        editarCapacidad.setError(null);
        editarAñoConstruccion.setError(null);
        editarDimension.setError(null);

        if (!validarString(nombre, editarNombre))
            return;

        if (!validarInt(capacidad, editarCapacidad))
            return;

        if (!validarInt(año, editarAñoConstruccion))
            return;

        if (!validarString(dimension, editarDimension))
            return;


        auxiliar.setNombre(editarNombre.getText().toString());
        auxiliar.setCapacidad(Integer.parseInt(editarCapacidad.getText().toString()));
        auxiliar.setAñoConstruccion(Integer.parseInt(editarAñoConstruccion.getText().toString()));
        auxiliar.setDimension(editarDimension.getText().toString());

        formularioValidado();

        listaEstadio.set(indiceEquipo, auxiliar);
    }


    public boolean validarString(String cadena, EditText error)
    {
        if (!cadena.trim().isEmpty())
            return true;
        else
        {
            error.setError(getString(R.string.errorCampo));
            error.requestFocus();
            return false;
        }
    }


    public boolean validarInt(String cadena, EditText error)
    {
        int numero;

        try
        {
            numero = Integer.parseInt(cadena);

            if (numero<=0)
            {
                error.setError(getString(R.string.errorCampoNumerico));
                error.requestFocus();
                return false;
            }
        }
        catch (NumberFormatException e)
        {
            error.setError(getString(R.string.errorCampoNoNumerico));
            error.requestFocus();
            return false;
        }

        return true;
    }


    public void formularioValidado()
    {
        VentanaEmergente cuadroDialogo = new VentanaEmergente("Mensaje.",
                "Formulario validado...",
                contexto);
        cuadroDialogo.ventana();
    }


    public void ActualizarFormulario()
    {
        editarNombre.setText(listaEstadio.get(indiceEquipo).getNombre());
        editarCapacidad.setText(String.valueOf(listaEstadio.get(indiceEquipo).getCapacidad()));
        editarAñoConstruccion.setText(String.valueOf(listaEstadio.get(indiceEquipo).getAñoConstruccion()));
        editarDimension.setText(listaEstadio.get(indiceEquipo).getDimension());
    }
}
