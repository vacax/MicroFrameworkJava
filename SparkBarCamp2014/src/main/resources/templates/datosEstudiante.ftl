<!DOCTYPE html>
<html>
<head>
    <title>Información Estudiante ${estudiante.nombre}</title>
    <link href="/css/miEstilo.css" rel="stylesheet" >
</head>
<body>
<h1>Matrícula ${estudiante.matricula?string["0"]} -  ${estudiante.nombre}</h1>
<h2>Estudia en la carrea de ${estudiante.carrera}</h2>
</body>
</html>