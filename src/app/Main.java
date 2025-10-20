package app;

import entity.Club;
import entity.Coach;
import entity.Player;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static final int NUMBER_OF_CLUBS = 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Dobro došli u sustav za evidenciju sportskih klubova");
        System.out.println("Molimo Vas da unesete podatke o klubovima\n");

        Club[] clubs = new Club[NUMBER_OF_CLUBS];

        for (int i = 0; i < NUMBER_OF_CLUBS; ++i) {
            System.out.println("Unos podataka za " + (i + 1) + ". klub");

            System.out.print("Naziv kluba: ");
            String name = sc.nextLine();

            System.out.print("Grad: ");
            String city = sc.nextLine();

            System.out.print("Sport: ");
            String sport = sc.nextLine();

            System.out.println("\nUnesite podatke o treneru");

            System.out.print("Puno ime: ");
            String coachFullName = sc.nextLine();

            System.out.print("Nacionalnost: ");
            String coachNationality = sc.nextLine();

            System.out.print("Godine iskustva: ");
            int experience = sc.nextInt();
            sc.nextLine();

            Coach coach = new Coach(coachFullName, experience, coachNationality);

            System.out.print("\nUnesite broj igrača koji želite upisati u sustav: ");
            int playersSize = sc.nextInt();
            sc.nextLine();

            Player[] players = new Player[playersSize];

            for (int j = 0; j < playersSize; j++) {
                System.out.println("Unos podataka za " + (j + 1) + ". igrača");

                System.out.print("Puno ime: ");
                String playerFullName = sc.nextLine();

                System.out.print("Nacionalnost: ");
                String playerNationality = sc.nextLine();

                System.out.print("Godine: ");
                int age = sc.nextInt();

                System.out.print("Godišnja plaća (€): ");
                BigDecimal salary = sc.nextBigDecimal();
                sc.nextLine();

                players[j] = new Player(playerFullName, playerNationality, salary, age);
            }

            clubs[i] = new Club(name, city, sport, coach, players);
            System.out.println();
        }

        System.out.println("Svi podaci su uspješno uneseni\n");

        int izbor;
        do {
            System.out.println("ODABERITE OPCIJU: ");
            System.out.println("1 - Pretraga klubova po gradu");
            System.out.println("2 - Pretraga klubova po sportu");
            System.out.println("3 - Pronađi najmlađeg igrača");
            System.out.println("4 - Pronađi igrača s najvećom plaćom");
            System.out.println("0 - Izlaz");
            System.out.print("Odaberite opciju: ");

            izbor = sc.nextInt();
            sc.nextLine();

            System.out.println();

            switch (izbor) {
                case 1 -> searchByCity(clubs, sc);
                case 2 -> searchBySport(clubs, sc);
                case 3 -> findYoungestPlayer(clubs);
                case 4 -> findHighestSalary(clubs);
                case 0 -> System.out.println("Izlaz iz programa");
                default -> System.out.println("Nepostojeća opcija. Pokušajte ponovno.");
            }

            System.out.println();
        } while (izbor != 0);
        sc.close();
    }

    public static void findYoungestPlayer(Club[] clubs) {
        Player youngest = null;

        for (Club c : clubs) {

            Player clubYoungest = c.youngestPlayer();
            if (clubYoungest == null) continue;

            if (youngest == null || clubYoungest.getAge() < youngest.getAge()) {
                youngest = clubYoungest;
            }
        }

        if (youngest != null)
            System.out.println("Najmlađi igrač: " + youngest.getFullName() + " (" + youngest.getAge() + " godina)");
        else
            System.out.println("Nema igrača u sustavu.");
    }

    public static void findHighestSalary(Club[] clubs) {
        Player richest = null;

        for (Club c : clubs) {

            Player clubRichest = c.highestSalary();
            if (clubRichest == null) continue;

            if (richest == null || clubRichest.getSalary().compareTo(richest.getSalary()) > 0) {
                richest = clubRichest;
            }
        }

        if (richest != null)
            System.out.println("Igrač s najvećom plaćom: " + richest.getFullName() +
                    " (" + richest.getSalary() + " €)");
        else
            System.out.println("Nema igrača u sustavu.");
    }

    public static void searchByCity(Club[] clubs, Scanner sc) {
        System.out.print("Unesite naziv grada: ");
        String city = sc.nextLine();
        boolean found = false;

        for (Club c : clubs) {
            if (c.getCity().equals(city)) {
                System.out.println(c.getName());
                found = true;
            }
        }

        if (!found)
            System.out.println("Nema klubova iz grada " + city + ".");
    }

    public static void searchBySport(Club[] clubs, Scanner sc) {
        System.out.print("Unesite naziv sporta: ");
        String sport = sc.nextLine();
        boolean found = false;

        for (Club c : clubs) {
            if (c.getSport().equals(sport)) {
                System.out.println(c.getName());
                found = true;
            }
        }

        if (!found)
            System.out.println("Nema klubova koji se bave sportom " + sport + ".");
    }


}
