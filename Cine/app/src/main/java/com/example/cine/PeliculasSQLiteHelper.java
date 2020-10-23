package com.example.cine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PeliculasSQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "PeliculasBD";
    public static final String TABLE_NAME = "Peliculas";
    public static final int  DATABASE_VERSION = 2;
    public  static  final  String  CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, anio TEXT, logo INTEGER, " +
            "urlTrailer TEXT)";
    public static final String DELETE_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;

    public PeliculasSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_TABLE);

        onCreate(sqLiteDatabase);
    }

    public void saveListPeliculas(ArrayList<Cartelera> list){
        for (Cartelera p : list){
            this.savePelicula(p);
        }
    }

   public void savePelicula(Cartelera p){
       SQLiteDatabase db = this.getWritableDatabase();
       if (db != null) {
           ContentValues newP = new ContentValues();
           newP.put("nombre", p.getNombrePelicula());
           newP.put("anio", p.getAnioPelicula());
           newP.put("logo", p.getLogo());
           newP.put("urlTrailer", p.getUrlTrailer());
           db.insert(PeliculasSQLiteHelper.TABLE_NAME, null, newP);
           db.close();
       }
   }

    public ArrayList<Cartelera> getAllPeliculas(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Cartelera> list = new ArrayList<Cartelera>();
        if(db != null){
            Cursor c = db.rawQuery("SELECT * FROM "+PeliculasSQLiteHelper.TABLE_NAME,null);
            if(c.moveToFirst()){
                do{
                    list.add(this.cursorToCartelera(c));
                }while (c.moveToNext());
            }
            db.close();
        }
        return list;
    }
    private Cartelera cursorToCartelera(Cursor c){
        Cartelera p = new Cartelera(c.getString(1), c.getString(2),c.getInt(3));
        p.setId(c.getLong(0));
        p.setUrlTrailer(c.getString(4));
        return p;
    }

    public Cartelera getPelicula(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+PeliculasSQLiteHelper.TABLE_NAME + " WHERE id = " + id,null);
        Cartelera p = null;
        if (db != null){
            if(c.moveToFirst()){
                p = this.cursorToCartelera(c);
            }
            db.close();
        }
        return p;
    }
}
