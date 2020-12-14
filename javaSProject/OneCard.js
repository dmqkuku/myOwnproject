const cardPool = {}            //freeze를 사용하면 내용물을 변경시키는 것과 사이즈를 변경시키는 것을 둘다 막는다.
let usedCard = {};     
let usedNumSet = new Set;

                                                         //seal을 사용하면 사이즈를 변경시키는 것을 막는다.
let spadeMap = new Map;
mapSet(spadeMap, "Spade");
Object.freeze(spadeMap)

let heartMap = new Map;
mapSet(heartMap, "Heart");
Object.freeze(heartMap)

let cloverMap = new Map;
mapSet(cloverMap, "Clover");
Object.freeze(cloverMap)

let diaMap = new Map;
mapSet(diaMap, "Diamond");  
Object.freeze(diaMap)

cardPool["Spade"] = spadeMap;//각각의 map들은 해당 패턴 + 숫자 를 key로,, 숫자를 value로 가지게된다.
cardPool["Clover"] = cloverMap;                                                        //맵일 필요가 있을까????
cardPool["Heart"] = heartMap;
cardPool["Diamond"] = diaMap;
//console.log(spadeMap);
function mapSet(map, pattern){
    for(let i = 1 ; i < 14 ; ++i){
        map.set(pattern + "" + i, i);
    }
}
function uniqueInt(){
    let counters = 1;
    return {
        resetCount : function(){
            counters = 1;
        },
        getCount : function(){
            return counters;          //this 사용하면 안된다~! 
        },
        countUp : function(){
            counters = counters + 1;   //counter 작동 안 한다...
        }
    }
}
uI = uniqueInt();
let flag = true;
function irrNumGen(rawnum){
    'use strict';
    if(flag){
        let processedNum = rawnum + uI.getCount();
        if(processedNum > 52){
            return irrNumGen(processedNum - rawnum);
        }
        if(!usedNumSet.has(processedNum)){
            uI.resetCount();
            return processedNum;
        }else{
            flag = false;
            return irrNumGen(rawnum);
        };
    }else{
        let processedNum = rawnum - uI.getCount();
        if(processedNum<=0){
            return irrNumGen(rawnum + uI.getCount());
        };
        if(!usedNumSet.has(processedNum)){
            uI.resetCount();
            flag = true;
            return processedNum;
        }else{
            flag = true;
            uI.countUp();
            return irrNumGen(rawnum);                       //recursive function call 할때도 return
        };
    }
}

function numGen(){
    'use strict';                                 //idea 덱을 셔플할 때 사용한다... 52까지의 수를 난수로 생성한다>>중첩되지 않도록 셋을 이용해 검사한다..
    let rawNum = (Math.round(Math.random() * 52)); //숫자를 4로 나눈 값은 카드의 넘버, 나머지는 각각 패턴을 가리키게 하면 된다..
    if(usedNumSet.has(rawNum)){                     //지금은 숫자가 작아서 괜찮지만,,,, 성능상 좋지 않은 방법이다... 아닌가?
        rawNum = irrNumGen(rawNum); 
        usedNumSet.add(rawNum);
        let num = Math.floor(rawNum / 4) + 1;
        let patternNum = rawNum % 4 + 1;
        return {
            Number : num,
            Pattern : patternNum
        };                 //예를들어 52개 중 1개만 남는다면 rawNum이 정확히 그 숫자가 되어 if else문을 통과할 확률은 1/52이다...
    }else{                               //idea 반복해서 계속 난수를 돌리지 말고, 중복 지점에서 위 혹은 아래로 중복되지 않은 수를 하나씩 찾아나가쟈...
        usedNumSet.add(rawNum);
        let num = Math.floor(rawNum / 4) + 1;
        let patternNum = rawNum % 4 + 1;
        return {
            Number : num,
            Pattern : patternNum
        };
    }
}/*
for(let i = 0 ; i < 52 ; i++){
    console.log(numGen());
}*/
let Pattern = {
    "1" : "Spade",
    "2" : "Clover",
    "3" : "Heart",
    "4" : "Diamond"
}
function draw(){
    let nums = numGen();                                         //1에서  52까지...
    let pattern = Pattern[nums.Pattern];
    if(nums.Number == 11){
        pattern += " " + "Jack"
    }else if(nums.Number == 12){
        pattern += " " + "Queen"
    }else if(nums.Number == 13){
        pattern += " " + "King";
    }else{
        pattern += " " + nums.Number;
    }
    let finPattern = pattern;
    return finPattern;
}
for(let i = 0 ; i < 20; i++){
    console.log(draw());
}