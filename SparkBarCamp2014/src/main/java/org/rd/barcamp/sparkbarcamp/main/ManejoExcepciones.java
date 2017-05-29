package org.rd.barcamp.sparkbarcamp.main;

import static spark.Spark.*;

/**
 * Presentando la forma de manejar las excepciones...
 */
public class ManejoExcepciones {

    /**
     * Permite capturar un error del tipo de conversion.     *
     */
    public void ejemplosManejoExcepciones(){

        /**
         * http://localhost:4567/errorConversion/kasdj
         */
        get("/errorConversion/:entero", (request, response) -> {
            int entero = Integer.parseInt(request.params("numero"));
            return "El numero recibido: "+entero;
        });

        /**
         * La excepción que se genere del tipo NumberFormatException, será atrapada
         * por este bloque.
         */
        exception(NumberFormatException.class, (e, request, response) -> {
            response.status(500);
            response.body("Error convertiendo un número....");
            e.printStackTrace();
        });

        /**
         * Ruta para probar el plugin para visualizar los errores:
         * http://localhost:4567/errorRuntime
         * La documentación:
         * com.sparkjava:spark-debug-tools:0.5
         */
        get("/errorRuntime", (request, response) -> {
            throw new RuntimeException("Error de ejecución...");
        });
    }
}
