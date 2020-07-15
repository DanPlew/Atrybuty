import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student implements Serializable {

    // Atrybuty podstawowe.
    String imie, nazwisko;

    // Ekstansja.
    static List<Student> ekstansja = new ArrayList<>();

    // Atrybut klasowy.
    static int maxId = 0;
    static String path = "C:\\Users\\Daniel\\IdeaProjects\\MASY\\MiniProjekt1\\ekstansja.txt";

    public static void setPath(String path){
        Student.path = path;
    }

    // Atrybut pochodny.
    int id;

    public Student(String imie, String nazwisko, Adres adresZameldowania, Data dataUrodzenia){
        maxId++;
        id = maxId;

        this.dataUrodzenia = dataUrodzenia;
        this.adresZameldowania = adresZameldowania;
        this.imie = imie;
        this.nazwisko = nazwisko;

        setAdresZamieszkania(adresZameldowania);

        oceny.put(Przedmiot.MATEMATYKA, new ArrayList<>());
        oceny.put(Przedmiot.PROGRAMOWANIE, new ArrayList<>());

        ekstansja.add(this);
    }

    // Atrybut złożony, Atrybut opcjonalny
    Adres adresZameldowania, adresZamieszkania;
    Data dataUrodzenia;
    public void setAdresZamieszkania(Adres adres){
        this.adresZamieszkania = adres;
    }

    // Atrybut powtarzalny.
    Map<Przedmiot, ArrayList<Integer>> oceny = new HashMap<>();
    public void addOcena(Przedmiot przedmiot, int ocena){
        oceny.get(przedmiot).add(ocena);
    }

    // Ekstansja trwałość.
    public static void saveEkstans(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Student.path))){
            out.writeInt(ekstansja.size());
            for(Student student : ekstansja) out.writeObject(student);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Metoda klasowa.
    public static void loadEkstans(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(Student.path))){
            int ekstansjaSize = in.readInt();
            for(int i=0;i<ekstansjaSize;i++) ekstansja.add((Student)in.readObject());

            maxId = ekstansja.size();
        }catch (EOFException e){
            System.out.println("Ekstansja z pliku jest pusta!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteStudent(Student student){
        if(student.id != Student.maxId) for(int i=student.id;i<Student.ekstansja.size();i++) Student.ekstansja.get(i).id--;
        ekstansja.remove(student);
    }

    @Override
    public String toString() {
        return  "Id: " + id + ", " + "\n"
                + "Imie: " + imie + ", " + "\n"
                + "Nazwisko: " + nazwisko + ", " + "\n"
                + "Data urodzenia: " + dataUrodzenia + ", " + "\n"
                + "Adres zameldowania: " + adresZameldowania + ", " + "\n"
                + "Adres zamieszkania: " + adresZamieszkania + ", " + "\n"
                + "Oceny: " + oceny + ", " + "\n" + "\n";

    }
}

class Adres implements Serializable{

    // Atrybut opcjonalny.
    String ulica, miejscowosc, dzielnica, kodPocztowy;
    int numerDomu;


    // Przeciążenie konstruktora o opcjonalny parametr.
    public Adres(String ulica, String miejscowosc, String kodPocztowy, int numerDomu){
        this.ulica = ulica;
        this.miejscowosc = miejscowosc;
        this.kodPocztowy = kodPocztowy;
        this.numerDomu = numerDomu;
    }

    public Adres(String ulica, String miejscowosc, String kodPocztowy, int numerDomu, String dzielnica){
        this.ulica = ulica;
        this.miejscowosc = miejscowosc;
        this.dzielnica = dzielnica;
        this.kodPocztowy = kodPocztowy;
        this.numerDomu = numerDomu;
    }

    // Przesłonięcie.
    @Override
    public String toString() {
        return "Ulica " + ulica + "/" + numerDomu + " " + miejscowosc + " " + kodPocztowy;
    }
}

class Data implements Serializable{
    int dzien, miesiac, rok;

    // Atrybut wyliczalny i opcjonalny.
    int wiek;

    public Data(int dzien, int miesiac, int rok){
        this.dzien = dzien;
        this.miesiac = miesiac;
        this.rok = rok;

        this.wiek = Year.now().getValue() - rok;
    }

    @Override
    public String toString() {
        return dzien + "." + miesiac + "." + rok + "r. Wiek " + wiek +" lat";
    }
}