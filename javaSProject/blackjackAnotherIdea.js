let cardPool = {};
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

//console.log(cardPool);
/*
cardPool.Spade.get("Spade1");
console.log(cardPool.Spade); // 자동으로 지워지지는 않는다.*/

let yourHand = {};

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
        return yourHand;
    }else cardGen();
}
cardGen("Hand1");
cardGen("Hand2");
/*
let cardProcessor1 = [yourHand["FirstHand"].number , yourHand["SecondHand"].number];
console.log(cardProcessor1);
let cardProcessor2 = cardProcessor1.forEach((value, index, arr) =>{
    if()
})*/


function cardProcessor(cG, index){
    let tempArr = [...cG[index].pattern];
    console.log(tempArr);
    if(cG == yourHand){
        if(cG[index].number == 11){
            cG[index].number = 10;
            /*delete cG[index].pattern[cG[index].pattern.length-1];
            delete cG[index].pattern[cG[index].pattern.length-1];*/
            tempArr.pop(tempArr.find(value => {typeof value == "number"}))
            tempArr.pop(tempArr.find(value => {typeof value == "number"}))
            let str = "";
            for(let letter of tempArr){
                str += letter;
            }
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
            let str = "";
            for(let letter of tempArr){
                str += letter;
            }
            cG[index].pattern = str + "King";
            //cG[index].pattern.replace("13", "King");*/
            return cG;
        }else if(cG[index].number == 1){
            let inputC = prompt("If you want to play it as a 11 press 'y'", "");
            
            if(inputC == "y"){
                cG[index].number = 11;

            
                tempArr.pop(tempArr.find(value => {typeof value == "number"}))
                tempArr.pop(tempArr.find(value => {typeof value == "number"}))
                let str = "";
                for(let letter of tempArr){
                    str += letter;
                }
                cG[index].pattern = str + "Ace";
            }else {
                cG[index].number = 1;

                tempArr.pop(tempArr.find(value => {typeof value == "number"}))
                tempArr.pop(tempArr.find(value => {typeof value == "number"}))
                let str = "";
                for(let letter of tempArr){
                    str += letter;
                }
                cG[index].pattern = str + "Ace";
            }
            
        }
    }
}
console.log(yourHand);
cardProcessor(yourHand, "Hand1");
cardProcessor(yourHand, "Hand2");
console.log(yourHand);
let resArr = [yourHand["Hand1"].pattern, yourHand["Hand2"].pattern];
alert(`당신의 패는 : ${resArr}`);

function Burst_Black(cG, int){

    let tempSum = 0;
    for(let i = 0 ; i < int ; ++i){
        tempSum += cG["Hand" + (i+1)].number;
        alert(tempSum);
    }
    if(tempSum == 21){
        alert("BlackJack!");
        cG["BlackJack"] = true;
    }else if(tempSum > 21){
        alert("Burst!");
        cG["Burst"] = true;
    }else ;
    return cG;
}

let Globalint = 3;
while(true){
let inputHS = prompt("Hit or Stand?", "")
    if(inputHS.toLowerCase() == "hit"){
        cardGen("Hand" + Globalint);
    }else if(inputHS.toLowerCase() == "stand"){
        break;
    }
    Burst_Black(yourHand, Globalint);
    if(yourHand["BlackJack"]){
        break;
    }else if(yourHand["Burst"]){
        break;
    }
    ++Globalint;

}

