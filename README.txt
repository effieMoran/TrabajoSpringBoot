Acá se listara a continuacion para q sirven cada metodo:

localhost:8080/Alumnos ---> Muestra todos los alumnos (Metodo GET)

localhost:8080/?numLeg=legajo ---> Muestra el alumno correspondiente a dicho legajo q se le pasa por parametro ejemplo ?numeLeg=1234 (Metodo GET)

localhost:8080/add?numLeg=1234&name=matias&lastname=parra&career=TSP&cantMat=8 ---> Añade a un alumno, con los valores pasado por parametros (Metodo POST)

localhost:8080/del?numLeg=123 ---> Borra el alumno con dicho legajo si no lo encuentra devuenve un string con dicho error sino lo devuelve con un string q dice Agregado exitoso (Metodo DELETE)

localhost:8080/1234/ --> Edita el alimno con el legajo 1234 (puedes cambiar el legajo a tu gusto) con los parametros q le pases (Metodo PUT)
