package blackJack;

import java.util.Set;

public interface Person /*extends Comparable*/{

	public Set<Card> getSet();
	public void ai();
	public void turn();
	public void hit();
	public int getSum();
	public String getName();
	public void emtyDeck();
	public boolean getBurst();
	public boolean getBJ();
	public boolean getGoOn();
	public void setScore(int score);
	@Override
	int hashCode();
	@Override
	boolean equals(Object obj);
	@Override
	String toString();
	
	
}
