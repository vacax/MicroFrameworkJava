package org.rd.barcamp.sparkbarcamp.main;

import java.util.Set;

import static spark.Spark.*;

/**
 * Hola Mundo Barcamp 2014
 */
public class Main {

    public static void main(String[] args) {

        //indicando los recursos publicos.
        staticFileLocation("/org/rd/barcamp/sparkbarcamp/recursos/publico/");

        /**
         * Hola mundo utilizando SparkJava
         */
        get("/", (request, response) -> "Hola Mundo Barcamp 2014");

        /**
         * Obteniendo los parametros...
         * http://localhost:4567/parametros/?param1=valor1&param2=valor2
         */
        get("/parametros/", (request, response) -> {
            Set<String> parametros = request.queryParams();
            String salida="";
            for(String param : parametros){
                salida+=String.format("Parametro[%s] = %s <br/>", param, request.queryParams(param));
            }
            return salida;
        });

        /**
         * Debes estar autenticado.
         * http://localhost:4567/zonaadmin/
         */
        get("/zonaadmin/", (request, response) -> "Zona Admin Barcamp 2014");

        //Ejemplos de rutas
        new ManejoRutas().ejemplosRutas();

        //Ejemplos de Cookies.
        new CookieYSesiones().ejemploCookieSesiones();

        //Aplicando los filtros
        new Filtros().aplicarFiltros();

        //Ejemplo para el manejo de las excepciones.
        new ManejoExcepciones().ejemplosManejoExcepciones();

        //Ejemplos para el manejo de templates.
        new ManejoTemplates().ejemplosTemplates();


    }
}
