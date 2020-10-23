package com.example.cine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tecprog_fich on 28/09/17.
 */

public class AdapterCartelera extends BaseAdapter {

    private ArrayList<Cartelera> listaPeliculas;
    private LayoutInflater inflater;
    private Context contexto;

    public AdapterCartelera(Context contexto, ArrayList<Cartelera> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
        //this.inflater = LayoutInflater.from(contexto);
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return listaPeliculas.size() ;
    }

    @Override
    public Object getItem(int i) {
        return listaPeliculas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaPeliculas.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ContenedorView contenedorPelicula = null;
        LayoutInflater inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = inflater.inflate(R.layout.item_screen, null);
            contenedorPelicula = new ContenedorView();
            contenedorPelicula.nombrePelicula = (TextView)view.findViewById(R.id.nombrePelicula);

            contenedorPelicula.anioPelicula = (TextView)view.findViewById(R.id.anioPelicula);


            contenedorPelicula.logo = (ImageView)view.findViewById(R.id.logo);

            Cartelera pelicula = (Cartelera)getItem(i);

            contenedorPelicula.nombrePelicula.setText(pelicula.getNombrePelicula());
            contenedorPelicula.anioPelicula.setText(pelicula.getAnioPelicula());
            contenedorPelicula.logo.setImageResource(pelicula.getLogo());



            view.setTag(contenedorPelicula);

        } else {
            contenedorPelicula = (ContenedorView)view.getTag();

            Cartelera pelicula = (Cartelera)getItem(i);

            contenedorPelicula.nombrePelicula.setText(pelicula.getNombrePelicula());
            contenedorPelicula.anioPelicula.setText(pelicula.getAnioPelicula());
            contenedorPelicula.logo.setImageResource(pelicula.getLogo());


        }


        return view;
    }
}
