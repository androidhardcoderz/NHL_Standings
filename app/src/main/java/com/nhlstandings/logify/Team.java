package com.nhlstandings.logify;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Scott on 12/25/2015.
 */
public class Team implements Parcelable {

    private String rank;
    private String name;
    private String market;
    private String wins;
    private String losses;
    private String ties;
    private String overtime_losses;
    private String win_pct;
    private String points;

    public String getGoals_diff() {
        return goals_diff;
    }

    public void setGoals_diff(String goals_diff) {
        this.goals_diff = goals_diff;
    }

    public String getGoals_for() {
        return goals_for;
    }

    public void setGoals_for(String goals_for) {
        this.goals_for = goals_for;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOvertime_losses() {
        return overtime_losses;
    }

    public void setOvertime_losses(String overtime_losses) {
        this.overtime_losses = overtime_losses;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getPowerplays() {
        return powerplays;
    }

    public void setPowerplays(String powerplays) {
        this.powerplays = powerplays;
    }

    public String getStreak() {
        return streak;
    }

    public void setStreak(String streak) {
        this.streak = streak;
    }

    public String getTies() {
        return ties;
    }

    public void setTies(String ties) {
        this.ties = ties;
    }

    public String getWin_pct() {
        return win_pct;
    }

    public void setWin_pct(String win_pct) {
        this.win_pct = win_pct;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    private String goals_for;
    private String goals_diff;
    private String powerplays;
    private String streak;

    public Team(){

    }


    protected Team(Parcel in) {
        rank = in.readString();
        name = in.readString();
        market = in.readString();
        wins = in.readString();
        losses = in.readString();
        ties = in.readString();
        overtime_losses = in.readString();
        win_pct = in.readString();
        points = in.readString();
        goals_for = in.readString();
        goals_diff = in.readString();
        powerplays = in.readString();
        streak = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rank);
        dest.writeString(name);
        dest.writeString(market);
        dest.writeString(wins);
        dest.writeString(losses);
        dest.writeString(ties);
        dest.writeString(overtime_losses);
        dest.writeString(win_pct);
        dest.writeString(points);
        dest.writeString(goals_for);
        dest.writeString(goals_diff);
        dest.writeString(powerplays);
        dest.writeString(streak);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}