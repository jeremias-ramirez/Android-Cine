package com.example.cine;

/**
 * Created by tecprog_fich on 28/09/17.
 */

public class Cartelera {
    private String nombrePelicula;
    private String anioPelicula;
    private int logo;
    private long id;
    private String urlTrailer;

    public Cartelera (String nombrePelicula, String anioPelicula, int logo) {
        this.nombrePelicula = nombrePelicula;
        this.anioPelicula = anioPelicula;
        this.logo = logo;
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

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
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
