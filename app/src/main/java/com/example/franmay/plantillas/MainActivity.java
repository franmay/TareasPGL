package com.example.franmay.plantillas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Jugador> listaJugadoresAlaves = new ArrayList<>();
    ArrayList<CuerpoTecnico> listaCuerpoTecnicoAlaves = new ArrayList<>();

    ArrayList<Jugador> listaJugadoresAtMadrid = new ArrayList<>();
    ArrayList<CuerpoTecnico> listaCuerpoTecnicoAtMadrid = new ArrayList<>();

    ArrayList<Estadio> listaEstadio = new ArrayList<>();
    ArrayList<InformacionGeneral> listaInformacion = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Alavés
        listaJugadoresAlaves.add(new Jugador(R.drawable.borja_baston, "Jugador", "Equipo", "",  0, "Jugador", 0, 0));
        listaJugadoresAlaves.add(new Jugador(R.drawable.tomas_pina, "Jugador", "Equipo", "", 0, "Jugador", 0, 0));
        listaJugadoresAlaves.add(new Jugador(R.drawable.wakaso, "Jugador", "Equipo", "",  0, "Jugador", 0, 0));

        listaCuerpoTecnicoAlaves.add(new CuerpoTecnico(R.drawable.fernando_pacheco, "Nombre", "Alavés", "Pais", "Cargo", 0, 0));
        listaCuerpoTecnicoAlaves.add(new CuerpoTecnico(R.drawable.antonio_sivera, "Nombre", "Alavés", "Pais", "Cargo", 0, 0));

        // At. Madrid
        listaJugadoresAtMadrid.add(new Jugador(R.drawable.oblak, "Jugador", "Equipo", "", 0, "Jugador", 0, 0));
        listaJugadoresAtMadrid.add(new Jugador(R.drawable.griezman, "Jugador", "Equipo", "", 0, "Jugador", 0, 0));
        listaJugadoresAtMadrid.add(new Jugador(R.drawable.diego_costa, "Jugador", "Equipo", "",  0, "Jugador", 0, 0));
        listaJugadoresAtMadrid.add(new Jugador(R.drawable.koke, "Jugador", "Equipo", "",  0, "Jugador", 0, 0));

        listaCuerpoTecnicoAtMadrid.add(new CuerpoTecnico(R.drawable.simeone, "Nombre", "Alavés", "Pais", "Cargo", 0, 0));
        listaCuerpoTecnicoAtMadrid.add(new CuerpoTecnico(R.drawable.oscar_ortega, "Nombre", "Alavés", "Pais", "Cargo", 0, 0));
        listaCuerpoTecnicoAtMadrid.add(new CuerpoTecnico(R.drawable.pablo_vercellone, "Nombre", "Alavés", "Pais", "Cargo", 0, 0));

        // Estadios
        listaEstadio.add(new Estadio(R.drawable.mendizorrotza, "", 0, 0, ""));
        listaEstadio.add(new Estadio(R.drawable.wanda_metropolitano, "", 0, 0, ""));

        // Información General
        listaInformacion.add(new InformacionGeneral(R.drawable.alaves, "", "", "", "",0));
        listaInformacion.add(new InformacionGeneral(R.drawable.at_madrid, "", "", "", "", 0));


        Bundle datos = getIntent().getExtras();

        if (datos!=null)
        {
            listaJugadoresAlaves = datos.getParcelableArrayList("listaAlaves1");
            listaCuerpoTecnicoAlaves = datos.getParcelableArrayList("listaAlaves2");

            listaJugadoresAtMadrid = datos.getParcelableArrayList("listaAtMadrid1");
            listaCuerpoTecnicoAtMadrid = datos.getParcelableArrayList("listaAtMadrid2");

            listaEstadio = datos.getParcelableArrayList("listaEstadio");
            listaInformacion = datos.getParcelableArrayList("listaInformacion");
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_informacion_general)
        {
            int opcion=1;
            String texto="Información General";

            Intent accion = new Intent(getApplicationContext(), SeccionesInformacion.class);

            accion.putExtra("listaAlaves1", listaJugadoresAlaves);
            accion.putExtra("listaAlaves2", listaCuerpoTecnicoAlaves);

            accion.putExtra("listaAtMadrid1", listaJugadoresAtMadrid);
            accion.putExtra("listaAtMadrid2", listaCuerpoTecnicoAtMadrid);

            accion.putExtra("listaEstadio", listaEstadio);
            accion.putExtra("listaInformacion", listaInformacion);

            startActivity(accion);
        }
        else
        if (id == R.id.nav_plantillas)
        {
            int opcion=2;
            String texto="Plantillas";

            Intent accion = new Intent(getApplicationContext(), ActividadEquipos.class);

            accion.putExtra("tituloNavigation", texto);
            accion.putExtra("opcionNavigation", opcion);

            accion.putExtra("listaAlaves1", listaJugadoresAlaves);
            accion.putExtra("listaAlaves2", listaCuerpoTecnicoAlaves);

            accion.putExtra("listaAtMadrid1", listaJugadoresAtMadrid);
            accion.putExtra("listaAtMadrid2", listaCuerpoTecnicoAtMadrid);

            accion.putExtra("listaEstadio", listaEstadio);
            accion.putExtra("listaInformacion", listaInformacion);

            startActivity(accion);
        }
        else
        if (id == R.id.nav_salir)
        {
            Intent accionSalir = new Intent(Intent.ACTION_MAIN);
            accionSalir.addCategory(Intent.CATEGORY_HOME);
            accionSalir.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(accionSalir);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
