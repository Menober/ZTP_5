import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static void main(String[] args) {
        Wydzial w8 = new Wydzial();
        w8.nazwa = "WiZ";
        Wydzial w4 = new Wydzial();
        w4.nazwa = "Nie W4rto";

        ArrayList<Kurs> kursy = new ArrayList<Kurs>();
        kursy.add(new Kurs("Programowanie", "Nowak"));
        kursy.add(new Kurs("Bazy danych", "Kowalski"));
        kursy.add(new Kurs("Systemy webowe", "Kozera"));
        kursy.add(new Kurs("Systemy decyzyjne", "Janz"));
        kursy.add(new Kurs("Hurtownie danych", "Gruber"));
        kursy.add(new Kurs("Systemy rozproszone", "Kozak"));
        kursy.add(new Kurs("Systemy poproszone", "Neumann"));

        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student(1, "mezczyzna", 90));
        students.add(new Student(2, "mezczyzna", 20));
        students.add(new Student(3, "mezczyzna", 18));
        students.add(new Student(4, "kobieta", 19));
        students.add(new Student(5, "kobieta", 18));
        students.add(new Student(6, "mezczyzna", 20));
        students.add(new Student(7, "mezczyzna", 19));
        students.add(new Student(8, "mezczyzna", 20));
        students.add(new Student(9, "kobieta", 19));
        students.add(new Student(10, "kobieta", 19));

        ArrayList<Student> students2 = (ArrayList<Student>) students.clone();
        students2.remove(1);
        students2.remove(3);
        students2.remove(5);

        kursy.get(0).zapisaniStudenci = students;
        kursy.get(1).zapisaniStudenci = students;
        kursy.get(2).zapisaniStudenci = students2;

        ArrayList<Kurs> kursy2 = new ArrayList<Kurs>();
        kursy2.add(new Kurs("Elektryka", "Indygo"));
        kursy2.add(new Kurs("Sieci", "Szkarłat"));
        kursy2.add(new Kurs("Informatyka", "Biel"));

        kursy2.get(0).zapisaniStudenci = new ArrayList<>();
        kursy2.get(0).zapisaniStudenci.add(new Student(123,"mezczyzna",33));
        kursy2.get(0).zapisaniStudenci.add(new Student(321,"mezczyzna",44));
        kursy2.get(0).zapisaniStudenci.add(new Student(231,"mezczyzna",22));
        kursy2.get(0).zapisaniStudenci.add(new Student(324,"mezczyzna",33));
        kursy2.get(0).zapisaniStudenci.add(new Student(431,"mezczyzna",33));

        w8.kursy = kursy;
        w4.kursy = kursy2;
        ArrayList<Wydzial> wydzialy = new ArrayList<>();
        wydzialy.add(w8);
        wydzialy.add(w4);


        //wydzialZNajwiekszaLiczbaKursow(wydzialy);
        System.out.println();
        // wyswietlKursyIStudentowZIndeksemPodzielnymPrzezDwa(w8);

        try {

            wydzialy.forEach(Main::getKursyStudenciWydzialu);

        } catch (NullPointerException e) {

        }
    }

    private static void getKursyStudenciWydzialu(Wydzial wydzial) {
        System.out.println(wydzial.nazwa+":");
        wydzial.kursy.forEach(Main::getKursAverage);
        System.out.println();
    }

    private static void getKursAverage(Kurs kurs) {
        if (kurs.zapisaniStudenci != null && kurs.zapisaniStudenci.size() > 0) {
            System.out.println(kurs.nazwaKursu + ":");
            averageOfGendersAge(kurs.zapisaniStudenci);
            System.out.println();
        }
    }

    private static void averageOfGendersAge(ArrayList<Student> students) {
        Map<String, Double> result = students.stream().collect(
                groupingBy(Student::getPlec, Collectors.averagingInt(Student::getWiek)));

        System.out.println(result);
    }


    private static void wyswietlKursyIStudentowZIndeksemPodzielnymPrzezDwa(Wydzial w8) {
        w8.kursy.forEach(c -> System.out.println("Nazwa kursu: " + c.nazwaKursu + "\n" + lambdaStudents(c)));
    }

    private static void wydzialZNajwiekszaLiczbaKursow(ArrayList<Wydzial> wydzialy) {
        Wydzial best = wydzialy.stream().max(new Comparator<Wydzial>() {
            @Override
            public int compare(Wydzial o1, Wydzial o2) {
                return o1.kursy.size() - o2.kursy.size();
            }
        }).get();

        System.out.println("Najwiecej kursów ma: " + best.nazwa + ". Liczba kursów: " + best.kursy.size());
    }

    private static String lambdaStudents(Kurs c) {
        final String[] resultT = {""};
        try {

            ArrayList result = (ArrayList) c.zapisaniStudenci
                    .stream()
                    .filter(stud -> stud.indeks % 2 == 0)
                    .collect(Collectors.toList());              // collect the output and convert streams to a List

            result.forEach(d -> resultT[0] += ("Indeks: " + ((Student) d).indeks + " Plec: " + ((Student) d).plec + "\n"));
        } catch (NullPointerException e) {
            return "Brak studentów zapisanych na kurs \n";
        }
        return resultT[0];
    }


}
