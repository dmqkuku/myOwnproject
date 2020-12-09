/*
Ace 선택지가 제대로 작동하지 않는 듯 하다...
ai도 안돌아가는 것 같다... 정확히는 아주 높은 빈도로 34같은 터무니없는 숫자가 출력된다...

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
    
*/



//console.log(cardPool);
/*
cardPool.Spade.get("Spade1");
console.log(cardPool.Spade); // 자동으로 지워지지는 않는다.*/

let yourHand = {};
let dealerHand = {};
function dealercardGen(index){
    let number = Math.round(Math.random() * 13) + 1;
    let pattern = Math.round(Math.random()* 4 ) + 1;
    if(pattern == 1){
        pattern = "Spade";
    }else if(pattern == 2){
        pattern = "Clover";
    }else if(pattern == 3){
        pattern = "Heart";
    }else{
        pattern = "Diamond";
    }
    dealerHand[index] = {};
    if(dealercardPool[pattern].get(pattern + number)){
        dealerHand[index]["number"] = dealercardPool[pattern].get(pattern + number);
        dealerHand[index]["pattern"] = pattern + number;
        dealercardPool[pattern].delete(pattern + number);
        return dealerHand;
    }else dealercardGen(index);
}


function cardGen(index){
    let number = Math.round(Math.random() * 13) + 1;
    let pattern = Math.round(Math.random()* 4 ) + 1;
    if(pattern == 1){
        pattern = "Spade";
    }else if(pattern == 2){
        pattern = "Clover";
    }else if(pattern == 3){
        pattern = "Heart";
    }else{
        pattern = "Diamond";
    }
    yourHand[index] = {};
    if(cardPool[pattern].get(pattern + number)){
        yourHand[index]["number"] = cardPool[pattern].get(pattern + number);
        yourHand[index]["pattern"] = pattern + number;
        cardPool[pattern].delete(pattern + number);
        alert(yourHand[index]["pattern"]);
        return yourHand;
    }else cardGen(index);
}
cardGen("Hand1");
cardGen("Hand2");
dealercardGen("Hand1");
dealercardGen("Hand2");
/*
let cardProcessor1 = [yourHand["FirstHand"].number , yourHand["SecondHand"].number];
console.log(cardProcessor1);
let cardProcessor2 = cardProcessor1.forEach((value, index, arr) =>{
    if()
})*/


function cardProcessor(cG, index){
    let tempArr = [...cG[index].pattern];
    console.log(tempArr);
    if(cG){
        if(cG[index].number == 11){
            cG[index].number = 10;
            /*delete cG[index].pattern[cG[index].pattern.length-1];
            delete cG[index].pattern[cG[index].pattern.length-1];*/
            tempArr.pop(tempArr.find(value => {typeof value == "number"}))
            tempArr.pop(tempArr.find(value => {typeof value == "number"}))
            /*let str = "";
            for(let letter of tempArr){
                str += letter;
            }*/
            let str = tempArr.join("");
            cG[index].pattern = str + "Jack";
            //cG[index].pattern.replace(`11`, "Jack");*/
            return cG;
        }else if(cG[index].number == 12){
            cG[index].number = 10;
        
            tempArr.pop(tempArr.find(value => {typeof value == "number"}))
            tempArr.pop(tempArr.find(value => {typeof value == "number"}))
            let str = "";
            for(let letter of tempArr){
                str += letter;
            }
            cG[index].pattern = str + "Queen";
            //cG[index].pattern.replace("12", "Queen");*/
            return cG;
        }else if(cG[index].number == 13){
            cG[index].number = 10;
            
            tempArr.pop(tempArr.find(value => {typeof value == "number"}))
            tempArr.pop(tempArr.find(value => {typeof value == "number"}))
            /*let str = "";
            //
            for(let letter of tempArr){
                str += letter;
            }*/
            let str = tempArr.join("");
            cG[index].pattern = str + "King";
            //cG[index].pattern.replace("13", "King");*/
            return cG;
        }else if((cG[index].number == 1)&&cG == yourHand){
            let inputC = prompt("If you want to play it as a 11 press 'y'", "");
            
            if(inputC == "y"){
                cG[index].number = 11;

                //tempArr.splice(-1, 2);???
                tempArr.pop(tempArr.find(value => {typeof value == "number"}))
                tempArr.pop(tempArr.find(value => {typeof value == "number"}))
                /*let str = "";
                for(let letter of tempArr){
                    str += letter;
                }*/
                let str = tempArr.join("");
                cG[index].pattern = str + "Ace";
            }else {
                cG[index].number = 1;

                tempArr.pop(tempArr.find(value => {typeof value == "number"}))
                tempArr.pop(tempArr.find(value => {typeof value == "number"}))
                /*let str = "";
                for(let letter of tempArr){
                    str += letter;
                }*/
                let str = tempArr.join("");
                cG[index].pattern = str + "Ace";
            }
            
        }else if((cG[index].number == 1)&&cG == dealerHand){
            let dHIndex = Number(index[4]);
            let tempSum = 0;
            for(let i = 1 ; i < dHIndex ; ++i){
                tempSum += cG["Hand" + i].number;
            }if(tempSum <= 10){
                cG[index].number = 11;
                tempArr.pop(tempArr.find(value => {typeof value == "number"}))
                tempArr.pop(tempArr.find(value => {typeof value == "number"}))
                let str = tempArr.join("");
                cG[index].pattern = str + "Ace";
            }else {
                cG[index].number = 1;
                tempArr.pop(tempArr.find(value => {typeof value == "number"}))
                tempArr.pop(tempArr.find(value => {typeof value == "number"}))
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

function Burst_Black(cG, int){

    let tempSum = 0;
    for(let i = 0 ; i < int ; ++i){
        tempSum += cG["Hand" + (i+1)].number;
    }
    alert(tempSum);
    if(tempSum == 21){
        alert("BlackJack!");
        cG["BlackJack"] = true;
    }else if(tempSum > 21){
        alert("Burst!");
        cG["Burst"] = true;
    }else ;
    
    return cG;
}

let Globalint = 2;
while(true){
    let inputHS = prompt("Hit or Stand?", "")
    if(inputHS){
        if(inputHS.toLowerCase() == "hit"){
            alert("카드를 한장 더 뽑습니다.");
            ++Globalint;
            cardGen("Hand" + Globalint);
            cardProcessor(yourHand, "Hand" + Globalint);
        }else if(inputHS.toLowerCase() == "stand"){
            alert("Stand!");
            break;
        }else ;
        Burst_Black(yourHand, Globalint);
        if(yourHand["BlackJack"]){
            break;
        }else if(yourHand["Burst"]){
            break;
        }
    }else continue;
}
let sum = 0;
for(let i = 1 ; i <= Globalint ; ++ i){
    sum += yourHand["Hand" + i].number; //반복적...
}
alert(`Your hand is ${sum}`);
function basicAi(dH){
    let Localint = 2;
    let tempSum = 0;
    while(true){
        if(tempSum > 17) {
            break;}
        tempSum = 0;
        ++Localint;
        dealercardGen("Hand" + Localint);
        cardProcessor(dealerHand, "Hand" + Localint);
        
        for(let i = 1 ; i <= Localint ; ++i){
            tempSum += dH["Hand" + i].number;
        }
       
    }
    return Localint;
}
let dealerIndex = basicAi(dealerHand);
Burst_Black(dealerHand, dealerIndex);


