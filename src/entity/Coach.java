package entity;

public final class Coach extends ClubMember implements Role{
    private final int experience;


    public Coach(String fullName, int experience, int age) {
        super(fullName, age);
        this.experience = experience;
    }

    public int getExperience(){
        return experience;
    }
    public void showProfile(){
        System.out.println("PROFIL TRENERA\n");
        System.out.println("Igrač: " + fullName);
        System.out.println("Godine: " + age);
        System.out.println("Iskustvo: " + experience);
    }
    @Override
    public String getRoleName(){
        return "Trener";
    }

}