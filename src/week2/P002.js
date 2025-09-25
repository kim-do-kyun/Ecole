const fs = require('fs');

const input = fs.readFileSync('/dev/stdin').toString().trim();

const numList = input.split('').map(Number)

for (let i=0; i<numList.length; i++) {
    let maxIndex = i;
    for (let j=i+1; j<numList.length; j++) {
        if(numList[j] > numList[maxIndex]) {
            maxIndex = j;
        }
    }

    [numList[i], numList[maxIndex]] = [numList[maxIndex], numList[i]];
}

console.log(numList.join(''));