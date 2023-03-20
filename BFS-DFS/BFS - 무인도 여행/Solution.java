import java.util.*;

class Solution {
    int[] moveX = new int[]{0, 1, 0, -1};
    int[] moveY = new int[]{1, 0, -1, 0};

    public int[] solution(String[] maps) {
        boolean noIsland = true;

        for(int i=0;i< maps.length;i++){
            for(int j=0;j<maps[i].length();j++){
                if(maps[i].charAt(j)!='X'){
                    noIsland = false;
                    break;
                }
            }
        }

        if(noIsland){
            return new int[]{-1};
        }

        String[][] fullMap = new String[maps.length][maps[0].length()];
        boolean[][] visited = new boolean[fullMap.length][fullMap[0].length];

        for (int i = 0; i < fullMap.length; i++) {
            fullMap[i] = maps[i].split("");
        }

        List<List<Integer>> islandList = new ArrayList<>();

        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                if (!fullMap[i][j].equals("X") && !visited[i][j]) {
                    List<Integer> island = new ArrayList<>();
                    islandList.add(bfs(fullMap, visited, island, i, j));
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        for (List<Integer> island : islandList) {
            result.add(island.stream()
                    .mapToInt(value -> value)
                    .sum());
        }

        Collections.sort(result);

        int[] answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private List<Integer> bfs(String[][] fullMap, boolean[][] visited, List<Integer> island, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        int[] current = new int[]{i, j};
        int currentX;
        int currentY;

        queue.add(current);

        island.add(Integer.parseInt(fullMap[i][j]));
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            current = queue.poll();
            currentX = current[0];
            currentY = current[1];

            for (int k = 0; k < 4; k++) {
                int nextX = currentX + moveX[k];
                int nextY = currentY + moveY[k];
                
                if (nextX >= visited.length || nextX < 0 || nextY >= visited[0].length || nextY < 0) {
                    continue;
                }
                if (!visited[nextX][nextY] && !fullMap[nextX][nextY].equals("X")) {
                    island.add(Integer.parseInt(fullMap[nextX][nextY]));
                    visited[nextX][nextY] = true;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }
        return island;
    }
}
