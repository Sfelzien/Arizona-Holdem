package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

public class Game {

	private List<Player> players = new ArrayList<Player>();
	private Map<String, PokerHand>allHands = new HashMap<String, PokerHand>();
	private Map<String, PokerHand>allHighestHands = new HashMap<String, PokerHand>();
	private List<PokerHand> winners = new ArrayList<PokerHand>();
	Set<String> winnerNames = new HashSet<String>();
	String winningPlayer = " ";
	private List<Card> communityDeck; 
    int playerCount = 0;
    double pot = 0.0;

    public void setPlayerCount(int playerCount){
    	this.playerCount = playerCount;
    }
    
    
    public void newPlayers() {
       	for (int i = 1; i < playerCount+1; i++) {
    		players.add(new Player("Player " + Integer.toString(i)));
    	}
    }
    
    public void run(){
    	Deck deck = new Deck();
    	communityDeck = deck.getCommDeck();
    	PokerHand commDeck = new PokerHand(communityDeck); 
    	int commRank = commDeck.getRank();
    	addPlayerData(deck, communityDeck);
    	System.out.print("Community Cards: ");
    	deck.showCommunityDeck();
    	displayPlayers();
    	getWinner();
    	addToPot();
    	splitPot();
    	displayWinners();
    	emptyHands();
    }
    
    
    public void addPlayerData(Deck deck, List<Card> communityDeck) {
 
    	for (Player player : players) {
    		player.addCard(deck.getCard(), deck.getCard());
    		player.ante();
    		player.addAllCards(communityDeck);
    		player.getCombinations();
    		player.getHighHand();
    		allHands.put(player.getName(), player.getBestHand());
    	}
    }
    
    
    public void getWinner() {
    	int maxValue = 0;
   	 for (Map.Entry<String, PokerHand> entry : allHands.entrySet()) {
		   if (maxValue == 0 || maxValue == (entry.getValue().getRank())) {
		     maxValue = entry.getValue().getRank();
		     allHighestHands.put(entry.getKey(), entry.getValue());
		   } else if (entry.getValue().getRank() > maxValue) {
		     maxValue = entry.getValue().getRank();
		     allHighestHands.clear();
		     allHighestHands.put(entry.getKey(), entry.getValue());
		     
		   }
		 }

    	

    	for (PokerHand value : allHighestHands.values()) {
    	    winners.add(value);
    	}
    	for (int t = 0; t < winners.size(); t++) {
    	for (int i = 0; i < winners.size()-1; i++) {
	 		for (int j = i + 1; j < winners.size(); j++) {
	 			if (winners.get(i).compareTo(winners.get(j)) > 0) {
	 				winners.remove(j);
	 			} else if (winners.get(i).compareTo(winners.get(j)) < 0) {
	 				winners.remove(i);
	 			}
	 		}
	 	}
	}
    	
        for (Entry<String, PokerHand> entry : allHighestHands.entrySet()) {
        	
            if (entry.getValue().equals(winners.get(0))) {
               winnerNames.add(entry.getKey());
            }
        }

    }
    
    public void displayWinners() {
    	System.out.println("Winning Hands: " + winningPlayer);
    	System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    	System.out.println(winners.get(0).getHandName() + "   " + winners.get(0));
   
    	
    }
    

    
    public void displayPlayers() {
    	System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    	for (Player player : players) {
    		player.showName();
    		System.out.print(player.getBalance());
    		player.showHands();
    		System.out.println();
    	}


    	}
    public void emptyHands() {
    	for (Player player : players) {
    		player.clearHands();
    	}
    	allHands.clear();
    	winners.clear();
    	allHighestHands.clear();
    	winnerNames.clear();
    	winningPlayer = " ";
    	pot = 0;
    }

    
    public void addToPot() {
    	pot += playerCount * 2.0;
    	
    }
    
    public void splitPot() {
    	double splitPot = pot/winnerNames.size();
    	for (String name : winnerNames) {
    		for (Player player : players) {
    			if (player.getName() == name) {
    				player.addWinnings(splitPot);
    				winningPlayer +=  player.getName() + "  " + player.getBalance() + "%n";
    				winningPlayer = String.format(winningPlayer);
    			}
    		}
    	}

    }
    

}



