package model;
import java.util.*;



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
  public PokerHand(List<Card> hand) throws DuplicateCardException {
	  
	  this.hand = hand; 
  
	  Collections.sort(hand);
	
	  calculateRank(); 
	  
	  
  }
  
  public String toString() {
	  String string = "";
	  for (Card s : hand) {
	      string += s.toString() + "  ";
	  }
	  return string; 
  }
  
  public void display() {
	  
	 System.out.println("**********************************");
	 System.out.println(handName.toUpperCase());
	 System.out.println("Rank: " + rank);
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
  
  public int isRoyalFlush() {
	  
	  if (isStraightFlush() && hand.get(0).getValue() == 10) {
		  rank = 10;
		  handName = "Royal Flush";
		  return rank; 
		  }
	  return rank; 
	  }

  
  
  public boolean isStraightFlush() {
	  
	  if (isStraight() && isFlush()) {
		  rank = 9;
		  handName = "Straight Flush";
		  return true; 
	  }

	  return false; 
  }
  
  public boolean isStraight() {
	  
	  
	  if (hand.get(0).getValue() == 2 && hand.get(1).getValue() == 3 && hand.get(2).getValue() == 4 && hand.get(3).getValue() == 5 && hand.get(4).getValue() == 14){
		  rank = 5;
		  handName = "Straight";
		  return true; 
	  }
	  
	  
	  int testRank = hand.get(0).getValue() + 1;

      for (int i = 1; i < 5; i++)
      {
         if ( hand.get(i).getValue() != testRank) {
        	 return false;	 
         }
         testRank++;
      }
     
      rank = 5;
	  handName = "Straight";
      return true; 

  }
	  
  
  public boolean isFlush() {
	  for (int i = 0; i < 4; i++)
	    {
	        if( !hand.get(i).getSuit().equals(hand.get(i + 1).getSuit()) )
	        {
	            return false;
	        }
	    }
  		rank = 6; 
  		handName = "Flush";
  		return true;
  	}

  public boolean isThreeOfAKind() {
	  
	  Set<Integer> ref = occurrenceMap.keySet();
	    Iterator<Integer> it = ref.iterator();

	    while (it.hasNext()) {
	      Integer o = it.next(); 
	      if(occurrenceMap.get(o) == 2) { 
	    	 return false; 
	 
	      } if(occurrenceMap.get(o) == 3) { 
	    	  threeKind = o;
	    	  rank = 4; 
			  handName = "Three of a Kind";
			  return true;  
	    }
	    }
 
	  return false; 
  }
  
  public boolean isFourOfAKind() {
		  
		  Set<Integer> ref = occurrenceMap.keySet();
		    Iterator<Integer> it = ref.iterator();

		    while (it.hasNext()) {
		      Integer o = it.next(); 
		       if(occurrenceMap.get(o) == 4) { 
		    	  fourKind = o;
		    	  rank = 8; 
				  handName = "Four of a Kind";
				  return true; 
		       }
	  } 
	  return false; 
	 
  }
 
  
  public boolean isFullHouse() {
	  
	  if (twoPair.size() == 1 && occurrenceMap.containsValue(3)) {
		  pair = twoPair.get(0);
		  Set<Integer> ref = occurrenceMap.keySet();
		    Iterator<Integer> it = ref.iterator();

		    while (it.hasNext()) {
		      Integer o = it.next(); 
		       if(occurrenceMap.get(o) == 3) { 
		    	  threeKind = o;
		    	  rank = 7; 
				  handName = "Full House";
				  return true; 
		       }
		    }
	  }
	  
	  return false; 
  }
  
  public boolean isTwoPair(){ 
	  
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
		  return true; 
	  }
	  return false; 
  }
  
  
  public boolean isPair(){
	    
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
		  return true; 
	  }
	  return false; 
  }
  
  public boolean isHighCard() {
	  if (rank ==1) {
		  handName = "High Card";
		  return true; 
	  }
	  return false; 
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
	  
	  isFlush();
	  isStraight();
      isStraightFlush();
      isRoyalFlush();
      isPair();
      isThreeOfAKind();
      isFullHouse();
      isFourOfAKind();
      isTwoPair();
      isHighCard();
      
      //display();
      
  
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
  	public String getHandName() {
  		return handName; 
  	}
  	Card get(int i) {
  		return this.hand.get(i);
  	}

  @Override
  public int compareTo(PokerHand o) {
	  
	  PokerHand hand1 = (PokerHand) o;
	 
	  
	  int thisRank = ((PokerHand)this).getRank();
	  int otherRank = ((PokerHand)o).getRank(); 

	  	if(thisRank != otherRank){
	  		
	  		return thisRank - otherRank;
	     }
	 
	 //if high card only
	 if (thisRank == 1) {
		 //step through high cards
		
		 //for (int i = 4; i >= 0; i--) {
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
			 for (int i = 4; i >= 0; i--) {
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
		} 
		
			
		for (int i = 4; i >= 0; i--) {
				 if (((PokerHand)this).highCard(i) != ((PokerHand)o).highCard(i)) {
					 return ((PokerHand)this).highCard(i) - ((PokerHand)o).highCard(i);
				}
			}
		}
			 
	//if three of a kind
	if (thisRank == 4) {
		if (((PokerHand)this).getThreeKind() != ((PokerHand)o).getThreeKind()) {
			return ((PokerHand)this).getThreeKind() - ((PokerHand)o).getThreeKind();
		} else {
			 for (int i = 4; i >= 0; i--) {
				 if (((PokerHand)this).highCard(i) != ((PokerHand)o).highCard(i)) {
					 return ((PokerHand)this).highCard(i) - ((PokerHand)o).highCard(i);
					 }
				 }
			  }

				 
	}
	//if straight
	if (thisRank == 5) {
		for (int i = 4; i >= 0; i--) {
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