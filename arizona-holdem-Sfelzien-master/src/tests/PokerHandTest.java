package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Card;
import model.DuplicateCardException;
import model.PokerHand;
import model.Rank;
import model.Suit;

/**
 * Tests the PokerHand class and the enums 
 * 
 * Rick includes all 52 cards to save you time (see end of file, after the @Test methods)
 * 
 * There are also some additional test cases here. However this unit test is in no way
 * complete since many more @Tests (test cases) are needed.   
 * 
 * Since PokerHand is not complete, all of the @Tests comparing PokerHand objects should fail.
 * 
 * The include enums Suit and Rank @Tests should pass since both enums are complete
*/
public class PokerHandTest {
	

	public void testStuff() {
		PokerHand a = new PokerHand(C2, C3, C4, C5, C6);
		a.display(); 
	}

  @Test
  public void testSuitEnum() {
    String result = "";
    for (Suit suit : Suit.values())
      result += suit + " ";
    assertEquals("CLUBS DIAMONDS HEARTS SPADES", result.trim());
  }

  @Test
  public void testRankEnum() {
    String result = "";
    for (Rank rank : Rank.values())
      result += rank + " ";
    assertEquals("DEUCE THREE FOUR FIVE SIX SEVEN EIGHT NINE TEN JACK QUEEN KING ACE",
        result.trim());
  }

  // Do not allow duplicate cards, throw an exception
  @Test(expected = DuplicateCardException.class)
  public void tryToAddTheSameCardTwiceA() {
    new PokerHand(C2, C3, C4, C5, C5);
  }
  
  @Test()
  public void testPairs() {
	  PokerHand a = new PokerHand(H3, CA, D4, H6, DA);
	    a.toString();
	   assertTrue(a.getRank() == 2);

  }
  
  @Test()
  public void testThreeKind() {
	  PokerHand a = new PokerHand(HA, CA, D4, H6, DA);
	    a.toString();
	   assertTrue(a.getRank() == 4);
	   assertTrue(a.getThreeKind() == 14);
  }
  
  @Test()
  public void testFullHouse() {
	  PokerHand a = new PokerHand(HA, CA, D6, H6, DA);
	    a.toString();
	   assertTrue(a.getRank() == 7);
  }
  
  @Test()
  public void testFullHouse2() {
	  PokerHand a = new PokerHand(HA, CA, D6, H6, DA);
	    a.toString();
	   assertTrue(a.getRank() == 7);
  }
  
  @Test()
  public void testPair() {
	  PokerHand a = new PokerHand(HA, C9, D4, H6, DA);
	    a.toString();
	   assertTrue(a.getRank() == 2);

  }
  
  @Test()
  public void testHighCard2() {
	  PokerHand a = new PokerHand(H2, C4, D6, H10, DA);
	  PokerHand b = new PokerHand(S2, C3, S5, D10, HA);
	  a.toString();
	   assertTrue(a.compareTo(b) > 0);

  } 
  @Test()
  public void testHighCard3() {
	  PokerHand a = new PokerHand(H2, C5, D6, H10, DA);
	  PokerHand b = new PokerHand(S2, D5, S6, D10, HA);
	  a.toString();
	   assertTrue(a.compareTo(b) == 0);
  }
  
  @Test()
  public void testHighCard4() {
	  PokerHand a = new PokerHand(H2, C5, D6, H10, DA);
	  PokerHand b = new PokerHand(S2, D4, S6, D10, HA);
	  a.toString();
	   assertTrue(a.compareTo(b) > 0);
  }
  
  
  @Test()
  public void testPairTieBreak() {
	  PokerHand a = new PokerHand(H2, C2, D6, H10, DA);
	  PokerHand b = new PokerHand(S3, D3, S6, D10, HA);
	  a.toString();
	   assertTrue(b.compareTo(a) > 0);
  }
  
  @Test()
	public void testPairTieBreak2() {
	PokerHand a = new PokerHand(H2, C2, D6, H10, DA);
	PokerHand b = new PokerHand(S2, D2, S6, D10, HK);
	a.toString();
	assertTrue(a.compareTo(b) > 0);
  }

  @Test()
	public void testPairTwoPair() {
	PokerHand a = new PokerHand(H2, C2, D6, H10, S10);
	PokerHand b = new PokerHand(S2, D2, S5, D10, C10);
	a.toString();
	assertTrue(a.compareTo(b) > 0);
}
  
  @Test()
 	public void testStraight() {
 	PokerHand a = new PokerHand(H2, C3, D4, H5, S6);
 	PokerHand b = new PokerHand(D6, D7, S8, D9, C10);
 	a.toString();
 	assertTrue(b.compareTo(a) > 0);
 }
  
  @Test()
 	public void testThreeKind2() {
 	PokerHand a = new PokerHand(H2, CA, D10, H10, S10);
 	PokerHand b = new PokerHand(S2, D9, S6, D6, C6);
 	a.toString();
 	assertTrue(a.compareTo(b) > 0);
 }
  
  @Test()
 	public void testPairTwoPair2() {
 	PokerHand a = new PokerHand(H3, C3, D6, H10, S10);
 	PokerHand b = new PokerHand(S2, D2, S5, D10, C10);
 	a.toString();
 	assertTrue(a.compareTo(b) > 0);
 }
  

  
  @Test()
  public void testFourKind() {
	  PokerHand a = new PokerHand(H3, C3, D3, S3, DA);
	    a.toString();
	   assertTrue(a.getRank() == 8);

  }

  @Test(expected = DuplicateCardException.class)
  public void testPair2() {
    PokerHand a = new PokerHand(H3, CA, D4, H6, DA);
    a.toString();
    PokerHand b = new PokerHand(H3, C5, HA, SA, C6);
    assertTrue(a.compareTo(b) < 0);
  }

  @Test
  public void testTwoPairWhenOnePairIsEqual() {
    PokerHand a = new PokerHand(C4, HK, D4, H3, DK);
    PokerHand b = new PokerHand(H4, C10, CA, DA, S4);
    assertTrue(a.compareTo(b) < 0);
    assertTrue(b.compareTo(a) > 0);
  }

  @Test
  public void testAceLowStraight() {
    PokerHand a = new PokerHand(CA, C2, C3, C4, H5);
    PokerHand b = new PokerHand(D2, D3, D4, D5, H6);
    boolean answer = a.compareTo(b) < 0;
    assertTrue(answer);
  }
  
  @Test
  public void testFourKind2() {
    PokerHand a = new PokerHand(D2, C2, H2, S2, H5);
   // PokerHand b = new PokerHand(D2, D3, D4, D5, H6);
   // boolean answer = a.compareTo(b) < 0;
    assertTrue(a.getFourKind() == 2);
  }
  
  @Test
  public void testLowHighPair() {
    PokerHand a = new PokerHand(S10, C10, C3, C2, H2);
    //PokerHand b = new PokerHand(D2, D3, D4, D5, H6);
    //boolean answer = a.compareTo(b) < 0;
    assertTrue(a.getHighPair() == 10);
    assertTrue(a.getLowPair() == 2);
  }
  
  @Test
  public void testRoyalFlush() {
    PokerHand a = new PokerHand(C10, CJ, CQ, CK, CA);
    assertTrue(a.getRank() == 10);
  }

  
  // Set up 52 cards to use C2 instead of new Card(Rank.Deuce, Suit.Clubs)
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

  // TEST CARD HGH HANDS

  private static PokerHand nothing72 = new PokerHand(C2, C3, C4, C5, D7);
  private static PokerHand nothing73 = new PokerHand(D2, D4, D5, D6, C7);
  private static PokerHand nothingJ = new PokerHand(C8, C9, C10, SJ, D3);
  private static PokerHand nothingK9 = new PokerHand(CK, CQ, CJ, D10, H9);
  private static PokerHand nothingK8 = new PokerHand(HK, HQ, HJ, H10, S8);
  private static PokerHand nothingA = new PokerHand(S9, SJ, SQ, SK, CA);

  @Test
  public void testNothing0() {
    assertTrue(nothing73.compareTo(nothing72) > 0);
  }

  @Test
  public void testNothing1() {
    assertTrue(nothingJ.compareTo(nothing73) > 0);
  }

  @Test
  public void testNothing2() {
    assertTrue(nothingK8.compareTo(nothingJ) > 0);
  }

  @Test
  public void testNothing3() {
    assertTrue(nothingK9.compareTo(nothingK8) > 0);
  }

  @Test
  public void testNothing4() {
    assertTrue(nothingA.compareTo(nothingK8) > 0);
  }

  // Many more tests needed

}