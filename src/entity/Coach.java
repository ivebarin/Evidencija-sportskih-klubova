package entity;

public class Coach {
    private String fullName;
    private int experience;
    private String nationality;

    public Coach(String fullName, int experience, String nationality) {
        this.fullName = fullName;
        this.experience = experience;
        this.nationality = nationality;
    }

    public String getFullName(){
        return fullName;
    }
    public int getExperience(){
        return experience;
    }
    public String getNationality(){
        return nationality;
    }
}
