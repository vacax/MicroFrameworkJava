package org.rd.barcamp.sparkbarcamp.main;

import org.rd.barcamp.sparkbarcamp.encapsulacion.Usuario;
import org.rd.barcamp.sparkbarcamp.servicios.FakeServices;
import spark.Session;

import java.util.Map;

import static spark.Spark.*;

/**
 * Manejo de Cookies y Sesiones usando Spark.
 */
public class CookieYSesiones {

    /**
     *
     */
    public void ejemploCookieSesiones(){

        /**
         * Lista todos los Cookies enviados desde el cliente.
         * http://localhost:4567/listarCookies/
         */
        get("/listarCookies/", (request, response)->{
            Map<String, String> cookies=request.cookies();
            System.out.println("El cookie: "+request.cookie("nombreCookie"));
            String salida="";
            System.out.println("La cantidad de elementos:"+cookies.size());
            for(String key : cookies.keySet()){
                salida+=String.format("Cookie %s = %s", key, cookies.get(key))+"<br/>";
            }
            return salida;
        });

        /**
         * Creando un cookies en Spark.
         * http://localhost:4567/crearCookie/barcamp/2014
         */
        get("/crearCookie/:nombreCookie/:valor", (request, response)->{
            //creando cookie en para un minuto
            response.cookie(request.params("nombreCookie"), request.params("valor"), 3600);

            return "Cookie creado con exito...";
        });

        /**
         * Registra elementos en el ambito web de sesion.
         * http://localhost:4567/contadorSesion/
         */
        get("/contadorSesion/", (request, response)->{
            //creando cookie en para un minuto
            Session session=request.session(true);
            Integer contador = session.attribute("contador");
            if(contador==null){
               contador=0;
            }
            contador++;
            session.attribute("contador", contador);

            return String.format("Usted a visitado está pagina %d", contador);
        });

        /**
         * Registra elementos en el ambito web de sesion.
         * http://localhost:4567/autenticar/barcamp/2014
         */
        get("/autenticar/:usuario/:contrasena", (request, response)->{
            //
            Session session=request.session(true);

            //
            Usuario usuario=FakeServices.getInstancia().autenticarUsuario(request.params("usuario"), request.params("contrasena"));

            if(usuario==null){
                halt(401,"Credenciales no validas...");
            }

            session.attribute("usuario", usuario);
            response.redirect("/zonaadmin/");

            return "";
        });

    }
}
