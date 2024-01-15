import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    bfs(picture, visited, i, j, answer);
                }
            }
        }

        return answer;
    }

    private void bfs(int[][] picture, boolean[][] visited, int i, int j, int[] answer) {
        visited[i][j] = true;
        int count = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});

        int[] moveX = new int[]{-1, 0, 1, 0};
        int[] moveY = new int[]{0, -1, 0, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];

            for (int k = 0; k < 4; k++) {

                int nextRow = currentRow + moveX[k];
                int nextCol = currentCol + moveY[k];

                if (nextRow >= 0 && nextRow < picture.length && nextCol >= 0 && nextCol < picture[i].length && picture[nextRow][nextCol] == picture[i][j] && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol});
                    count++;
                }
            }
        }

        answer[0]++;
        if(count > answer[1]){
            answer[1] = count;
        }
    }
}
