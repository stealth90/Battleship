package com.shipbattle;

import java.util.Scanner;

public class Player {
    private int numRows = 3;
    private int numCols = 3;
    private int ships=1;
    private String name;
    private String[][] grid= new String[][]{{" "," "," "},{" "," "," "},{" "," "," "}};
    private String[][] enemyGrid= new String[][]{{" "," "," "},{" "," "," "},{" "," "," "}};;


    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public Player(Scanner name) {
        this.name = " "+name.nextLine();
    }

    public int getShips() {
        return ships;
    }

    public void setShips(String operation) {
        if(operation.equals("+")) this.ships++;
        else this.ships--;
    }

    public  String[][] getGrid() {
        return this.grid;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

    public  String[][] getEnemyGrid() {
        return enemyGrid;
    }

    public  void setEnemyGrid(String[][] missedGuesses) {
        this.enemyGrid = missedGuesses;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
