package blackJack;

import java.util.HashSet;
import java.util.Set;

public class Player implements Person {
	private String name;
	private int score;
	
	private HashSet<Card> playerCard;
	private boolean burst;
	private boolean blackjack;
	private boolean goOn;
	
	private int tempsum;
	private int output;
	
	public Player(String name) {
		this.name = name;
		score = 0;
		burst = false;
		blackjack = false;
		goOn = true;
		tempsum = 0;
		output = 0;
		playerCard = new HashSet<>(2);
		
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Player) {
			Player p = (Player) obj;
			return p.name.equals(this.name);

		}
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("성명 : %s || 점수 : %d", this.name, this.score);
	}

	public void setSet(HashSet<Card> cards) {
		this.playerCard = cards;
	}
	@Override
	public HashSet<Card> getSet() {
		// TODO Auto-generated method stub
		return playerCard;
	}

	@Override
	public void ai() {
		// TODO Auto-generated method stub

	}

	@Override
	public void turn() {
		// TODO Auto-generated method stub
		while (playerCard.size() < 2) {
			playerCard.add(Main.cardGenerator());
		}
		for (Card c : playerCard) {
			tempsum += c.getNumber();
		}
		if (tempsum == 21) {
			blackjack = true;
			goOn = false;
			System.out.println("블랙잭!");
		} else
			;
		tempsum = 0;
	}

	@Override
	public void hit() {
		// TODO Auto-generated method stub
		int size = playerCard.size();
		Card newCard = Main.cardGenerator();
		
		playerCard.add(newCard);
		int nextSize = playerCard.size();
		if(size == nextSize)
			playerCard.add(Main.cardGenerator());
		for (Card c : playerCard) {
			tempsum += c.getNumber();
		}
		if (tempsum == 21) {
			blackjack = true;
			goOn = false;
			System.out.println("블랙잭!");
		} else if (tempsum > 21) {
			burst = true;
			goOn = false;
			System.out.println("버스트!");
		} else
			;
	}

	@Override
	public boolean getBurst() {
		// TODO Auto-generated method stub
		return burst;
	}

	@Override
	public boolean getBJ() {
		// TODO Auto-generated method stub
		return blackjack;
	}

	@Override
	public boolean getGoOn() {
		// TODO Auto-generated method stub
		return goOn;
	}

	@Override
	public int getSum() {
		// TODO Auto-generated method stub
		
		for(Card c : playerCard)
			output += c.getNumber();
		return output;
	}

	@Override
	public void setScore(int score) {
		// TODO Auto-generated method stub
		this.score += score;
	}
/*
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if( o instanceof Player) {
			Player p = (Player)o;
			if(this.output > p.output) return 1;
			else if(this.output < p.output) return -1;
		}
		return 0;
	}*/

	@Override
	public void emtyDeck() {
		// TODO Auto-generated method stub
		playerCard.clear();
		this.burst = false;
		this.blackjack = false;
		this.goOn = true;
	}

	

}
