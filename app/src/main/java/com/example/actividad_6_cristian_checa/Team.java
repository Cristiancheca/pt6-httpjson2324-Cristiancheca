package com.example.actividad_6_cristian_checa;

public class Team {

    private int teamId;
    private String TxtViewTitles;
    private String teamAbbreviation;

    public Team(int teamId, String TextViewTitles, String teamAbbreviation) {
        this.teamId = teamId;
        this.TxtViewTitles = TextViewTitles;
        this.teamAbbreviation = teamAbbreviation;
    }

    public int getteamId() {
        return teamId;
    }

    public void setteamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTextViewTitles() {
        return TxtViewTitles;
    }

    public void setTextViewTitles(String TextViewTitles) {
        this.TxtViewTitles = TextViewTitles;
    }

    public String getteamAbbreviation() {
        return teamAbbreviation;
    }

    public void setteamAbbreviation(String teamAbbreviation) { this.teamAbbreviation = teamAbbreviation;}

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", TextViewTitles='" + TxtViewTitles + '\'' +
                ", teamAbbreviation='" + teamAbbreviation + '\'' +
                '}';
    }
}
