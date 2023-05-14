import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
      /**
      * Comparator를 람다식으로 어떻게 구현하는지만 알면 쉽게 풀 수 있다.
      */

        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) {
                return o2[0] - o1[0];
            }
            return o1[col - 1] - o2[col - 1];
        });

        for (int i = row_begin - 1; i < row_end; i++) {
            int index = i;
            int mod = Arrays.stream(data[i])
                    .map(num -> num % (index + 1))
                    .sum();

            answer ^= mod;
        }
        return answer;
    }
}
