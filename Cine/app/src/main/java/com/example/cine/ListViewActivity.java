package com.example.cine;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by tecprog_fich on 28/09/17.
 */

public class ListViewActivity extends AppCompatActivity{
    private static boolean savePeliculasEstate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_main);
        PeliculasSQLiteHelper peldbh = new PeliculasSQLiteHelper(this, PeliculasSQLiteHelper.DATABASE_NAME,
                null, PeliculasSQLiteHelper.DATABASE_VERSION);
        if(!savePeliculasEstate){
            ArrayList<Cartelera> listaPeliculas = new ArrayList<Cartelera>();
            Cartelera p = new Cartelera("Batman", "1989", R.drawable.batman);
            p.setUrlTrailer("https://www.youtube.com/watch?v=ftNOhfNjXjk");
            listaPeliculas.add(p);
            p = new Cartelera("Superman", "1978", R.drawable.superman);
            p.setUrlTrailer("https://www.youtube.com/watch?v=q-v1RyLNWU8");
            listaPeliculas.add(p);
            p = new Cartelera("It", "1990", R.drawable.it);
            p.setUrlTrailer("https://www.youtube.com/watch?v=8i3_2iGTQi8");
            listaPeliculas.add(p);
            peldbh.saveListPeliculas(listaPeliculas);
            savePeliculasEstate = true;
        }

        ArrayList<Cartelera> listaPeliculas = peldbh.getAllPeliculas();

        AdapterCartelera adaptador = new AdapterCartelera(this, listaPeliculas);

        ListView miLista = (ListView)findViewById(R.id.MiLista);

        miLista.setAdapter(adaptador);

        miLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), SegundaPantalla.class);

                //GuardarValor
                intent.putExtra("itemId", id);
                startActivity(intent);
                finish();

            }
        });


    }


}
