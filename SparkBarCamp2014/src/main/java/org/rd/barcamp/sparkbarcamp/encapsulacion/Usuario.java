package org.rd.barcamp.sparkbarcamp.encapsulacion;

/**
 * Created by vacax on 11/28/14.
 */
public class Usuario {

    private String usuario;
    private String contrasena; //

    public Usuario() {
    }

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

