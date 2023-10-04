import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        // 가장 작은 수를 기준으로 공약수를 찾기 위해 정렬
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        // 가장 작은 수의 약수들을 담을 배열
        List<Integer> aDivisorList = new ArrayList<>();
        List<Integer> bDivisorList = new ArrayList<>();

        getDivisorList(arrayA, aDivisorList);
        getDivisorList(arrayB, bDivisorList);

        // 공약수만 담을 큐. 큰 수부터 뽑아 쓰면 된다.
        PriorityQueue<Integer> aCommonDivisorQueue = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> bCommonDivisorQueue = new PriorityQueue<>(Collections.reverseOrder());

        getCommonDivisors(arrayA, aDivisorList, aCommonDivisorQueue);
        getCommonDivisors(arrayB, bDivisorList, bCommonDivisorQueue);

        answer = getAnswer(arrayB, answer, aCommonDivisorQueue);
        answer = getAnswer(arrayA, answer, bCommonDivisorQueue);

        return answer;
    }

    private static void getDivisorList(int[] arrayA, List<Integer> setA) {
        for (int i = 1; i < Math.sqrt(arrayA[0]); i++) {
            if (arrayA[0] % i == 0) {
                setA.add(i);
                setA.add(arrayA[0] / i);
            }
        }
    }

    private static void getCommonDivisors(int[] array, List<Integer> divisorList, PriorityQueue<Integer> commonDivisorQueue) {
        for (int divisor : divisorList) {
            boolean isCommonDivisor = true;
            // 배열을 돌며 공약수가 아닌 경우는 false로 변견
            for (int num : array) {
                if (num % divisor != 0) {
                    isCommonDivisor = false;
                    break;
                }
            }
            if (isCommonDivisor) {
                commonDivisorQueue.add(divisor);
            }
        }
    }

    private int getAnswer(int[] array, int answer, PriorityQueue<Integer> commonDivisorQueue) {
        while (!commonDivisorQueue.isEmpty()) {
            int num = commonDivisorQueue.poll();
            boolean isAnswer = true;
            for (int number : array) {
                // 만일 배열의 하나라도 나누어 떨어지면 정답이 아니다.
                if (number % num == 0) {
                    isAnswer = false;
                    break;
                }
            }

            // 만일 정답을 찾으면 멈춘다. 최댓값이면 바꿔주고 끝내고, 그렇지 않으면 이후에는 더 작은 숫자만 나오니 끝내도 된다.
            if (isAnswer) {
                if (num > answer) {
                    answer = num;
                }
                break;
            }
        }
        return answer;
    }
}
