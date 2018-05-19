package org.rd.barcamp.sparkbarcamp.main;

import org.rd.barcamp.sparkbarcamp.encapsulacion.Estudiante;
import org.rd.barcamp.sparkbarcamp.servicios.FakeServices;

import static spark.Spark.*;

/**
 * Ejemplos de uso de rutas.
 */
public class ManejoRutas {

    /**
     *
     */
    public void ejemplosRutas(){

        /**
         * El sistema de ruta se determina por tres factores:
         * El verbo o el metodo de protocolo http: get, put, post...
         * La ruta: "/rutas/"
         */
        get("/rutas/", (request, reponse) -> {
            return "Ejemplo de rutas... GET";
        });

        post("/rutas/", (request, reponse) -> {
            return "Ejemplo de rutas... POST";
        });

        put("/rutas/", (request, reponse) -> {
            return "Ejemplo de rutas... PUT";
        });

        delete("/rutas/", (request, reponse) -> {
            return "Ejemplo de rutas... DELETE";
        });

        options("/rutas/", (request, reponse) -> {
            return "Ejemplo de rutas... OPTIONS";
        });



        /**
         * Ejemplos de patrones de rutas
         * http://localhost:4567/rutas/20011126
         */
        get("/rutas/:matricula", (request, response)->{
            int matricula=Integer.parseInt(request.params("matricula"));
            //Consulta Fake a la base de datos...
            Estudiante estudiante = FakeServices.getInstancia().getEstudianteMatricula(matricula);
            //Estudiante estudiante =new Estudiante(matricula, "Estudiante "+matricula, "ISC");

            return estudiante;
        });

        /**
         *
         */
        get("/rutas/:matricula/:cedula/:telefono", (request, response)->{
            int matricula=Integer.parseInt(request.params("matricula"));
            //Consulta Fake a la base de datos...
            Estudiante estudiante = FakeServices.getInstancia().getEstudianteMatricula(matricula);
            //Estudiante estudiante =new Estudiante(matricula, "Estudiante "+matricula, "ISC");

            String cedula =  request.params("cedula");
            String telefono =  request.params("telefono");

            System.out.println("Cedula: "+cedula);
            System.out.println("Telefono: "+telefono);
            if(request.queryParams("parametro1")!=null){
                System.out.println("Fue enviando el parametro1 = "+request.queryParams("parametro1"));
            }
            return estudiante+" Con cedula: "+cedula+", telefono: "+telefono;
        });


        /**
         * Ejemplo de uso de comodines en la ruta.
         * http://localhost:4567/rutas/20011126/transferirMonto/100.00/a/20011287
         */
        get("/rutas/:matricula/transferirMonto/*/a/*", (request, response)->{
            //obtiendo los parametros vía comodines:
            String[] comodines=request.splat();

            //Uso de comodines
            String montoOrigen = comodines[0];
            String matriculaDestino = comodines[1];

            //Omitiendo validaciones....
            Estudiante origen= FakeServices.getInstancia().getEstudianteMatricula(Integer.parseInt(request.params("matricula")));
            Estudiante destino = FakeServices.getInstancia().getEstudianteMatricula(Integer.parseInt(matriculaDestino));

            return String.format("Monto Transferido: $%s, del Estudiante %s al %s, realizado con éxito", montoOrigen, origen.getNombre(), destino.getNombre());
        });

        /**
         * Permite agrupar direcciones, muy importante si estamos trabajando
         * sobre REST.
         */
        path("/api", () -> {
            before("/*", (q, a) -> System.out.println("Petición realizada al API."));

            /**
             * dentro de API, tendremos uno de correo.
             * http://localhost:4567/api/email/
             */
            path("/email", () -> {
                get("/",       (request, response) -> "API Email");
                post("/agregar",       (request, response) -> "Agregando Correo");
                put("/modificar",     (request, response) -> "Actualizando Correo");
                delete("/eliminar",  (request, response) -> "Eliminando Correo");
            });

            /**
             * dentro de API, tendremos el manejo de usuario.
             * http://localhost:4567/api/usuario/
             */
            path("/usuario", () -> {
                get("/",      (request, response) -> "API Usuario");
                post("/agregar",      (request, response) -> "Agregando Usuario");
                put("/modificar",     (request, response) -> "Modificando Usuario");
                delete("/eliminar", (request, response) -> "Eliminando Usuario");
            });
        });

    }
}
