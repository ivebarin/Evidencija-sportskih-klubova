package app;

import entity.*;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static final int NUMBER_OF_CLUBS = 5;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Dobro došli u sustav za evidenciju sportskih klubova\n");

        Club[] clubs = new Club[NUMBER_OF_CLUBS];

        for (int i = 0; i < NUMBER_OF_CLUBS; i++) {
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

            System.out.print("Godine: ");
            int coachAge = sc.nextInt();
            sc.nextLine();

            System.out.print("Godine iskustva: ");
            int experience = sc.nextInt();
            sc.nextLine();

            Coach coach = new Coach(coachFullName, experience, coachAge);

            System.out.print("\nUnesite broj igrača koji želite upisati: ");
            int playersSize = sc.nextInt();
            sc.nextLine();

            Player[] players = new Player[playersSize];

            for (int j = 0; j < playersSize; j++) {
                System.out.println("Unos podataka za " + (j + 1) + ". igrača");

                System.out.print("Puno ime: ");
                String playerFullName = sc.nextLine();

                System.out.print("Godine: ");
                int age = sc.nextInt();
                sc.nextLine();

                System.out.print("Godišnja plaća (€): ");
                BigDecimal salary = sc.nextBigDecimal();
                sc.nextLine();

                System.out.print("Pozicija: ");
                String position = sc.nextLine();

                System.out.print("Broj odigranih utakmica: ");
                int gamesPlayed = sc.nextInt();
                sc.nextLine();

                System.out.print("Broj postignutih golova: ");
                int goals = sc.nextInt();
                sc.nextLine();

                System.out.print("Broj asistencija: ");
                int assists = sc.nextInt();
                sc.nextLine();

                PlayerStats stats = new PlayerStats(gamesPlayed, goals, assists);
                players[j] = new Player.PlayerBuilder(playerFullName, age)
                        .salary(salary)
                        .position(position)
                        .stats(stats)
                        .build();
            }

            clubs[i] = new Club(name, city, sport, coach, players);
            System.out.println();
        }

        int izbor;
        do {
            System.out.println("\nODABERITE OPCIJU:");
            System.out.println("1 - Pretraga klubova po gradu");
            System.out.println("2 - Pretraga klubova po sportu");
            System.out.println("3 - Pronađi najmlađeg igrača");
            System.out.println("4 - Pronađi igrača s najvećom plaćom");
            System.out.println("5 - Ispiši sve klubove i igrače");
            System.out.println("6 - Upravljaj članovima kluba");
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
                case 5 -> printAllData(clubs);
                case 6 -> manageClubMembers(clubs, sc);
                case 0 -> System.out.println("Izlaz iz programa.");
                default -> System.out.println("Nepostojeća opcija.");
            }

        } while (izbor != 0);

        sc.close();
    }

    public static void findYoungestPlayer(Club[] clubs) {
        Player youngest = null;
        for (Club c : clubs) {
            Player clubYoungest = c.youngestPlayer();
            if (clubYoungest == null) continue;
            if (youngest == null || clubYoungest.getAge() < youngest.getAge()) youngest = clubYoungest;
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
            if (richest == null || clubRichest.getSalary().compareTo(richest.getSalary()) > 0)
                richest = clubRichest;
        }
        if (richest != null)
            System.out.println("Igrač s najvećom plaćom: " + richest.getFullName() + " (" + richest.getSalary() + " €)");
        else
            System.out.println("Nema igrača u sustavu.");
    }

    public static void searchByCity(Club[] clubs, Scanner sc) {
        System.out.print("Unesite grad: ");
        String city = sc.nextLine();
        boolean found = false;
        for (Club c : clubs) {
            if (c.getCity().equals(city)) {
                System.out.println(c.getName());
                found = true;
            }
        }
        if (!found) System.out.println("Nema klubova iz grada " + city + ".");
    }

    public static void searchBySport(Club[] clubs, Scanner sc) {
        System.out.print("Unesite sport: ");
        String sport = sc.nextLine();
        boolean found = false;
        for (Club c : clubs) {
            if (c.getSport().equals(sport)) {
                System.out.println(c.getName());
                found = true;
            }
        }
        if (!found) System.out.println("Nema klubova koji se bave sportom " + sport + ".");
    }

    public static void printAllData(Club[] clubs) {
        for (Club c : clubs) {
            System.out.println("\nKlub: " + c.getName());
            System.out.println("Grad: " + c.getCity());
            System.out.println("Sport: " + c.getSport());
            System.out.println("\nTrener: " + c.getCoach().getFullName() + " (" + c.getCoach().getExperience() + " god. iskustva)");
            System.out.println("\nIgrači:");
            for (Player p : c.getPlayers()) {
                System.out.println("- " + p.getFullName() + " (" + p.getPosition() + ", " + p.getSalary() + " €)");
            }
        }
    }

    public static void manageClubMembers(Club[] clubs, Scanner sc) {
        System.out.println("Odaberite klub: ");
        for(int i = 0; i < clubs.length; i++){
            System.out.println((i + 1) + " - " + clubs[i].getName());
        }
        System.out.println("Vaš odabir: ");
        int clubChoice = sc.nextInt() - 1;

        Club chosenClub = clubs[clubChoice];

        Player[] clubPlayers = chosenClub.getPlayers();
        ClubMember[] members = new ClubMember[clubPlayers.length + 1];

        members[0] = chosenClub.getCoach();
        for(int i = 0; i < clubPlayers.length; i++){
            members[i + 1] = clubPlayers[i];
        }

        System.out.println("\nČlanovi kluba " + chosenClub.getName() + ":");
        for(int i = 0; i < members.length; i++){
            System.out.println((i + 1) + " - " + members[i].getFullName() +
                    " (" + ((Role) members[i]).getRoleName() + ")");
        }

        System.out.println("Odaberite člana: ");
        int memberChoice = sc.nextInt() - 1;
        sc.nextLine();

        ClubMember member = members[memberChoice];

        System.out.println();

        if(member instanceof Player player){
            System.out.println("1 - Odigraj utakmicu");
            System.out.println("2 - Ispiši profil");
            System.out.print("Odaberite opciju: ");
            int option = sc.nextInt();
            sc.nextLine();

            if (option == 1) player.playGame();
            else if (option == 2) member.showProfile(); //tu koristimo member za primjer polimorfizma

        } else if (member instanceof Coach coach) {
            System.out.println("1 - Ispiši profil");
            System.out.print("Odaberite opciju: ");
            int option = sc.nextInt();
            sc.nextLine();

            if (option == 1) member.showProfile(); //ovdi isto koristimo member da dokazemo polimorfizam.
        }
    }
}

