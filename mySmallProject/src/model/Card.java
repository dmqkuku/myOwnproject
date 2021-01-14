package model;

public class Card {
	private int num;
	private int patternedNum;			
	private String pattern = null;
	public Card() {
		num = -1;
		patternedNum = -1;				//-1으로 초기화!
	}									//why? 0에서 4. 0에서 12의 값을 입력받을 것이므로, 입력이 되었는지 확인하기 위함.
	
	public void autoPatternizer() {
		if(num != -1 && patternedNum != -1) {
			String numString = null;
			String patternedNumString = null;
			switch(patternedNum) {
			case 0:
				patternedNumString = "Spade";
				break;
			case 1:
				patternedNumString = "Heart";
				break;
			case 2:
				patternedNumString = "Clover";
				break;
			case 3:
				patternedNumString = "Diamond";
				break;
			}
			if(num > 0 && num <= 10) {
				numString = num + "";
			}else if(num == 0) {
				numString = "Ace";
			}else if(num > 10 && num <= 13) {
				switch(num - 10) {
				case 1:
					numString = "Jack";
					break;
				case 2:
					numString = "Queen";
					break;
				case 3:
					numString = "King";
					break;
				}
			}
			
			this.pattern = patternedNumString + "|" + numString;
		}
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPatternedNum() {
		return patternedNum;
	}
	public void setPatternedNum(int patternedNum) {
		this.patternedNum = patternedNum;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return num * 1000 + patternedNum;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Card) {
			Card objC = (Card)obj;
			if(objC.num == this.num && objC.patternedNum == this.patternedNum && objC.pattern.equals(this.pattern))
				return true;
			
		
		}
		return false;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("num is %d | patterned num is %d | pattern is %s |",num, patternedNum, pattern );
	}
	
}
