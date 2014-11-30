package org.rd.barcamp.sparkbarcamp.servicios

import org.rd.barcamp.sparkbarcamp.encapsulacion.Estudiante

/**
 * Created by vacax on 11/29/14.
 */
class FakeServices {

    /**
     * Simula una conexión a una base de datos.
     * @param matricula
     * @return
     */
    public static Estudiante getEstudianteByMatricula(int matricula){
       return new Estudiante(matricula: matricula, nombre: "Estudiante $matricula", carrera: "ISC");
    }
}
