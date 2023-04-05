import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int orderIndex = 0;
        Stack<Integer> stack = new Stack<>();
        int num = 1;
        stack.push(num);
      
        while (num < order.length + 2) {
            if(stack.isEmpty()){ // 모든 상자를 다 꺼냈으면 break;
                break;
            }
            if (stack.peek() == order[orderIndex]) {
                answer++;
                orderIndex++;
                stack.pop();
                if(stack.isEmpty()&& num<order.length){ // 마지막 경우가 아닌데 스택이 비면 반복문이 끝나므로 다음 상자 투입.
                    num++;
                    stack.push(num);
                }
                continue;
            }
            num++;  // 원하는 상자가 아니면 상자 투입
            stack.push(num);
        }
        return answer;
    }
}
