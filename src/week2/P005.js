const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const n = parseInt(input[0]);
// input[0]은 n이므로, input[1]부터 숫자 리스트입니다.
const numList = input.slice(1).map(Number);

function mergeSort(arr) {
  // 배열의 길이가 2보다 작은 경우 return
  if (arr.length < 2) {
    return arr;
  }

  // 1. 분할 (Divide)
  const mid = Math.floor(arr.length / 2);
  // 중간을 기준으로 왼쪽(low)과 오른쪽(high) 배열로 나눕니다.
  // 재귀 호출을 통해 계속 분할.
  const low = mergeSort(arr.slice(0, mid));
  const high = mergeSort(arr.slice(mid));

  // 2. 병합 (Merge)
  const merged = [];
  let l = 0, h = 0;

  // 왼쪽(low)과 오른쪽(high) 배열의 요소를 하나씩 비교하며 작은 값을 새 배열에 추가합니다.
  while (l < low.length && h < high.length) {
    if (low[l] < high[h]) {
      merged.push(low[l]);
      l++;
    } else {
      merged.push(high[h]);
      h++;
    }
  }

  // 비교 후 남은 요소들을 새 배열의 뒤에 그대로 붙여줍니다.
  // 전개 구문(...)을 사용하면 더 간결하게 합칠 수 있습니다.
  merged.push(...low.slice(l));
  merged.push(...high.slice(h));
  
  return merged;
}

const result = mergeSort(numList);
console.log(result.join('\n'));