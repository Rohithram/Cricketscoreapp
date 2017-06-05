package io.rohithram.cricapp;

/**
 * Created by rohithram on 5/6/17.
 */

public class Matchscores {
    private String innings_requirement;
    private String score;
    private Boolean matchStarted;

    public Matchscores(String innings_requirement, String scores, Boolean matchStarted) {
        this.innings_requirement = innings_requirement;
        this.score = scores;
        this.matchStarted = matchStarted;
    }

    public String getStatus() {
        return innings_requirement;
    }

    public void setStatus(String status) {
        innings_requirement = status;
    }

    public String getScores() {
        return score;
    }

    public void setScores(String scores) {
        this.score = scores;
    }


    public Boolean getMatchStarted() {
        return matchStarted;
    }

    public void setMatchStarted(Boolean matchStarted) {
        this.matchStarted = matchStarted;
    }
}
