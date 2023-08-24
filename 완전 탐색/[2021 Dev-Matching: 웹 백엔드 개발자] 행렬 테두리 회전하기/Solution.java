import java.util.Arrays;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        for (int[] query : queries) {
            for (int i = 0; i < query.length; i++) {
                query[i] = query[i] - 1;
            }
        }

        int[][] arr = new int[rows][columns];

        makeArr(rows, columns, arr);

        int answerIndex = 0;

        for (int[] query : queries) {
            int[][] tmp = makeTmp(arr, query);
            int startRow = query[0];
            int startColumn = query[1];
            int endRow = query[2];
            int endColumn = query[3];

            int min = getMin(tmp);

            answer[answerIndex] = min;

            answerIndex++;

            int x = 0;
            int y = 0;
            for (int i = startRow; i <= endRow; i++) {
                for (int j = startColumn; j <= endColumn; j++) {
                    arr[i][j] = tmp[y][x];
                    x++;
                }
                y++;
                x = 0;
            }
        }

        return answer;
    }

    private static int getMin(int[][] tmp) {
        int min = tmp[0][0];
        for(int i = 0; i< tmp[0].length; i++){
            if(tmp[0][i]<min){
                min = tmp[0][i];
            }
        }
        for(int i = 0; i< tmp.length; i++){
            if(tmp[i][tmp[0].length-1]<min){
                min = tmp[i][tmp[0].length-1];
            }
        }
        for(int i = 0; i< tmp[0].length; i++){
            if(tmp[tmp.length-1][i]<min){
                min = tmp[tmp.length-1][i];
            }
        }
        for(int i = 0; i< tmp.length; i++){
            if(tmp[i][0]<min){
                min = tmp[i][0];
            }
        }
        return min;
    }

    private int[][] makeTmp(int[][] arr, int[] query) {
        int width = query[3] - query[1] + 1;
        int height = query[2] - query[0] + 1;
        int[][] tmp = new int[height][width];
        int[][] tmp2 = new int[height][width];

        for (int i = 0; i < tmp.length; i++) {
            System.arraycopy(arr[query[0] + i], query[1], tmp[i], 0, tmp[0].length);
            System.arraycopy(arr[query[0] + i], query[1], tmp2[i], 0, tmp[0].length);
        }

        for (int i = 0; i < width - 1; i++) {
            tmp[0][i + 1] = tmp2[0][i];
        }

        for (int i = 0; i < height - 1; i++) {
            tmp[i + 1][width - 1] = tmp2[i][width - 1];
        }

        for (int i = 0; i < width - 1; i++) {
            tmp[height - 1][width - i - 2] = tmp2[height - 1][width - i - 1];
        }

        for (int i = 0; i < height - 1; i++) {
            tmp[height - i - 2][0] = tmp2[height - i - 1][0];
        }
        return tmp;
    }

    private void makeArr(int rows, int columns, int[][] arr) {
        int num = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = num;
                num++;
            }
        }
    }
}
