package sax_parsing;

import java.util.Vector;

public class SesionesPelicula {
    
    Pelicula pelicula;
    Vector sesionesStrPelicula;
    
    public SesionesPelicula(Pelicula pelicula, Vector sesionesStrPelicula) {
        this.pelicula = pelicula;
        this.sesionesStrPelicula = sesionesStrPelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public void setSesionesStrPelicula(Vector sesionesStrPelicula) {
        this.sesionesStrPelicula = sesionesStrPelicula;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public Vector getSesionesStrPelicula() {
        return sesionesStrPelicula;
    }
}
