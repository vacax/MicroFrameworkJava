<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${titulo}</title>
</head>
<body>
    <#-- probando los if-->
     <#if usuario.usuario == "camacho">
          Hola Sr. Carlos Camacho
     </#if>

    <#if usuario.usuario == "asdad">
    No debe presentarse....
    </#if>


    <p>
       La cantidad de estudiantes: ${listaEstudiante?size}
        <table>
        <tr><th>Matricula</th><th>Nombre</th><th>Carrera</th></tr>
        <#-- Iterando elementos.-->
<#list listaEstudiante as estudiante>
    <tr><td>${estudiante.matricula?string["0"]}</td><td>${estudiante.nombre}</td><td>${estudiante.carrera}</td></tr>
</#list>
        </table>
    </p>

    <#-- Incluyendo codigo para reutilizar...-->
    <#include "/include_html.html">
</body>
</html>