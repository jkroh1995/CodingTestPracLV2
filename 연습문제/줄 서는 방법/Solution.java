import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int n, long k) {
        k--;
        int[] answer = new int[n];
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        int answerIndex = 0;
        while (1 <= n) {
            int index = (int) (k / factorial(n - 1));
            answer[answerIndex] = list.remove(index);
            k %= factorial(n - 1);
            answerIndex++;
            n--;
        }
        return answer;
    }

    private long factorial(int num) {
        if (num == 0) {
            return 1;
        }

        long result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}
