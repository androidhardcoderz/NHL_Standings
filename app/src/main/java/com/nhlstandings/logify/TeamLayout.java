package com.nhlstandings.logify;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.widget.TextView;


/**
 * Created by Scott on 12/25/2015.
 */
public class TeamLayout extends LinearLayoutCompat {

    public TeamLayout(Context context, Team team) {
        super(context);

        LayoutInflater.from(getContext()).inflate(R.layout.team_row, this, true);

        TextView teamName = (TextView) this.findViewById(R.id.teamNameTextView);
        TextView teamRank = (TextView) this.findViewById(R.id.rankTextView);
        TextView teamRecord = (TextView) this.findViewById(R.id.teamRecordTextView);

        teamRank.setText(team.getRank());
        teamName.setText(team.getMarket() + " " + team.getName());
        teamRecord.setText((team.getWins() + "-" + team.getLosses()));

        if(!team.getTies().equals("")){
            teamRecord.setText((team.getWins() + "-" + team.getLosses() + "-" + team.getTies()));
        }
    }
}
