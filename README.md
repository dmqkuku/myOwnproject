# myOwnproject
JAVA 공부하면서 만든 작은 코드들

solution 1;
  아마도 map을 바로 setproperty하는 과정에서 문제가 발생했을 수 있다.
  jsp:setProperty혹은 jsp:useBean의 경우에서 문제가 발생하는 것이 확실하다.
  따라서 해당 파트를 없애고 상부 혹은 서버측에서 Card로 분해해서 전달하도록 한다.
  이 경우 Hand1인지 Hand2인지 즉 어떤 핸드인지 표기할 인덱스(기존 맵에서 key에 해당하는 부분)이 필요하다.
  이 feature는 나중에 doSplit을 구현할 때 필요할 것이다.

Think 1;
  현재 가장 큰 문제는 BlackJack.jsp에서 카드를 display하는 데 이 페이지에서 동시에 Hit or Stay or Split의 지시어를 서버에 전달할 것이며
  서버에서 다시 전달받은 값을 BlackJack.jsp에서 띄운다는 것이다. 현재 내가 아는 한 JSP는 이 경우 그 페이지 자체를 refresh하므로... 매우 비효율적이고 보는 측에서도 아마
  페이지가 깜박이는 듯한 현상이 발생할 수 있다.
  한 페이지에서 부분적으로만 갱신하려면 AJAX와 같은 기술이 필요하다...
  이 사항에 대해서는 좀 더 알아볼 필요가 있다.
