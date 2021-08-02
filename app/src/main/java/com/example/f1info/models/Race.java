package com.example.f1info.models;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Race {

    private String raceName;
    private String raceNickName;
    private ZonedDateTime raceDateTime;
    private String season;
    private String round;
    private ArrayList<Session> sessionList;

    public Race(String raceName, String season, String round, String date, String time,String extras){
        this.raceName=raceName;
        raceNickName=raceName;
        if(raceName.contains("Grand Prix")){
            raceNickName=raceName.substring(0,raceName.indexOf("Grand Prix"))+"GP";
        }
        this.season=season;
        this.round=round;
        StringBuilder raceDateTime =new StringBuilder();
        raceDateTime.append(date);
        raceDateTime.append("T");
        raceDateTime.append(time);
        this.raceDateTime= ZonedDateTime.parse(raceDateTime);
        this.raceDateTime=this.raceDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        this.generateSessions();

    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public ZonedDateTime getRaceDateTime() {
        return raceDateTime;
    }

    public void setRaceDateTime(ZonedDateTime raceDateTime) {
        this.raceDateTime = raceDateTime;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public ArrayList<Session> getSessions() {
        return sessionList;
    }


    public String getRaceNickName() {
        return raceNickName;
    }

    public void setRaceNickName(String raceNickName) {
        this.raceNickName = raceNickName;
    }

    @Override
    public String toString() {
        return "Race{" +
                "raceName='" + raceName + '\'' +
                ", raceDateTime=" + raceDateTime +
                ", season=" + season +
                ", round=" + round +
                '}';
    }

    private void generateSessions(){
        sessionList=new ArrayList<Session>();
        if(raceNickName.toLowerCase().contains("monaco")){
            sessionList.add(new Session("Practice 1",raceDateTime.minusDays(3).minusMinutes(210)));
            sessionList.add(new Session("Practice 2",raceDateTime.minusDays(3)));
        }else {
            sessionList.add(new Session("Practice 1", raceDateTime.minusDays(2).minusMinutes(210)));
            sessionList.add(new Session("Practice 2", raceDateTime.minusDays(2)));
        }
        sessionList.add(new Session("Practice 3",raceDateTime.minusDays(1).minusHours(3)));
        sessionList.add(new Session("Qualifying",raceDateTime.minusDays(1)));
        sessionList.add(new Session("Race",raceDateTime));
    }
}
