const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

/**
 * 최소 힙 (Min Heap) 클래스 구현
 */
class MinHeap {
    constructor() {
        // 힙을 저장할 배열
        this.heap = [];
    }

    // 힙의 크기를 반환
    size() {
        return this.heap.length;
    }

    // 두 값을 교체하는 메서드
    _swap(idx1, idx2) {
        [this.heap[idx1], this.heap[idx2]] = [this.heap[idx2], this.heap[idx1]];
    }

    // 새로운 값을 힙에 추가
    push(value) {
        this.heap.push(value);
        this._bubbleUp();
    }

    // 가장 작은 값(루트)을 제거하고 반환
    pop() {
        if (this.heap.length === 1) {
            return this.heap.pop();
        }
        const min = this.heap[0];
        this.heap[0] = this.heap.pop();
        this._bubbleDown(0);
        return min;
    }

    // 요소를 위로 올려보내며 힙 속성을 유지
    _bubbleUp() {
        let index = this.heap.length - 1;
        while (index > 0) {
            const parentIndex = Math.floor((index - 1) / 2);
            if (this.heap[parentIndex] <= this.heap[index]) break;
            this._swap(index, parentIndex);
            index = parentIndex;
        }
    }

    // 요소를 아래로 내려보내며 힙 속성을 유지
    _bubbleDown(index) {
        while (true) {
            let leftChildIndex = 2 * index + 1;
            let rightChildIndex = 2 * index + 2;
            let smallestIndex = index;

            if (leftChildIndex < this.heap.length && this.heap[leftChildIndex] < this.heap[smallestIndex]) {
                smallestIndex = leftChildIndex;
            }
            if (rightChildIndex < this.heap.length && this.heap[rightChildIndex] < this.heap[smallestIndex]) {
                smallestIndex = rightChildIndex;
            }
            if (smallestIndex === index) break;

            this._swap(index, smallestIndex);
            index = smallestIndex;
        }
    }
}


// --- 문제 풀이 로직 ---

const n = parseInt(input[0]);
const minHeap = new MinHeap();

for (let i = 1; i <= n; i++) {
    minHeap.push(parseInt(input[i]));
}

// 카드 묶음이 하나일 경우 비교할 필요가 없으므로 0을 출력
if (n === 1) {
    console.log(0);
} else {
    let totalComparisons = 0;
    
    // 힙에 묶음이 하나만 남을 때까지 반복
    while (minHeap.size() > 1) {
        // 가장 작은 두 묶음을 꺼내서 합침
        const first = minHeap.pop();
        const second = minHeap.pop();
        const sum = first + second;
        
        // 합친 횟수(비교 횟수)를 누적
        totalComparisons += sum;
        
        // 합친 묶음을 다시 힙에 넣음
        minHeap.push(sum);
    }
    
    console.log(totalComparisons);
}