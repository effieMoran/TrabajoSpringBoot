package hello;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.ejb.access.EjbAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private final AtomicLong counter = new AtomicLong();

	/// ------->simulacion de bases de datos<-------///
	// private static String[] carreras ={"ISI","TSP","LAR","IQ","IEM"};

	private LinkedList<GreetingAlumno> alumnos;

	// seteo la lista
	// metodo para generar un numero random
	// public static int generarNumero(int desde, int hasta){
	// Random rnd = new Random();
	// return rnd.nextInt(hasta-desde+1)+desde;
	// }
	// static{
	// //primer alumno
	// String nombre = "Matias";
	// String apellido ="Parra";
	// int numAleatorio = generarNumero(0, 4);
	// String carrera = carreras[numAleatorio];
	// int numLega = generarNumero(1, 9999);
	// int cantMaterias=generarNumero(1, 25);
	// GreetingAlumno alu = new GreetingAlumno(numLega,nombre,apellido,carrera,cantMaterias);
	// agregar(alu);
	//
	// //segundo alumno
	// String n = "Matias";
	// String a ="Parra";
	// numAleatorio = generarNumero(0, 4);
	// String car = carreras[numAleatorio];
	// int numL = generarNumero(1, 9999);
	// int cantMt=generarNumero(1, 25);
	// Alumno alu1 = new Alumno(numL,n,a,car,cantMt);
	// agregar(alu1);
	//
	//
	// }
	// fin de la simulacion

	/// ------->aca arranca lo de POST GET DELETE y PUT<-------///

	// muestra la lista de alumno por metodo GET
	@RequestMapping("/alumno")
	public LinkedList<GreetingAlumno> greeting() {
		if (alumnos == null) {
			LinkedList<GreetingAlumno> pepe = new LinkedList<GreetingAlumno>();
			GreetingAlumno error = new GreetingAlumno(counter.incrementAndGet(), "error lista vacia");
			pepe.add(error);
			return pepe;
		}
		return alumnos;
	}

	// muestra un alumno espesifico
	@RequestMapping("/")
	public GreetingAlumno greetin(@RequestParam(value = "numLeg") int numLeg) {
		int pos;
		try {
			pos = buscar(numLeg);
			if (pos >= 0) {
				GreetingAlumno alum = alumnos.get(pos);
				String name = alum.getName();
				String lastname = alum.getLastname();
				String career = alum.getCareer();
				int cantMat = alum.getCantMat();

				return new GreetingAlumno(counter.incrementAndGet(), numLeg, name, lastname, career, cantMat);

			}
			if (pos == -1) {
				throw new ExcepcionDatos("Error no se encotro el alumno");
			}
			throw new ExcepcionDatos("Error lista vacia");

		} catch (ExcepcionDatos e) {
			return new GreetingAlumno(counter.incrementAndGet(), e.getMessage());
		}

	}

	// agrega alumno en la lista con los datos pasado por parametros por metodo POST
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String greeting(@RequestParam(value = "numLeg") String numLeg, @RequestParam(value = "name") String name, @RequestParam(value = "lastname") String lastname,
			@RequestParam(value = "career") String career, @RequestParam(value = "cantMat") String cantMat) throws ExcepcionDatos {
		try {
			int numL = Integer.parseInt(numLeg);
			if (buscar(numL) >= 0) {
				return "ya existe el numero de legajo";
			} else {
				// cambio a int los parametros numLeg y cantMat
				if ((name.equals("")) || (lastname.equals("")) || career.equals("") || cantMat.equals("")) {
					throw new ExcepcionDatos("ExcepcionFaltanDatos : Es necesario completar todos los datos para crear un elemento nuevo");
				}

				int mat = Integer.parseInt(cantMat);
				GreetingAlumno al = new GreetingAlumno(counter.incrementAndGet(), numL, name, lastname, career, mat);
				agregar(al);// agrega al alumno
				return "agregado exitoso";// muestra msj q se agrego exitosamente
			}
		} catch (ExcepcionDatos e) {
			return e.getMessage();// si pasan un parametro nulo muestra respectiva exepcion
		}
	}

	// borra alumno pasando el numero de legajo medienta a metodo DELETE
	@RequestMapping(value = "/del", method = RequestMethod.DELETE)
	public String greeting(@RequestParam(value = "numleg") int numLeg) {
		try {
			int pos = buscar(numLeg);// busca el alumno segun el numero de legajo
			if (pos >= 0) {// si no lo encontro devuelve -1 el metodo
				alumnos.remove(pos);// si lo encontro lo remueve y muestra el mensaje q se borro
				return "Borrado exitoso";
			}
			if (pos == -1) {
				throw new ExcepcionDatos("Error no se encotro el alumno");
			}
			throw new ExcepcionDatos("Error lista vacia");

		} catch (ExcepcionDatos e) {
			return e.getMessage();
		}

	}

	// modifica el alumno por metodo PUT
	@RequestMapping(value = "/{numLeg}/", method = RequestMethod.PUT)
	public GreetingAlumno greeting(@PathVariable int numLeg, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "career", required = false) String career, @RequestParam(value = "cantMat", required = false) String cantMat) {
		try {
			int pos = buscar(numLeg);
			if (pos >= 0) {
				if (name == null && lastname == null && career == null && cantMat == null) {
					return new GreetingAlumno(counter.incrementAndGet(), "ERROR TODOS LOS PARAMETROS SON NULOS ");
				}
				if (name != null) {// comprueba si hay parametro spring
					alumnos.get(pos).setName(name);
					;// setea el nombre
				}
				if (lastname != null) {// comprueba si hay parametro spring
					alumnos.get(pos).setLastname(lastname);// setea el apellido
				}
				if (career != null) {// comprueba si hay parametro spring
					alumnos.get(pos).setCareer(career);// setea carrera
				}
				if (cantMat != null) {// comprueba si hay parametro spring
					alumnos.get(pos).setCareer(cantMat);// setea cantidad de materias
				}

				GreetingAlumno alum = alumnos.get(pos);

				String n = alum.getName();
				String l = alum.getLastname();
				String c = alum.getCareer();
				int cM = alum.getCantMat();

				return new GreetingAlumno(counter.incrementAndGet(), numLeg, n, l, c, cM);// muestra msj si se modifico el alumno y sus datos
			}

			if (pos == -1) {
				throw new ExcepcionDatos("Error no se encotro el alumno");
			}
			throw new ExcepcionDatos("Error lista vacia");

		} catch (Exception e) {
			return new GreetingAlumno(counter.incrementAndGet(), e.getMessage());
		}

	}

	/// ------->metodos correspondientes<-------///
	// metodo de busqueda por numero de legajo
	private int buscar(int numLeg) throws ExcepcionDatos {
		boolean encontro = false;
		int i = 0;
		if (alumnos != null) {
			int l = alumnos.size();
			while (!encontro && i < l) {
				if (numLeg == alumnos.get(i).getNumLega()) {
					encontro = true;
				} else {
					i++;
				}
			}
			if (encontro) {
				return i;
			}
			return -1;
		}
		return -2;

	}

	// metodo para agregar un alumno
	private GreetingAlumno agregar(GreetingAlumno alu) {
		if (alumnos == null) {
			alumnos = new LinkedList<GreetingAlumno>();
		}
		alumnos.add(alu);
		return alu;
	}

}
