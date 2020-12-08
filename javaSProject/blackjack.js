let myCard ={};
let card = {};
function cardGen(){
    card = {};
    let num = Math.round(Math.random() * 13) + 1;
    let pattern = Math.round(Math.random() * 4) + 1;
    if(pattern == 1){
        pattern = "Spade";
    }else if(pattern == 2){
        pattern = "Clover";
    }else if(pattern == 3){
        pattern = "Heart";
    }else{
        pattern = "Diamond";
    }
    if(num<=10 && num >1){}
    else if(num == 11){
        num = 10;
        pattern += "Jack";
    }else if(num == 12){
        num = 10;
        pattern += "Queen"
    }else if(num == 13){
        num = 10;
        pattern += "King";
    }else if(num == 1){
        alert("행운의 에이스!");
        let z =  "y"//prompt("에이스를 11으로 사용하실 거라면 y를 눌러 주세요!" , "")
        if(z && z =="y"){
            num = 11;
            pattern += "Ace";
        }else{
            num = 1;
            pattern += "Ace";
        }
    }
    card['num'] = num;
    card['pattern'] = pattern;
    return card;
     //객체를 주고받을까?
}
myCard["firsthand"] = cardGen();
myCard["secondhand"] = new cardGen();
console.log(myCard); // 같은 함수가 호출된다...



/*prompt("Hello! This is Black jack Hit or Stand");*/