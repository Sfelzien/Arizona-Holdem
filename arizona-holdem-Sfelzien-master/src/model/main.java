package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class main {


	public static void main(String[] args) {

		Game game = new Game(); 
		Scanner sc=new Scanner(System.in);
		System.out.println("How many players?");  
		int playerCount=sc.nextInt(); 
        
		game.setPlayerCount(playerCount);
		game.newPlayers();
        do {
    		game.run();
    		System.out.println("Would you like to play again? Enter Y or N:");
        }
		while (sc.next().equalsIgnoreCase("Y"));
		
	}

	
}


