package model;

import java.util.HashMap;

public class HashMapBean {
	private HashMap<String, Card> map = new HashMap<String, Card>();

	public HashMap<String, Card> getMap() {
		return map;
	}

	public void setBhm(HashMap<String, Card> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Wang Wang!", null);
	}
	
	
}

