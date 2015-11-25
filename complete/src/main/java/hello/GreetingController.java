package hello;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    private static String[] carreras ={"ISI","TSP","LAR","IQ","IEM"};
    
    private static LinkedList<Alumno> alumnos;
    private static Alumno agregar(Alumno alu){
    	if (alumnos==null) {
			alumnos = new LinkedList<Alumno>();
		}
    	alumnos.add(alu);
    	return alu;
    }
    //seteo la lista
    public static int generarNumero(int desde, int hasta){
    	Random rnd = new Random();
    	return rnd.nextInt(hasta-desde+1)+desde;
    }
    static{
    		//primer alumno
	    	String nombre = "Matias";
			String apellido ="Parra";
			int numAleatorio = generarNumero(0, 4);
			String carrera = carreras[numAleatorio];
			int numLega = generarNumero(1, 9999);
			int cantMaterias=generarNumero(1, 25);
			Alumno alu = new Alumno(numLega,nombre,apellido,carrera,cantMaterias);
			agregar(alu);
			
			//segundo alumno
			String n = "Matias";
			String a ="Parra";
			numAleatorio = generarNumero(0, 4);
			String car = carreras[numAleatorio];
			int numL = generarNumero(1, 9999);
			int cantMt=generarNumero(1, 25);
			Alumno alu1 = new Alumno(numL,n,a,car,cantMt);
			agregar(alu1);
		
			
    }
    
	@RequestMapping("/mostrar")
    public Greeting greeting() {
		return  new Greeting(counter.incrementAndGet(),alumnos);
    }
	
	@RequestMapping(value="/insertar", method = RequestMethod.POST)
	public String greeting(@RequestParam(value="numLeg") String numLeg ,@RequestParam(value="name") String nombre, @RequestParam(value="apellido") String apellido,
			@RequestParam(value="carrera")String carrera,@RequestParam(value="cantMat")String cantMat) {
		try{
			int numL = Integer.parseInt(numLeg);
			int mat = Integer.parseInt(cantMat);
			Alumno al = new Alumno(numL, nombre, apellido, carrera, mat);
			agregar(al);
			return "agregado exitoso";
		}catch(NullPointerException e){
			return e.getMessage();
		}
		
		
	}
}
