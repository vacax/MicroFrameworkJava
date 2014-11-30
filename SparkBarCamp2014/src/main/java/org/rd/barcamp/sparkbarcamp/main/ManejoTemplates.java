package org.rd.barcamp.sparkbarcamp.main;

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
        configuration.setClassForTemplateLoading(ManejoTemplates.class, "/org/rd/barcamp/sparkbarcamp/recursos/templates/");

        /**
         * http://localhost:4567/datosEstudiante
         */
        get("/datosEstudiante/:matricula", (request, response) -> {
            //obteniendo la matricula.
            Estudiante estudiante= FakeServices.getInstancia().getEstudianteMatricula(Integer.parseInt(request.params("matricula")));

            Map<String, Object> attributes = new HashMap<String, Object>();
            attributes.put("estudiante", estudiante);

            //enviando los parametros a la vista.
            return new ModelAndView(attributes, "datosEstudiante.ftl");
        }, new FreeMarkerEngine(configuration)); //
    }

}
