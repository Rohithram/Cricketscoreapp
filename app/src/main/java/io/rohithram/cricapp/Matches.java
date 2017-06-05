package io.rohithram.cricapp;


/**
 * Created by rohithram on 4/6/17.
 */

public class Matches {
        private double matchid;
        private String team1;
        private String team2;
    public Matches(double matchid, String team1 ,String team2){
        this.matchid = matchid;
        this.team1 = team1;
        this.team2 = team2;
    }

    public double getMatchid() {
        return matchid;
    }

    public void setMatchid(double matchid) {
        this.matchid = matchid;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }


}
