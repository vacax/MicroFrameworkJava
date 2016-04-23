package org.rd.barcamp.sparkbarcamp.main;

import com.google.gson.Gson;
import org.rd.barcamp.sparkbarcamp.encapsulacion.Estudiante;
import org.rd.barcamp.sparkbarcamp.transformaciones.JsonTransformer;

import static spark.Spark.*;

/**
 * Created by vacax on 23/04/16.
 */
public class ManejoTransformaciones {

    public void ejemploTransformaciones(){

        get("/estudianteJson/", (request, response) -> {
            return new Estudiante(20011136, "Carlos Camacho", "ISC");
        }, new JsonTransformer());

        get("/estudianteJsonAceptado/", "application/json", (request, response) -> {
            return new Estudiante(20011136, "Carlos Camacho", "ISC");
        }, new JsonTransformer());

        Gson gson = new Gson();
        get("/estudianteJsonDirecto", (request, response) -> new Estudiante(20011136, "Carlos Camacho", "ISC"), gson::toJson);

    }
}
