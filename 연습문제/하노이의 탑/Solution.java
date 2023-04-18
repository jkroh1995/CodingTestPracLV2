import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] solution(int n) {
        List<int[]> list = new ArrayList<>();
        hanoi(list, n, 1, 3, 2);
        return list.toArray(int[][]::new);
    }

    private void hanoi(List<int[]> list, int count, int from, int to, int empty) {
            if(count  == 0){
                return;
            }
            hanoi(list, count-1, from, empty, to);
            list.add(new int[]{from, to});
            hanoi(list, count-1, empty, to, from);
    }
}
