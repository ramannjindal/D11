package com.example.testingpoc;

public interface LineBarDataProvider {
    String[] cats = {"WK", "BAT", "AR", "BOWL"};
    int[] category_min = {1, 3, 1, 3};
    int[] category_max = {4, 6, 4, 6};
    int totalPoints = 100;
    int minPlayerEachTeam = 4;
    //    String[]players_1={"I Kishan","M Panday","R Gaikwad","S Iyer","S Gill","A Singh","H Vihari","Axar-Patel","K Pandya","D Chahar","R Chahar","K Ahmed","N Saini","W Sundar","A Khan"};
    //    String[] players_2 = {"D Thomas","S Dowrich","S Ambris","J Campbell","J Carter","Kjorn Ottley","S Rutherford","R Cornwall","R Powell","R Chase","R Reifer","K Paul","K Pierre","R Shepherd","W Sundar","Akeem Jord"};
    String[] players_mt_1 = {"I Kishan", "M Panday", "R Gaikwad", "S Iyer", "S Gill", "A Singh", "H Vihari", "Axar-Patel", "K Pandya", "D Chahar", "R Chahar"};
    String[] players_mt_2 = {"D Thomas", "S Dowrich", "S Ambris", "J Campbell", "J Carter", "Kjorn Ottley", "S Rutherford", "R Cornwall", "R Powell", "R Chase", "R Reifer"};
    String[] category_mt_1 = {cats[0], cats[0], cats[1], cats[1], cats[1], cats[2], cats[2], cats[2], cats[3], cats[3], cats[3]};
    String[] category_mt_2 = {cats[0], cats[1], cats[1], cats[1], cats[2], cats[2], cats[2], cats[3], cats[3], cats[3], cats[3]};
    float[] credit_points_mt_1 = {9.0f, 10.0f, 9.5f, 9.5f, 9.0f, 8.5f, 8.5f, 9.0f, 9.0f, 9.0f, 8.5f};
    float[] credit_points_mt_2 = {8.5f, 8.0f, 9.5f, 9.0f, 8.5f, 8.5f, 8.5f, 9.0f, 9.0f, 9.0f, 8.5f};
}
