package org.rd.barcamp.sparkbarcamp.main

import org.rd.barcamp.sparkbarcamp.servicios.FakeServices
import ratpack.groovy.templating.TemplatingModule
import ratpack.handling.Handlers
import ratpack.jackson.Jackson
import ratpack.jackson.JacksonModule
import ratpack.session.SessionModule
import ratpack.session.store.MapSessionsModule
import ratpack.session.store.SessionStorage

import static ratpack.groovy.Groovy.groovyTemplate
import static ratpack.groovy.Groovy.ratpack

/**
 * Created by vacax .
 */

ratpack {

    bindings {
        //Incluyendo el modulo para los JSON
        add(new JacksonModule())
        //El modulo para trabajar con las plantillas
        add(new TemplatingModule());
        //Trabajar con las sesiones.
        add(new SessionModule())
        add(new MapSessionsModule(10, 5))
    }

    handlers{

        // Para la path / -> http://localhost:5050/
        get{
            render("Hola Barcamp 2014, Utilizando Ratpack :-D");
        }

        post{
            render("Llamada Ratcpack");
        }

        put{
            render("Llamada Ratcpack");
        }

        delete{
            render("Llamada Ratcpack");
        }

        /**
         * Recuperando parametros enviados:
         * http://localhost:5050/parametros?param1=valor1&param2=valor2
         */
        get("parametros"){
            String salida="Listando los parametros\n";
            def listadoParametros = request.queryParams;
            listadoParametros.each {k, v ->
                salida+="$k = $v \n";
            }
            render salida;
        }

        /**
         * Retornando referencia de objeto estudiante formato JSON.
         * http://localhost:5050/estudiante/20011136/
         */
        get("estudiante/:matricula"){
            println("Consulta de Estudiante...")
            int matricula = pathTokens.asInt("matricula"); // conversión automatica
            def estudiante = FakeServices.getEstudianteByMatricula(matricula);
            render(Jackson.json(estudiante));
        }

        /**
         * Ejemplo de utilización de plantillas utilizando RatPack.
         * http://localhost:5050/plantilla
         */
        get("plantilla"){
           //se utiliza con la variable model en la vista.
           render(groovyTemplate([fecha: new Date()], "barcamp2014.html" ))
        }

        /**
         * Permite listar los Cookies enviados hacia el cliente...
         * http://localhost:5050/cookies
         */
        get("cookies"){
           def listaCookies = request.cookies;
           String salida= "Listado de Cookies disponibles\n";
            listaCookies.each { cookie->
               salida+="${cookie.name} = ${cookie.value} \n"
            }

            render(salida);

        }

        /**
         * Permite crear un Cookie
         * http://localhost:5050/crearCookie
         */
        get("crearCookie"){

            def cookie =response.cookie("Ratpack", "Barcamp 2014");

            render("Creando el cookie: "+cookie);

        }

        /**
         * Permite trabajar con las sesiones.
         * http://localhost:5050/contador
         */
        get("contador"){
            SessionStorage sessionStorage = request.get(SessionStorage);
            Integer contador = sessionStorage.get("contador");
            if(!contador){
                contador=0;
            }
            contador++;
            sessionStorage.put("contador", contador);
            render("La cantidad de visita es $contador");
        }

        //Paso directo en caso de no aplicar ninguna regla.
        //Ejemplo: http://localhost:5050/css/miEstilo.css
        assets("publico");

    }
}
