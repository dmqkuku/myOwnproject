/*
Ace 선택지가 제대로 작동하지 않는 듯 하다...
ai도 안돌아가는 것 같다... 정확히는 아주 높은 빈도로 '34'같은 터무니없는 숫자가 출력된다... <해결됨...>

딜러가 너무 잘 진다...
Ai개선이 필요하다...
여러 사람이 플레이할 수 있도록 만들어야 한다...
*/ 




let cardPool = {};
let dealercardPool = {};

let cardSpade = new Map;
for(let i = 1 ; i < 14 ; ++i){
    cardSpade.set(`Spade${i}`, i);
}
let cardHeart = new Map;
for(let i = 1 ; i < 14 ; ++i){
    cardHeart.set(`Heart${i}`, i);
}
let cardClover = new Map;
for(let i = 1 ; i < 14 ; ++i){
    cardClover.set(`Clover${i}`, i);
}
let cardDiamond = new Map;
for(let i = 1 ; i < 14 ; ++i){
    cardDiamond.set(`Diamond${i}`, i);
}
cardPool["Spade"] = cardSpade;
cardPool["Heart"] = cardHeart;
cardPool["Clover"] = cardClover;
cardPool["Diamond"] = cardDiamond;

dealercardPool["Spade"] = cardSpade;
dealercardPool["Heart"] = cardHeart;
dealercardPool["Clover"] = cardClover;
dealercardPool["Diamond"] = cardDiamond;
/*
    cardPool과 dealercardPool의 구조는 예를들어 : { "Spade" : Map(cardSpade)}가 된다.
    각각의 map에는 예를들어 Spade11 : 11 식으로 key와 value가 매칭되어 있다.
    
*/



//console.log(cardPool);
/*
cardPool.Spade.get("Spade1");
console.log(cardPool.Spade); // 자동으로 지워지지는 않는다.*/

let yourHand = {};
let dealerHand = {};
//손패를 나타내는 객체이다.
let Pattern = {
    "1" : "Spade",
    "2" : "Clover",
    "3" : "Heart",
    "4" : "Diamond"
}
/*
function dealercardGen(index){ //index는 Hand1, Hand2와 같이 몇번째 손패인지 표시하는 프로퍼티이다.
    let number = Math.round(Math.random() * 12) + 1;
    let pattern = Math.round(Math.random()* 3 ) + 1;
    
    pattern = Pattern[pattern];
    /*if(pattern == 1){
        pattern = "Spade";
    }else if(pattern == 2){
        pattern = "Clover";
    }else if(pattern == 3){
        pattern = "Heart";
    }else{
        pattern = "Diamond";  //ugly??? yes it is ugly
    }*//*
    dealerHand[index] = {}; //해당 index에 맞춰서 객체를 value로 추가한다.

    if(dealercardPool[pattern].get(pattern + number)){
        dealerHand[index]["number"] = dealercardPool[pattern].get(pattern + number);
        dealerHand[index]["pattern"] = pattern + number;
        dealercardPool[pattern].delete(pattern + number);
        return dealerHand;
        //index. 예를 들어 Hand1의 객체에 number : 11, pattern : Spade11과 같은 값을 불러와서 입력한뒤, cardPool에서 불러온 카드를 삭제한다.
        //그리고 위의 if문에서 cardPool의 해당 카드가 존재하는 지 체크하는 것으로 중복된 카드를 뽑는 것을 원천적으로 봉쇄한다.
    }else dealercardGen(index);
}
*/

function cardGen(cG, cP,  index){
    let number = Math.round(Math.random() * 12) + 1;
    let pattern = Math.round(Math.random()* 3 ) + 1; //cG는 yourHand혹은 dealerHand cP는 cardPool
    
    pattern = Pattern[pattern];
    /*
    if(pattern == 1){
        pattern = "Spade";
    }else if(pattern == 2){
        pattern = "Clover";
    }else if(pattern == 3){
        pattern = "Heart";
    }else{
        pattern = "Diamond";
    }*/
    cG[index] = {};
    if(cP[pattern].get(pattern + number)){
        cG[index]["number"] = cP[pattern].get(pattern + number);
        cG[index]["pattern"] = pattern + number;
        cP[pattern].delete(pattern + number);
        alert(cG[index]["pattern"]);
        return cG;
    }else cardGen(cG, cP, index);
}
//현재는 dealerGen과 cardGen이 다르지만 곧 합쳐질것..
cardGen(yourHand, cardPool, "Hand1");
cardGen(yourHand, cardPool, "Hand2");
cardGen(dealerHand, dealercardPool, "Hand1");
cardGen(dealerHand, dealercardPool,"Hand2");
//블랙잭 룰에 따라 시작시 두 장의 카드를 딜러와 플레이어가 분배받는다.

function getSum(cG, Iindex){
    let resSum = 0;
    for(let i = 1 ; i <=Iindex ; ++i){
        resSum += cG["Hand" + i].number; 
    }
    return resSum;
}
//앞으로 자주 나올 지금까지의 손패가 얼마만큼인지 sum을 구하는 함수이다.


function cardProcessor(cG, index){
    let tempArr = [...cG[index].pattern];
    function Helper(Tarr){
        Tarr.pop(Tarr.find(value => {typeof value == "number"}));
        Tarr.pop(Tarr.find(value => {typeof value == "number"}));
    }
    //cardProcessor내의 helper 함수이다. 자동으로 배열내의 숫자를 두개까지 찾아내서 제거한다.
    //다른 방법 예를 들어 splice를 사용해도 된다. 이 경우 맨 뒤에서 두 개의 숫자를 제거하면 된다고 알고 있기 때문이다.
    //근데 이상하게 잘 먹히지가 않아서 다른 방법을 사용했다..
    if(cG){
        if(cG[index].number == 11){
            cG[index].number = 10;
            
            Helper(tempArr);
            let str = tempArr.join("");
            cG[index].pattern = str + "Jack";
            
            return cG;
        }else if(cG[index].number == 12){  //만일 number값이 11, 12, 13 일 경우 각각에 맞춰서 jack, queen,king으로 바꾸어 준다. 
            cG[index].number = 10;         //앞에서 각각의 Hand에 pattern으로 Spade11라고 저장했다면,
            Helper(tempArr);               //Spade11을 tempArr로 만들고, tempArr에서 두개의 숫자를 제거한 후, 문자열로 만든다.
            let str = "";                   //이 경우 join("")을 이용한다. 지금 코드는 바꾸다 말았다.
            for(let letter of tempArr){
                str += letter;
            }
            cG[index].pattern = str + "Queen";
            //cG[index].pattern.replace("12", "Queen");*/
            return cG;
        }else if(cG[index].number == 13){
            cG[index].number = 10;
            Helper(tempArr);
            
            let str = tempArr.join("");
            cG[index].pattern = str + "King";
            
            return cG;
        }else if((cG[index].number == 1)&&cG == yourHand){  //입력받은 인수인 cG가 yourHand일 경우 prompt를 띄워서 직접 선택한다.
            let inputC = prompt("If you want to play it as a 11 press 'y'", "");
            
            if(inputC == "y"){
                cG[index].number = 11;
                Helper(tempArr);
                
                let str = tempArr.join("");
                cG[index].pattern = str + "Ace";
            }else {
                cG[index].number = 1;
                Helper(tempArr);
                
                let str = tempArr.join("");
                cG[index].pattern = str + "Ace";
            }
            
        }else if((cG[index].number == 1)&&cG == dealerHand){    //만일 dealer의 손패일 경우 조건을 충족할 경우 11 아닐 경우 1이 된다.
            let dHIndex = Number(index[4]);
            let tempSum = getSum(cG , dHIndex -1);
            
            if(tempSum <= 10){
                cG[index].number = 11;
                Helper(tempArr);
                
                let str = tempArr.join("");
                cG[index].pattern = str + "Ace";
            }else {
                cG[index].number = 1;
                Helper(tempArr);
                
                let str = tempArr.join("");
                cG[index].pattern = str + "Ace";
            }

        }
    }
}

cardProcessor(yourHand, "Hand1");
cardProcessor(yourHand, "Hand2");
cardProcessor(dealerHand, "Hand1");
cardProcessor(dealerHand, "Hand2");

let resArr = [yourHand["Hand1"].pattern, yourHand["Hand2"].pattern];
alert(`당신의 패는 : ${resArr}`);

let dealresArr = [dealerHand["Hand1"].pattern, dealerHand["Hand2"].pattern];
alert(`딜러의 패는 : ${dealresArr}`);

function Burst_Black(cG, int){//이 함수는 burst 인지 blackJack인지 체크한다. 
                              //만일 blackjack혹은 burst일 경우 각각의 Hand에 blackjack, burst프로퍼티를 만들고 true로 셋팅한다.
    let tempSum = getSum(cG, int);
      
    if(tempSum == 21){
        alert("BlackJack!");
        cG["BlackJack"] = true;
    }else if(tempSum > 21){
        alert("Burst!");
        cG["Burst"] = true;
    }else ;
    
    return cG;
}

let Globalint = 2; //closure 로 씁시다? 핸드를 자동적으로 증가시키는 것은 쉽지만,,,,
const handCounter = (function(counter){
    return {
        countUp : function(){
            ++counter;
        },
        get count(){
            return counter;
        }
    }
}(2));
const dealhandCounter = (function(counter){
    return {
        countUp : function(){
            ++counter;
        },
        get count(){
            return counter;
        }
    }
}(2)); 
while(true){
    let inputHS = prompt("Hit or Stand?", "")
    if(inputHS){
        if(inputHS.toLowerCase() == "hit"){
            alert("카드를 한장 더 뽑습니다.");
            handCounter.countUp();
            //++Globalint;
            cardGen(yourHand, cardPool, "Hand" + handCounter.count);
            cardProcessor(yourHand, "Hand" + handCounter.count);
        }else if(inputHS.toLowerCase() == "stand"){
            alert("Stand!");
            break;
        }else ;
        Burst_Black(yourHand, handCounter.count);
        if(yourHand["BlackJack"]){
            break;
        }else if(yourHand["Burst"]){
            break;
        }
    }else continue;
}
let sum = getSum(yourHand, handCounter.count);

alert(`Your hand is ${sum}`);
let Localint = 2;
function basicAi(dH){
    
    let tempSum = 0;
    while(true){
        
        if(tempSum > 17) {
            break;}
        tempSum = 0;
        dealhandCounter.countUp();
        cardGen(dealerHand, dealercardPool, "Hand" + dealhandCounter.count);
        cardProcessor(dealerHand, "Hand" + dealhandCounter.count);
        tempSum = getSum(dH, dealhandCounter.count);

        Burst_Black(dealerHand, dealhandCounter.count);
        alert(`딜러의 현재 카드패 : ${tempSum}`);
        if(dealerHand["BlackJack"]){
            break;
        }else if(dealerHand["Burst"]){
            break;
        }
        
    }
    return dealhandCounter.count;
}
alert("딜러의 턴입니다.~");
let dealerIndex = basicAi(dealerHand);


///승자 계산...

let dealSum = getSum(dealerHand, dealhandCounter.count);
if(dealerHand["BlackJack"]&&yourHand["BlackJack"]||dealerHand["Burst"]&&yourHand["Burst"]||sum == dealSum){
    alert("무승부!");
}else if(yourHand["BlackJack"] || sum > dealSum || dealerHand["Burst"]&& !yourHand["Burst"]){
    alert("You Win!");
}else if(dealerHand["BlackJack"] || sum < dealSum && !dealerHand["Burst"]){
    alert("You lost!");                                                             //ugly 어떻게든지 이걸 줄여봅시다...
}

//취합한다음 if else를 돌리는 편이 더 깔끔할 것이다..


