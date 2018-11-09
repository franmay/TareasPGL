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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class FormularioCuerpoTecnico extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    
    Spinner spinnerPosicion;
    
    String equipo;

    EditText editarNombre, editarEdad, editarPais;

    ImageView imagen;

    ArrayList<CuerpoTecnico> lista = new ArrayList<>();

    ArrayList<Jugador> listaJugadoresAlaves = new ArrayList<>();
    ArrayList<CuerpoTecnico> listaCuerpoTecnicoAlaves = new ArrayList<>();

    ArrayList<Jugador> listaJugadoresAtMadrid = new ArrayList<>();
    ArrayList<CuerpoTecnico> listaCuerpoTecnicoAtMadrid = new ArrayList<>();

    ArrayList<Estadio> listaEstadio = new ArrayList<>();
    ArrayList<InformacionGeneral> listaInformacion = new ArrayList<>();

    CuerpoTecnico jugador = new CuerpoTecnico();

    int lugarPosicion=0, indiceEquipo, indicePersona, posicionCargo;

    int opcionNavigation;
    String tituloNavigation;

    Context contexto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cuerpo_tecnico);
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
        editarEdad = (EditText) findViewById(R.id.editTextAge);
        editarPais = (EditText) findViewById(R.id.editTextCountry);

        imagen = (ImageView) findViewById(R.id.escudoEquipo);

        spinnerPosicion = (Spinner) findViewById(R.id.spinnerCargo);

        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(
                this, R.array.cargo, R.layout.spinner_item);

        spinnerPosicion.setAdapter(adaptador);
        spinnerPosicion.setOnItemSelectedListener(this);


        // habilitar botón de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle informacionRecibida = getIntent().getExtras();
        equipo=informacionRecibida.getString("nombreEquipo");
        this.setTitle(equipo);

        indiceEquipo=informacionRecibida.getInt("indiceEquipo");
        indicePersona=informacionRecibida.getInt("indicePersona");

        tituloNavigation=informacionRecibida.getString("tituloNavigation");
        opcionNavigation=informacionRecibida.getInt("opcionNavigation");

        listaJugadoresAlaves=informacionRecibida.getParcelableArrayList("listaAlaves1");
        listaCuerpoTecnicoAlaves=informacionRecibida.getParcelableArrayList("listaAlaves2");

        listaJugadoresAtMadrid=informacionRecibida.getParcelableArrayList("listaAtMadrid1");
        listaCuerpoTecnicoAtMadrid=informacionRecibida.getParcelableArrayList("listaAtMadrid2");

        listaEstadio = informacionRecibida.getParcelableArrayList("listaEstadio");
        listaInformacion=informacionRecibida.getParcelableArrayList("listaInformacion");


        if (indiceEquipo==0)
        {
            for (int i=0; i<listaCuerpoTecnicoAlaves.size(); i++)
            {
                CuerpoTecnico auxiliar = new CuerpoTecnico();

                auxiliar.setFoto(listaCuerpoTecnicoAlaves.get(i).getFoto());
                auxiliar.setNombre(listaCuerpoTecnicoAlaves.get(i).getNombre());
                auxiliar.setPais(listaJugadoresAlaves.get(i).getPais());
                auxiliar.setEquipo(listaCuerpoTecnicoAlaves.get(i).getEquipo());
                auxiliar.setIndiceCargo(listaCuerpoTecnicoAlaves.get(i).getIndiceCargo());
                auxiliar.setCargo(listaCuerpoTecnicoAlaves.get(i).getCargo());
                auxiliar.setIndiceCargo(listaCuerpoTecnicoAlaves.get(i).getIndiceCargo());
                auxiliar.setEdad(listaCuerpoTecnicoAlaves.get(i).getEdad());

                lista.add(auxiliar);
            }
        }
        else
        {
            for (int i=0; i<listaCuerpoTecnicoAtMadrid.size(); i++)
            {
                CuerpoTecnico auxiliar = new CuerpoTecnico();

                auxiliar.setFoto(listaCuerpoTecnicoAtMadrid.get(i).getFoto());
                auxiliar.setNombre(listaCuerpoTecnicoAtMadrid.get(i).getNombre());
                auxiliar.setPais(listaCuerpoTecnicoAtMadrid.get(i).getPais());
                auxiliar.setEquipo(listaCuerpoTecnicoAtMadrid.get(i).getEquipo());
                auxiliar.setIndiceCargo(listaCuerpoTecnicoAtMadrid.get(i).getIndiceCargo());
                auxiliar.setCargo(listaCuerpoTecnicoAtMadrid.get(i).getCargo());
                auxiliar.setIndiceCargo(listaCuerpoTecnicoAtMadrid.get(i).getIndiceCargo());
                auxiliar.setEdad(listaCuerpoTecnicoAtMadrid.get(i).getEdad());

                lista.add(auxiliar);
            }
        }


        jugador.setFoto(lista.get(indicePersona).getFoto());
        jugador.setNombre(lista.get(indicePersona).getNombre());
        jugador.setPais(lista.get(indicePersona).getPais());
        jugador.setEquipo(lista.get(indicePersona).getEquipo());
        jugador.setIndiceCargo(lista.get(indicePersona).getIndiceCargo());
        jugador.setCargo(lista.get(indicePersona).getCargo());
        jugador.setIndiceCargo(lista.get(indicePersona).getIndiceCargo());
        jugador.setEdad(lista.get(indicePersona).getEdad());


        posicionCargo=lista.get(indicePersona).getIndiceCargo();

        imagen.setImageResource(jugador.getFoto());

        if (!jugador.getNombre().equals("Nombre"))
            editarNombre.setText(lista.get(indicePersona).getNombre());

        //int edad = lista.get(indicePersona).getEdad();
        int edad = jugador.getEdad();

        if (edad>0)
            editarEdad.setText(String.valueOf(edad));

        String pais;

        pais = jugador.getPais();

        if (!pais.equals("Pais"))
            editarPais.setText(pais);


        spinnerPosicion.setSelection(jugador.getIndiceCargo());
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

                accion.putExtra("tituloNavigation", tituloNavigation);
                accion.putExtra("opcionNavigation", opcionNavigation);

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

            case R.id.menuJugadorActualizar:
                ActualizarFormulario();
                break;

            case android.R.id.home:
                Intent accion2 = new Intent(this, ActividadEquipos.class);

                accion2.putExtra("tituloNavigation", tituloNavigation);
                accion2.putExtra("opcionNavigation", opcionNavigation);

                accion2.putExtra("listaAlaves1", listaJugadoresAlaves);
                accion2.putExtra("listaAlaves2", listaCuerpoTecnicoAlaves);

                accion2.putExtra("listaAtMadrid1", listaJugadoresAtMadrid);
                accion2.putExtra("listaAtMadrid2", listaCuerpoTecnicoAtMadrid);

                accion2.putExtra("listaEstadio", listaEstadio);
                accion2.putExtra("listaInformacion", listaInformacion);

                accion2.putExtra("equipo", equipo);
                startActivity(accion2);
        }

        return true;
    }


    public void validarCampos()
    {
        String nombre = editarNombre.getText().toString();
        String pais = editarPais.getText().toString();
        String edad = editarEdad.getText().toString();


        editarNombre.setError(null);
        editarEdad.setError(null);
        editarPais.setError(null);

        if (!validarString(nombre, editarNombre))
            return;

        if (!validarString(pais, editarPais))
            return;

        if (!validarInt(edad, editarEdad))
            return;


        if (lugarPosicion==0)
        {
            VentanaEmergente cuadroDialogo = new VentanaEmergente("Advertencia",
                    "No se ha seleccionado ningún cargo...",
                    contexto);
            cuadroDialogo.ventana();

            return;
        }


        jugador.setNombre(editarNombre.getText().toString());
        jugador.setEdad(Integer.parseInt(editarEdad.getText().toString()));
        jugador.setPais(editarPais.getText().toString());

        formularioValidado();

        posicionCargo=lugarPosicion;

        lista.set(indicePersona, jugador);

        if (indiceEquipo==0)
            listaCuerpoTecnicoAlaves.set(indicePersona, jugador);
        else
            listaCuerpoTecnicoAtMadrid.set(indicePersona, jugador);
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
        return;
    }


    public void ActualizarFormulario()
    {
        if (!jugador.getNombre().equals("Nombre"))
            editarNombre.setText(jugador.getNombre());
        else
            editarNombre.setText("");


        int edad = jugador.getEdad();

        if (edad > 0)
            editarEdad.setText(String.valueOf(edad));
        else
            editarEdad.setText("");


        if (!jugador.getPais().equals("Pais"))
            editarPais.setText(jugador.getPais());
        else
            editarPais.setText("");


        spinnerPosicion.setSelection(posicionCargo);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        if (parent.getId()==spinnerPosicion.getId())
        {
            lugarPosicion=position;

            jugador.setCargo(String.valueOf(parent.getItemAtPosition(position)));
            jugador.setIndiceCargo(position);
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
