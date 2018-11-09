package com.example.franmay.plantillas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class otrosDatosDeInteres extends AppCompatActivity implements View.OnClickListener {

    int indice;
    Jugador jugadorRecibido = new Jugador();
    Jugador jugadorActual = new Jugador();

    boolean radioPulsado = false;
    boolean cajaPulsada = false;

    boolean estadoCajaLiga=false;
    boolean estadoCajaExtranjera=false;
    boolean estadoCajaCopa=false;
    boolean estadoCajaChampions=false;
    boolean estadoCajaMundial=false;

    Button boton1, boton2, boton3;
    RadioButton opcionNacionalizado, opcionComunitario, opcionExtraComunitario;

    CheckBox cajaLiga, cajaExtranjera, cajaCopa, cajaChampions, cajaMundial;

    RadioGroup radioGroupInteres;

    Context contexto;

    private Bundle savedInstanceState;

    ArrayList<Jugador> lista;
    boolean cajas[] = new boolean[5];
    boolean cajaActual[] = new boolean[5];

    int radio=0;
    int radioFinal=0;

    int caja=0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otros_datos_de_interes);

        Arrays.fill(cajas, Boolean.FALSE);

        contexto = this;

        boton1 = (Button) findViewById(R.id.buttonRecord);
        boton2 = (Button) findViewById(R.id.buttonCancel);
        boton3 = (Button) findViewById(R.id.buttonBack);

        opcionNacionalizado = (RadioButton) findViewById(R.id.RbOpcion1);
        opcionComunitario = (RadioButton) findViewById(R.id.RbOpcion2);
        opcionExtraComunitario = (RadioButton) findViewById(R.id.RbOpcion3);

        cajaLiga = (CheckBox) findViewById(R.id.checkboxLiga);
        cajaExtranjera = (CheckBox) findViewById(R.id.checkboxExtranjeras);
        cajaCopa = (CheckBox) findViewById(R.id.checkboxCopa);
        cajaChampions= (CheckBox) findViewById(R.id.checkboxChampions);
        cajaMundial = (CheckBox) findViewById(R.id.checkboxMundial);


        RadioGroup radioGroupSituacion = (RadioGroup) findViewById(R.id.GrbGrupo1);

        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);

        cajaLiga.setOnClickListener(this);
        cajaExtranjera.setOnClickListener(this);
        cajaCopa.setOnClickListener(this);
        cajaChampions.setOnClickListener(this);
        cajaMundial.setOnClickListener(this);


        Bundle informacionRecibida = getIntent().getExtras();

        indice = informacionRecibida.getInt("indiceActual");

        lista = informacionRecibida.getParcelableArrayList("listaActual");

        jugadorRecibido = informacionRecibida.getParcelable("objeto");
        jugadorActual = informacionRecibida.getParcelable("objetoActual");

        radio=jugadorActual.getRadio();
        cajas=jugadorActual.getCajas();
        cajaActual=jugadorActual.getCajas();


        if (radio==1)
        {
            opcionNacionalizado.setChecked(true);
            radioPulsado=true;
            radioFinal=radio;
        }
        else
        if (radio==2)
        {
            opcionComunitario.setChecked(true);
            radioPulsado=true;
            radioFinal=radio;
        }

        else
        if (radio==3)
        {
            opcionExtraComunitario.setChecked(true);
            radioPulsado=true;
            radioFinal=radio;
        }


        if (cajas[0])
        {
            cajaLiga.setChecked(true);
            cajaPulsada=true;
        }

        if (cajas[1])
        {
            cajaExtranjera.setChecked(true);
            cajaPulsada=true;
        }

        if (cajas[2])
        {
            cajaCopa.setChecked(true);
            cajaPulsada=true;
        }

        if (cajas[3])
        {
            cajaChampions.setChecked(true);
            cajaPulsada=true;
        }

        if (cajas[4])
        {
            cajaMundial.setChecked(true);
            cajaPulsada=true;
        }


        radioGroupSituacion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch (checkedId)
                {
                    case R.id.RbOpcion1:
                        radio=1;
                        break;

                    case R.id.RbOpcion2:
                        radio=2;
                        break;

                    case R.id.RbOpcion3:
                        radio=3;
                        break;
                }
            }
        });
    }


    public void ventana(String titulo, String mensaje,Context contexto)
    {
        AlertDialog.Builder ventana = new AlertDialog.Builder(contexto);

        ventana.setTitle(titulo);
        ventana.setMessage(mensaje);


        ventana.setPositiveButton("Continuar", new DialogInterface.OnClickListener()
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


    @Override
    public void onClick(View v)
    {
        boolean estado=false;


        if (v.getId()==boton1.getId())
        {
            if (radio>0)
            {
                radioFinal=radio;
                jugadorActual.setRadio(radio);
                lista.set(indice, jugadorActual);

                if (!radioPulsado)
                    radioPulsado=true;
            }


            if (cajas[0])
            {
                if (!estadoCajaLiga)
                    estadoCajaLiga=true;
            }
            else
            if (!cajas[0])
            {
                if (estadoCajaLiga)
                    estadoCajaLiga=false;
            }


            if (cajas[1])
            {
                if (!estadoCajaExtranjera)
                    estadoCajaExtranjera=true;
            }
            else
            if (!cajas[1])
            {
                if (estadoCajaExtranjera)
                    estadoCajaExtranjera=false;
            }


            if (cajas[2])
            {
                if (!estadoCajaCopa)
                    estadoCajaCopa=true;
            }
            else
            if (!cajas[2])
            {
                if (estadoCajaCopa)
                    estadoCajaCopa=false;
            }


            if (cajas[3])
            {
                if (!estadoCajaChampions)
                    estadoCajaChampions=true;
            }
            else
            if (!cajas[3])
            {
                if (estadoCajaChampions)
                    estadoCajaChampions=false;
            }


            if (cajas[4])
            {
                if (!estadoCajaMundial)
                    estadoCajaMundial=true;
            }
            else
            if (!cajas[4])
            {
                if (estadoCajaMundial)
                    estadoCajaMundial=false;
            }

            cajas[0]=cajaLiga.isChecked();
            cajas[1]=cajaExtranjera.isChecked();
            cajas[2]=cajaCopa.isChecked();
            cajas[3]=cajaChampions.isChecked();
            cajas[4]=cajaMundial.isChecked();

            jugadorActual.setCajas(cajas);
            lista.set(indice, jugadorActual);

            estado=true;



            if (estado)
                ventana("Mensaje:","Formulario grabado correctamente...", contexto);
        }
        else
        if (v.getId()==boton2.getId())
        {
            if (!radioPulsado)
            {
                if (opcionNacionalizado.isChecked())
                    opcionNacionalizado.setChecked(false);
                else
                if (opcionComunitario.isChecked())
                    opcionComunitario.setChecked(false);
                else
                if (opcionExtraComunitario.isChecked())
                    opcionExtraComunitario.setChecked(false);

                radio=0;
            }
            else
            {
                if (radio!=1   &&  radioFinal==1)
                    opcionNacionalizado.setChecked(true);
                else
                if (radio!=2   &&  radioFinal==2)
                    opcionComunitario.setChecked(true);
                else
                if (radio!=3   &&  radioFinal==3)
                    opcionExtraComunitario.setChecked(true);
            }


                if (!cajas[0]   &&   estadoCajaLiga)
                    cajaLiga.setChecked(true);
                else
                if (cajas[0]   &&   !estadoCajaLiga)
                    cajaLiga.setChecked(false);


                if (!cajas[1]   &&   estadoCajaExtranjera)
                    cajaExtranjera.setChecked(true);
                else
                if (cajas[1]   &&   !estadoCajaExtranjera)
                    cajaExtranjera.setChecked(false);


                if (!cajas[2]   &&   estadoCajaCopa)
                    cajaCopa.setChecked(true);
                else
                if (cajas[2]   &&   !estadoCajaCopa)
                    cajaCopa.setChecked(false);


                if (!cajas[3]   &&   estadoCajaChampions)
                    cajaChampions.setChecked(true);
                else
                if (cajas[3]   &&   !estadoCajaChampions)
                    cajaChampions.setChecked(false);


                if (!cajas[4]   &&   estadoCajaMundial)
                    cajaMundial.setChecked(true);
                else
                if (cajas[4]   &&   !estadoCajaMundial)
                    cajaMundial.setChecked(false);
        }
        else
        if (v.getId()==boton3.getId())
        {
            Intent accion = new Intent(contexto, Actividad.class);



            accion.putExtra("actividad", false);
            accion.putExtra("indiceRecibido", indice);
            accion.putExtra("objeto", (Parcelable) jugadorRecibido);
            accion.putExtra("objetoActual", (Parcelable) jugadorActual);
            accion.putExtra("lista", lista);

            startActivity(accion);
        }
        else
        if (v.getId()==cajaLiga.getId())
        {


            boolean check = cajaLiga.isChecked();

            boton3.setText(String.valueOf(check));
            if (!cajaLiga.isChecked())
            {
                cajas[0] = false;
            }
            else
            {
                cajas[0] = true;
            }
        }
        else
        if (v.getId()==cajaExtranjera.getId())
        {
            if (!cajaExtranjera.isChecked())
                cajas[1] = false;
            else
                cajas[1] = true;
        }
        else
        if (v.getId()==cajaCopa.getId())
        {
            if (!cajaCopa.isChecked())
                cajas[2] = false;
            else
                cajas[2] = true;
        }
        else
        if (v.getId()==cajaChampions.getId())
        {
            if (!cajaChampions.isChecked())
                cajas[3] = false;
            else
                cajas[3] = true;
        }
        else
        if (v.getId()==cajaMundial.getId())
        {
            if (!cajaMundial.isChecked())
                cajas[4] = false;
            else
                cajas[4] = true;
        }
    }
}
