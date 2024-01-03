class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        String[][] map = new String[m][n];
        for (int i = 0; i < map.length; i++) {
            map[i] = board[i].split("");
        }

        // 2 * 2 칸만큼 사라짐
        boolean boom = true;

        while (boom) {
            boom = false;
            boolean[][] booleans = new boolean[m][n];

            // 만일 해당 칸이 비어있는 문자가 아니고, 2 * 2를 만족하면 제거합니다.
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    String word = map[i][j];
                    if (!word.isEmpty() && map[i][j + 1].equals(word) && map[i + 1][j].equals(word) && map[i + 1][j + 1].equals(word)) {
                        booleans[i][j] = true;
                        booleans[i + 1][j] = true;
                        booleans[i][j + 1] = true;
                        booleans[i + 1][j + 1] = true;
                        boom = true;
                    }
                }
            }

            // 기록된 인덱스의 칸을 비워줍니다.
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (booleans[i][j]) {
                        answer++;
                        map[i][j] = "";
                    }
                }
            }

            // 빈 칸만큼 블록을 내립니다.
            for (int j = 0; j < n; j++) {
                for (int i = m - 1; i >= 0; i--) {
                    if (map[i][j].isEmpty()) {
                        int blankIndex = i;
                        while (map[i][j].isEmpty()) {
                            if (i == 0) {
                                break;
                            }
                            i--;
                        }
                        String word = map[i][j];
                        if (word.isEmpty()) {
                            break;
                        }
                        map[i][j] = "";
                        map[blankIndex][j] = word;
                        i = blankIndex;
                    }
                }
            }
        }
        return answer;
    }
}
