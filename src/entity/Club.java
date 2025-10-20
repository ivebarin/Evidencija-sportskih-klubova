package entity;

import java.math.BigDecimal;

public class Club {
    private String name;
    private String city;
    private String sport;
    private Coach coach;
    private Player[] players;

    public Club(String name, String city, String sport, Coach coach, Player[] players){
        this.name = name;
        this.city = city;
        this.sport = sport;
        this.coach = coach;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getSport() {
        return sport;
    }

    public Coach getCoach() {
        return coach;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player highestSalary(){
        Player richest = null;

        for(Player p : players){
            if(richest == null || p.getSalary().compareTo(richest.getSalary()) > 0){
                richest = p;
            }
        }

        return richest;
    }
    public Player youngestPlayer(){
        Player youngest = null;

        for(Player p : players){
            if(youngest == null || p.getAge() < youngest.getAge()){
                youngest = p;
            }
        }

        return youngest;
    }
}
