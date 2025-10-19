package entity;

import java.math.BigDecimal;

public class Player {
    private String fullName;
    private String nationality;
    private BigDecimal salary;
    private int age;

    public Player(String fullName, String nationality, BigDecimal salary, int age) {
        this.fullName = fullName;
        this.nationality = nationality;
        this.salary = salary;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNationality() {
        return nationality;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }
}
