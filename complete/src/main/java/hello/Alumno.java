package hello;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Alumno {
	@NotNull
	private int numLega;
	
	@NotNull
	@Size(max = 60)
	private String name;
	
	@NotNull
	@Size(max =60)
	private String lastname;
	
	@NotNull
	private String career;
	
	private int cantMat;
	
	
	public Alumno(int numLega, String nombre, String apellido, String carrera, int cantMaterias) {
		
		this.numLega = numLega;
		this.cantMat = cantMaterias;
		this.name = nombre;
		this.lastname = apellido;
		this.career = carrera;
	}


	public int getNumLega() {
		return numLega;
	}


	public void setNumLega(int numLega) {
		this.numLega = numLega;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getCareer() {
		return career;
	}


	public void setCareer(String career) {
		this.career = career;
	}


	public int getCantMat() {
		return cantMat;
	}


	public void setCantMat(int cantMat) {
		this.cantMat = cantMat;
	}


	@Override
	public String toString() {
		return "numLega=" + numLega + ", name=" + name + ", lastname=" + lastname + ", career=" + career + ", cantMat=" + cantMat;
	}
	
	
	
	
}
