package blackJack;

import java.util.HashSet;

public class Dealer implements Person {
	private HashSet<Card> dealerSet = new HashSet<>(2);
	private boolean dealBurst = false;
	private boolean dealBlackjack = false;
	private boolean dealGoOn = true;
	private int tempSum = 0;

	public boolean getBurst() {
		return dealBurst;
	}

	public boolean getBJ() {
		return dealBlackjack;
	}

	public boolean getGoOn() {
		return dealGoOn;
	}

	public HashSet<Card> getSet() {
		return dealerSet;
	}

	public void turn() {
		while (dealerSet.size() < 2) {
			dealerSet.add(Main.cardGenerator());
		}
	}

	public void hit() {
		if (tempSum < 16) {
			dealerSet.add(Main.cardGenerator());
			tempSum = 0;
			System.out.println(dealerSet);
			this.ai();
		} else
			;
		this.dealGoOn = false;
	}

	public void ai() {

		for (Card c : dealerSet) {
			tempSum += c.getNumber();
		}
		if (tempSum > 21) {
			System.out.println("딜러 버스트!");
			dealGoOn = false;
			dealBurst = true;
		} else if (tempSum == 21) {
			System.out.println("딜러 블랙잭!");
			dealGoOn = false;
			dealBlackjack = true;
		} else
			this.hit();

	}

	@Override
	public int getSum() {
		// TODO Auto-generated method stub
		int output = 0;
		for (Card c : dealerSet)
			output += c.getNumber();
		return output;
	}

	@Override
	public void setScore(int score) {
		// TODO Auto-generated method stub

	}
	/*
	 * @Override public int compareTo(Object o) { // TODO Auto-generated method stub
	 * return 0; }
	 */

	@Override
	public void emtyDeck() {
		// TODO Auto-generated method stub
		dealerSet.clear();
		this.dealBlackjack = false;
		this.dealBurst = false;
		this.dealGoOn = true;
	}

}
