package com.example.franmay.plantillas.fragmentos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.example.franmay.plantillas.adaptadores.AdaptadorEquipos;
import com.example.franmay.plantillas.formularios.FormularioEstadio;
import com.example.franmay.plantillas.formularios.FormularioInformacionGeneral;
import com.example.franmay.plantillas.pojos.CuerpoTecnico;
import com.example.franmay.plantillas.pojos.Equipo;
import com.example.franmay.plantillas.pojos.Estadio;
import com.example.franmay.plantillas.pojos.InformacionGeneral;
import com.example.franmay.plantillas.pojos.Jugador;

import java.util.ArrayList;

public class SeccionesInformacion extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    Context contexto;

    static Bundle datos;

    static String equipo;

    static ArrayList<Equipo> lista = new ArrayList<>();

    static ArrayList<Jugador> listaJugadoresAlaves = new ArrayList<>();
    static ArrayList<CuerpoTecnico> listaCuerpoTecnicoAlaves = new ArrayList<>();

    static ArrayList<Jugador> listaJugadoresAtMadrid = new ArrayList<>();
    static ArrayList<CuerpoTecnico> listaCuerpoTecnicoAtMadrid = new ArrayList<>();

    static ArrayList<Estadio> listaEstadio = new ArrayList<>();
    static ArrayList<InformacionGeneral> listaInformacion = new ArrayList<>();

    static boolean actividad;

    static int indiceEquipo, indicePersona;

    static String tituloNavigation;
    static int opcionNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secciones_informacion);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsInformacion);
        tabLayout.setupWithViewPager(mViewPager);


        // habilitar botón de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contexto=this;

        Bundle datos = getIntent().getExtras();


        listaJugadoresAlaves=datos.getParcelableArrayList("listaAlaves1");
        listaCuerpoTecnicoAlaves=datos.getParcelableArrayList("listaAlaves2");

        listaJugadoresAtMadrid=datos.getParcelableArrayList("listaAtMadrid1");
        listaCuerpoTecnicoAtMadrid=datos.getParcelableArrayList("listaAtMadrid2");

        listaEstadio=datos.getParcelableArrayList("listaEstadio");
        listaInformacion=datos.getParcelableArrayList("listaInformacion");


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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_secciones_informacion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

            switch (item.getItemId())
            {
                case android.R.id.home:
                    Intent accion = new Intent(this, MainActivity.class);

                    accion.putExtra("listaAlaves1", listaJugadoresAlaves);
                    accion.putExtra("listaAlaves2", listaCuerpoTecnicoAlaves);

                    accion.putExtra("listaAtMadrid1", listaJugadoresAtMadrid);
                    accion.putExtra("listaAtMadrid2", listaCuerpoTecnicoAtMadrid);

                    accion.putExtra("listaEstadio", listaEstadio);
                    accion.putExtra("listaInformacion", listaInformacion);

                    startActivity(accion);
            }

            return true;
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

            View vista = inflater.inflate(R.layout.fragment_secciones_informacion, container, false);

            int fragmento=getArguments().getInt(ARG_SECTION_NUMBER);

            switch (fragmento)
            {
                case 1:
                    ListView listaImagenes;
                    listaImagenes = (ListView) vista.findViewById(R.id.listadoFinal);

                    AdaptadorEquipos adaptador1;
                    adaptador1 = new AdaptadorEquipos(getActivity(), lista);
                    listaImagenes.setAdapter(adaptador1);
                    listaImagenes.setOnItemClickListener(this);
                    break;

                case 2:
                    ListView listaImagenes2;
                    listaImagenes2 = (ListView) vista.findViewById(R.id.listadoFinal);

                    AdaptadorEquipos adaptador2;
                    adaptador2 = new AdaptadorEquipos(getActivity(), lista);
                    listaImagenes2.setAdapter(adaptador2);
                    listaImagenes2.setOnItemClickListener(this);
            }

            return vista;
        }


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            String nombreEquipo;
            String equipos[] = {"Alaves", "At. Madrid", "Ath. Bilbao", "Barcelona", "Betis", "Celta", "Eibar", "Getafe"};
            nombreEquipo=equipos[position];

            int fragmentoActual=getArguments().getInt(ARG_SECTION_NUMBER);

            Intent accion;

            if (fragmentoActual==1)
            {
                if (position<=1)
                {
                    accion = new Intent(getActivity(), FormularioInformacionGeneral.class);

                    accion.putExtra("indiceEquipo", position);
                    accion.putExtra("nombreEquipo", nombreEquipo);

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
            {
                if (position<=1)
                {
                    accion = new Intent(getActivity(), FormularioEstadio.class);

                    accion.putExtra("indiceEquipo", position);
                    accion.putExtra("nombreEquipo", nombreEquipo);

                    accion.putExtra("tituloNavigation", tituloNavigation);
                    accion.putExtra("opcionNavigation", opcionNavigation);

                    accion.putExtra("listaAlaves1", listaJugadoresAlaves);
                    accion.putExtra("listaAlaves2", listaCuerpoTecnicoAlaves);

                    accion.putExtra("listaAtMadrid1", listaJugadoresAtMadrid);
                    accion.putExtra("listaAtMadrid2", listaCuerpoTecnicoAtMadrid);

                    accion.putExtra("listaEstadio", listaEstadio);
                    accion.putExtra("listaInformacion", listaInformacion);

                    startActivity(accion);
                }
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
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }


        @Override
        public CharSequence getPageTitle(int position)
        {
            switch (position)
            {
                case 0: return "Información General";

                case 1: return "Estadio";
            }

            return null;
        }
    }
}
