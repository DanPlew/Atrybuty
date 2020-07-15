/**
 * Moim celem biznesowm jest przedstawienie studentów jakiejś uczelni w sposób klarowny i łatwy do zapisywania oraz odczytywania.
 * Są to informację osobiste studenta uczęszczającego na poszczególną uczelnie.
 */

public class Main {
    public static void main(String[] arg){
        Student.loadEkstans();

        Student student1 = new Student("Daniel", "Plewiński",
                new Adres("Sokołowska 9", "Warszawa", "01-142", 296),
                new Data(29,4,1997));
        student1.oceny.get(Przedmiot.PROGRAMOWANIE).add(5);
        student1.oceny.get(Przedmiot.PROGRAMOWANIE).add(5);
        student1.oceny.get(Przedmiot.MATEMATYKA).add(5);

        Student student2 = new Student("Hubert123", "Daniel",
                new Adres("Mierzejewskiego 10", "Warszawa", "01-142", 14),
                new Data(5,9,1998));

        Student student3 = new Student("Szymon123", "Obłucki",
                new Adres("Niewiadomska 15", "Warszawa", "01-142", 10),
                new Data(14,5,1998));
        student3.oceny.get(Przedmiot.PROGRAMOWANIE).add(4);
        student3.oceny.get(Przedmiot.PROGRAMOWANIE).add(4);
        student3.oceny.get(Przedmiot.MATEMATYKA).add(4);

        Student.deleteStudent(student2);

        Student.ekstansja.forEach(student -> System.out.print(student));
        Student.saveEkstans();
    }
}