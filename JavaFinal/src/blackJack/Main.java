package blackJack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void whoWin(Person player1, Person dealer) {
		if (player1.getBurst() && dealer.getBurst()) {// 둘다 버스트일경우
			System.out.println("무승부!");

		} else if (player1.getBurst()) { // 플레이어가 버스트일경우
			System.out.println("플레이어 패배!");
			player1.setScore(-40 + player1.getSum() - dealer.getSum());

		} else if (dealer.getBurst()) { // 딜러가 버스트일 경우
			System.out.println("딜러 패배!");
			player1.setScore(100 + player1.getSum() - dealer.getSum());

		} else {
			if (dealer.getSum() > player1.getSum()) { // 딜러의 카드패가 플레이어보다 좋을 경우
				System.out.println("딜러의 승리!");
				player1.setScore(-40 + player1.getSum() - dealer.getSum());
			} else if (dealer.getSum() < player1.getSum()) {// 딜러의 카드패가 플레이어의 카드패보다 낮을 경우
				System.out.println(player1 + "의 승리!");
				player1.setScore(100 + player1.getSum() - dealer.getSum());
			}
		}
	}

	public static Card cardGenerator() {
		int num1 = (int) (Math.random() * 13) + 1;
		int pattern1 = (int) (Math.random() * 4) + 1;
		Card tempCard = new Card();
		switch (pattern1) { // 패턴은 1에서 4 사이의 무작위 정수,,, 패턴 값에 따른 switch문을 돌림
		case 1: {

			tempCard.setPattern("Spade");
			break;
		}
		case 2: {

			tempCard.setPattern("Clover");
			break;
		}
		case 3: {

			tempCard.setPattern("Diamond");
			break;
		}
		case 4: {

			tempCard.setPattern("Heart");
			break;
		}
		default:
		}
		///////// pattern 계산...

		// num을 rough하게 1에서 13으로 얻었으므로, 진짜 카드패처럼 계산하려면 가공이 필요함

		if (num1 < 11 && num1 > 1) { //

			tempCard.setNumber(num1);
		} else if (num1 == 1) { // 에이스
			System.out.println("행운의 에이스!");
			System.out.println("에이스를 11로 사용할 것이라면 y를 눌러 주세요!");
			String tempStr = scanner.next();
			if (tempStr.equals("y"))
				tempCard.setNumber(11);
			else
				tempCard.setNumber(1);

		} else { // 잭 퀸 킹
			int tempnum = num1 - 10;
			switch (tempnum) {
			case 1: {
				String tempStr = tempCard.getPattern() + "Jack";
				tempCard.setJQK("Jack");

				tempCard.setNumber(10);
				tempCard.setPattern(tempStr);
				break;
			}
			case 2: {
				String tempStr1 = tempCard.getPattern() + "Queen";

				tempCard.setJQK("Queen");
				tempCard.setNumber(10);
				tempCard.setPattern(tempStr1);
				break;
			}
			case 3: {
				String tempStr1 = tempCard.getPattern() + "King";

				tempCard.setJQK("King");
				tempCard.setNumber(10);
				tempCard.setPattern(tempStr1);
				break;
			}
			}

		}
		return tempCard;
	}

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		List<Person> playerList = new ArrayList<>();

		System.out.println("본인의 성명과 같이 플레이 하실 동료분들을 입력해주세요. quit으로 입력을 마칩니다.");
		String name = scanner.next();
		while (!name.equals("quit")) {
			playerList.add(new Player(name));
			name = scanner.next();
		}

		for (int i = 0; i < 3; ++i) { // 총 라운드의 수
			for (Person p : playerList) { // playerlist의 각 person에 대하여
				p.emtyDeck(); // 라운드의 시작시점에서 deck을 비웁니다.
				p.turn(); // 두장 받아오기

				System.out.println(p.toString());
				System.out.println(p.getSet());
			}

			Person dealer = new Dealer();			//새로운 딜러 입장	
			dealer.turn();							//딜러의 차례 시작 딜러 두 장 받기
			System.out.println("딜러의 카드패는 다음과 같습니다.");
			System.out.println(dealer.getSet());

			for (Person p : playerList) {			//순서대로 한명씩 hit stand 중 선택하여 차례 진행
				System.out.println(p.toString() + "의 턴입니다.");

				while (p.getGoOn()) {
					String input = scanner.next();
					if (input.equals("hit")) {
						p.hit();

						System.out.println(p.getSet());
						continue;
					}
					if (input.equals("stand"))
						break;
					if (input.equals("double")) {

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
			if (i < 3) {
				System.out.println(
						"--------------------------------------------" + (i + 2) + "라운드 시작!" + "---------------------");
			}

		}
	}
}