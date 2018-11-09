package com.example.franmay.plantillas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listaImagenes;
    AdaptadorImagenes adaptador;
    ArrayList<Jugador> lista= new ArrayList<>();
    Button botonSalir;

    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        contexto=this;


        listaImagenes = (ListView) findViewById(R.id.listado);
        botonSalir = (Button) findViewById(R.id.buttonExit);

        botonSalir.setOnClickListener(this);

        boolean botones[] = new boolean[3];
        boolean cajas[] = new boolean[5];

        Arrays.fill(cajas, Boolean.FALSE);


        lista.add(new Jugador(R.drawable.asensio, "Jugador", "Equipo", "",  0, "Jugador", 0, 0, 0, cajas));
        lista.add(new Jugador(R.drawable.rulli, "Jugador", "Equipo", "", 0, "Jugador", 0, 0,0, cajas));
        lista.add(new Jugador(R.drawable.messi, "Jugador", "Equipo", "", 0, "Jugador", 0, 0, 0, cajas));
        lista.add(new Jugador(R.drawable.navas, "Jugador", "Equipo", "", 0, "Jugador", 0, 0, 0, cajas));
        lista.add(new Jugador(R.drawable.lopetegui, "Jugador", "Equipo", "", 0, "Jugador", 0, 0, 0, cajas));
        lista.add(new Jugador(R.drawable.oblak, "Jugador", "Equipo", "",  0, "Jugador", 0, 0, 0, cajas));
        lista.add(new Jugador(R.drawable.cazorla, "Jugador", "Equipo", "", 0, "Jugador", 0, 0, 0, cajas));
        lista.add(new Jugador(R.drawable.joaquin, "Jugador", "Equipo", "", 0, "Jugador", 0, 0, 0, cajas));
        lista.add(new Jugador(R.drawable.jesus_navas, "Jugador", "Equipo", "", 0, "Jugador", 0, 0, 0, cajas));



        Bundle objetoRecibido = getIntent().getExtras();

        if (objetoRecibido!=null)
        {
            lista = objetoRecibido.getParcelableArrayList("lista");
        }


        adaptador = new AdaptadorImagenes(this, lista);

        listaImagenes.setAdapter(adaptador);

        listaImagenes.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> a, View v, int position, long id)
            {
                int posicion = position;
                Intent accion = new Intent(contexto, Actividad.class);

                accion.putExtra("actividad", true);
                accion.putExtra("indice", posicion);

                accion.putExtra("lista", lista);
                startActivity(accion);
            }
        });
    }


    @Override
    public void onClick(View v)
    {
        if (v.getId() == botonSalir.getId())
        {
            Intent accionSalir = new Intent(Intent.ACTION_MAIN);
            accionSalir.addCategory(Intent.CATEGORY_HOME);
            accionSalir.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(accionSalir);
        }
    }
}
