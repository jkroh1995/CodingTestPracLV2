import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(String[] board) {
        String[][] map = new String[board.length][board[0].length()];
        for (int i = 0; i < map.length; i++) {
            map[i] = board[i].split("");
        }
        int[][] visit = new int[map.length][map[0].length];
        int startRow = 0;
        int startColumn = 0;
        int goalRow = 0;
        int goalColumn = 0;

        // 시작점과 도착점 탐색
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals("R")) {
                    startRow = i;
                    startColumn = j;
                }
                if (map[i][j].equals("G")) {
                    goalRow = i;
                    goalColumn = j;
                }
            }
        }

        return bfs(map, visit, startRow, startColumn, goalRow, goalColumn);
    }

    private int bfs(String[][] map, int[][] visit, int startRow, int startColumn, int goalRow, int goalColumn) {
        visit[startRow][startColumn] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startColumn});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int currentRow = current[0];
            int currentColumn = current[1];

            // 왼쪽으로 이동
            int goal = moveLeft(map, visit, currentColumn, currentRow, queue);
            if (isGoal(goal)) {
                return goal;
            }

            // 오른쪽으로 이동
            goal = moveRight(map, visit, currentColumn, currentRow, queue);
            if (isGoal(goal)) {
                return goal;
            }

            // 아래로 이동
            goal = moveDown(map, visit, currentRow, currentColumn, queue);
            if (isGoal(goal)) {
                return goal;
            }

            // 위로 이동
            goal = moveUpper(map, visit, currentRow, currentColumn, queue);
            if (isGoal(goal)) {
                return goal;
            }
        }

        return -1;
    }

    private static boolean isGoal(int goal) {
        return goal != -1;
    }

    private static int moveUpper(String[][] map, int[][] visit, int currentRow, int currentColumn,Queue<int[]> queue) {
        int nextRow = currentRow;
        int nextColumn = currentColumn;
        boolean move = false;

        for (int i = currentRow; i >= 0; i--) {
            // 보드의 끝에 닿았을 때
            if (i == 0) {
                //골 지점이라면
                if (map[i][currentColumn].equals("G")) {
                    return visit[currentRow][currentColumn];
                }
                // 방문한 적이 없다면
                if(visit[i][currentColumn] == 0){
                    nextRow = i;
                    move = true;
                    visit[i][currentColumn] = visit[currentRow][currentColumn] + 1;
                }
                break;
            }
            // 벽을 만났을 때
            if (map[i - 1][currentColumn].equals("D")) {
                // 골 지점이라면
                if (map[i][currentColumn].equals("G")) {
                    return visit[currentRow][currentColumn];
                }
                // 방문한 적이 없다면
                if(visit[i][currentColumn] == 0){
                    nextRow = i;
                    move = true;
                    visit[i][currentColumn] = visit[currentRow][currentColumn] + 1;
                }
                break;
            }
        }
        if (move) {
            queue.add(new int[]{nextRow, nextColumn});
        }
        return -1;
    }

    private static int moveDown(String[][] map, int[][] visit, int currentRow, int currentColumn, Queue<int[]> queue) {
        int nextRow = currentRow;
        int nextColumn = currentColumn;
        boolean move = false;

        for (int i = currentRow; i < map.length; i++) {
            if (i == map.length - 1) {
                if (map[i][currentColumn].equals("G")) {
                    return visit[currentRow][currentColumn];
                }
                if(visit[i][currentColumn] == 0){
                    nextRow = i;
                    move = true;
                    visit[i][currentColumn] = visit[currentRow][currentColumn] + 1;
                }
                break;
            }
            if (map[i + 1][currentColumn].equals("D")) {
                if (map[i][currentColumn].equals("G")) {
                    return visit[currentRow][currentColumn];
                }
                if(visit[i][currentColumn] == 0){
                    nextRow = i;
                    move = true;
                    visit[i][currentColumn] = visit[currentRow][currentColumn] + 1;
                }
                break;
            }
        }
        if (move) {
            queue.add(new int[]{nextRow, nextColumn});
        }
        return -1;
    }

    private static int moveRight(String[][] map, int[][] visit, int currentColumn, int currentRow, Queue<int[]> queue) {
        int nextRow = currentRow;
        int nextColumn = currentColumn;
        boolean move = false;

        for (int i = currentColumn; i < map[0].length; i++) {
            if (i == map[0].length - 1) {
                if (map[currentRow][i].equals("G")) {
                    return visit[currentRow][currentColumn];
                }
                if(visit[currentRow][i] == 0){
                    nextColumn = i;
                    move = true;
                    visit[currentRow][i] = visit[currentRow][currentColumn] + 1;
                }
                break;
            }
            if (map[currentRow][i + 1].equals("D")) {
                if (map[currentRow][i].equals("G")) {
                    return visit[currentRow][currentColumn];
                }
                if(visit[currentRow][i] == 0){
                    nextColumn = i;
                    move = true;
                    visit[currentRow][i] = visit[currentRow][currentColumn] + 1;
                }
                break;
            }
        }
        if (move) {
            queue.add(new int[]{nextRow, nextColumn});
        }
        return -1;
    }

    private static int moveLeft(String[][] map, int[][] visit, int currentColumn, int currentRow, Queue<int[]> queue) {
        int nextRow = currentRow;
        int nextColumn = currentColumn;
        boolean move = false;

        for (int i = currentColumn; i >= 0; i--) {
            if (i == 0) {
                if (map[currentRow][i].equals("G")) {
                    return visit[currentRow][currentColumn];
                }
                if(visit[currentRow][i] == 0) {
                    nextColumn = i;
                    move = true;
                    visit[currentRow][i] = visit[currentRow][currentColumn] + 1;
                }
                break;
            }
            if (map[currentRow][i - 1].equals("D")) {
                if (map[currentRow][i].equals("G")) {
                    return visit[currentRow][currentColumn];
                }
                if(visit[currentRow][i] == 0) {
                    nextColumn = i;
                    move = true;
                    visit[currentRow][i] = visit[currentRow][currentColumn] + 1;
                }
                break;
            }
        }
        if (move) {
            queue.add(new int[]{nextRow, nextColumn});
        }
        return -1;
    }
}
