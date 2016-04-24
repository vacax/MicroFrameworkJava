package org.rd.barcamp.sparkbarcamp.main;

import org.rd.barcamp.sparkbarcamp.encapsulacion.Usuario;

import static spark.Spark.*;

/**
 * Ejemplos de uso de filtros en Spark.
 */
public class Filtros {

    /**
     *
     */
    public void aplicarFiltros(){

        /**
         * Se ejecuta antes de un request. Podemos modificar las llamada.
         */
        before((request, response) -> {
            System.out.println("Filtro Before -> Realizando llamada a la ruta: "+request.pathInfo());
        });

        /**
         * Luego de la ejecución permute interceptar el response...
         */
        after((request, response) -> {
            System.out.println("Filtro After -> Incluyendo Header...");
            response.header("barcamp", "2016");
            response.header("otroHeader", "Cualquier Cosa");
        });

        /**
         * Se ejecuta antes de un request. Podemos modificar las llamada.
         */
        before("/zonaadmin/*",(request, response) -> {
            Usuario usuario=request.session(true).attribute("usuario");
            if(usuario==null){
                //parada del request, enviando un codigo.
                halt(401, "No tiene permisos para acceder -- Lo dice el filtro....");
            }
        });

    }
}
