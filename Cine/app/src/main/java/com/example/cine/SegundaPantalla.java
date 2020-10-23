package com.example.cine;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by tecprog_fich on 14/09/17.
 */

public class SegundaPantalla extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segunda_pantalla);

        PeliculasSQLiteHelper peldbh = new PeliculasSQLiteHelper(this, PeliculasSQLiteHelper.DATABASE_NAME,
                null, PeliculasSQLiteHelper.DATABASE_VERSION);

        Long itemId= this.getIntent().getLongExtra("itemId", 0);
        Cartelera p = peldbh.getPelicula(itemId);

        LinearLayout backgroud = (LinearLayout)findViewById(R.id.background);
        TextView nombrePelicula = (TextView)findViewById(R.id.nombrePelicula);
        TextView anioPelicula = (TextView)findViewById(R.id.anioPelicula);
        ImageView logo = (ImageView)findViewById(R.id.logo);
        WebView urlTrailer = (WebView)findViewById(R.id.urlTrailer);

        backgroud.setBackgroundResource(p.getLogo());
        nombrePelicula.setText(p.getNombrePelicula());
        anioPelicula.setText(p.getAnioPelicula());
        logo.setImageResource(p.getLogo());

        urlTrailer.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        urlTrailer.getSettings().setJavaScriptEnabled(true);
        urlTrailer.loadUrl(p.getUrlTrailer());


    }

}
