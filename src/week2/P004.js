const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const [n, k] = input[0].split(' ').map(Number);
const numList = input[1].split(' ').map(Number);

function partition(start, end) {
  // 데이터가 2개인 경우 특별 처리
  if (start + 1 === end) {
    if (numList[start] > numList[end]) {
      swap(start, end);
    }
    return end;
  }

  // 중간 값을 피벗으로 선택하고 맨 앞으로 이동
  const mid = Math.floor((start + end) / 2);
  swap(start, mid);

  const pivotIndex = start; // 피벗의 '인덱스'
  let i = start + 1;
  let j = end;

  while (i <= j) {
    // 피벗보다 큰 수가 나올 때까지 i 증가
    while (i < numList.length - 1 && numList[pivotIndex] > numList[i]) {
      i++;
    }
    // 피벗보다 작은 수가 나올 때까지 j 감소
    while (j > 0 && numList[pivotIndex] < numList[j]) {
      j--;
    }

    if (i <= j) {
      swap(i, j);
      i++;
      j--;
    }
  }
  // 피벗 값을 최종 위치(j)로 이동
  const tmp = numList[pivotIndex];
  numList[start] = numList[j];
  numList[j] = tmp;

  return j;
}

function quickSort(start, end, k) {
  if (start < end) {
    const pivot = partition(start, end);
    if (pivot < k) {
      quickSort(pivot + 1, end, k);
    } else if (k < pivot) {
      quickSort(start, pivot - 1, k);
    } 
    // pivot === k 이면 암시적으로 탐색 종료
  }
}


function swap(a, b) {
  [numList[a], numList[b]] = [numList[b], numList[a]];
}

quickSort(0, n - 1, k - 1);
console.log(numList[k - 1]);