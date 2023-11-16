import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;


        Queue<Integer> first = new LinkedList<>();
        Queue<Integer> second = new LinkedList<>();

        long firstSum = 0;
        long secondSum = 0;

        long max = 0;

        for (int num : queue1) {
            firstSum += num;
            first.add(num);
            if (num > max) {
                max = num;
            }
        }

        for (int num : queue2) {
            secondSum += num;
            second.add(num);
            if (num > max) {
                max = num;
            }
        }

        long sum = firstSum + secondSum;
        if (sum % 2 == 1) { // 전체 합이 홀수이면
            return -1;
        }

        if (max > sum / 2) { // 특정 원소가 전체 합의 절반보다 크면
            return -1;
        }

        int count = 0;

        while (firstSum != secondSum) {
            if (firstSum > secondSum) {
                int num = first.poll();
                second.add(num);
                firstSum -= num;
                secondSum += num;
            } else {
                int num = second.poll();
                first.add(num);
                firstSum += num;
                secondSum -= num;
            }

            count++;
            if (count > queue1.length * 4) { // 이동 횟수가 기준점 이상이 되면
                return -1;
            }
            answer++;
        }

        return answer;
    }
}
