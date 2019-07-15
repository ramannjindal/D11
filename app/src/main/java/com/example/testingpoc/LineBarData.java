package com.example.testingpoc;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class LineBarData implements LineBarDataProvider {
    private ArrayList<Player> players_T1 = null;
    private ArrayList<Player> players_T2 = null;
    private HashMap<String, ArrayList<Player>> finalTeams;

    public void loadData() {
        players_T1 = new ArrayList<>(players_mt_1.length);
        for (int i = 0; i < players_mt_1.length; i++)
            players_T1.add(new Player(players_mt_1[i], category_mt_1[i], credit_points_mt_1[i]));
        players_T2 = new ArrayList<>(players_mt_2.length);
        for (int i = 0; i < players_mt_1.length; i++)
            players_T2.add(new Player(players_mt_2[i], category_mt_2[i], credit_points_mt_2[i]));
        HashMap<String, ArrayList<Player>> allCombination = new HashMap<>();// all combination of categories and players
        for (String currentCat : cats) {
            ArrayList<Player> allCurrentCatPlayers = new ArrayList<>();
            for (Player player : players_T1)
                if (currentCat.contentEquals(player.category))
                    allCurrentCatPlayers.add(player);
            for (Player player : players_T2)
                if (currentCat.contentEquals(player.category))
                    allCurrentCatPlayers.add(player);
            int combinationIndex = 0;
            for (int i = 0; i < allCurrentCatPlayers.size(); i++) {
                for (int j = i + 1; j < allCurrentCatPlayers.size(); j++) {
                    ++combinationIndex;
                    ArrayList<Player> players = new ArrayList<>();
                    players.add(allCurrentCatPlayers.get(i));
                    players.add(allCurrentCatPlayers.get(j));
                    allCombination.put(currentCat + "_" + combinationIndex, players);
                }
            }
        }
        Log.d("DONE", "DONE");

    }

    private class Player {
        String name;
        String category;
        float creditPoints;

        Player(String name, String category, float creditPoints) {
            this.name = name;
            this.category = category;
            this.creditPoints = creditPoints;
        }
    }

}