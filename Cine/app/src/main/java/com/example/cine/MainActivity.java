package com.example.cine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        EditText caja;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //Button boton = (Button)findViewById(R.id.boton);

            //caja = (EditText)findViewById(R.id.cajaTexto);

            //boton.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View view) {
            //        Intent intent = new Intent(getApplicationContext(), SengundaPantalla.class);

            //        //GuardarValor
            //        intent.putExtra("nombre", caja.getText().toString());


            //        startActivity(intent);
            //        finish();
            //    }

            //});

            //Button buscar = (Button)findViewById(R.id.buscar);

            //buscar.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View view) {
            //        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            //        Intent buscar = new Intent(Intent.ACTION_VIEW, (Uri.parse("google.streetview:cbll=-31.6404049,-60.6796709")));
            //        // Make the Intent explicit by setting the Google Maps package
            //        buscar.setPackage("com.google.android.apps.maps");


            //        if (buscar.resolveActivity(getPackageManager()) != null) {
            //            startActivity(buscar);
            //        } else Toast.makeText(getApplicationContext(), "La aplicación Google Maps no esta instalada", Toast.LENGTH_SHORT).show();
            //    }
            //});
        }
    }
