package com.example.franmay.plantillas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorEquipos extends BaseAdapter {

    private Context contexto;
    private ArrayList<Equipo> lista;

    public AdaptadorEquipos(Context contexto, ArrayList<Equipo> lista)
    {
        this.contexto = contexto;
        this.lista = lista;
    }


    @Override
    public int getCount()
    {
        return lista.size();
    }

    @Override
    public Object getItem(int position)
    {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        Equipo itemLista = (Equipo) getItem(position);


        vista = LayoutInflater.from(contexto).inflate(R.layout.item_equipos, null);

        /*if (convertView == null)
        {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inf.inflate(R.layout.item, null);
        }*/

        ImageView imagenFoto = (ImageView) vista.findViewById(R.id.iconoEquipo);
        imagenFoto.setImageResource(itemLista.getFoto());

        TextView nombreEquipo = (TextView) vista.findViewById(R.id.nombreEquipo);
        nombreEquipo.setText(itemLista.getEquipo());

        return vista;
    }
}
