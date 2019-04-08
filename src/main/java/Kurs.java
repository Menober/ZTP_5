import java.util.ArrayList;

public class Kurs {
    String prowadzacy;
    String nazwaKursu;
    ArrayList<Student> zapisaniStudenci;
    public Kurs(String nazwa, String prowadzacy){
        this.nazwaKursu=nazwa;
        this.prowadzacy=prowadzacy;
    }
}
