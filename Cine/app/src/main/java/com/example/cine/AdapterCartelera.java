package com.example.cine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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

            Picasso.get().load(pelicula.getUriImg()).placeholder(R.drawable.placeholder).into(contenedorPelicula.logo);

            view.setTag(contenedorPelicula);

        } else {
            contenedorPelicula = (ContenedorView)view.getTag();

            Cartelera pelicula = (Cartelera)getItem(i);

            contenedorPelicula.nombrePelicula.setText(pelicula.getNombrePelicula());
            contenedorPelicula.anioPelicula.setText(pelicula.getAnioPelicula());

            Picasso.get().load(pelicula.getUriImg()).placeholder(R.drawable.placeholder).into(contenedorPelicula.logo);

            //new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
             //       .execute(MY_URL_STRING);

            //FutureTarget<Bitmap> futureTarget =
            //        Glide.with(this.contexto)
            //                .asBitmap()
            //                .load(pelicula.getUriImg())
            //                .submit(100, 100);

            //try {
            //    Bitmap bitmap = futureTarget.get();
            //    contenedorPelicula.logo.setImageBitmap(bitmap);
            //} catch (ExecutionException e) {
            //    e.printStackTrace();
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}

        }

        return view;
    }




}
