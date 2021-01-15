package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Deck {
	private Set<Integer> usedNumSet = new HashSet<Integer>();
	private boolean flag = true;
	
	public void shuffle() {
		usedNumSet.clear();
	}
	
	public ArrayList<Card> deckGen() {
		ArrayList<Card> cardList = new ArrayList<Card>();
		for(int i = 0 ; i < 52 ; i++) {
			cardList.add(numGen());
		}
		return cardList;
	}
	public Card numGen() {
		int rawNum = (int)Math.round(Math.random() * 52) + 1;
		if(usedNumSet.contains(rawNum)) {
			LocalCounter lCounter = new LocalCounter();
			rawNum = irrNumGen(rawNum, lCounter);
			usedNumSet.add(rawNum);	
			int num = rawNum % 13;						//숫자의 가짓수는 13!
			int patternedNum = rawNum % 4;				//패턴의 종류는 4가지!
			Card c = new Card();
			c.setNum(num);
			c.setPatternedNum(patternedNum);
			c.autoPatternizer();
			return c;
		}else {
			usedNumSet.add(rawNum);
			int num = rawNum % 13;
			int patternedNum = rawNum % 4;
			Card c = new Card();
			c.setNum(num);
			c.setPatternedNum(patternedNum);
			c.autoPatternizer();
			return c;
		}
	}
	public int irrNumGen(int rawNum, LocalCounter lCounter) {
		if(flag) {
			int processedNum = rawNum + lCounter.getCount();
			if(processedNum > 52)
				return irrNumGen(processedNum - rawNum, lCounter);
			if(!usedNumSet.contains(processedNum)) {
				lCounter.reset();
				return processedNum;
			}else {
				this.flag = !flag;
				return irrNumGen(rawNum, lCounter);
			}
		}else {
			int processedNum = rawNum - lCounter.getCount();
			if(processedNum < 0)
				return irrNumGen(rawNum + lCounter.getCount(), lCounter);
			if(!usedNumSet.contains(processedNum)) {
				lCounter.reset();
				this.flag = !flag;
				return processedNum;
				
			}else {
				flag = true;
				lCounter.upCount();
				return irrNumGen(rawNum, lCounter);
			}
		}
	}
	private class LocalCounter {
		private int counter;
		LocalCounter(){	
			counter = 1;
		}
		public int getCount() {
			return counter;
		}
		public void upCount() {
			counter++;
		}
		public void reset() {
			counter = 1;
		}
	};
	
}
