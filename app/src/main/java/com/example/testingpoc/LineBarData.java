package com.example.testingpoc;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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

        for (int i1 = 0; i1 < cats.length; i1++) {
            String currentCat = cats[i1];
            ArrayList<Player> allCurrentCatPlayers = new ArrayList<>();
            for (Player player : players_T1)
                if (currentCat.contentEquals(player.category))
                    allCurrentCatPlayers.add(player);
            for (Player player : players_T2)
                if (currentCat.contentEquals(player.category))
                    allCurrentCatPlayers.add(player);
            for (int i = category_min[i1]; i <= Math.min(category_max[i1], allCurrentCatPlayers.size()); i++) {
                try {
                    printCombination(allCombination, allCurrentCatPlayers, allCurrentCatPlayers.size(), i, currentCat);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            for (int i = 0; i < allCurrentCatPlayers.size(); i++) {
//                for (int j = i + 1; j < allCurrentCatPlayers.size(); j++) {
//                    ++combinationIndex;
//                    ArrayList<Player> players = new ArrayList<>();
//                    players.add(allCurrentCatPlayers.get(i));
//                    players.add(allCurrentCatPlayers.get(j));
//                    allCombination.put(currentCat + "_" + combinationIndex, players);
//                }
//            }
        }
        Log.d("DONE", "DONE");

    }

    private void combinationUtil(HashMap<String, ArrayList<Player>> allCombination, ArrayList<Player> players, ArrayList<Player> tmpPlayers, int start,
                                 int end, int index, int r, String currentCat) {
        if (index == r) {
            String currentKey = currentCat + "_" + r + "_" + new Random().nextInt(9999999);
            for (int j = 0; j < r; j++) {
                ArrayList<Player> players1 = allCombination.get(currentKey);
                if (players1 == null)
                    players1 = new ArrayList<>();
                players1.add(tmpPlayers.get(j));
                allCombination.put(currentKey, players1);
            }
            return;
        }
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            tmpPlayers.add(index, players.get(i));
            combinationUtil(allCombination, players, tmpPlayers, i + 1, end, index + 1, r, currentCat);
        }
    }

    private void printCombination(HashMap<String, ArrayList<Player>> allCombination, ArrayList<Player> players, int n, int r, String currentCat) {
        ArrayList<Player> tmpPlayers = new ArrayList<>(players.size());
        combinationUtil(allCombination, players, tmpPlayers, 0, n - 1, 0, r, currentCat);
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