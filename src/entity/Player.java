package entity;

import java.math.BigDecimal;

public final class Player extends ClubMember implements Playable, Role {
    private final BigDecimal salary;
    private final String position;
    private final PlayerStats stats;

    private Player(PlayerBuilder builder) {
        super(builder.fullName, builder.age);
        this.salary = builder.salary;
        this.position = builder.position;
        this.stats = builder.stats;
    }

    public BigDecimal getSalary() { return salary; }
    public String getPosition() { return position; }
    public PlayerStats getStats() { return stats; }

    @Override
    public void playGame() {
        System.out.println(fullName + " je odigrao odličnu utakmicu.");
    }

    @Override
    public void showProfile() {
        System.out.println("PROFIL IGRAČA\n");
        System.out.println("Ime: " + fullName);
        System.out.println("Godine: " + age);
        System.out.println("Pozicija: " + position);
        System.out.println("Plaća: " + (salary != null ? "€" + salary : "N/A"));
        if (stats != null) {
            System.out.println("Utakmice: " + stats.gamesPlayed());
            System.out.println("Golovi: " + stats.goals());
            System.out.println("Asistencije: " + stats.assists());
        }
    }

    @Override
    public String getRoleName() { return "Igrač"; }


    public static class PlayerBuilder {
        private final String fullName;
        private final int age;

        private BigDecimal salary = BigDecimal.ZERO;
        private String position = "";
        private PlayerStats stats = null;

        public PlayerBuilder(String fullName, int age) {
            this.fullName = fullName;
            this.age = age;
        }

        public PlayerBuilder salary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public PlayerBuilder position(String position) {
            this.position = position;
            return this;
        }

        public PlayerBuilder stats(PlayerStats stats) {
            this.stats = stats;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}
