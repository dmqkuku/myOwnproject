package blackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
//double down 이 들어가야 하며,,,,,,,
	public static void whoWin(Person player1, Person dealer) {
		if (player1.getBurst() && dealer.getBurst()) {
			System.out.println("무승부!");
		} else if (player1.getBurst()) {
			System.out.println("플레이어 패배!");
			player1.setScore(-40 + player1.getSum() - dealer.getSum());
		} else if (dealer.getBurst()) {
			System.out.println("딜러 패배!");
			player1.setScore(100 + player1.getSum() - dealer.getSum());
		} else {
			if (dealer.getSum() > player1.getSum()) {
				System.out.println("딜러의 승리!");
				player1.setScore(-40 + player1.getSum() - dealer.getSum());
			} else if (dealer.getSum() < player1.getSum()) {
				System.out.println(player1 + "의 승리!");
				player1.setScore(100 + player1.getSum() - dealer.getSum());
			}
		}
	}
	/*
	 * static class BBG { public static boolean burst; public static boolean
	 * blackjack; public static boolean goOn;
	 * 
	 * public BBG() { BBG.burst = false; BBG.blackjack = false; BBG.goOn = false; }
	 * 
	 * }
	 * 
	 * public static void burstOrBlackjack(Set<Card> sset) {
	 * 
	 * // BBG bbg = new BBG(); int sum = 0; Iterator<Card> citer = sset.iterator();
	 * while (citer.hasNext()) { sum += citer.next().getNumber(); } if (sum > 21) {
	 * System.out.println("Burst!"); BBG.burst = true; } else if (sum == 21) {
	 * System.out.println("Blackjack"); BBG.blackjack = true; } else BBG.goOn =
	 * true; }
	 */

	public static Card cardGenerator() {
		int num1 = (int) (Math.random() * 13) + 1;
		int pattern1 = (int) (Math.random() * 4) + 1;
		Card tempCard = new Card();
		switch (pattern1) {
		case 1: {
			// System.out.println("Spade!");
			tempCard.setPattern("Spade");
			break;
		}
		case 2: {
			// System.out.println("Clover");
			tempCard.setPattern("Clover");
			break;
		}
		case 3: {
			// System.out.println("Diamond");
			tempCard.setPattern("Diamond");
			break;
		}
		case 4: {
			// System.out.println("Heart");
			tempCard.setPattern("Heart");
			break;
		}
		default:
		}
		///////// pattern 계산...

		if (num1 < 11 && num1 > 1) {
			// System.out.println(num1);
			tempCard.setNumber(num1);
		} else if (num1 == 1) {
			System.out.println("행운의 에이스!");
			System.out.println("에이스를 11로 사용할 것이라면 y를 눌러 주세요!");
			String tempStr = scanner.next();
			if (tempStr.equals("y"))
				tempCard.setNumber(11);
			else
				tempCard.setNumber(1);

		} else {
			int tempnum = num1 - 10;
			switch (tempnum) {
			case 1: {
				String tempStr = tempCard.getPattern() + "Jack";
				// System.out.println(tempStr);
				tempCard.setNumber(10);
				tempCard.setPattern(tempStr);
				break;
			}
			case 2: {
				String tempStr1 = tempCard.getPattern() + "Queen";
				// System.out.println(tempStr1);
				tempCard.setNumber(10);
				tempCard.setPattern(tempStr1);
				break;
			}
			case 3: {
				String tempStr1 = tempCard.getPattern() + "King";
				// System.out.println(tempStr1);
				tempCard.setNumber(10);
				tempCard.setPattern(tempStr1);
				break;
			}
			}

		}
		return tempCard;
	}
	static boolean doublePossible = false;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Set <Card> cardSet = new HashSet<>();
		// Person player1 = new Player("장현식");
		List<Person> playerList = new ArrayList<>();
		// player1.turn();

		System.out.println("본인의 성명과 같이 플레이 하실 동료분들을 입력해주세요. quit으로 입력을 마칩니다.");
		String name = scanner.next();
		while (!name.equals("quit")) {
			playerList.add(new Player(name));
			name = scanner.next();
		}
		//int numOfP = playerList.size();
		for (int i = 0; i < 3; ++i) {
			for (Person p : playerList) {
				p.emtyDeck();
				p.turn();
				//Set <>
				/*
				 * if(p.aceF()) { System.out.println("에이스를 11로 적용하실 것이라면 y를 눌러 주세요"); String
				 * tempstr = scanner.next(); Set<Card> tempCardSet = p.getSet();
				 * if(tempstr.equals("Y")) {
				 * 
				 * tempCardSet.forEach(t-> { if(t.getNumber() == 1) t.setNumber(11); }); }else;
				 */
				// }
				System.out.println(p.toString());
				System.out.println(p.getSet());
			}
			// System.out.println("이것이 당신의 카드패 입니다.");
			// System.out.println(player1.getSet());

			Person dealer = new Dealer();
			dealer.turn();
			System.out.println("딜러의 카드패는 다음과 같습니다.");
			System.out.println(dealer.getSet());

			for (Person p : playerList) {
				System.out.println(p.toString() + "의 턴입니다.");

				while (p.getGoOn()) {
					String input = scanner.next();
					if (input.equals("hit")) {
						p.hit();
						/*
						 * if(p.aceAfter()) { System.out.println("에이스를 11로 적용하실 것이라면 y를 눌러 주세요"); String
						 * tempstr = scanner.next(); Set<Card> tempCardSet = p.getSet();
						 * if(tempstr.equals("Y")) {
						 * 
						 * tempCardSet.forEach(t-> { if(t.getNumber() == 1) t.setNumber(11); }); }else;
						 * }
						 */
						System.out.println(p.getSet());
						continue;
					}
					if (input.equals("stand"))
						break;
					if(input.equals("double")) {
						
					}

				}
			}
			dealer.ai();
			for (Person p : playerList) {
				System.out.println(p.toString() + "과 딜러의 승부!");
				whoWin(p, dealer);

			}
			System.out.println();
			System.out.println("---------------------" + (i + 1) + "라운드" + "---------------------" + "종료!");
			if(i < 3) {
				System.out.println("--------------------------------------------" + (i + 2) + "라운드 시작!" + "---------------------");
			}
			// List<Person> personList = new ArrayList<>();
			// personList.add(player1);
			// personList.add(dealer);

		}
	}
}