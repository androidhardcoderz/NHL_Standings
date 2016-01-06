package com.nhlstandings.logify;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Scott on 12/25/2015.
 */
public class JSONParser {

    private final String NAME = "name";
    private final String MARKET = "market";

    LoadDivisions iLoadDivisions;

    public JSONParser(LoadDivisions loadDivisions){
        this.iLoadDivisions = loadDivisions;
    }


    public void parseJsonData(String string) throws JSONException {

        JSONObject mainObject = new JSONObject(string);
        JSONArray conferenceArray = mainObject.getJSONArray("conferences");

        for(int c = 0; c < conferenceArray.length();c++){

            JSONObject conferenceObject = conferenceArray.getJSONObject(c);
            Conference conference = new Conference();
            conference.setName(conferenceObject.getString("name"));

            JSONArray divisionArray = conferenceObject.getJSONArray("divisions");
            for(int d = 0; d < divisionArray.length();d++){

                JSONObject divisionObject = divisionArray.getJSONObject(d);
                Division division = new Division();
                division.setName(divisionObject.getString("name"));

                JSONArray teamArray = divisionObject.getJSONArray("teams");
                for(int t = 0; t < teamArray.length();t++){
                    JSONObject teamObject = teamArray.getJSONObject(t);
                    Team team = new Team();
                    team.setRank((t + 1) + "");
                    team.setName(teamObject.getString(NAME));
                    team.setMarket(teamObject.getString(MARKET));
                    team.setWins(teamObject.getString("wins"));
                    team.setLosses(teamObject.getString("losses"));
                    getTies(teamObject, team);


                    division.getTeams().add(team);
                }
                conference.getDivisions().add(division);
            }
            this.iLoadDivisions.loadConference(conference);
        }
    }

    private void getTies(JSONObject teamObject, Team team)  {
        try {
            team.setTies(teamObject.getString("ties"));

        } catch (JSONException e) {
            team.setTies("");
            e.printStackTrace();
        }
    }
}
