package com.example.franmay.plantillas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Actividad extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    int numero, indice=0;
    int lugarEquipo=0, lugarPosicion=0;

    EditText editarNombre, editarEdad, editarPais;

    ImageView imagen;
    Button boton1, boton2, boton3, boton4;

    Context contexto;

    Spinner opciones;
    Spinner equipos;

    ArrayList<Jugador> listaNueva;
    Jugador jugadorModificado = new Jugador();
    Jugador jugadorActual = new Jugador();

    private Bundle savedInstanceState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);


        Bundle datos = getIntent().getExtras();


        editarNombre = (EditText) findViewById(R.id.editTextName);
        editarEdad = (EditText) findViewById(R.id.editTextAge);
        editarPais = (EditText) findViewById(R.id.editTextCountry);

        equipos = (Spinner) findViewById(R.id.spinnerEquipo);
        opciones = (Spinner) findViewById(R.id.spinnerPosicion);


        ArrayAdapter<CharSequence> adaptador1 = ArrayAdapter.createFromResource(this,
                R.array.listadoEquipos, R.layout.spinner_item);

        equipos.setAdapter(adaptador1);


        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this,
                R.array.opciones, R.layout.spinner_item);

        opciones.setAdapter(adaptador);


        boton1 = (Button) findViewById(R.id.buttonAccept);
        boton2 = (Button) findViewById(R.id.buttonBack);
        boton3 = (Button) findViewById(R.id.buttonCancel);
        boton4 = (Button) findViewById(R.id.buttonOther);

        equipos.setOnItemSelectedListener(this);
        opciones.setOnItemSelectedListener(this);
        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
        boton4.setOnClickListener(this);

        imagen = (ImageView) findViewById(R.id.foto);

        contexto=this;


        boolean actividad = datos.getBoolean("actividad");

        if (actividad)
        {
            listaNueva = datos.getParcelableArrayList("lista");

            indice=datos.getInt("indice");

            jugadorModificado.setFoto(listaNueva.get(indice).getFoto());
            jugadorModificado.setNombre(listaNueva.get(indice).getNombre());
            jugadorModificado.setPais(listaNueva.get(indice).getPais());
            jugadorModificado.setEquipo(listaNueva.get(indice).getEquipo());
            jugadorModificado.setIndiceEquipo(listaNueva.get(indice).getIndiceEquipo());
            jugadorModificado.setPosicion(listaNueva.get(indice).getPosicion());
            jugadorModificado.setIndicePosicion(listaNueva.get(indice).getIndicePosicion());
            jugadorModificado.setEdad(listaNueva.get(indice).getEdad());
            jugadorModificado.setRadio(listaNueva.get(indice).getRadio());
            jugadorModificado.setCajas(listaNueva.get(indice).getCajas());

            if (!jugadorModificado.getNombre().equals("Jugador"))
                editarNombre.setText(listaNueva.get(indice).getNombre());

            int edad = listaNueva.get(indice).getEdad();

            if (edad > 0)
                editarEdad.setText(String.valueOf(edad));

            imagen.setImageResource(listaNueva.get(indice).getFoto());

            editarPais.setText(listaNueva.get(indice).getPais());

            equipos.setSelection(listaNueva.get(indice).getIndiceEquipo());
            opciones.setSelection(listaNueva.get(indice).getIndicePosicion());

            jugadorActual.setFoto(jugadorModificado.getFoto());
            jugadorActual.setNombre(jugadorModificado.getNombre());
            jugadorActual.setPais(jugadorModificado.getPais());
            jugadorActual.setEquipo(jugadorModificado.getEquipo());
            jugadorActual.setIndiceEquipo(jugadorModificado.getIndiceEquipo());
            jugadorActual.setPosicion(jugadorModificado.getPosicion());
            jugadorActual.setIndicePosicion(jugadorModificado.getIndicePosicion());
            jugadorActual.setEdad(jugadorModificado.getEdad());
            jugadorActual.setRadio(jugadorModificado.getRadio());
            jugadorActual.setCajas(jugadorModificado.getCajas());
        }
        else
        {
            listaNueva = datos.getParcelableArrayList("lista");

            indice=datos.getInt("indiceRecibido");

            Jugador jugadorRecibido = new Jugador();

            jugadorRecibido = datos.getParcelable("objeto");
            jugadorActual = datos.getParcelable("objetoActual");


            jugadorModificado.setFoto(jugadorRecibido.getFoto());
            jugadorModificado.setNombre(jugadorRecibido.getNombre());
            jugadorModificado.setEquipo(jugadorRecibido.getEquipo());
            jugadorModificado.setPais(jugadorRecibido.getPais());
            jugadorModificado.setIndiceEquipo(jugadorRecibido.getIndiceEquipo());
            jugadorModificado.setPosicion(jugadorRecibido.getPosicion());
            jugadorModificado.setIndicePosicion(jugadorRecibido.getIndicePosicion());
            jugadorModificado.setEdad(jugadorRecibido.getEdad());
            jugadorModificado.setRadio(jugadorRecibido.getRadio());
            jugadorModificado.setCajas(jugadorRecibido.getCajas());

            if (!jugadorModificado.getEquipo().equals("Jugador"))
                editarNombre.setText(jugadorRecibido.getNombre());

            int edad = jugadorRecibido.getEdad();

            if (edad > 0)
                editarEdad.setText(String.valueOf(edad));

            imagen.setImageResource(jugadorRecibido.getFoto());

            editarPais.setText(jugadorRecibido.getPais());
            equipos.setSelection(jugadorRecibido.getIndiceEquipo());
            opciones.setSelection(jugadorRecibido.getIndicePosicion());


            jugadorActual.setFoto(listaNueva.get(indice).getFoto());
            jugadorActual.setNombre(listaNueva.get(indice).getNombre());
            jugadorActual.setPais(listaNueva.get(indice).getPais());
            jugadorActual.setEquipo(listaNueva.get(indice).getEquipo());
            jugadorActual.setIndiceEquipo(listaNueva.get(indice).getIndiceEquipo());
            jugadorActual.setPosicion(listaNueva.get(indice).getPosicion());
            jugadorActual.setIndicePosicion(listaNueva.get(indice).getIndicePosicion());
            jugadorActual.setEdad(listaNueva.get(indice).getEdad());
            jugadorActual.setRadio(listaNueva.get(indice).getRadio());
            jugadorActual.setCajas(listaNueva.get(indice).getCajas());
        }
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

        if (lugarEquipo==0)
        {
            ventana("Advertencia:", "No se ha seleccionado ningún equipo...", contexto);
            return;
        }

        if (lugarPosicion==0)
        {
            ventana("Advertencia:", "No se ha seleccionado ninguna posición...", contexto);
            return;
        }




        jugadorModificado.setFoto(listaNueva.get(indice).getFoto());
        jugadorModificado.setNombre(editarNombre.getText().toString());
        jugadorModificado.setEdad(Integer.parseInt(editarEdad.getText().toString()));
        jugadorModificado.setRadio(listaNueva.get(indice).getRadio());
        jugadorModificado.setPais(editarPais.getText().toString());
        jugadorModificado.setEquipo(equipos.getItemAtPosition(lugarEquipo).toString());
        jugadorModificado.setPosicion(opciones.getItemAtPosition(lugarPosicion).toString());
        jugadorModificado.setIndiceEquipo(lugarEquipo);
        jugadorModificado.setIndicePosicion(lugarPosicion);
        jugadorModificado.setCajas(listaNueva.get(indice).getCajas());

        listaNueva.set(indice, jugadorModificado);
        formularioValidado();
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
        ventana("Mensaje:", "Validación correcta...", contexto);
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
        if (v.getId()==boton1.getId())
        {
            validarCampos();
        }
        else
        if (v.getId()==boton2.getId())
        {
            Intent accion = new Intent(contexto, MainActivity.class);
            accion.putExtra("lista", listaNueva);
            startActivity(accion);
        }
        else
        if (v.getId()==boton3.getId())
        {
            if (!listaNueva.get(indice).getNombre().equals("Jugador"))
                editarNombre.setText(listaNueva.get(indice).getNombre());
            else
                editarNombre.setText("");


            int edad = listaNueva.get(indice).getEdad();

            if (edad > 0)
                editarEdad.setText(String.valueOf(edad));
            else
                editarEdad.setText("");


            editarPais.setText(listaNueva.get(indice).getPais());
            equipos.setSelection(listaNueva.get(indice).getIndiceEquipo());
            opciones.setSelection(listaNueva.get(indice).getIndicePosicion());
        }
        else
        if (v.getId()==boton4.getId())
        {
            Intent accion = new Intent(contexto, otrosDatosDeInteres.class);

            Jugador jugadorGuardado = new Jugador();

            jugadorGuardado.setNombre(editarNombre.getText().toString());
            jugadorGuardado.setPais(editarPais.getText().toString());


            int longitudEdad = editarEdad.length();

            if (longitudEdad>0)
                jugadorGuardado.setEdad(Integer.parseInt(editarEdad.getText().toString()));

            jugadorGuardado.setRadio(jugadorModificado.getRadio());

            jugadorGuardado.setFoto(jugadorModificado.getFoto());

            jugadorGuardado.setEquipo(jugadorModificado.getEquipo());
            jugadorGuardado.setPosicion(jugadorModificado.getPosicion());

            jugadorGuardado.setIndiceEquipo(jugadorModificado.getIndiceEquipo());
            jugadorGuardado.setIndicePosicion(jugadorModificado.getIndicePosicion());

            accion.putExtra("indiceActual", indice);
            accion.putExtra("objeto", (Parcelable) jugadorGuardado);
            accion.putExtra("objetoActual", jugadorActual);
            accion.putExtra("listaActual", listaNueva);

            startActivity(accion);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        if (parent.getId()==equipos.getId())
        {
            lugarEquipo=position;
            jugadorModificado.setEquipo(String.valueOf(parent.getItemAtPosition(position)));
            jugadorModificado.setIndiceEquipo(position);
        }
        else
        if (parent.getId()==opciones.getId())
        {
            lugarPosicion=position;
            jugadorModificado.setPosicion(String.valueOf(parent.getItemAtPosition(position)));
            jugadorModificado.setIndicePosicion(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
