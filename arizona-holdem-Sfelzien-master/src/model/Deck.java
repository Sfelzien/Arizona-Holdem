package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	private int numCards; 
	private List<Card> cards;
	private List<Card> communityDeck;

	public Deck(){
		numCards = 52; 
		communityDeck = new ArrayList<Card>(5);
		cards = new ArrayList<Card>(52);
	    for (Suit suit : Suit.values()) {
	        for (Rank rank : Rank.values()) {
	           cards.add(new Card(rank, suit));
	       } 
	   }
	    Collections.shuffle(cards);
	}
	
	public Card getCard() {
		Card playCard = cards.get(0);
		cards.remove(0);
		return playCard;
	}
	
	
	public List<Card> getCommDeck() {
		for (int i = 0 ; i < 5; i++) {
			communityDeck.add(getCard());
		}
		return communityDeck;
	} 
	
	public void showCommunityDeck() {
		//System.out.println();
		for (Card s : communityDeck) {
	  	      System.out.print(s.toString() + "  ");
	  	  	}
		System.out.println();
	}
	
	 private final static Card C2 = new Card(Rank.DEUCE, Suit.CLUBS);
	  private final static Card C3 = new Card(Rank.THREE, Suit.CLUBS);
	  private final static Card C4 = new Card(Rank.FOUR, Suit.CLUBS);
	  private final static Card C5 = new Card(Rank.FIVE, Suit.CLUBS);
	  private final static Card C6 = new Card(Rank.SIX, Suit.CLUBS);
	  private final static Card C7 = new Card(Rank.SEVEN, Suit.CLUBS);
	  private final static Card C8 = new Card(Rank.EIGHT, Suit.CLUBS);
	  private final static Card C9 = new Card(Rank.NINE, Suit.CLUBS);
	  private final static Card C10 = new Card(Rank.TEN, Suit.CLUBS);
	  private final static Card CJ = new Card(Rank.JACK, Suit.CLUBS);
	  private final static Card CQ = new Card(Rank.QUEEN, Suit.CLUBS);
	  private final static Card CK = new Card(Rank.KING, Suit.CLUBS);
	  private final static Card CA = new Card(Rank.ACE, Suit.CLUBS);

	  private final static Card D2 = new Card(Rank.DEUCE, Suit.DIAMONDS);
	  private final static Card D3 = new Card(Rank.THREE, Suit.DIAMONDS);
	  private final static Card D4 = new Card(Rank.FOUR, Suit.DIAMONDS);
	  private final static Card D5 = new Card(Rank.FIVE, Suit.DIAMONDS);
	  private final static Card D6 = new Card(Rank.SIX, Suit.DIAMONDS);
	  private final static Card D7 = new Card(Rank.SEVEN, Suit.DIAMONDS);
	  private final static Card D8 = new Card(Rank.EIGHT, Suit.DIAMONDS);
	  private final static Card D9 = new Card(Rank.NINE, Suit.DIAMONDS);
	  private final static Card D10 = new Card(Rank.TEN, Suit.DIAMONDS);
	  private final static Card DJ = new Card(Rank.JACK, Suit.DIAMONDS);
	  private final static Card DQ = new Card(Rank.QUEEN, Suit.DIAMONDS);
	  private final static Card DK = new Card(Rank.KING, Suit.DIAMONDS);
	  private final static Card DA = new Card(Rank.ACE, Suit.DIAMONDS);

	  private final static Card H2 = new Card(Rank.DEUCE, Suit.HEARTS);
	  private final static Card H3 = new Card(Rank.THREE, Suit.HEARTS);
	  private final static Card H4 = new Card(Rank.FOUR, Suit.HEARTS);
	  private final static Card H5 = new Card(Rank.FIVE, Suit.HEARTS);
	  private final static Card H6 = new Card(Rank.SIX, Suit.HEARTS);
	  private final static Card H7 = new Card(Rank.SEVEN, Suit.HEARTS);
	  private final static Card H8 = new Card(Rank.EIGHT, Suit.HEARTS);
	  private final static Card H9 = new Card(Rank.NINE, Suit.HEARTS);
	  private final static Card H10 = new Card(Rank.TEN, Suit.HEARTS);
	  private final static Card HJ = new Card(Rank.JACK, Suit.HEARTS);
	  private final static Card HQ = new Card(Rank.QUEEN, Suit.HEARTS);
	  private final static Card HK = new Card(Rank.KING, Suit.HEARTS);
	  private final static Card HA = new Card(Rank.ACE, Suit.HEARTS);

	  private final static Card S2 = new Card(Rank.DEUCE, Suit.SPADES);
	  private final static Card S3 = new Card(Rank.THREE, Suit.SPADES);
	  private final static Card S4 = new Card(Rank.FOUR, Suit.SPADES);
	  private final static Card S5 = new Card(Rank.FIVE, Suit.SPADES);
	  private final static Card S6 = new Card(Rank.SIX, Suit.SPADES);
	  private final static Card S7 = new Card(Rank.SEVEN, Suit.SPADES);
	  private final static Card S8 = new Card(Rank.EIGHT, Suit.SPADES);
	  private final static Card S9 = new Card(Rank.NINE, Suit.SPADES);
	  private final static Card S10 = new Card(Rank.TEN, Suit.SPADES);
	  private final static Card SJ = new Card(Rank.JACK, Suit.SPADES);
	  private final static Card SQ = new Card(Rank.QUEEN, Suit.SPADES);
	  private final static Card SK = new Card(Rank.KING, Suit.SPADES);
	  private final static Card SA = new Card(Rank.ACE, Suit.SPADES);
}
