package model;

import java.util.HashMap;
import java.util.Map;

public class Player {
	private Map<String, Card> hand = new HashMap<String, Card>();
	private String index;
	private innerCounter iCounter;
	public Player() {
		
	}
	
	public Map<String, Card> getHand() {
		return hand;
	}

	public void setHand(Map<String, Card> hand) {
		this.hand = hand;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String indexGen() {
		String result = "Hand" + iCounter.getCount();
		iCounter.upCount();
		return result;
	}
	class innerCounter {
		private int counter = 1;
		innerCounter(){
		}
		public void reset() {
			counter = 1;
		}
		public void upCount() {
			counter++;
		}
		public int getCount() {
			return counter;
		}
	}
}
