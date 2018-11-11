package com.example.franmay.plantillas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.franmay.plantillas.adaptadores.AdaptadorEquipos;
import com.example.franmay.plantillas.clases.Equipo;

import java.util.ArrayList;

public class ActividadEquipos extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Context contexto;


    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_equipos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_escudos);
        setSupportActionBar(toolbar);

        // habilitar botón de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contexto=this;
        AdaptadorEquipos adaptador;

        ArrayList<Equipo> lista=new ArrayList<>();


        ListView listaImagenes = (ListView) findViewById(R.id.listadoEscudos);

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

        adaptador = new AdaptadorEquipos(getApplicationContext(), lista);
        listaImagenes.setAdapter(adaptador);
        listaImagenes.setOnItemClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == android.R.id.home)
        {
            Intent accion = new Intent(this, MainActivity.class);
            startActivity(accion);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        int indiceEquipo=position;

        String nombreEquipo;
        String equipos[] = {"Alaves", "At. Madrid", "Ath. Bilbao", "Barcelona", "Betis", "Celta", "Eibar", "Español",
                            "Getafe", "Girona", "Huesca", "Leganés", "Levante", "Rayo Vallecano", "Real Madrid",
                            "Real Sociedad", "Sevilla", "Valencia", "Valladolid", "Villareal"};

        nombreEquipo=equipos[position];

        Intent accion = new Intent(contexto, SeccionesPlantillas.class);
        accion.putExtra("nombreEquipo", nombreEquipo);
        startActivity(accion);
    }
}
