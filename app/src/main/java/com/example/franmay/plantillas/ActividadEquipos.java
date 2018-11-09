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

import java.util.ArrayList;

public class ActividadEquipos extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Context contexto;


    ArrayList<Jugador> listaJugadoresAlaves = new ArrayList<>();
    ArrayList<CuerpoTecnico> listaCuerpoTecnicoAlaves = new ArrayList<>();

    ArrayList<Jugador> listaJugadoresAtMadrid = new ArrayList<>();
    ArrayList<CuerpoTecnico> listaCuerpoTecnicoAtMadrid = new ArrayList<>();

    ArrayList<Estadio> listaEstadio = new ArrayList<>();
    ArrayList<InformacionGeneral> listaInformacion = new ArrayList<>();

    int opcionNavigation;
    String tituloNavigation;

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


        Bundle datos = getIntent().getExtras();

        opcionNavigation=datos.getInt("opcionNavigation");
        tituloNavigation=datos.getString("tituloNavigation");

        this.setTitle(tituloNavigation);


        listaJugadoresAlaves = datos.getParcelableArrayList("listaAlaves1");
        listaCuerpoTecnicoAlaves = datos.getParcelableArrayList("listaAlaves2");

        listaJugadoresAtMadrid = datos.getParcelableArrayList("listaAtMadrid1");
        listaCuerpoTecnicoAtMadrid = datos.getParcelableArrayList("listaAtMadrid2");

        listaEstadio = datos.getParcelableArrayList("listaEstadio");
        listaInformacion=datos.getParcelableArrayList("listaInformacion");


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


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_secciones_plantillas, menu);
        return true;
    }*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == android.R.id.home)
        {
            Intent accion = new Intent(this, MainActivity.class);

            accion.putExtra("listaAlaves1", listaJugadoresAlaves);
            accion.putExtra("listaAlaves2", listaCuerpoTecnicoAlaves);

            accion.putExtra("listaAtMadrid1", listaJugadoresAtMadrid);
            accion.putExtra("listaAtMadrid2", listaCuerpoTecnicoAtMadrid);

            accion.putExtra("listaEstadio", listaEstadio);
            accion.putExtra("listaInformacion", listaInformacion);

            startActivity(accion);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        int indiceEquipo=position;

        String nombreEquipo;
        String equipos[] = {"Alaves", "At. Madrid", "Ath. Bilbao", "Barcelona", "Betis", "Celta", "Eibar", "Getafe"};
        nombreEquipo=equipos[position];


        /*if (opcionNavigation==1)
        {
            if (position<=1)
            {
                Intent accion = new Intent(contexto, SeccionesInformacion.class);

                accion.putExtra("listaAlaves1", listaJugadoresAlaves);
                accion.putExtra("listaAlaves2", listaCuerpoTecnicoAlaves);

                accion.putExtra("listaAtMadrid1", listaJugadoresAtMadrid);
                accion.putExtra("listaAtMadrid2", listaCuerpoTecnicoAtMadrid);

                accion.putExtra("listaEstadio", listaEstadio);
                accion.putExtra("listaInformacion", listaInformacion);

                startActivity(accion);
            }
        }
        else
        {*/
            if (position<=1)
            {
                Intent accion1 = new Intent(contexto, SeccionesPlantillas.class);

                accion1.putExtra("indiceEquipo", position);
                accion1.putExtra("equipo", nombreEquipo);

                accion1.putExtra("tituloNavigation", tituloNavigation);
                accion1.putExtra("opcionNavigation", opcionNavigation);

                accion1.putExtra("listaAlaves1", listaJugadoresAlaves);
                accion1.putExtra("listaAlaves2", listaCuerpoTecnicoAlaves);

                accion1.putExtra("listaAtMadrid1", listaJugadoresAtMadrid);
                accion1.putExtra("listaAtMadrid2", listaCuerpoTecnicoAtMadrid);

                accion1.putExtra("listaEstadio", listaEstadio);
                accion1.putExtra("listaInformacion", listaInformacion);

                startActivity(accion1);
            }
        //}
    }
}
