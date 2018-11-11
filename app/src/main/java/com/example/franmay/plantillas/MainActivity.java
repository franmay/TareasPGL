package com.example.franmay.plantillas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.franmay.plantillas.permisos.ObtenerPermisosCamara;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //ImageView imagen;
    //Button botonGrabarNacionalidad;
    int nacionalizado=0, comunitario=0, extracomuniario=0;
    int auxNacionalizado=0, auxComunitario=0, auxExtracomuniario=0;

    int liga=0, ligaExtranjera=0, copa=0, champions=0, mundial=0;
    int auxLiga=0, auxLigaExtranjera=0, auxCopa=0, auxChampions=0, auxMundial=0;

    boolean grabar=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*Intent accionInformacion = new Intent(this, InformacionJugadores.class);
        /*accionInformacion.putExtra("registroGrabado", registroGrabado);
        accionInformacion.putExtra("objeto", (Parcelable) jugador);
        startActivity(accionInformacion);*/
    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
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
            Intent accion = new Intent(getApplicationContext(), ObtenerPermisosCamara.class);
            startActivity(accion);
        }
        else
        if (id == R.id.nav_plantillas)
        {
            Intent accion = new Intent(getApplicationContext(), ActividadEquipos.class);
            startActivity(accion);
        }
        else
        if (id == R.id.nav_imagenes)
        {
            Intent accion = new Intent(getApplicationContext(), ObtenerPermisosCamara.class);
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
