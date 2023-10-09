import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(String[] maps) {

        // 시작점, 레버, 도착점의 위치 검색
        int leverRow = 0;
        int leverColumn = 0;
        int startRow = 0;
        int startColumn = 0;
        int endRow = 0;
        int endColumn = 0;
      
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (maps[i].charAt(j) == 'S') {
                    startRow = i;
                    startColumn = j;
                    findStart = true;
                    continue;
                }
                if (maps[i].charAt(j) == 'L') {
                    leverRow = i;
                    leverColumn = j;
                    findLever = true;
                    continue;
                }
                if (maps[i].charAt(j) == 'E') {
                    endRow = i;
                    endColumn = j;
                    findEnd = true;
                }
            }
        }

        // bfs 탐색
        return bfs(maps, startRow, startColumn, leverRow, leverColumn, endRow, endColumn);
    }

    private int bfs(String[] maps, int startRow, int startColumn, int leverRow, int leverColumn, int endRow, int endColumn) {
        boolean leverPulled = false;
        Queue<int[]> queue = new LinkedList<>();
        int[] start = new int[]{startRow, startColumn, 0};
        int[] lever = new int[]{leverRow, leverColumn, 0};
        queue.add(start);
        int count = 0;

        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        visited[startRow][startColumn] = true;

        int[] rowDirect = new int[]{0, 1, 0, -1};
        int[] columnDirect = new int[]{1, 0, -1, 0};

        // 레버의 위치까지 bfs
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[0] == leverRow && current[1] == leverColumn) {
                lever[2] = current[2];
                leverPulled = true;
                break;
            }
            move(maps, visited, queue, rowDirect, columnDirect, current);
        }

        // 만일 레버가 당겨졌다면 도착지까지 bfs
        if (leverPulled) {
            queue.clear();
            queue.add(lever);
            visited = new boolean[maps.length][maps[0].length()];
            visited[leverRow][leverColumn] = true;
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                if (current[0] == endRow && current[1] == endColumn) {
                    count = current[2];
                    break;
                }
                move(maps, visited, queue, rowDirect, columnDirect, current);
            }
        }
        if(count == 0){
            return -1;
        }
        return count;
    }

    private void move(String[] maps, boolean[][] visited, Queue<int[]> queue, int[] rowDirect, int[] columnDirect, int[] current) {
        for (int i = 0; i < 4; i++) {
            int nextRow = current[0] + rowDirect[i];
            int nextColumn = current[1] + columnDirect[i];
            if (nextRow >= 0 && nextRow < maps.length && nextColumn >= 0 && nextColumn < maps[i].length() && maps[nextRow].charAt(nextColumn) != 'X'&& !visited[nextRow][nextColumn]) {
                visited[nextRow][nextColumn] = true;
                queue.add(new int[]{nextRow, nextColumn, current[2] + 1});
            }
        }
    }
}
