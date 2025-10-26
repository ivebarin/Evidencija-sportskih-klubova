package entity;

public abstract class ClubMember{
    protected String fullName;
    protected int age;

    public ClubMember(String fullName, int age){
        this.fullName = fullName;
        this.age = age;}
    public String getFullName() {
        return fullName;
    }
    public int getAge() {
        return age;
    }
    public abstract void showProfile();
}
