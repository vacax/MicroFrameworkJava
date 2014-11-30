package org.rd.barcamp.sparkbarcamp.encapsulacion;

/**
 * Pojo estudiante
 */
public class Estudiante {

    int matricula;
    String nombre;
    String carrera;

    /**
     *
     */
    public Estudiante() {

    }

    @Override
    public String toString() {
        return String.format("matricula: %d, Nombre: %s, Carrera: %s", matricula, nombre, carrera);
    }

    /**
     *
     * @param matricula
     * @param nombre
     * @param carrera
     */
    public Estudiante(int matricula, String nombre, String carrera) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.carrera = carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getMatricula() {
        return matricula;

    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
}
