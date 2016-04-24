package org.rd.barcamp.sparkbarcamp.main;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.rd.barcamp.sparkbarcamp.encapsulacion.Estudiante;
import org.rd.barcamp.sparkbarcamp.servicios.FakeServices;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * Permite trabajar con los templates
 */
public class ManejoTemplates {

    /**
     *
     */
    public void ejemplosTemplates(){

        //Indicando la carpeta por defecto.
        Configuration configuration=new Configuration();
        configuration.setClassForTemplateLoading(ManejoTemplates.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);


        /**
         * http://localhost:4567/formulario
         */
        get("/formulario/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Formulario en FreeMarker");
            return new ModelAndView(attributes, "formulario.ftl");
        }, freeMarkerEngine);

        /**
         * http://localhost:4567/datosEstudiante
         */
        get("/datosEstudiante/:matricula", (request, response) -> {
            //obteniendo la matricula.
            Estudiante estudiante= new Estudiante(Integer.parseInt(request.params("matricula")), "Estudiante", "Carrera");//FakeServices.getInstancia().getEstudianteMatricula(Integer.parseInt(request.params("matricula")));

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("estudiante", estudiante);

            //enviando los parametros a la vista.
            return new ModelAndView(attributes, "datosEstudiante.ftl");
        }, freeMarkerEngine); //


        post("/procesarFormulario/", (request, response) -> {
            //obteniendo la matricula.

            int matricula = Integer.parseInt(request.queryParams("matricula"));
            String nombre =request.queryParams("nombre");
            String carrera =request.queryParams("carrera");

            Estudiante estudiante= new Estudiante(matricula, nombre, carrera);

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Procesando Estudiante");
            attributes.put("estudiante", estudiante);

            //enviando los parametros a la vista.
            return new ModelAndView(attributes, "formularioProcesado.ftl");
        }, freeMarkerEngine); //

    }

}
