package com.example.franmay.plantillas.formularios;

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

import com.example.franmay.plantillas.MainActivity;
import com.example.franmay.plantillas.R;
import com.example.franmay.plantillas.VentanaEmergente;
import com.example.franmay.plantillas.fragmentos.SeccionesInformacion;
import com.example.franmay.plantillas.pojos.CuerpoTecnico;
import com.example.franmay.plantillas.pojos.Estadio;
import com.example.franmay.plantillas.pojos.InformacionGeneral;
import com.example.franmay.plantillas.pojos.Jugador;

import java.util.ArrayList;

public class FormularioInformacionGeneral extends AppCompatActivity {

    String equipo;

    EditText editarNombreEquipo, editarPresidente, editarCiudad, editarLugar, editarFundacion;

    ImageView imagen;

    ArrayList<Jugador> listaJugadoresAlaves = new ArrayList<>();
    ArrayList<CuerpoTecnico> listaCuerpoTecnicoAlaves = new ArrayList<>();

    ArrayList<Jugador> listaJugadoresAtMadrid = new ArrayList<>();
    ArrayList<CuerpoTecnico> listaCuerpoTecnicoAtMadrid = new ArrayList<>();

    ArrayList<Estadio> listaEstadio = new ArrayList<>();
    ArrayList<InformacionGeneral> listaInformacion = new ArrayList<>();

    InformacionGeneral auxiliar = new InformacionGeneral();

    int indiceEquipo;

    Context contexto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_informacion_general);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        contexto=this;

        editarNombreEquipo = (EditText) findViewById(R.id.editTextTeam);
        editarPresidente = (EditText) findViewById(R.id.editTextPresidente);
        editarCiudad = (EditText) findViewById(R.id.editTextCity);
        editarLugar = (EditText) findViewById(R.id.editTextPlace);
        editarFundacion = (EditText) findViewById(R.id.editTextYear);

        imagen = (ImageView) findViewById(R.id.escudoEquipo);


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


        auxiliar.setFoto(listaInformacion.get(indiceEquipo).getFoto());
        auxiliar.setNombreEquipo(listaInformacion.get(indiceEquipo).getNombreEquipo());
        auxiliar.setPresidente(listaInformacion.get(indiceEquipo).getPresidente());
        auxiliar.setCiudad(listaInformacion.get(indiceEquipo).getCiudad());
        auxiliar.setLugarEntrenamiento(listaInformacion.get(indiceEquipo).getLugarEntrenamiento());
        auxiliar.setAñoFundacion(listaInformacion.get(indiceEquipo).getAñoFundacion());

        imagen.setImageResource(auxiliar.getFoto());

        editarNombreEquipo.setText(listaInformacion.get(indiceEquipo).getNombreEquipo());
        editarPresidente.setText(listaInformacion.get(indiceEquipo).getPresidente());
        editarCiudad.setText(listaInformacion.get(indiceEquipo).getCiudad());
        editarLugar.setText(listaInformacion.get(indiceEquipo).getLugarEntrenamiento());
        editarFundacion.setText(String.valueOf(listaInformacion.get(indiceEquipo).getAñoFundacion()));
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

                /*case R.id.menuJugadorValidar: validarCampos();
                    break;

                case R.id.menuJugadorActualizar: ActualizarFormulario();
                    break;*/

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
            String nombreEquipo = editarNombreEquipo.getText().toString();
            String presidente = editarPresidente.getText().toString();
            String ciudad = editarCiudad.getText().toString();
            String lugar  = editarLugar.getText().toString();
            String fundacion  = editarFundacion.getText().toString();

            editarNombreEquipo.setError(null);
            editarPresidente.setError(null);
            editarCiudad.setError(null);
            editarLugar.setError(null);
            editarFundacion.setError(null);


            if (!validarString(nombreEquipo, editarNombreEquipo))
                return;

            if (!validarString(presidente, editarPresidente))
                return;

            if (!validarString(ciudad, editarCiudad))
                return;

            if (!validarString(lugar, editarLugar))
                return;

            if (!validarInt(fundacion, editarFundacion))
                return;


            auxiliar.setNombreEquipo(editarNombreEquipo.getText().toString());
            auxiliar.setPresidente(editarPresidente.getText().toString());
            auxiliar.setCiudad(editarCiudad.getText().toString());
            auxiliar.setLugarEntrenamiento(editarLugar.getText().toString());
            auxiliar.setAñoFundacion(Integer.parseInt(editarFundacion.getText().toString()));


            formularioValidado();

            listaInformacion.set(indiceEquipo, auxiliar);
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
            editarNombreEquipo.setText(listaInformacion.get(indiceEquipo).getNombreEquipo());
            editarPresidente.setText(listaInformacion.get(indiceEquipo).getPresidente());
            editarCiudad.setText(listaInformacion.get(indiceEquipo).getCiudad());
            editarLugar.setText(listaInformacion.get(indiceEquipo).getLugarEntrenamiento());
            editarFundacion.setText(String.valueOf(listaInformacion.get(indiceEquipo).getAñoFundacion()));
        }
}
