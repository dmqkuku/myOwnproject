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
		score = 0; 							//점수
		burst = false;
		blackjack = false;
		goOn = true;						//burst, blackjack, goOn 체크
		tempsum = 0;
		output = 0;							//내부 계산용 인수와 외부 출력용 인수
		playerCard = new HashSet<>(2);		
		
	}

	
	
	
	public String getName() {
		return name;
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
	}//Set를 받아서 플레이어카드에 저장
	@Override
	public HashSet<Card> getSet() {
		// TODO Auto-generated method stub
		return playerCard;
	}//hashSet으로 리턴

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
	}//게임을 시작할때 두장의 패를 받는 것을 구현한 것...
	//카드를 생성하는 것은 main의 cardGenerator함수에 정의되어 있다.
	//첫번째 카드를 두장 받아서 바로 버스트가 나올 수 없으므로 블랙잭만 체크하여 goOn과 blackJack 값을 변경
	//blackjack / goOn / burst는 Main의 whoWin메소드에 사용됨

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
	}//hit을 누를 경우 카드를 한장 더 뽑는 것을 구현한 것
	//Main.generator로 카드를 한장 생성하고
	//이 때의 sum을 계산해서 블랙잭, 버스트 인수 값을 조정함...

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
//한 라운드가 종료될 때마다 손패를 비우는 것
	

}
