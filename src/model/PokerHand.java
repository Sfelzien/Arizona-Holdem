package model;
import java.util.*;

// Model a five card PokerHand that is constructed with five Card objects
//
// PokerHand a = new PokerHand(H3, CA, D4, H6, DA);
//

public class PokerHand implements Comparable<PokerHand> {
	
	private Map<Integer, Integer> occurrenceMap = new HashMap<Integer, Integer>();
	private List<Integer> twoPair = new ArrayList<Integer>();
	private List<Card> hand;
	private int rank = 1;
	private String handName;
	private int pair; 
	private int lowPair; 
	private int highPair; 
	private int threeKind;
	private int fourKind;  
	


  // Construct a five card poker hand.
  public PokerHand(Card c1, Card c2, Card c3, Card c4, Card c5) throws DuplicateCardException {
	  
	    
	  hand = new ArrayList<Card>();
	  hand.add(c1);
	  hand.add(c2);
	  hand.add(c3);
	  hand.add(c4);
	  hand.add(c5);
	  
	  Collections.sort(hand);
	
	  calculateRank(); 
	  display();
	  
	  
  }
  
  public void display() {
	  
	 System.out.println("**********************************");
	 //System.out.println(handName.toUpperCase());
	 System.out.println("Rank: " + rank);
	 if (rank == 1) {
		 System.out.println("High Card: " + hand.get(4));
	 }
	 System.out.println(); 
	  for (Card s : hand) {
	      System.out.print(s.toString() + "  ");
	  }
	 System.out.println(); 
	 System.out.println("**********************************");
	 System.out.println(); 
  }
  
  public int highCard(int index) {
	  return hand.get(index).getValue();
  }
  

  
  public int isStraightFlush() {
	  for (int i = 0; i < hand.size()-1; i ++) {
		  if (hand.get(i).getSuit() == hand.get(i+1).getSuit()) {
			  if (hand.get(i).getValue() == (hand.get(i+1).getValue() +1)){
				  if(hand.get(i).getValue() == 10) {
					  rank = 10; 
					  handName = "Royal Flush";
					  return rank;
				  }
				  rank = 9;
				  handName = "Straight Flush";
				  return rank; 
			  }
		  }
	  }
	  
	  for (int i = 0; i < hand.size()-1; i ++) {
		  if (hand.get(i).getSuit() == hand.get(i+1).getSuit()) {
			 if (hand.get(4).getValue() == 13 && hand.get(3).getValue() == 12 && hand.get(2).getValue() == 11 && hand.get(1).getValue() == 10 && hand.get(0).getValue()== 14) {
				 rank = 10; 
				 handName = "Royal Flush";
				 return rank;
			 }
		  }
	  }

	  return rank; 
  }
  
  public int isStraight() {
	  
	  if (hand.get(0).getValue() == 14 && hand.get(0).getSuit() != hand.get(1).getSuit() && hand.get(1).getValue() == 2 && hand.get(1).getSuit() != hand.get(2).getSuit() &&
    		  hand.get(2).getValue() == 3 && hand.get(2).getSuit() != hand.get(3).getSuit() &&
    		  hand.get(3).getValue() == 4 && hand.get(3).getSuit() != hand.get(4).getSuit()){
    	  		rank = 5;
    	  		handName = "Straight";
    	  		return rank; 
	  }
	  
	  int testRank = hand.get(0).getValue() + 1;

      for (int i = 1; i < 5; i++)
      {
         if ( hand.get(i).getValue() != testRank || hand.get(i).getSuit() == hand.get(i+1).getSuit()) {
        	 return rank;
         }
         	testRank++;
      }
     
      rank = 5;
	  handName = "Straight";
      return rank; 

  }
	  
  
  public int isFlush() {
	  for (int i = 0; i < hand.size()-1; i++) {
			  if (hand.get(i).getSuit() != hand.get(i+1).getSuit()) {
				  return rank; 
			  }
		  }
  		rank = 6; 
  		handName = "Flush";
  		return rank;
  	}

  public int isThreeOfAKind() {
	  
	  Set<Integer> ref = occurrenceMap.keySet();
	    Iterator<Integer> it = ref.iterator();

	    while (it.hasNext()) {
	      Integer o = it.next(); 
	      if(occurrenceMap.get(o) == 2) { 
	    	 return rank; 
	 
	      } if(occurrenceMap.get(o) == 3) { 
	    	  threeKind = o;
	    	  rank = 4; 
			  handName = "Three of a Kind";
			  return rank;  
	    }
	    }
 
	  return rank; 
  }
  
  public int isFourOfAKind() {
	  if (occurrenceMap.containsValue(4)) {
		  rank = 8;
		  handName = "Four of a Kind";
		  return rank; 
	  } 
	  return rank; 
	 
  }
  
  public int isFullHouse() {
	  if (twoPair.size() == 1 && occurrenceMap.containsValue(3)) {
		  rank = 7;
		  handName = "Full House";
		  return rank; 
	  }
	  
	  return rank; 

  }
  
  public int isTwoPair(){ 
	  
	  int secondPair = 0; 
	   
	  if (twoPair.size()==(2)) {
		  
		  pair = twoPair.get(0);
		  secondPair = twoPair.get(1);
		  
		  if (pair < secondPair) {
			  lowPair = pair; 
			  highPair = secondPair; 
		  } else {
			  lowPair = secondPair; 
			  highPair = pair; 
		  }
		  rank = 3;
		  handName = "Two Pair";
		  return rank; 
	  }
	  return rank; 
  }
  
  public int isPair(){
	    
	  Set<Integer> ref = occurrenceMap.keySet();
	    Iterator<Integer> it = ref.iterator();

	    while (it.hasNext()) {
	      Integer o = it.next(); 
	      if(occurrenceMap.get(o).equals(2)) { 
	    	 twoPair.add(o); 
	      } 
	    }

	  if (twoPair.size() == 1) {
		  pair = twoPair.get(0);
		  rank = 2;
		  handName = "Pair";
		  return rank; 
	  }
	  return 0; 
  }
  
  
  public void calculateRank(){
	  
	  for (int i = 0; i < hand.size()-1; i++){
		  for(int j = i+1; j < hand.size(); j++) {
			  if (hand.get(i)== hand.get(j)) {
				  throw new DuplicateCardException();
			  }
		  }
	  }
	  
	  
	  int occurrences = 1;
	  
	  for (int i = 0; i < hand.size()-1; i++){
		  if (hand.get(i).getValue() == hand.get(i+1).getValue()) {
			  occurrences++;
			  occurrenceMap.put(hand.get(i).getValue(), occurrences); 
		  } else {
			  occurrences = 1; 
		  }
	  }
	  

      isStraightFlush();
      isPair();
      isTwoPair();
      isThreeOfAKind();
      isStraight();
      isFullHouse();
      isFourOfAKind();
      isFlush();
  
   }
   
  
  //constructors
  	public int getPair() {
	  return pair; 
  	}
  	public int getLowPair() {
  	  return lowPair; 
    	}
  	public int getHighPair() {
    	  return highPair; 
      	}
  	public int getThreeKind() {
  		return threeKind; 
  	}
  	public int getFourKind() {
  		return fourKind; 
  	}
  	public int getRank() {
  		return rank; 
  	}
  	private Card get(int i) {
  		return this.hand.get(i);
  	}

  @Override
  public int compareTo(PokerHand o) {
	  
	  PokerHand hand1 = (PokerHand) o;
	  
	  for (int i = 0; i < 5; i++) {
		  for (int j = 0; j < 5; j++) {
			  if (hand1.get(i) == hand.get(j)) {
				  throw new DuplicateCardException();
			  }
		  }
	  }
	 
	  
	  int thisRank = ((PokerHand)this).getRank();
	  int otherRank = ((PokerHand)o).getRank(); 

	  	if(thisRank != otherRank){
	  		
	  		return thisRank - otherRank;
	     }
	 
	 //if high card only
	 if (thisRank == 1) {
		 //step through high cards
		
		if (((PokerHand)this).highCard(4) != ((PokerHand)o).highCard(4)) {
				 return ((PokerHand)this).highCard(4) - ((PokerHand)o).highCard(4);
			}	
		 if (((PokerHand)this).highCard(3) != ((PokerHand)o).highCard(3)) {
			 return ((PokerHand)this).highCard(3) - ((PokerHand)o).highCard(3);
			 }
		 if (((PokerHand)this).highCard(2) != ((PokerHand)o).highCard(2)) {
			 return ((PokerHand)this).highCard(2) - ((PokerHand)o).highCard(2);
			 }
		 if (((PokerHand)this).highCard(1) != ((PokerHand)o).highCard(1)) {
			 return ((PokerHand)this).highCard(1) - ((PokerHand)o).highCard(1);
			 }
		 if (((PokerHand)this).highCard(0) != ((PokerHand)o).highCard(0)) {
			 return ((PokerHand)this).highCard(0) - ((PokerHand)o).highCard(0);
			 }
			 return 0;
			}
	 
	 //if pair
	 if (thisRank == 2) {
		  if (((PokerHand)this).getPair() != ((PokerHand)o).getPair()) {
			  return ((PokerHand)this).getPair() - ((PokerHand)o).getPair();
		 } else {
			 for (int i = hand.size(); i > 0; i--) {
				 if (((PokerHand)this).highCard(i) != ((PokerHand)o).highCard(i)) {
					 return ((PokerHand)this).highCard(i) - ((PokerHand)o).highCard(i);
					 }
				 }
			  }
		 
		 
	 }
	//if two pair
	if (thisRank == 3) {
		if (((PokerHand)this).getHighPair() != ((PokerHand)o).getHighPair()) {
			return ((PokerHand)this).getHighPair() - ((PokerHand)o).getHighPair();
		} else if (((PokerHand)this).getLowPair() != ((PokerHand)o).getLowPair()) {
			return ((PokerHand)this).getLowPair() - ((PokerHand)o).getLowPair();
			
		} else {
			for (int i = hand.size(); i > 0; i--) {
				 if (((PokerHand)this).highCard(i) != ((PokerHand)o).highCard(i)) {
					 return ((PokerHand)this).highCard(i) - ((PokerHand)o).highCard(i);
					 }
				 }
		}
			 
	 }
	//if three of a kind
	if (thisRank == 4) {
		if (((PokerHand)this).getThreeKind() != ((PokerHand)o).getThreeKind()) {
			return ((PokerHand)this).getThreeKind() - ((PokerHand)o).getThreeKind();
		} 
		for (int i = hand.size(); i > 0; i--) {
			 if (((PokerHand)this).highCard(i) != ((PokerHand)o).highCard(i)) {
				 return ((PokerHand)this).highCard(i) - ((PokerHand)o).highCard(i);
				 }
			 }
				 
	}
	//if straight
	if (thisRank == 5) {
		for (int i = hand.size()-1; i > 0; i--) {
			 if (((PokerHand)this).highCard(i) != ((PokerHand)o).highCard(i)) {
				 return ((PokerHand)this).highCard(i) - ((PokerHand)o).highCard(i);
				 }
			 }
		 
	}
	// if flush
	if (thisRank == 6) {
		for (int i = hand.size()-1; i > 0; i--) {
			 if (((PokerHand)this).highCard(i) != ((PokerHand)o).highCard(i)) {
				 return ((PokerHand)this).highCard(i) - ((PokerHand)o).highCard(i);
				 }
			 }
		 
		 
	}
	// if full house
	if (thisRank == 7) {
		if (((PokerHand)this).getThreeKind() != ((PokerHand)o).getThreeKind()) {
			return ((PokerHand)this).getThreeKind() - ((PokerHand)o).getThreeKind();
		} else if (((PokerHand)this).getPair() != ((PokerHand)o).getPair()) {
			  return ((PokerHand)this).getPair() - ((PokerHand)o).getPair(); 
		}
		for (int i = hand.size(); i > 0; i--) {
		if (((PokerHand)this).highCard(i) != ((PokerHand)o).highCard(i)) {
			 return ((PokerHand)this).highCard(i) - ((PokerHand)o).highCard(i);
			 }
		}
	}
	// if four of a kind
	if (thisRank == 8) {
		if (((PokerHand)this).getFourKind() != ((PokerHand)o).getFourKind()) {
			return ((PokerHand)this).getFourKind() - ((PokerHand)o).getFourKind();
		}
		for (int i = hand.size(); i > 0; i--) {
			if (((PokerHand)this).highCard(i) != ((PokerHand)o).highCard(i)) {
				 return ((PokerHand)this).highCard(i) - ((PokerHand)o).highCard(i);
				 }
			}
			 
	}
	// if straight flush
	if (thisRank == 9) {
		for (int i = hand.size(); i > 0; i--) {
			if (((PokerHand)this).highCard(i) != ((PokerHand)o).highCard(i)) {
				 return ((PokerHand)this).highCard(i) - ((PokerHand)o).highCard(i);
				 }
			}
	}
	// if royal flush
	if (thisRank == 10) {
		return 0;	 
	}
   return thisRank - otherRank;
}

}