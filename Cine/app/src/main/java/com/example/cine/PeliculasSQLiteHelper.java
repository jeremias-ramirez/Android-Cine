package com.example.cine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PeliculasSQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "PeliculasBD";
    public static final String TABLE_NAME = "Peliculas";
    public static final int  DATABASE_VERSION = 23;
    public  static  final  String  CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, anio TEXT, uriImg TEXT, " +
            "urlTrailer TEXT)";
    public static final String DELETE_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;

    public PeliculasSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        this.insertarPeliculas(sqLiteDatabase);
    }
    private void insertarPeliculas(SQLiteDatabase sqLiteDatabase){
        String insert = " INSERT INTO " + TABLE_NAME + "(nombre, anio, uriImg, urlTrailer) VALUES ";
        insert += "('Batman', 1989, "+
                "'https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/3427fb1d-24fc-4ee0-9ce8-dc0c4d75d6fb/d2lh5xx-b1af30b2-c3b2-470b-b157-f0d6750131a2.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3sicGF0aCI6IlwvZlwvMzQyN2ZiMWQtMjRmYy00ZWUwLTljZTgtZGMwYzRkNzVkNmZiXC9kMmxoNXh4LWIxYWYzMGIyLWMzYjItNDcwYi1iMTU3LWYwZDY3NTAxMzFhMi5qcGcifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6ZmlsZS5kb3dubG9hZCJdfQ.p_pY0hk6QJzDzJrt29ufTsIS_5qZknXIB-LXM_gHaZ8', "+
                "'https://www.youtube.com/watch?v=ftNOhfNjXjk'), ";
        insert += "('Superman', 1978, 'https://alchetron.com/cdn/Superman-1978-film-images-e4973b41-e5aa-4618-a32d-bb0ba77b86c.jpg', " +
                "'https://www.youtube.com/watch?v=q-v1RyLNWU8'), ";
        insert += "('It', 1990, 'https://upload.wikimedia.org/wikipedia/en/thumb/b/b0/It_1990_Promotional_Poster.JPG/220px-It_1990_Promotional_Poster.JPG', " +
                "'https://www.youtube.com/watch?v=8i3_2iGTQi8');" ;
        sqLiteDatabase.execSQL(insert);
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
           Log.d("entro", "base de dato not null");
           ContentValues newP = new ContentValues();
           newP.put("nombre", p.getNombrePelicula());
           newP.put("anio", p.getAnioPelicula());
           newP.put("uriImg", p.getUriImg());
           newP.put("urlTrailer", p.getUrlTrailer());
           db.insert(PeliculasSQLiteHelper.TABLE_NAME, null, newP);
           db.close();
       }else{
           Log.d("error", "base de dato null");
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
        Cartelera p = new Cartelera(c.getString(1), c.getString(2));
        p.setUriImg(c.getString(3));
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
