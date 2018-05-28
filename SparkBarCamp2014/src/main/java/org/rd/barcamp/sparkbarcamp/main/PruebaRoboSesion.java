package org.rd.barcamp.sparkbarcamp.main;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class PruebaRoboSesion {

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:4567/contadorSesion/";
        String sesionId = "1sd5a4z16b4qcb1ohmjzngxex"; //Cambiar la sesion Id.
        //ejecutando
        Connection.Response resp = Jsoup.connect(url).cookie("JSESSIONID", sesionId).method(Connection.Method.GET).execute();
        System.out.println("La respuesta es: "+resp.body());
    }
}
