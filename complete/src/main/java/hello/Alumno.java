package hello;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Alumno {
	@NotNull
	private int numLega;
	
	@NotNull
	@Size(max = 60)
	private String nombre;
	
	@NotNull
	@Size(max =60)
	private String apellido;
	
	@NotNull
	private String carrera;
	
	private int cantMaterias;
	
	
	public Alumno(int numLega, String nombre, String apellido, String carrera, int cantMaterias) {
		
		this.numLega = numLega;
		this.cantMaterias = cantMaterias;
		this.nombre = nombre;
		this.apellido = apellido;
		this.carrera = carrera;
	}
	
	public int getNumLega() {
		return numLega;
	}
	public void setNumLega(int numLega) {
		this.numLega = numLega;
	}
	public int getCantMaterias() {
		return cantMaterias;
	}
	public void setCantMaterias(int cantMaterias) {
		this.cantMaterias = cantMaterias;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	@Override
	public String toString() {
		return "Numero de Legajo=" + numLega + ", apellido=" + apellido +  
				", nombre=" + nombre + ", carrera=" + carrera+", cantMaterias=" + cantMaterias ;
	}
	
	
}
