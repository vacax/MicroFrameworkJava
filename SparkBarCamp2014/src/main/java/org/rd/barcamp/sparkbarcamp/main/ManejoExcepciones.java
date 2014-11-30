package org.rd.barcamp.sparkbarcamp.main;

import static spark.Spark.*;

/**
 * Presentando la forma de manejar las excepciones...
 */
public class ManejoExcepciones {

    /**
     * Permite capturar un error del tipo de conversion.
     * http://localhost:4567/errorConversion/kasdj
     */
    public void ejemplosManejoExcepciones(){
        get("/errorConversion/:entero", (request, response) -> {
            int entero = Integer.parseInt(request.params("numero"));
            return "El numero recibido: "+entero;
        });

        exception(NumberFormatException.class, (e, request, response) -> {
            response.status(500);
            response.body("Error convertiendo un número....");
        });
    }
}
