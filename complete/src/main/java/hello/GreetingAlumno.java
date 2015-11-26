package hello;

import java.util.LinkedList;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GreetingAlumno {
	 	private final long id;
//	    private Alumno alu;
//	    private final String coment;
		private int numLega;
		private String name;
		private String lastname;
		private String career;
		private int cantMat;
		private String coment;
		
		
		
		public GreetingAlumno(long id, String coment) {
			super();
			this.id = id;
			this.coment = coment;
		}
		
		public GreetingAlumno(long id, int numLega, String name, String lastname, String career, int cantMat) {
			this.id = id;
			this.numLega = numLega;
			this.name = name;
			this.lastname = lastname;
			this.career = career;
			this.cantMat = cantMat;
			this.coment="200 OK";
		}

		public long getId() {
			return id;
		}

		public int getNumLega() {
			return numLega;
		}

		public String getName() {
			return name;
		}

		public String getLastname() {
			return lastname;
		}

		public String getCareer() {
			return career;
		}

		public int getCantMat() {
			return cantMat;
		}
		
		public String getComent() {
			return coment;
		}

		public void setNumLega(int numLega) {
			this.numLega = numLega;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

		public void setCareer(String career) {
			this.career = career;
		}

		public void setCantMat(int cantMat) {
			this.cantMat = cantMat;
		}

		public void setComent(String coment) {
			this.coment = coment;
		}

		
		
		
		
	   
}
