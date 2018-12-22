package com.example.franmay.plantillas.fragmentos;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.franmay.plantillas.MainActivity;
import com.example.franmay.plantillas.R;
import com.example.franmay.plantillas.VentanaEmergente;
import com.example.franmay.plantillas.adaptadores.AdaptadorEquipos;
import com.example.franmay.plantillas.formularios.FormularioEquiposJugador;
import com.example.franmay.plantillas.pojos.Equipo;
import com.example.franmay.plantillas.pojos.EquipoJugador;
import com.example.franmay.plantillas.pojos.Jugador;
import com.example.franmay.plantillas.proveedor_contenido.Contrato;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class SeccionesEquipos extends AppCompatActivity {

    static int id, recursoImagenEquipo;
    byte[] foto;
    static String nombreEquipo;

    static boolean insertar;
    static String equipo;

    static Jugador jugadorActual = new Jugador();

    static int numberTab=0;

    static ArrayList<EquipoJugador> listaEquipos;
    static ArrayList<Equipo> listaPrimeraDivision = new ArrayList<>();
    static ArrayList<Equipo> listaSegundaDivision = new ArrayList<>();

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secciones_equipo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsSeccionesEquipos);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                numberTab = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });

        // habilitar botón de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.setTitle("Seleccionar equipo.");

        listaEquipos = new ArrayList<>();

        Bundle datos = getIntent().getExtras();

        insertar=datos.getBoolean("insertar");
        equipo=datos.getString("nombreEquipo");
        jugadorActual=datos.getParcelable("objeto");
        listaEquipos=datos.getParcelableArrayList("lista");

       /* VentanaEmergente vv = new VentanaEmergente("..", String.valueOf(listaEquipos.size()), this);

        vv.ventana();*/


        id=jugadorActual.getId();
        foto=jugadorActual.getFoto();

        leerPrimeraDivision();
        leerSegundaDivision();
    }


    public void leerPrimeraDivision()
    {
        listaPrimeraDivision.add(new Equipo(R.drawable.alaves, "Alavés", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.at_madrid, "At. Madrid", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.bilbao, "Ath. Bilbao", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.barcelona, "Barcelona", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.betis, "Betis", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.celta, "Celta", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.eibar, "Eibar", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.espanyol, "Español", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.getafe, "Getafe", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.girona, "Girona", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.huesca, "Huesca", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.leganes, "Leganes", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.levante, "Levante", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.rayo_vallecano, "Rayo Vallecano", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.real_madrid, "Real Madrid", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.real_sociedad, "Real Sociedad", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.sevilla, "Sevilla", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.valencia, "Valencia", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.valladolid, "Valladolid", 0));
        listaPrimeraDivision.add(new Equipo(R.drawable.villareal, "Villareal", 0));
    }


    public void leerSegundaDivision()
    {
        listaSegundaDivision.add(new Equipo(R.drawable.albacete, "Albacete", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.alcorcon, "Alcorcón", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.almeria, "Almería", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.cadiz, "Cádiz", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.cordoba, "Córdoba", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.deportivo_coruna, "Deportivo Coruña", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.elche, "Elche", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.extremadura, "Extremadura", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.gimnastic_tarragona, "Gimnastic Tarragona", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.granada, "Granada", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.las_palmas, "UD Las Palmas", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.lugo, "Lugo", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.malaga, "Málaga", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.mallorca, "Mallorca", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.numancia, "Numancia", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.osasuna, "Osasuna", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.rayo_majadahonda, "Rayo Majadahonda", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.real_oviedo, "Real Oviedo", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.reus_deportivo, "Reus Deportivo", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.sporting_gijon, "Sporting Gijón", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.tenerife, "Tenerife", 0));
        listaSegundaDivision.add(new Equipo(R.drawable.zaragoza, "Zaragoza", 0));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_secciones_equipo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.menuHome:
                Intent accion = new Intent(this, MainActivity.class);
                startActivity(accion);
            break;

            case android.R.id.home:
                Intent accionVolver = new Intent(this, FormularioEquiposJugador.class);
                //.putExtra("nombreEquipo", equipo);
                accionVolver.putExtra("insertar", false);
                accionVolver.putExtra("nombreEquipo", equipo);
                accionVolver.putExtra("objeto", (Parcelable) jugadorActual);
                startActivity(accionVolver);
        }

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings)
        {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements AdapterView.OnItemClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View vista = inflater.inflate(R.layout.fragment_secciones_equipo, container, false);

            int fragmento=getArguments().getInt(ARG_SECTION_NUMBER);

            ListView listaImagenes;
            AdaptadorEquipos adaptador;


            switch (fragmento)
            {
                case 1:
                    //ListView listaImagenes;
                    listaImagenes = (ListView) vista.findViewById(R.id.listaDivisiones);

                    //AdaptadorCuerpoTecnico adaptador1;
                    adaptador = new AdaptadorEquipos(getActivity(), listaPrimeraDivision);
                    listaImagenes.setAdapter(adaptador);
                    listaImagenes.setOnItemClickListener(this);
                break;


                case 2:
                    //ListView listaImagenes2;
                    listaImagenes = (ListView) vista.findViewById(R.id.listaDivisiones);

                    //AdaptadorJugadores adaptador2;
                    adaptador = new AdaptadorEquipos(getActivity(), listaSegundaDivision);
                    listaImagenes.setAdapter(adaptador);
                    listaImagenes.setOnItemClickListener(this);
            }

            return vista;
        }


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            int fragmentoActual=getArguments().getInt(ARG_SECTION_NUMBER);

            if (fragmentoActual==1)
            {
                nombreEquipo=listaPrimeraDivision.get(position).getEquipo();
                recursoImagenEquipo=listaPrimeraDivision.get(position).getFoto();
            }
            else
            {
                nombreEquipo=listaSegundaDivision.get(position).getEquipo();
                recursoImagenEquipo=listaSegundaDivision.get(position).getFoto();
            }


            String mensaje="¿Quiere agregar este equipo?" + "\n--> " + nombreEquipo;

            AlertDialog.Builder ventana = new AlertDialog.Builder(getContext());

            ventana.setTitle("Mensaje.");
            ventana.setMessage(mensaje);

            ventana.setPositiveButton("Continuar", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                    buscarEquipo();
                }
            });

            ventana.setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
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


        public void buscarEquipo()
        {
            int indice=0;
            boolean salir=false;
            String equipoBuscar;
            String mensaje="Ya existe registrado el equipo:\n--> ";


            while ((indice<listaEquipos.size())   &&   (!salir))
            {
                equipoBuscar=listaEquipos.get(indice).getNombre();

                if (nombreEquipo.equals(equipoBuscar))
                {
                    salir=true;
                }
                else
                    indice++;
            }

            if (salir)
            {
                mensaje+=nombreEquipo;

                VentanaEmergente existe = new VentanaEmergente("Advertencia.", mensaje, getContext());
                existe.ventana();
            }
            else
                grabarEquipo();
        }


        public void grabarEquipo()
        {
            ContentValues valores = new ContentValues();

            valores.put(Contrato.ID_JUGADOR, id);
            valores.put(Contrato.NOMBRE_EQUIPO, nombreEquipo);
            valores.put(Contrato.EQUIPO_PAIS, "España");

            Drawable drawable = getResources().getDrawable(recursoImagenEquipo);

            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            byte[] byteArray = stream.toByteArray();
            valores.put(Contrato.FOTO_EQUIPO, byteArray);

            try
            {
                Uri uri = getContext().getContentResolver().insert(Contrato.CONTENT_URI_EQUIPOS, valores);

                Intent accion = new Intent(getContext(), FormularioEquiposJugador.class);
                accion.putExtra("insertar", insertar);
                accion.putExtra("nombreEquipo", equipo);
                accion.putExtra("objeto", (Parcelable) jugadorActual);
                startActivity(accion);
            }
            catch (IllegalArgumentException e)
            {
                VentanaEmergente vv = new VentanaEmergente("Uri incorrecta.", e.getMessage(), getContext());
                vv.ventana();
            }
            catch (SQLException e)
            {
                VentanaEmergente vv = new VentanaEmergente("Consulta SQL incorrecta.", e.getMessage(), getContext());
                vv.ventana();
            }
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }


        @Override
        public int getCount()
        {
            // Show 3 total pages.
            return 2;
        }


        @Override
        public CharSequence getPageTitle(int position)
        {
            switch (position)
            {
                case 0: return "Primera División";

                case 1: return "Segunda División";
            }

            return null;
        }
    }
}
