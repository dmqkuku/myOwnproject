package blackJack;

public class Card {
	private String pattern;
	private int number;
	
	public Card(String pattern, int number) {
		this.pattern = pattern;
		this.number = number;
	}

	public Card() {
		// TODO Auto-generated constructor stub
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int i = pattern.hashCode();
		return i;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Card) {
			Card c = (Card)obj;
			return c.number ==this.number && c.pattern.equals(this.pattern);
		}return false;
				
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("|%s - %d|\t", this.pattern , this.number);
	}
	
}
