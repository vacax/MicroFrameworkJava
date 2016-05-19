package org.rd.barcamp.sparkbarcamp.main;

import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Set;

import static spark.Spark.*;

/**
 * Hola Mundo Barcamp 2014
 */
public class Main {

    public static void main(String[] args) {

        //indicando los recursos publicos.
        staticFileLocation("/publico");

        /**
         * Hola mundo utilizando  SparkJava
         */
        get("/", (request, response) -> "Hola Mundo Barcamp 2014");

        /**
         *
         */
        get("/basico", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                //Conocer header del cliente.
                System.out.println("El navegador o cliente: "+request.userAgent());
                System.out.println("La IP: "+request.ip());
                System.out.println("La puerto: "+request.port());
                System.out.println("Protocolo del HTPP: "+request.protocol());
                System.out.println("Metodo del HTPP: "+request.requestMethod());
                System.out.println("Los headers: ");

                for(String header : request.headers()){
                    System.out.println(""+header+" = "+request.headers(header));
                }
                //
                //response.status(201);

                //
                return "Otra llamada";
            }
        });

        /**
         *
         */
        post("/UnaLlamadaPost", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Otra llamada";
            }
        });

        /**
         * Obteniendo los parametros...
         * http://localhost:4567/parametros?param1=valor1&param2=valor2&paramN=valorN
         */
        get("/parametros/", (request, response) -> {
            return procesarParametros(request, response);
        });

        post("/parametros/", (request, response) -> {
             return procesarParametros(request, response);
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

        new ManejoTransformaciones().ejemploTransformaciones();
    }

    /**
     * Metodo para procesar información, como ejemplo para ser llamado en rutas sin importar el metodo.
     * @param request
     * @param response
     * @return
     */
    private static Object procesarParametros(Request request, Response response){
        System.out.println("Recibiendo mensaje por el metodo: "+request.requestMethod());
        Set<String> parametros = request.queryParams();
        String salida="";
        for(String param : parametros){
            salida+=String.format("Parametro[%s] = %s <br/>", param, request.queryParams(param));
        }
        return salida;
    }

}
