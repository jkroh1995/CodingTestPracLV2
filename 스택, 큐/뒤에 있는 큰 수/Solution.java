import java.util.Stack;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];

        Stack<Integer> stack = new Stack<>(); // 배열의 인덱스를 담는 스택
        stack.add(0); // 0번은 무조건 들어가고 시작
        
        for (int i = 1; i < numbers.length; i++) {
            while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]){ // 스택에 인덱스가 남아있고 이번 숫자가 앞선 인덱스가 가르키는 숫자들보다 크면
                answer[stack.pop()] = numbers[i]; // 인덱스가 카르키는 숫자들을 지금 숫자로 바꿔줌.
            }
            stack.push(i); // 지금 인덱스를 넣음
        }
        while(!stack.isEmpty()){ // 남아있는 애들은 큰 수가 없는 애들
            answer[stack.pop()] = -1; // 남아있는 인덱스는 -1로 바꿔줌
        }
        answer[answer.length-1] = -1;

        return answer;
    }
}
