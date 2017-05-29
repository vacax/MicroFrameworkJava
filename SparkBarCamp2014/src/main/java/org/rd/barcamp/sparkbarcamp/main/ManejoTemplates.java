package org.rd.barcamp.sparkbarcamp.main;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.rd.barcamp.sparkbarcamp.encapsulacion.Estudiante;
import org.rd.barcamp.sparkbarcamp.encapsulacion.Usuario;
import org.rd.barcamp.sparkbarcamp.servicios.FakeServices;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

/**
 * Permite trabajar con los templates
 */
public class ManejoTemplates {

    // Declaración para simplificar el uso del motor de template Thymeleaf.
    public static String renderThymeleaf(Map<String, Object> model, String templatePath) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void manejoTemplate(){
        ejemplosTemplatesFreeMarker();
        ejemplosTemplateThymeleaf();
    }

    /**
     * FreeMarker utiliza por defecto la carpeta: spark/template/freemarker
     * En el ejemplo se cambia con fines de demostración.
     */
    public void ejemplosTemplatesFreeMarker(){

        //Indicando la carpeta por defecto que estaremos usando.
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(ManejoTemplates.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        /**
         * Formulario sencillo en
         * http://localhost:4567/formulario/
         */
        get("/formulario/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Formulario en FreeMarker");
            return new ModelAndView(attributes, "formulario.ftl");
        }, freeMarkerEngine);

        /**
         * http://localhost:4567/datosEstudiante/20011136
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

            String variableOculta = request.queryParams("variable_oculta");
            int matricula = Integer.parseInt(request.queryParams("matricula"));
            String nombre =request.queryParams("nombre");
            String carrera =request.queryParams("carrera");
            System.out.println("La variable Oculta: "+variableOculta);

            Estudiante estudiante= new Estudiante(matricula, nombre, carrera);

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Procesando Estudiante");
            attributes.put("estudiante", estudiante);

            //enviando los parametros a la vista.
            return new ModelAndView(attributes, "formularioProcesado.ftl");
        }, freeMarkerEngine); //

        /**
         * http://localhost:4567/funcionalidadFreemarker
         */
        get("/funcionalidadFreemarker", (request, response) -> {

                    List<Estudiante> listaEstudiante = new ArrayList<>();
                    listaEstudiante.add(new Estudiante(20011136, "Carlos Camacho", "ITT"));
                    listaEstudiante.add(new Estudiante(20011137, "Otro Estudiante", "ISC"));

                    Map<String, Object> attributes = new HashMap<>();
                    attributes.put("titulo", "Ejemplo de funcionalidad");
                    attributes.put("listaEstudiante", listaEstudiante);
                    attributes.put("usuario", new Usuario("camacho", "1234"));

                    return new ModelAndView(attributes, "/funcionalidad.ftl");},
                freeMarkerEngine);

    }

    /**
     * Otro manejador de template, buena integración con tecnologia Spring.
     * Por defecto utiliza como base la carpeta templates.
     */
    public void ejemplosTemplateThymeleaf(){

        /**
         * http://localhost:4567/thymeleaf/
         */
        get("/thymeleaf/", (request, response) -> {
            List<Estudiante> listaEstudiante = new ArrayList<>();
            listaEstudiante.add(new Estudiante(20011136, "Carlos Camacho", "ITT"));
            listaEstudiante.add(new Estudiante(20011137, "Otro Estudiante", "ISC"));

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("titulo", "Ejemplo de funcionalidad");
            modelo.put("listaEstudiante", listaEstudiante);
            modelo.put("usuario", new Usuario("camacho", "1234"));

            return renderThymeleaf(modelo,"/thymeleaf/funcionalidad");
        });
    }

}
