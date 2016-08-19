package sax_parsing;

public class Pelicula {
    
    public String codigo, titulo, director, actores;
    
    public Pelicula(String codigo, String titulo, String director, String actores) {
        
        this.codigo = codigo;
        this.titulo = titulo;
        this.director = director;
        this.actores = actores;

    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDirector() {
        return director;
    }

    public String getActores() {
        return actores;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }
    
    
}
