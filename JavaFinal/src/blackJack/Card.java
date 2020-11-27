package blackJack;

public class Card {
	private String pattern;
	private String JQK;
	private int number;
	//JQK는 잭, 퀸, 킹
	//number는 1에서 10, ace의 경우 11의 값을 나타냄...
	//pattern은 스페이드, 클로버, 퀸, 킹

	public Card(String pattern, int number) {
		this.pattern = pattern;
		this.number = number;

	}

	public String getJQK() {
		return JQK;
	}
	

	public void setJQK(String jQK) {
		JQK = jQK;
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
		if (obj instanceof Card) {
			Card c = (Card) obj;
			return c.number == this.number && c.pattern.equals(this.pattern);
		}
		return false;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("|%s - %d|\t", this.pattern, this.number);
	}

}
