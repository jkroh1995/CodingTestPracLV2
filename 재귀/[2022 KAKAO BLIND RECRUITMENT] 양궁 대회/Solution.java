import java.util.*;

class Solution {
    public int[] solution(int n, int[] info) {
        int[] answer = {};
        int[] ryan = new int[info.length];
        Map<int[], Integer> map = new HashMap<>();
        recursive(n, info, ryan, 0, map);
        if (map.isEmpty()) {
            return new int[]{-1};
        }
        int max = 0;

        for (int[] eachRyan : map.keySet()) {
            int difference = map.get(eachRyan);
            if (difference > max) {
                max = difference;
                answer = eachRyan;
            }
        }

        for (int[] eachRyan : map.keySet()) {
            int difference = map.get(eachRyan);
            if (difference == max) {
                for (int i = eachRyan.length - 1; i >= 0; i--) {
                    if (eachRyan[i] != 0 && answer[i] == 0) {
                        answer = eachRyan;
                        break;
                    }
                }
            }
        }

        return answer;
    }

    private void recursive(int n, int[] info, int[] ryan, int takeIndex, Map<int[], Integer> map) {
        if (n < 0) {
            return;
        }
        if (n == 0) {
            calc(info, ryan, map);
            return;
        }

        if (takeIndex == info.length) {
            for (int i = 0; i < info.length; i++) {
                if (info[i] >= ryan[i] && n > info[i]) {
                    ryan[i] = info[i] + 1;
                    n -= info[i] + 1;
                }
                if (n == 0) {
                    break;
                }
            }

            ryan[10] += n;

            calc(info, ryan, map);
            return;
        }
      
        int[] nextRyan = Arrays.copyOf(ryan, ryan.length);
        recursive(n, info, nextRyan, takeIndex + 1, map);
        nextRyan = Arrays.copyOf(ryan, ryan.length);
        nextRyan[takeIndex] = info[takeIndex] + 1;
        recursive(n - info[takeIndex] - 1, info, nextRyan, takeIndex + 1, map);
    }

    private void calc(int[] info, int[] ryan, Map<int[], Integer> map) {
        int apeachScore = 0;
        int ryanScore = 0;
        for (int i = 0; i < info.length; i++) {
            if (ryan[i] > info[i]) {
                ryanScore += 10 - i;
            } else if (ryan[i] == info[i] && info[i] != 0) {
                apeachScore += 10 - i;
            } else if (ryan[i] < info[i]) {
                apeachScore += 10 - i;
            }
        }

        if (ryanScore > apeachScore) {
            map.put(ryan, ryanScore - apeachScore);
        }
    }
}
