package sax_parsing;

import java.util.Vector;
// Imports de JAXP API
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
// Imports de SAX API
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Sax_Parsing extends DefaultHandler {
// Atributos en los que se cachea el estado de SesionesPelicula
// Vector con todas las sesiones por pelicula de este cine

    private Vector sesionesPeliculas;
// Vector con los strings de horarios de todas las sesiones de una pelicula
    private Vector sesionesStrPelicula;
// Metadata asociada a una película
    private String codigo, titulo, director, actores;
// Contenido textual de una sesión
    private String textoSesion;
// Flag que indica si estamos parseando el contenido de texto de una sesión
    private boolean esTextoSesion = false;

    public Sax_Parsing(String url) {
        // Obtener referencia al parser factory
        SAXParserFactory factory
                = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true); // activar namespaces
        try {
            // Crear una nueva instancia de un SAX parser
            SAXParser saxParser = factory.newSAXParser();
            // Parsear el documento apuntado por URL
            saxParser.parse(url, this);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void startElement(String namespaceURI, String lName,
            String qName, Attributes attrs)
            throws SAXException {
        // lname --> contiene el nombre local (sin prefijo), o el string vacio
        // si procesamiento de namespaces no se lleva a cabo.
        // qname --> el nombre cualificado (con prefijo), o el string vacío si
        // namespaces no activo
        if (qName.equals("CarteleraCine")) {
            this.sesionesPeliculas = new Vector();
        } else if (qName.equals("Pelicula")) {
            // Por cada peli preparar vector con horarios de sesiones
            this.sesionesStrPelicula = new Vector();
            this.codigo = attrs.getValue("codigo");
            this.titulo = attrs.getValue("titulo");
            this.director = attrs.getValue("director");
            this.actores = attrs.getValue("actores");
        } else if (qName.equals("Sesion")) {
            // Sólo recoger texto si dentro de sesión
            this.esTextoSesion = true;
            this.textoSesion = "";
        }
    }

    public void characters(char[] ch, int start, int length) {
        if (this.esTextoSesion == true) {
            // Sólo si estamos dentro de un elemento sesión
            // recogemos su contenido.
            // Si el contenido es largo este método
            //callback puede ser 
            // invocado varias veces por el parser SAX

            this.textoSesion += (new String(ch, start,
                    length)).trim();
        }
    }

    public void endElement(String namespaceURI,
            String sName, String qName)
            throws SAXException {
        if (qName.equals("Pelicula")) {
            // Salvamos los detalles de una pelicula en un objeto
            Pelicula pelicula = new Pelicula(this.codigo, this.titulo,
                    this.director,
                    this.actores);
            // Añadimos nuevo objeto SesionesPelicula a vector
            sesionesPeliculas
            this.sesionesPeliculas.add(new SesionesPelicula(pelicula,
                    this.sesionesStrPelicula));
        } else if (qName.equals("Sesion")) {
// Por cada sesión salvar el texto conteniendo horario sesión
            this.esTextoSesion = false;
            this.sesionesStrPelicula.add(this.textoSesion);
        }
    }

    // Método para recuperar vector con
    // objetos SesionesPelicula creados
    // en parsing
    public Vector getSesionesPeliculas() {
        return this.sesionesPeliculas;
    }

    public static void main(String[] args) {

    }

}
