import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Route implements Comparable<Route> {
    int start;
    int end;

    public Route(int[] arr) {
        this.start = arr[0];
        this.end = arr[1];
    }

    @Override
    public int compareTo(Route o) {
        return this.end - o.end;
    }
}

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;

        List<Route> list = new ArrayList<>();
        for (int[] route : routes) {
            list.add(new Route(route));
        }
        Collections.sort(list);

        boolean[] visited = new boolean[list.size()];

        for (int i = 0; i < list.size(); i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            for (int j = i + 1; j < list.size(); j++) {
                if(list.get(i).end >= list.get(j).start){
                    visited[j] = true;
                }
            }
            answer++;
        }
        return answer;
    }
}
