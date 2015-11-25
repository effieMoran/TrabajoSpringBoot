package hello;

import java.util.LinkedList;

public class Greeting {

    private final long id;
    private LinkedList<Alumno> alu;

    public Greeting(long id, LinkedList<Alumno> alu) {
        this.id = id;
        this.alu = alu;
    }

//    public long getId() {
//        return id;
//    }

    public LinkedList<Alumno> getAlumno() {
        return alu;
    }
    
}
