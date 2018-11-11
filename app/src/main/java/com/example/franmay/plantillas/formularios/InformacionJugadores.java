package com.example.franmay.plantillas.formularios;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.franmay.plantillas.R;
import com.example.franmay.plantillas.clases.Jugador;

import java.util.ArrayList;
import java.util.Arrays;

public class InformacionJugadores extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    Jugador jugador = new Jugador();


    boolean radioPulsado = false;
    boolean cajaPulsada = false;

    boolean estadoCajaLiga=false;
    boolean estadoCajaExtranjera=false;
    boolean estadoCajaCopa=false;
    boolean estadoCajaChampions=false;
    boolean estadoCajaMundial=false;

    Button botonGrabar, botonResetear, botonVolver;

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
        setContentView(R.layout.activity_informacion_jugadores);


        Arrays.fill(cajas, Boolean.FALSE);

        contexto = this;

        botonGrabar = (Button) findViewById(R.id.buttonRecord);
        botonResetear = (Button) findViewById(R.id.buttonCancel);
        botonVolver = (Button) findViewById(R.id.buttonBack);

        opcionNacionalizado = (RadioButton) findViewById(R.id.RbOpcion1);
        opcionComunitario = (RadioButton) findViewById(R.id.RbOpcion2);
        opcionExtraComunitario = (RadioButton) findViewById(R.id.RbOpcion3);

        cajaLiga = (CheckBox) findViewById(R.id.checkboxLiga);
        cajaExtranjera = (CheckBox) findViewById(R.id.checkboxExtranjeras);
        cajaCopa = (CheckBox) findViewById(R.id.checkboxCopa);
        cajaChampions= (CheckBox) findViewById(R.id.checkboxChampions);
        cajaMundial = (CheckBox) findViewById(R.id.checkboxMundial);


        RadioGroup radioGroupSituacion = (RadioGroup) findViewById(R.id.GrbGrupo1);

        radioGroupSituacion.setOnCheckedChangeListener(this);

        botonGrabar.setOnClickListener(this);
        botonResetear.setOnClickListener(this);
        botonVolver.setOnClickListener(this);

        cajaLiga.setOnClickListener(this);
        cajaExtranjera.setOnClickListener(this);
        cajaCopa.setOnClickListener(this);
        cajaChampions.setOnClickListener(this);
        cajaMundial.setOnClickListener(this);

        /*radio=jugador.getRadio();
        cajas=jugador.getCajas();
        cajaActual=jugador.getCajas();*/

        /*Bundle datos = getIntent().getExtras();

        boolean registroGrabado = datos.getBoolean("registroGrabado");
        jugador = datos.getParcelable("objeto");*/


        /*if (jugador.getNacionalizado()==1)
        {
            opcionNacionalizado.setChecked(true);
            radioPulsado=true;
            radioFinal=radio;
        }
        else
        if (jugador.getComunitario()==1)
        {
            opcionComunitario.setChecked(true);
            radioPulsado=true;
            radioFinal=radio;
        }
        else
        if (jugador.getExtracomunitario()==1)
        {
            opcionExtraComunitario.setChecked(true);
            radioPulsado=true;
            radioFinal=radio;
        }


        if (jugador.getLiga()==1)
        {
            cajaLiga.setChecked(true);
            cajaPulsada=true;
        }

        if (jugador.getLiga_extranjera()==1)
            {
            cajaExtranjera.setChecked(true);
            cajaPulsada=true;
        }

        if (jugador.getCopa_rey()==1)
        {
            cajaCopa.setChecked(true);
            cajaPulsada=true;
        }

        if (jugador.getChampions_league()==1)
        {
            cajaChampions.setChecked(true);
            cajaPulsada=true;
        }

        if (jugador.getMundial()==1)
        {
            cajaMundial.setChecked(true);
            cajaPulsada=true;
        }







        /*jugador.getNacionalizado(),
                jugador.getComunitario(),
                jugador.getExtracomunitario(),

                jugador.getLiga(),
                jugador.getLiga_extranjera(),
                jugador.getCopa_rey(),
                jugador.getChampions_league(),
                jugador.getMundial());*/






    }

    @Override
    public void onClick(View v)
    {
        if (v.getId()==cajaLiga.getId())
        {

            Toast toast = Toast.makeText(getApplicationContext(), "caja 1", Toast.LENGTH_LONG);
            toast.show();
            /*boolean check = cajaLiga.isChecked();

            boton3.setText(String.valueOf(check));
            if (!cajaLiga.isChecked())
            {
                cajas[0] = false;
            }
            else
            {
                cajas[0] = true;
            }*/
        }
        else
        if (v.getId()==cajaExtranjera.getId())
        {
            Toast toast = Toast.makeText(getApplicationContext(), "caja 2", Toast.LENGTH_LONG);
            toast.show();

            /*if (!cajaExtranjera.isChecked())
                cajas[1] = false;
            else
                cajas[1] = true;*/
        }
        else
        if (v.getId()==cajaCopa.getId())
        {
            Toast toast = Toast.makeText(getApplicationContext(), "caja 3", Toast.LENGTH_LONG);
            toast.show();
            /*if (!cajaCopa.isChecked())
                cajas[2] = false;
            else
                cajas[2] = true;*/
        }
        else
        if (v.getId()==cajaChampions.getId())
        {
            Toast toast = Toast.makeText(getApplicationContext(), "caja 4", Toast.LENGTH_LONG);
            toast.show();

            /*if (!cajaChampions.isChecked())
                cajas[3] = false;
            else
                cajas[3] = true;*/
        }
        else
        if (v.getId()==cajaMundial.getId())
        {

            Toast toast = Toast.makeText(getApplicationContext(), "caja 5", Toast.LENGTH_LONG);
            toast.show();
            /*if (!cajaMundial.isChecked())
                cajas[4] = false;
            else
                cajas[4] = true;*/
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        if (group.getId() == R.id.GrbGrupo1)
        {
            switch (checkedId)
            {
                case R.id.RbOpcion1:
                   Toast toast = Toast.makeText(getApplicationContext(), "uno", Toast.LENGTH_LONG);
                    toast.show();

                    radio=1;
                    break;

                case R.id.RbOpcion2:
                    Toast toast1 = Toast.makeText(getApplicationContext(), "dos", Toast.LENGTH_LONG);
                    toast1.show();

                    radio=2;
                    break;

                case R.id.RbOpcion3:
                    Toast toast2 = Toast.makeText(getApplicationContext(), "tres", Toast.LENGTH_LONG);
                    toast2.show();
                    radio=3;
                    break;
            }
        }
    }
}
