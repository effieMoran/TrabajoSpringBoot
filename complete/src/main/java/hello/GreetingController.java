package hello;

import java.time.format.FormatStyle;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.support.StringMultipartFileEditor;

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
	//muestra un alumno espesifico
	@RequestMapping("/")
	public String Greeting(@RequestParam(value="numLeg") int numLeg){
		int pos = buscar(numLeg);
		if (pos == -1) {
			return "ERROR alumno no existe en la base de datos";
		}
		return "Alumno encontrado: "+alumnos.get(pos);
	}
	
	//agrega alumno en la lista con los datos pasado por parametros por metodo POST 
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String greeting(@RequestParam(value="numLeg") String numLeg ,@RequestParam(value="name") String name, @RequestParam(value="lastname") String lastname,
			@RequestParam(value="career")String career,@RequestParam(value="cantMat")String cantMat) {
		try{
			//cambio a int los parametros numLeg y cantMat
			int numL = Integer.parseInt(numLeg);
			int mat = Integer.parseInt(cantMat);
			Alumno al = new Alumno(numL, name, lastname, career, mat);
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
	
	//modifica el alumno por metodo PUT
	@RequestMapping(value="/{numLeg}/", method = RequestMethod.PUT)
	public String greeting(@PathVariable int numLeg,@RequestParam(value="name",required=false) String name, @RequestParam(value="lastname",required=false) String lastname,
			@RequestParam(value="career",required=false)String career,@RequestParam(value="cantMat",required=false)String cantMat){
		int pos = buscar(numLeg);
		if(pos!=-1){
			if(name != null){//comprueba si hay parametro spring
				alumnos.get(pos).setName(name);//setea el nombre
			}
			if(lastname != null){//comprueba si hay parametro spring
				alumnos.get(pos).setLastname(lastname);//setea el apellido
			}
			if(career != null){//comprueba si hay parametro spring
				alumnos.get(pos).setCareer(career);//setea carrera
			}
			if(cantMat != null){//comprueba si hay parametro spring
				alumnos.get(pos).setCareer(cantMat);//setea cantidad de materias
			}
			return "modificacion exitosa del "+alumnos.get(pos);//muestra msj si se modifico el alumno y sus datos
		}
		return "ERROR almenos tiene que pasar una variable no nula";
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
