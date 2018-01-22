package model;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Player {
	 private double balance;
	 int maxValue = 0;
	 private List<Card> twoCardHand;
	 private List<Card> sevenCards;
	 private Map<PokerHand, Integer> playerHands = new HashMap<PokerHand, Integer>();
	 private ArrayList<List<Card>> possibleHands = new ArrayList<>();
	 private List<PokerHand> highestHands;
	 private List<Card> bestHand1 = new ArrayList<Card>(5);
	 private PokerHand  bestHand;
	
	
	 private String name; 
	 
	 public Player(String name) {
		    balance = 100;
		    twoCardHand = new ArrayList<Card>();
		    sevenCards = new ArrayList<Card>();
		    highestHands = new ArrayList<PokerHand>();
		    this.name = name; 
		    
		  }
	
	public PokerHand getBestHand(){
		return this.bestHand = bestHand;	
	} 
	

	 public <Card> ArrayList<List<Card>> getHandCombinations(List<Card> elements, int k) {
		    return getPossibleHands(elements,k,0);
		}

		public <Card> ArrayList<List<Card>> getPossibleHands(List<Card> sevenCards, int k, int i) {
			ArrayList<List<Card>> temp = new ArrayList<>();
		    if(k > 0) {
		        int n = sevenCards.size();
		        for(int j = i; j <= n-k; j++) {
		            Card val = sevenCards.get(j);
		            ArrayList<List<Card>> tails = getPossibleHands(sevenCards,k-1,j+1);
		            for(List<Card> tail : tails) {
		                List<Card> result = new ArrayList<>();
		                result.add(val);
		                result.addAll(tail);
		                temp.add(result);
		            }
		        }
		    } else {
		    	
		        temp.add(new ArrayList<Card>());
		    }
		    return temp;
		}
		
	 
	 public void ante() {
		 balance -= 2.0;
	 }
	 public void addWinnings(double splitPot) {
		 balance += splitPot;
	 }
	 
	 public String getBalance() {
		 	NumberFormat formatter = DecimalFormat.getCurrencyInstance();
		 	return formatter.format(balance); 
		  }
	 public void addCard(Card card1, Card card2) {
		 twoCardHand.add(card1);
		 twoCardHand.add(card2);
		 
		
		 sevenCards.add(card1);
		 sevenCards.add(card2);
	
		 
	 }
	 public void getCombinations() { 
		 for(List<Card> card : getHandCombinations(sevenCards,5)) {
			 	possibleHands.add(card);
			    
			}
	 }
	 public void getHighHand() { 
		 for(List<Card> card : possibleHands) {
			 PokerHand possibleHand = new PokerHand(card); 
			 playerHands.put(possibleHand, possibleHand.getRank());
			 
			 for (PokerHand name: playerHands.keySet()){

		            String key = name.toString();
		            String value = playerHands.get(name).toString();   
		           // System.out.println(key + " " + value);
			 }
		 }
		 
			 
			 for (Map.Entry<PokerHand, Integer> entry : playerHands.entrySet()) {
			   if (maxValue == 0 || maxValue == (entry.getValue())) {
			     maxValue = entry.getValue();
			     highestHands.add(entry.getKey());
			   } else if (entry.getValue() > maxValue) {
			     maxValue = entry.getValue();
			     highestHands.clear();
			     highestHands.add(entry.getKey());
			     
			   }
			 }

			
			
		while (highestHands.size() > 1) {		 
	 		 for (int i = 0; i < highestHands.size()-1; i++) {
			 		for (int j = i + 1; j < highestHands.size(); j++) {
			 			if (highestHands.get(i).compareTo(highestHands.get(j)) > 0 || highestHands.get(i).compareTo(highestHands.get(j)) == 0) {
			 				highestHands.remove(j);
			 			} else {
			 				highestHands.remove(i);
			 				
			 			}
			 	
			 		}
	 			}
	 	}
		for (PokerHand p : highestHands) { 
			  for (int i = 0; i < 5; i++) {
				  bestHand1.add(p.get(i));
			} 
	 	}
		 bestHand = new PokerHand(bestHand1); 
	 }
			 
		
	 
	 public void addAllCards(List<Card> communityDeck) {
	
		for (Card card: communityDeck) {
		 sevenCards.add(card);
		 
		 Collections.sort(sevenCards);
		}
	 }
	 
	 public String getName() {
		 return name; 
	 }
	 
	 public void showName() {
		 System.out.print(name + ": ");
	 }
	 
	 public void clearHands() {
		twoCardHand.clear();
		highestHands.clear();
		sevenCards.clear();
		possibleHands.clear(); 
		playerHands.clear();
		bestHand1.clear();
		maxValue = 0;
	 }
	 

	 public void showHands() {
		 System.out.print("  -");
		 for (Card s : twoCardHand) {
		      System.out.print(" " + s.toString() + "  ");
		  }
		 System.out.println();
		 System.out.print("	Best Hand:  ");
		 for (int i = 0; i < 5; i++) {
		 System.out.print(bestHand.get(i) + " ");
		 }
		 System.out.print("-- " + bestHand.getHandName());
	 }
	 


}
