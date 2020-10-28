package com.example.cine;

/**
 * Created by tecprog_fich on 28/09/17.
 */

public class Cartelera {
    private String nombrePelicula;
    private String anioPelicula;
    private String uriImg;
    private long id;
    private String urlTrailer;

    public Cartelera (String nombrePelicula, String anioPelicula) {
        this.nombrePelicula = nombrePelicula;
        this.anioPelicula = anioPelicula;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public String getAnioPelicula() {
        return anioPelicula;
    }

    public void setAnioPelicula(String anioPelicula) {
        this.anioPelicula = anioPelicula;
    }

    public String getUriImg() {
        return uriImg;
    }

    public void setUriImg(String uriImg) {
        this.uriImg = uriImg;
    }

    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUrlTrailer(String urlTrailer){
       this.urlTrailer = urlTrailer;
    }

    public String getUrlTrailer(){
        return urlTrailer;
    }
}
