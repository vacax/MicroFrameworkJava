package org.rd.barcamp.sparkbarcamp.main;

import spark.Request;
import spark.Response;
import spark.Route;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

/**
 * Hola Mundo Barcamp 2014
 */
public class Main {

    public static void main(String[] args) {

        //Seteando el puerto en Heroku
        port(getHerokuAssignedPort());

        
        //indicando los recursos publicos.
        //staticFiles.location("/META-INF/resources"); //para utilizar los WebJars.
        staticFiles.location("/publico");

        //Linea para agregar la pantalla de debug. En productivo se debe quitar.
        enableDebugScreen();



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
                response.status(201);

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
         * Equivale el modelo 1 de desarrollo APP Web. Decada de los 90.
         */
        get("/formaAntigua", (request, response)->{
           String salida ="";
           salida += "<html><head><title>Forma Antigua</title></head><body>";
           salida += "<h1>Ejemplo de programación en los 90</h1>";
           salida += "<hr/>";
           salida += "</body></html>";
           response.header("Content-Type", "text/html");
           return salida;
        });

        /**
         * Obteniendo los parametros...
         * http://localhost:4567/parametros?param1=valor1&param2=valor2&paramN=valorN
         */
        get("/parametros", (request, response) -> {
            return procesarParametros(request, response); //encapsular las petición y las respuetas.
        });

        post("/parametros", (request, response) -> {
            return procesarParametros(request, response);
        });



        /**
         * Debes estar autenticado.
         * http://localhost:4567/zonaadmin/
         */
        get("/zonaadmin/", (request, response) -> "Zona Admin Barcamp 2014");

        /**
         * Llamada que provoca un error por redirect infinitos.
         */
        get("/loop", (request, response) -> {
            response.redirect("/loop");
            return "";
        });

        /**
         * Retorna la fecha actual, para ser utilizandos llamadas ajax.
         */
        get("/fecha", (request, response) -> {
            return ""+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        });

        //Ejemplos de rutas
        new ManejoRutas().ejemplosRutas();

        //Ejemplos de Cookies.
        new CookieYSesiones().ejemploCookieSesiones();

        //Aplicando los filtros
        new Filtros().aplicarFiltros();

        //Ejemplo para el manejo de las excepciones.
        new ManejoExcepciones().ejemplosManejoExcepciones(); //Deshabilitado para ver la ventana de debug.

        //Ejemplos para el manejo de templates.
        new ManejoTemplates().manejoTemplate();

        new ManejoTransformaciones().ejemploTransformaciones();

        //
        new ManejoDescarga().rutas();
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

    /**
     * Metodo para setear el puerto en Heroku
     * @return
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
