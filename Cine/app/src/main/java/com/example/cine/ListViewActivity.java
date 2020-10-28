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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_main);
        PeliculasSQLiteHelper peldbh = new PeliculasSQLiteHelper(this, PeliculasSQLiteHelper.DATABASE_NAME,
                null, PeliculasSQLiteHelper.DATABASE_VERSION);

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
