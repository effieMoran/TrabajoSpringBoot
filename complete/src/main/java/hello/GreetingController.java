package hello;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.net.SyslogAppender;


@RestController
public class GreetingController {

    
    private final AtomicLong counter = new AtomicLong();
    
    
    ///------->simulacion de bases de datos<-------///
    private static String[] carreras ={"ISI","TSP","LAR","IQ","IEM"};
    
    private static LinkedList<Alumno> alumnos;
    
    //seteo la lista
    //metodo para generar un numero random
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
    //fin de la simulacion
    
    
  ///------->aca arranca lo de POST GET DELETE y PUT<-------///
    
    //muestra la lista de alumno por metodo GET
	@RequestMapping("/alumno")
    public Greeting greeting() {
		return  new Greeting(counter.incrementAndGet(),alumnos);
    }
	
	//agrega alumno en la lista con los datos pasado por parametros por metodo POST 
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String greeting(@RequestParam(value="numLeg") String numLeg ,@RequestParam(value="name") String nombre, @RequestParam(value="apellido") String apellido,
			@RequestParam(value="carrera")String carrera,@RequestParam(value="cantMat")String cantMat) {
		try{
			//cambio a int los parametros numLeg y cantMat
			int numL = Integer.parseInt(numLeg);
			int mat = Integer.parseInt(cantMat);
			Alumno al = new Alumno(numL, nombre, apellido, carrera, mat);
			agregar(al);//agrega al alumno
			return "agregado exitoso";//muestra msj q se agrego exitosamente
		}catch(NullPointerException e){
			return e.getMessage();//si pasan un parametro nulo muestra respectiva exepcion
		}	
	}
	
	//borra alumno pasando el numero de legajo medienta a metodo DELETE
	@RequestMapping(value="/del" , method = RequestMethod.DELETE)
	public String greeting(@RequestParam(value="numleg") int numLeg){
		int pos=buscar(numLeg);//busca el alumno segun el numero de legajo
		if(pos==-1){//si no lo encontro devuelve -1 el metodo
			return "Error no se encontro alumno";
		}
		alumnos.remove(pos);//si lo encontro lo remueve y muestra el mensaje q se borro
		return "Borrado exitoso";
	}
	
	///------->metodos correspondientes<-------///
	//metodo de busqueda por numero de legajo
	private static int buscar(int numLeg) {
		boolean encontro = false;
		int i=0;
		int l=alumnos.size();
		while(!encontro&&i<l){
			if(numLeg == alumnos.get(i).getNumLega()){
				encontro=true;
			}else{
				i++;
			}
		}
		if (encontro) {
			return i;
		}
		return -1;
		
	}
	//metodo para agregar un alumno
    private static Alumno agregar(Alumno alu){
    	if (alumnos==null) {
			alumnos = new LinkedList<Alumno>();
		}
    	alumnos.add(alu);
    	return alu;
    }
	
}
