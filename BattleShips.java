package com.shipbattle;

import java.util.*;

public class BattleShips {
    private String[][] placement= new String[3][3];

    public void createBoards(Player player){
        //First section of Ocean Maps
        System.out.println(" MY BOARD" + player);
        System.out.print(" "+ " ");
        for(int i = 0; i < player.getGrid().length; i++)
            System.out.print(i + " ");
        System.out.println();


        //Middle section of Ocean Map
        for(int i = 0; i < player.getGrid().length; i++) {
            for (int j = 0; j < player.getGrid()[i].length; j++) {
                if (j == 0)
                    System.out.print(i + "|" + player.getGrid()[i][j]);
                else if (j == player.getGrid()[i].length - 1)
                    System.out.print("|"+ player.getGrid()[i][j] +"|"+i);
                else
                    System.out.print("|" +player.getGrid()[i][j]);

            }
            System.out.println();
        }

        //Last section of Ocean Map
        System.out.print(" "+ " ");
        for(int i = 0; i < player.getGrid().length; i++)
            System.out.print(i + " ");
        System.out.println();

    //First section of Enemy Maps
        System.out.println(" MY ENEMY BOARD" + player);
        System.out.print(" "+ " ");
        for(int i = 0; i < player.getGrid().length; i++)
            System.out.print(i + " ");
        System.out.println();


    //Middle section of Ocean Map
        for(int i = 0; i < player.getEnemyGrid().length; i++) {
        for (int j = 0; j < player.getEnemyGrid()[i].length; j++) {
            if (j == 0)
                System.out.print(i + "|" + player.getEnemyGrid()[i][j]);
            else if (j == player.getEnemyGrid()[i].length - 1)
                System.out.print("|"+ player.getEnemyGrid()[i][j] +"|"+i);
            else
                System.out.print("|" +player.getEnemyGrid()[i][j]);

        }
        System.out.println();
    }

    //Last section of Ocean Map
        System.out.print(" "+ " ");
        for(int i = 0; i < player.getGrid().length; i++)
            System.out.print(i + " ");
        System.out.println();
}

    public  void deployPlayerShips(Player player){
        Scanner input = new Scanner(System.in);
        System.out.println("********************************");
        System.out.println(player+"\nDeploy your ships:");
        //Deploying one ships for player
        for (int i = 1; i <= player.getShips(); ) {
            System.out.println("Enter X coordinate for your " + i + " ship: ");
            int x = input.nextInt();
            System.out.println("Enter Y coordinate for your " + i + " ship: ");
            int y = input.nextInt();
            System.out.println("Enter what direction want for your " + i +" ship: 1 for horizontal and 2 for vertical ");
            int direction=input.nextInt();
            if((x >= 0 && x <player.getNumRows()) && (y >= 0 && y < player.getNumCols()) && (player.getGrid()[x][y].equals(" "))) {
                if(direction!=1 && direction!=2)continue;
                if(direction==1) {
                    if(y==player.getGrid().length-1)
                        for(int j=0;j<2;j++){
                            placement[x][y-j]="@";
                            player.setGrid(placement);
                        }
                    else {
                        for(int j=0; j<2;j++) {
                            placement[x][y + j] = "@";
                            player.setGrid(placement);
                        }
                        i++;
                    }
                }
                else {
                    if(x==player.getGrid().length-1)
                        for(int j=0;j<2;j++){
                            placement[x-j][y]= "@";
                            player.setGrid(placement);
                        }
                    else {
                        for(int j=0;j<2;j++) {
                            placement[x+j][y] = "@";
                            player.setGrid(placement);
                        }
                        i++;
                    }
                }

            }
            else if((x >= 0 && x < player.getNumRows()) && (y >= 0 && y < player.getNumCols()) && player.getGrid()[x][y].equals("@"))
                System.out.println("You can't place two or more ships on the same location");
            else if((x < 0 || x >= player.getNumRows()) || (y < 0 || y >= player.getNumCols()))
                System.out.println("You can't place ships outside the " + player.getNumRows() + " by " + player.getNumCols() + " grid");
        }
        createBoards(player);
    }


    public  void Battle(Player me, Player enemy){
        playerTurn(me,enemy);
        printOceanMap(me);

        playerTurn(enemy,me);
        printOceanMap(enemy);

        System.out.println();
        System.out.println("Your ships: " + me.getShips() + " | Computer ships: " + enemy.getShips());
        System.out.println();
    }

    public void playerTurn(Player me, Player enemy){
        System.out.println(me + "It's your turn");
        int x = -1, y = -1;
        do {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter X coordinate: ");
            x = input.nextInt();
            System.out.println("Enter Y coordinate: ");
            y = input.nextInt();

            if ((x >= 0 && x < enemy.getNumRows()) && (y >= 0 && y < enemy.getNumCols())) //valid guess
            {
                if (enemy.getGrid()[x][y].equals("@")) //if computer ship is already there; computer loses ship
                {
                    System.out.println("Boom! You sunk the ship!");
                    placement[x][y]= "!";
                    me.setEnemyGrid(placement); //Hit mark
                    me.setShips("+");
                }
                else if (enemy.getGrid()[x][y].equals(" ")) {
                    System.out.println("Oh no, you missed :(");
                    placement[x][y] = "x";
                    me.setEnemyGrid(placement);

                }
            }
            else if ((x < 0 || x >= enemy.getNumRows()) || (y < 0 || y >= enemy.getNumCols()))  //invalid guess
                System.out.println("You can't place ships outside the " + enemy.getNumRows() + " by " + enemy.getNumCols() + " grid");
        }while((x < 0 || x >= enemy.getNumRows()) || (y < 0 || y >= enemy.getNumCols()));  //keep re-prompting till valid guess
    }


    public void gameOver(Player me, Player enemy){
        System.out.println("Your ships: " + me.getShips() + " | Computer ships: " + enemy.getShips());
        if(me.getShips() > 0 && enemy.getShips() <= 0)
            System.out.println("Hooray! You won the battle :)");
        else
            System.out.println("Sorry, you lost the battle");
        System.out.println();
    }

    public void printOceanMap(Player player){
        System.out.println(" MY BOARD" + player);
        System.out.print(" "+ " ");
        for(int i = 0; i < player.getGrid().length; i++)
            System.out.print(i + " ");
        System.out.println();


        //Middle section of Ocean Map
        for(int i = 0; i <player.getGrid().length; i++) {
            for (int j = 0; j <player.getGrid()[i].length; j++) {
                if (j == 0)
                    System.out.print(i + "|" +player.getGrid()[i][j]);
                else if (j == player.getGrid()[i].length - 1)
                    System.out.print("|"+player.getGrid()[i][j] +"|"+i);
                else
                    System.out.print("|" + player.getGrid()[i][j]);

            }
            System.out.println();
        }

        //Last section of Ocean Map
        System.out.print(" "+ " ");
        for(int i = 0; i < player.getGrid().length; i++)
            System.out.print(i + " ");
        System.out.println();
    }
}