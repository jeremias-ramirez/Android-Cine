package com.example.cine;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.provider.MediaStore;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;

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

        TextView nombrePelicula = (TextView)findViewById(R.id.nombrePelicula);
        TextView anioPelicula = (TextView)findViewById(R.id.anioPelicula);
        ImageView background = (ImageView)findViewById(R.id.background);
        WebView urlTrailer = (WebView)findViewById(R.id.urlTrailer);

        nombrePelicula.setText(p.getNombrePelicula());
        anioPelicula.setText(p.getAnioPelicula());

        Picasso.get().load(p.getUriImg()).into(background);

        urlTrailer.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        urlTrailer.getSettings().setJavaScriptEnabled(true);
        urlTrailer.loadUrl(p.getUrlTrailer());
    }

    private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getContentResolver().query(contentURI,
                null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

}
